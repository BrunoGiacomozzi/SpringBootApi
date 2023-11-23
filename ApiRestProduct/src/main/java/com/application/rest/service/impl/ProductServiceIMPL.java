package com.application.rest.service.impl;

import com.application.rest.entities.Product;
import com.application.rest.persistence.IProductDAO;
import com.application.rest.service.IProductService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceIMPL implements IProductService {

    private final IProductDAO pd;

    @Override
    public Optional<Product> findByid(Long id) {
        return pd.findByid(id);
    }

    @Override
    public List<Product> findAll() {
        return pd.findAll();
    }

    @Override
    public void saved(Product product) {
        pd.saved(product);
    }

    @Override
    public void deleteById(Long id) {
        pd.deleteById(id);;
    }

    @Override
    public List<Product> findByPriceInRange(Double minPrice, Double maxPrice) {
        return pd.findByPriceInRange(minPrice, maxPrice);
    }

    @Override
    public List<Product> findByTitle(String title) {
        return pd.findByTitle(title);
    }

    @Override
    public List<Product> finByStock() {
        return pd.finByStock();
    }

}
