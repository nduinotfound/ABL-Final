package com.ndui.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;    
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndui.product.model.Produk;
import com.ndui.product.service.ProductService;


@RestController
@RequestMapping("/api/produk")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Produk> getAllProducts(){
        return productService.gettAllProducts();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Produk> getProductById(@PathVariable Long id){
        Produk product = productService.getProducById(id);
        return product !=null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }
    @PostMapping
    public Produk createProduct(@RequestBody Produk product){
        return productService.createProduct(product);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
