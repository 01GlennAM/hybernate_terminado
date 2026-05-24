package com.example.repository;

import com.example.Curso;
import com.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class CursoRepository extends GenericRepositoryImpl<Curso, Long> {
    public CursoRepository(EntityManager em) {
        super(em, Curso.class);
    }
}

