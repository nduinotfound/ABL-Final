package com.randu.anggota.controller;

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
import com.randu.anggota.model.Anggota;
import com.randu.anggota.service.AnggotaService;

@RestController
@RequestMapping("/anggota")
public class AnggotaController {

    private static final Logger log = LoggerFactory.getLogger(AnggotaController.class);

    @Autowired
    private AnggotaService anggotaService;

    @GetMapping
    public List<Anggota> getAllAnggotas(){
        log.info("Request: Mengambil semua data anggota");
        return anggotaService.gettAllAnggotas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anggota> getAnggotaById(@PathVariable Long id){
        Anggota anggota = anggotaService.getAnggotaById(id);
        return anggota != null ? ResponseEntity.ok(anggota) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Anggota createAnggota(@RequestBody Anggota anggota){
        log.info("Request: Mendaftarkan anggota baru dengan nama: {}", anggota.getNama());
        return anggotaService.createAnggota(anggota);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnggota(@PathVariable Long id){
        anggotaService.deleteAnggota(id);
        return ResponseEntity.ok().build();
    }
}