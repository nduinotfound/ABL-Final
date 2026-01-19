package com.randu.pelanggan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.randu.pelanggan.model.Pelanggan;

@Repository
public interface PelangganRepository extends JpaRepository<Pelanggan, Long>{

}
