package com.co.serna.usuario.controller;

import com.co.serna.usuario.entity.Alumno;
import com.co.serna.usuario.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AlumnoController {

    @Autowired
    AlumnoService service;

    @GetMapping
    public ResponseEntity<?> listarAlumno() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}") // Specify the path for getting a single student
    public ResponseEntity<?> ver(@PathVariable Long id) {
        Optional<Alumno> ob = service.findById(id);

        if (ob.isEmpty()) {
            return ResponseEntity.noContent().build(); // Use 204 No Content if not found
        }
        return ResponseEntity.ok().body(ob.get());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Alumno alumno) {
        Alumno alumnoDb = service.save(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb); // Use 201 Created
    }

    @PutMapping("/{id}") // Specify the path for editing
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody Alumno alumno) {
        Optional<Alumno> ob = service.findById(id);

        if (ob.isEmpty()) {
            return ResponseEntity.noContent().build(); // Use 204 No Content if not found
        }

        Alumno alumnoDb = ob.get();
        alumnoDb.setNombre(alumno.getNombre());
        alumnoDb.setApellido(alumno.getApellido());
        alumnoDb.setEmail(alumno.getEmail());

        Alumno updatedAlumno = service.save(alumnoDb);
        return ResponseEntity.ok(updatedAlumno); // Return updated student with 200 OK
    }

    @DeleteMapping("/{id}") // Specify the path for deletion
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build(); // Return 204 No Content after deletion
    }
}
