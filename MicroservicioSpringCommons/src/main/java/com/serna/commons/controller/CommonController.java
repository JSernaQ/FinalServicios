package com.serna.commons.controller;

import com.serna.commons.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommonController<E, S extends CommonService<E>> {


    @Autowired
    protected S service;


    @Value("${config.balanceador.test:default}")
    protected String balanceadorTest;


    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(service.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id) {
        Optional<E> entity = service.findById(id);

        if (entity.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Entidad no encontrada con el ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok().body(entity.get());
    }


    @PostMapping
    public ResponseEntity<?> crear(@RequestBody E entity) {
        E savedEntity = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<E> entity = service.findById(id);

        if (entity.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Entidad no encontrada con el ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        service.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Entidad eliminada con éxito con el ID: " + id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/test-balanceador")
    public ResponseEntity<?> pruebaBalanceador() {
        Map<String, Object> response = new HashMap<>();
        response.put("balanceadorTest", balanceadorTest);
        response.put("entities", service.findAll());
        return ResponseEntity.ok(response);
    }
}
