package com.serna.curso.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.serna.commons.controller.CommonController;
import com.serna.curso.models.entity.Curso;
import com.serna.curso.service.CursoService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class CursoController extends CommonController<Curso, CursoService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody Curso curso) {
        // Buscar el curso por ID
        Optional<Curso> ob = service.findById(id);

        // Si no se encuentra el curso, retornar un mensaje adecuado
        if (ob.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Curso no encontrado con el ID: " + id); // Cambié el mensaje para referir a 'Curso'
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Obtener el curso encontrado
        Curso cursoDb = ob.get();
        cursoDb.setNombre(curso.getNombre()); // Actualizar los atributos del curso

        // Guardar el curso actualizado
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDb));
    }
}
