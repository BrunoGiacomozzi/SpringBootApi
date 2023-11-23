package com.application.rest.persistence.impl;

import com.application.rest.entities.Product;
import com.application.rest.persistence.IProductDAO;
import com.application.rest.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductDaoIMPL implements IProductDAO {

    private final ProductRepository pr;

    @Override
    public Optional<Product> findByid(Long id) {
        return pr.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) pr.findAll();
    }

    @Override
    public void saved(Product product) {
        pr.save(product);
    }

    @Override
    public void deleteById(Long id) {
        pr.deleteById(id);
    }

    @Override
    public List<Product> findByPriceInRange(Double minPrice, Double maxPrice) {
        return pr.findByPriceInRange(minPrice, maxPrice);
    }

    @Override
    public List<Product> findByTitle(String title) {
        return pr.findByTitle(title);
    }

    @Override
    public List<Product> finByStock() {
        return pr.finByStock();
    }

}
