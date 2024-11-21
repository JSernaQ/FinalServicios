package com.serna.usuario.controller;

import com.serna.usuario.entity.Alumno;
import com.serna.usuario.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService service;


    @Value("${config.balanceador.test}")
    private String balanceadorTest;

    @GetMapping
    public ResponseEntity<?> listarAlumno() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id) {
        Optional<Alumno> ob = service.findById(id);

        if (ob.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Alumno no encontrado con el ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok().body(ob.get());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Alumno alumno) {
        Alumno alumnoDb = service.save(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody Alumno alumno) {
        Optional<Alumno> ob = service.findById(id);

        if (ob.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Alumno no encontrado con el ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Alumno alumnoDb = ob.get();
        alumnoDb.setNombre(alumno.getNombre());
        alumnoDb.setApellidos(alumno.getApellidos());
        alumnoDb.setEmail(alumno.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Alumno> ob = service.findById(id);

        if (ob.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Alumno no encontrado con el ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        service.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Alumno eliminado con éxito con el ID: " + id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test-balanceador")
    public ResponseEntity<?> pruebaBalanceador() {
        Map<String, Object> response = new HashMap<>();
        response.put("balanceadorTest", balanceadorTest);
        response.put("alumnos", service.findAll());
        return ResponseEntity.ok(response);
    }
}
