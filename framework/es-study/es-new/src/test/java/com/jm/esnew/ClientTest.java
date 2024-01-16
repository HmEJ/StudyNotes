package com.jm.esnew;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.AggregationVariant;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch._types.aggregations.TermsAggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryVariant;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperationVariant;
import co.elastic.clients.elasticsearch.core.bulk.CreateOperation;
import co.elastic.clients.elasticsearch.core.search.*;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import co.elastic.clients.util.NamedValue;
import com.jm.esnew.pojo.Hotel;
import com.jm.esnew.pojo.HotelDoc;
import com.jm.esnew.service.IHotelService;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.*;

/**
 * @Classname ClientTest
 * @Description
 * @Date 2024/1/12 下午3:36
 * @Created by joneelmo
 */
@SpringBootTest
public class ClientTest {
    private final String SERVER_URL = "http://localhost:9200";
    private ElasticsearchClient esClient;

    @Autowired
    private IHotelService hotelService;

    @BeforeEach
    void getConn() {
        RestClient rsClient = RestClient.builder(HttpHost.create(SERVER_URL)).build();
        ElasticsearchTransport transport = new RestClientTransport(rsClient, new JacksonJsonpMapper());
        this.esClient = new ElasticsearchClient(transport);
    }

    @Test
    void testMatchAll() throws IOException {
        SearchResponse<HotelDoc> response = esClient.search(s -> s.index("hotel").query(
                q -> q.term(t -> t.field("name").value("上海"))
        ), HotelDoc.class);
        System.out.println(response.hits().hits());
        System.out.println(response.hits().total());
    }

    @Test
    void testRange() throws IOException {
        SearchResponse<HotelDoc> response = esClient.search(s -> s.index("hotel").query(
                q -> q.range(r -> r.field("price").gte(JsonData.of(1000)))
        ), HotelDoc.class);
        System.out.println(response.hits().hits());
        System.out.println(response.hits().total());
    }

    @Test
    void testBool() throws IOException {
        SearchResponse<HotelDoc> response = esClient.search(s -> s.index("hotel").query(q -> q.bool(
                b -> b.must(m -> m.term(t -> t.field("city").value("上海")))
                        .should(sh -> sh.term(t -> t.field("brand").value("华美达").value("皇冠假日")))
                        .mustNot(mn -> mn.range(r -> r.field("price").lte(JsonData.of(500))))
                        .filter(fl -> fl.range(ran -> ran.field("score").gte(JsonData.of(45))))
        )), HotelDoc.class);
        System.out.println(response.hits().hits());
        System.out.println(response.hits().total());
    }

    @Test
    void testSort() throws IOException {

        SortOptions priceSort = new SortOptions.Builder().field(f -> f.field("price").order(SortOrder.Asc)).build();
        SortOptions scoreSort = new SortOptions.Builder().field(f -> f.field("score").order(SortOrder.Desc)).build();

        SearchResponse<HotelDoc> response = esClient.search(s -> s
                        .index("hotel")
                        .query(
                                q -> q.term(t -> t.field("name").value("上海"))
                        )
                        .sort(priceSort, scoreSort)
                , HotelDoc.class);

        System.out.println(response.hits().total());
        System.out.println(response.hits().hits());
    }

    @Test
    void testPageOut() throws IOException {
        int page = 2;
        int size = 5;

        SearchResponse<HotelDoc> response = esClient.search(s -> s.index("hotel")
                        .from((page - 1) * size)
                        .size(size)
                        .query(q -> q.match(m -> m.field("name").query("上海")))
                        .sort(ss -> ss.field(ff -> ff.field("price").order(SortOrder.Asc)))
                        .highlight(
                                high -> high.requireFieldMatch(false)
                                        .encoder(HighlighterEncoder.Html)
                                        .fields("name", builder -> builder)
                        )
                , HotelDoc.class);
        Long totalValue = response.hits().total().value();
        System.out.println("共查到" + totalValue + "条数据");
        System.out.println(response.hits().hits().get(0).source().getName());
    }

    @Test
    void testConditional() throws IOException {
        QueryVariant variant;

        String key = "上海";
        if (key == null || "".equals(key)) {
            /*matchAll*/
            variant = new MatchAllQuery.Builder().build();
        } else {
            /*match*/
            variant = new MatchQuery.Builder().field("all").query(key).build();
        }

        SearchRequest searchRequest = new SearchRequest.Builder().index("hotel").query(new Query(variant)).build();

        SearchResponse<HotelDoc> response = esClient.search(searchRequest, HotelDoc.class);

        System.out.println(response.hits().total());
        System.out.println(response.hits().hits());
    }

//    @Test
//    void testFunctionScoreQuery() throws IOException {
//
//        FunctionScoreQuery variant = new FunctionScoreQuery.Builder()
//                .query()
//                .functions()
//                .boostMode(FunctionBoostMode.Multiply)
//                .build();
//
//        SearchRequest searchRequest = new SearchRequest.Builder().index("hotel").query(new Query(variant)).build();
//
//        SearchResponse<HotelDoc> response = esClient.search(searchRequest, HotelDoc.class);
//
//        System.out.println(response.hits().total().value());
//        System.out.println(response.hits().hits());
//    }

    @Test
    void testAggregation() throws IOException {

        AggregationVariant variant = new TermsAggregation.Builder().field("brand").size(10).order(NamedValue.of("_count", SortOrder.Asc)).build();

        SearchRequest request = new SearchRequest.Builder().index("hotel").size(0).aggregations("brandAggs", new Aggregation(variant)).build();

        SearchResponse<HotelDoc> response = esClient.search(request, HotelDoc.class);

        List<StringTermsBucket> brandAggs = response.aggregations().get("brandAggs").sterms().buckets().array();
        for (StringTermsBucket brandAgg : brandAggs) {
            System.out.println(brandAgg.key().stringValue());
        }
    }

    @Test
    void testAggregation2() throws IOException {
        Map<String, Aggregation> aggMap = new HashMap<>();

        Aggregation brandAgg = new Aggregation(new TermsAggregation.Builder().field("brand").size(100).build());
        Aggregation cityAgg = new Aggregation(new TermsAggregation.Builder().field("city").size(100).build());
        Aggregation starAgg = new Aggregation(new TermsAggregation.Builder().field("starName").size(100).build());

        aggMap.put("brandAgg", brandAgg);
        aggMap.put("cityAgg", cityAgg);
        aggMap.put("starAgg", starAgg);

        SearchRequest request = new SearchRequest.Builder().index("hotel").size(0).aggregations(aggMap).build();

        SearchResponse<HotelDoc> response = esClient.search(request, HotelDoc.class);

        System.out.println(response.aggregations());
        System.out.println("====================================================");
        System.out.println(response.aggregations().get("brandAgg").sterms().buckets().array());
        System.out.println(response.aggregations().get("cityAgg").sterms().buckets().array());
        System.out.println(response.aggregations().get("starAgg").sterms().buckets().array());

    }

    @Test
    void testImportData() throws IOException {
        /*从数据库获取数据*/
        List<Hotel> hotels = hotelService.list();
        List<BulkOperation> bulkList = new ArrayList<>();
        BulkRequest request = null;

        for (Hotel hotel : hotels) {
            /*将原始数据转为便于传输的数据格式*/
            HotelDoc hotelDoc = new HotelDoc(hotel);
            /*构造bulk操作变体，放入数据，id*/
            BulkOperationVariant variant = new CreateOperation.Builder<HotelDoc>().document(hotelDoc).id(String.valueOf(hotelDoc.getId())).build();
            BulkOperation bo = new BulkOperation(variant);
            /*循环将所有数据添加到list中*/
            bulkList.add(bo);

//            System.out.println(bulkList);
        }
        /*bulk操作，一次性将list中的内容插入es数据库中*/
        request = new BulkRequest.Builder().index("hotel").operations(bulkList).build();
        BulkResponse response = esClient.bulk(request);
        System.out.println(response);
    }

    /**
     * 自动补全搜索测试
     */
    @Test
    void testSuggestions() throws IOException {
        /*查询的值*/
        String key = "sd";

        SearchRequest.Builder rb = new SearchRequest.Builder().index("hotel").suggest(new Suggester.Builder().text(key).suggesters("suggestion", sug -> sug
                .completion(comp -> comp
                        .field("suggestion")
                        .skipDuplicates(true)
                        .size(10)
                )
        ).build());

        SearchResponse<HotelDoc> response = esClient.search(rb.build(), HotelDoc.class);

        List<Suggestion<HotelDoc>> suggestions = response.suggest().get("suggestion");
        for (Suggestion<HotelDoc> suggestion : suggestions) {
            List<CompletionSuggestOption<HotelDoc>> options = suggestion.completion().options();
            System.out.println(options);
        }
    }

    @Test
    void testDeleteIndex() throws IOException {
        DeleteResponse response = esClient.delete(del -> del.index("hotel").id("36934"));
        System.out.println(response);
    }

    @Test
    void testAddDoc(){
//        esClient.index(i->i.index("hotel").id().document())
    }

}
