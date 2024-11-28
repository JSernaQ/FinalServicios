package com.serna.curso.service;

import com.serna.curso.models.entity.Curso;
import com.serna.curso.repository.CursoRepository;
import org.springframework.stereotype.Service;
import com.serna.commons.service.CommonServiceImpl;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

    public CursoServiceImpl(CursoRepository repository) {
        super(repository);
    }

}
