package com.example.repository;

import com.example.ProgresoLeccion;
import com.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class ProgresoLeccionRepository extends GenericRepositoryImpl<ProgresoLeccion, Long> {
    public ProgresoLeccionRepository(EntityManager em) {
        super(em, ProgresoLeccion.class);
    }
}
