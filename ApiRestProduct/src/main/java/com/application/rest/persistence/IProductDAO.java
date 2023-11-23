package com.application.rest.persistence;

import com.application.rest.entities.Product;
import java.util.List;
import java.util.Optional;

public interface IProductDAO {

    Optional<Product> findByid(Long id);

    List<Product> findAll();

    void saved(Product product);

    void deleteById(Long id);
    
    List<Product> findByPriceInRange(Double minPrice , Double maxPrice);
    
    List<Product> findByTitle(String title);
    
    List<Product> finByStock();
}
