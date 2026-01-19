package com.randu.buku.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;    
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.randu.buku.model.Buku;
import com.randu.buku.service.BukuService;

@RestController
@RequestMapping("/buku")
public class BukuController {

    private static final Logger log = LoggerFactory.getLogger(BukuController.class);

    @Autowired
    private BukuService bukuService;

    @GetMapping
    public List<Buku> getAllBukus(){
        log.info("Request: Mengambil semua data buku");
        return bukuService.gettAllBukus();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Buku> getBukuById(@PathVariable Long id){
        log.info("Request: Mencari buku dengan ID: {}", id);
        Buku buku = bukuService.getBukuById(id);
        return buku != null ? ResponseEntity.ok(buku) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Buku createBuku(@RequestBody Buku buku){
        log.info("Request: Menambahkan buku baru: {}", buku.getJudul());
        return bukuService.createBuku(buku);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBuku(@PathVariable Long id){
        log.info("Request: Menghapus buku dengan ID: {}", id);
        bukuService.deleteBuku(id);
        return ResponseEntity.ok().build();
    }
}