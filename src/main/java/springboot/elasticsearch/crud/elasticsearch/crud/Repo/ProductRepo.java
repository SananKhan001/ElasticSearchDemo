package springboot.elasticsearch.crud.elasticsearch.crud.Repo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import springboot.elasticsearch.crud.elasticsearch.crud.Entity.Product;

@Repository
public interface ProductRepo extends ElasticsearchRepository<Product, Integer> {

}
