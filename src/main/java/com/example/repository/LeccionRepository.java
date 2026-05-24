package com.example.repository;

import com.example.Leccion;
import com.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class LeccionRepository extends GenericRepositoryImpl<Leccion, Long> {
    public LeccionRepository(EntityManager em) {
        super(em, Leccion.class);
    }
}
