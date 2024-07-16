package springboot.elasticsearch.crud.elasticsearch.crud.Service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.stereotype.Service;
import springboot.elasticsearch.crud.elasticsearch.crud.Entity.Product;
import springboot.elasticsearch.crud.elasticsearch.crud.util.ElasticSearchUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;

@Slf4j
@Service
public class ElasticSearchService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public SearchResponse<Map>  matchAllServices() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplier();
        SearchResponse<Map> searchResponse = elasticsearchClient.search(s -> s.query(supplier.get()),Map.class);
        log.info("elasticsearch query is {}", supplier.get().toString());
        return searchResponse;
    }

    public SearchResponse<Product>  matchAllProductServices() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplier();
        SearchResponse<Product> searchResponse = elasticsearchClient.search(s -> s.index("products")
                                                                .query(supplier.get()),Product.class);
        log.info("elasticsearch query is {}", supplier.get().toString());
        return searchResponse;
    }

    public SearchResponse<Product> matchProductServices(String description, String query) throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.matchQuerySupplier(description, query);
        SearchResponse<Product> searchResponse = elasticsearchClient.search(s -> s.index("products")
                                                                    .query(supplier.get()),Product.class);
        log.info("elasticsearch query is {}", supplier.get().toString());
        return searchResponse;
    }
}
