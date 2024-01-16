package com.jm.esnew;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@MapperScan("com.jm.esnew.mapper")
@Configuration
public class EsNewApplication {
    private final String ES_SERVER_URL="http://localhost:9200";

    public static void main(String[] args) {
        SpringApplication.run(EsNewApplication.class, args);
    }

    @Bean
    public ElasticsearchClient esClient(){
        RestClient rsClient = RestClient.builder(HttpHost.create(ES_SERVER_URL)).build();
        ElasticsearchTransport transport = new RestClientTransport(rsClient,new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }
}
