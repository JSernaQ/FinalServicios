package com.co.serna.usuario.service;

import java.util.Optional;
import com.co.serna.usuario.entity.Alumno;

public interface AlumnoService {

    public Iterable<Alumno> findAll();
    public Optional<Alumno> findById(Long id);
    public Alumno save(Alumno alumno);
    public void deleteById(Long id);

}