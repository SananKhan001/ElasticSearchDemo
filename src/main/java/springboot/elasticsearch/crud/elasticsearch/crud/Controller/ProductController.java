package springboot.elasticsearch.crud.elasticsearch.crud.Controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.elasticsearch.crud.elasticsearch.crud.Entity.Product;
import springboot.elasticsearch.crud.elasticsearch.crud.Service.ElasticSearchService;
import springboot.elasticsearch.crud.elasticsearch.crud.Service.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/apis")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping("/findAll")
    public Iterable<Product> findAll(){
        return productService.getProducts();
    }

    @PostMapping("/insert")
    public Product insert(@RequestBody Product product){
        return productService.insertProduct(product);
    }

    @GetMapping("/matchAll")
    public String matchAll() throws IOException {
        SearchResponse<Map> searchResponse = elasticSearchService.matchAllServices();
        log.info("{}", searchResponse.hits().hits().toString());
        return searchResponse.hits().hits().toString();
    }

    @GetMapping("/matchAllProducts")
    public List<Product> matchAllProducts() throws IOException {
        SearchResponse<Product> searchResponse = elasticSearchService.matchAllProductServices();
        log.info("{}", searchResponse.hits().hits().toString());
        return searchResponse.hits().hits()
                .stream()
                .map(x -> x.source())
                .collect(Collectors.toList());
    }

    @GetMapping("/matchProducts")
    public List<Product> matchProducts(@RequestParam("query") String query) throws IOException {
        SearchResponse<Product> searchResponse = elasticSearchService.matchProductServices("description", query);
        log.info("{}", searchResponse.hits().hits().toString());
        return searchResponse.hits().hits()
                .stream()
                .map(x -> x.source())
                .collect(Collectors.toList());
    }

}
