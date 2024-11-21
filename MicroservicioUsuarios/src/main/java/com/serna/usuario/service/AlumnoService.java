package com.serna.usuario.service;

import com.serna.commons.service.CommonService;
import com.serna.usuario.entity.Alumno;

import java.util.Optional;

public interface AlumnoService extends CommonService<Alumno> {

    public Alumno save(Alumno alumno);

    public void deleteById(Long id);

}