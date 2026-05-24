package com.example.repository;

import com.example.Inscripcion;
import com.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class InscripcionRepository extends GenericRepositoryImpl<Inscripcion, Long> {
    public InscripcionRepository(EntityManager em) {
        super(em, Inscripcion.class);
    }
}
