package com.randu.anggota.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.randu.anggota.model.Anggota;

@Repository
public interface AnggotaRepository extends JpaRepository<Anggota, Long>{
}
