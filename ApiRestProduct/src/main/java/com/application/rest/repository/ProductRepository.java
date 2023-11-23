package com.application.rest.repository;

import com.application.rest.entities.Product;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN ?1 AND ?2")//JPQL
    public List<Product> findByPriceInRange(Double minPrice, Double maxPrice);

    public List<Product> findProductByPriceBetween(Double minPrice, Double maxPrice);// QueryMethod

    @Query("SELECT p FROM Product p WHERE p.title= :name")
    public List<Product> findByTitle(@Param("name") String title);

    @Query("SELECT p FROM Product p WHERE p.stock > 0")
    public List<Product> finByStock();
}
