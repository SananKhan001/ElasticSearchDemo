package springboot.elasticsearch.crud.elasticsearch.crud.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.elasticsearch.crud.elasticsearch.crud.Entity.Product;
import springboot.elasticsearch.crud.elasticsearch.crud.Repo.ProductRepo;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Iterable<Product> getProducts(){
        return productRepo.findAll();
    }

    public Product insertProduct(Product product){
        return productRepo.save(product);
    }

    public Product updateProduct(Product product, int id){
        Product prod1 = productRepo.findById(id).get();
        prod1.setPrice(product.getPrice());
        return productRepo.save(prod1);
    }

    public void deleteProduct(int id){
        productRepo.deleteById(id);
    }

}
