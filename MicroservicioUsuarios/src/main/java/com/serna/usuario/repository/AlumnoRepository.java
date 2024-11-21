package com.serna.usuario.repository;

import org.springframework.data.repository.CrudRepository;
import com.serna.usuario.entity.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {

}
