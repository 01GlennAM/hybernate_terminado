package com.example.repository;

import com.example.Modulo;
import com.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class ModuloRepository extends GenericRepositoryImpl<Modulo, Long> {
    public ModuloRepository(EntityManager em) {
        super(em, Modulo.class);
    }
}
