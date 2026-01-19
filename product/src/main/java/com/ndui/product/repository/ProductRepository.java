package com.ndui.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ndui.product.model.Produk;

@Repository
public interface ProductRepository extends JpaRepository<Produk, Long>{

}
