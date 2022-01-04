package com.enonnemacher.course.service;

import com.enonnemacher.course.entities.Product;
import com.enonnemacher.course.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> result = productRepository.findById(id);
        return result.get();
    }
}
