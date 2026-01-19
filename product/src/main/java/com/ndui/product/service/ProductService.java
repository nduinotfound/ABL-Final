package com.ndui.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndui.product.model.Produk;
import com.ndui.product.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Produk> gettAllProducts(){
        return productRepository.findAll();
    }

    public Produk getProducById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public Produk createProduct(Produk product){
        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
