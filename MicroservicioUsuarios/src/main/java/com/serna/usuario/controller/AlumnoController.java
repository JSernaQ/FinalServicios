package com.serna.usuario.controller;

import com.serna.commons.controller.CommonController;
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
public class AlumnoController extends CommonController<Alumno, AlumnoService> {

    @Autowired
    public AlumnoController(AlumnoService service) {
        super(service);
    }

    @Value("${config.balanceador.test}")
    private String balanceadorTest;

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

    @GetMapping("/test-balanceador")
    public ResponseEntity<?> pruebaBalanceador() {
        Map<String, Object> response = new HashMap<>();
        response.put("balanceadorTest", balanceadorTest);
        response.put("alumnos", service.findAll());
        return ResponseEntity.ok(response);
    }
}
