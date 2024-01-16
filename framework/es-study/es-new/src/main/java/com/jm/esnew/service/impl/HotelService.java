package com.jm.esnew.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.*;
import co.elastic.clients.elasticsearch._types.aggregations.*;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.CompletionSuggestOption;
import co.elastic.clients.elasticsearch.core.search.Hit;

import co.elastic.clients.elasticsearch.core.search.Suggestion;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.json.JsonData;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jm.esnew.mapper.HotelMapper;
import com.jm.esnew.pojo.Hotel;
import com.jm.esnew.pojo.HotelDoc;
import com.jm.esnew.pojo.PageResult;
import com.jm.esnew.pojo.RequestParams;
import com.jm.esnew.service.IHotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @Classname HotelService
 * @Description
 * @Date 2024/1/12 下午3:29
 * @Created by joneelmo
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class HotelService extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {
    @Autowired
    private ElasticsearchClient esClient;

    @Override
    public PageResult search(RequestParams params) {
        SearchResponse<HotelDoc> response;
        QueryVariant queryVariant;
        SortOptionsVariant sortOptionsVariant;

        String key = params.getKey();
        Integer page = params.getPage();
        Integer size = params.getSize();
        String sortBy = params.getSortBy();
        String city = params.getCity();
        String brand = params.getBrand();
        String starName = params.getStarName();
        Integer minPrice = params.getMinPrice();
        Integer maxPrice = params.getMaxPrice();
        String location = params.getLocation();

        /* 构建查询变体|多条件过滤 */
        queryVariant = buildBasicQuery(key, city, brand, starName, minPrice, maxPrice);

        /* 构建查询 */
        Query query = new Query(queryVariant);

        /* 构建普通查询请求（包含赋分操作） */
        SearchRequest searchRequest = new SearchRequest.Builder().index("hotel").query(query).build();

        /*地理坐标排序*/
        if (location != null && !"".equals(location)) {
            sortOptionsVariant = new GeoDistanceSort.Builder().field("location").location(l -> l.text(location))
                    .order(SortOrder.Asc)
                    .unit(DistanceUnit.Kilometers)
                    .build();
            /* 如果需要排序，构建排序用查询请求（包含赋分操作） */
            searchRequest = new SearchRequest.Builder().index("hotel").query(query).sort(new SortOptions(sortOptionsVariant)).build();
        }

        /* 发送搜索请求 */
        try {
            response = esClient.search(searchRequest, HotelDoc.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /* 结果处理 */
        return handleResponse(response);
    }

    @Override
    public Map<String, List<String>> filters(RequestParams params) {
        /* 多字段聚合 */
        Map<String, List<String>> resultMap = null;
        try {
            Map<String, Aggregation> aggMap = buildAggregation();

            SearchRequest request = new SearchRequest.Builder().index("hotel").size(0).aggregations(aggMap).build();

            SearchResponse<HotelDoc> response = esClient.search(request, HotelDoc.class);

            resultMap = new HashMap<>();
            /*根据名称获取品牌结果*/
            List<String> brandList = getAggByName(response, "brandAgg");
            resultMap.put("brand", brandList);

            List<String> cityList = getAggByName(response, "cityAgg");
            resultMap.put("city", cityList);

            List<String> starList = getAggByName(response, "starAgg");
            resultMap.put("starName", starList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return resultMap;
    }

    /**
     * 实现搜索框自动补全功能
     */
    @Override
    public List<List<String>> getSuggestions(String prifix) {
        SearchResponse<HotelDoc> response;
        try {
            response = esClient.search(s -> s
                            .suggest(sug -> sug
                                    .text(prifix)
                                    .suggesters("suggestions", sugg -> sugg
                                            .completion(comp -> comp
                                                    .field("suggestion")
                                                    .skipDuplicates(true)
                                                    .size(10)
                                            )
                                    )
                            )
                    , HotelDoc.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<List<String>> returnList = new ArrayList<>();

        for (Suggestion<HotelDoc> suggestions : response.suggest().get("suggestions")) {
            List<CompletionSuggestOption<HotelDoc>> options = suggestions.completion().options();
            for (CompletionSuggestOption<HotelDoc> option : options) {
                List<String> suggestion = option.source().getSuggestion();
                returnList.add(suggestion);
            }
        }

        return returnList;
    }
    /**
     * 根据id新增或更新
     */
    @Override
    public void insertById(Long id) {
        Hotel hotel = getById(id);
        HotelDoc hotelDoc = new HotelDoc(hotel);
        IndexResponse response;
        try {
            response = esClient.index(idx -> idx
                    .index("hotel")
                    .id(String.valueOf(id))
                    .document(hotelDoc)
            );
        } catch (IOException e) {
            log.info("新增或修改数据失败！");
            throw new RuntimeException(e);
        }
        log.info("新增或修改数据成功");
    }
    /**
     * 根据id删除
     */
    @Override
    public void deleteById(Long id) {
        try {
            DeleteResponse response = esClient.delete(del -> del.index("hotel").id(String.valueOf(id)));
        } catch (IOException e) {
            log.info("数据删除失败！");
            throw new RuntimeException(e);
        }
        log.info("数据删除成功! ");
    }

    private static List<String> getAggByName(SearchResponse<HotelDoc> response, String aggName) {
        List<StringTermsBucket> bucketList = response.aggregations().get(aggName).sterms().buckets().array();

        List<String> brandList = new ArrayList<>();

        for (StringTermsBucket bucket : bucketList) {
            brandList.add(bucket.key().stringValue());
        }
        return brandList;
    }

    private static Map<String, Aggregation> buildAggregation() {
        Map<String, Aggregation> aggMap = new HashMap<>();

        Aggregation brandAgg = new Aggregation(new TermsAggregation.Builder().field("brand").size(100).build());
        Aggregation cityAgg = new Aggregation(new TermsAggregation.Builder().field("city").size(100).build());
        Aggregation starAgg = new Aggregation(new TermsAggregation.Builder().field("starName").size(100).build());

        aggMap.put("brandAgg", brandAgg);
        aggMap.put("cityAgg", cityAgg);
        aggMap.put("starAgg", starAgg);
        return aggMap;
    }

    private static QueryVariant buildBasicQuery(String key, String city, String brand, String starName, Integer minPrice, Integer maxPrice) {
        QueryVariant queryVariant;

        /*queryList存放不同条件下的query,实现搜索条件叠加*/
        List<Query> queryList = new ArrayList<>();

        /* 判断key是否存在（判断搜索框有没有值） */
        if (key == null || "".equals(key)) {

            /* 没输入东西：matchAll */
            queryList.add(new Query(new MatchAllQuery.Builder().build()));
            queryVariant = new BoolQuery.Builder().must(queryList).build();
        } else {

            /* 输入了东西：match */
            queryList.add(new Query(new MatchQuery.Builder().field("all").query(key).build()));
            queryVariant = new BoolQuery.Builder().must(queryList).build();
        }

        /*  城市 */
        if (city != null && !"".equals(city)) {
            queryList.add(new Query(new BoolQuery.Builder().filter(f -> f.term(
                    t -> t.field("city").value(city)
            )).build()));
            queryVariant = new BoolQuery.Builder().filter(queryList).build();
        }

        /*  品牌 */
        if (brand != null && !"".equals(brand)) {
            queryList.add(new Query(new BoolQuery.Builder().filter(f -> f.term(
                    t -> t.field("brand").value(brand)
            )).build()));
            queryVariant = new BoolQuery.Builder().filter(queryList).build();
        }

        /*  星级 */
        if (starName != null && !"".equals(starName)) {
            queryList.add(new Query(new BoolQuery.Builder().filter(f -> f.term(
                    t -> t.field("starName").value(starName)
            )).build()));
            queryVariant = new BoolQuery.Builder().filter(queryList).build();
        }

        /* 价格 */
        if (minPrice != null && maxPrice != null) {
            queryList.add(new Query(new BoolQuery.Builder().filter(f -> f.range(
                    r -> r.field("price")
                            .lte(JsonData.of(maxPrice))
                            .gte(JsonData.of(minPrice))
            )).build()));
            queryVariant = new BoolQuery.Builder().filter(queryList).build();
        }
        return queryVariant;
    }

    private PageResult handleResponse(SearchResponse<HotelDoc> response) {
        List<HotelDoc> list = new ArrayList<>();

        /*获取分页信息*/
        TotalHits total = response.hits().total();

        /*获取命中结果*/
        List<Hit<HotelDoc>> hits = response.hits().hits();

        for (Hit<HotelDoc> hit : hits) {

            /*获取返回的参数对象*/
            HotelDoc hd = hit.source();

            /*获取排序值*/
            List<FieldValue> sortValues = hit.sort();
            if (sortValues.size() > 0) {
                FieldValue val = sortValues.get(0);

                /* 距离转为double值 */
                double v = val.doubleValue();
                hd.setDistance(v);
            }

            list.add(hd);
        }
        return new PageResult(total.value(), list);
    }
}
