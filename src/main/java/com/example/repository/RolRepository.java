package com.example.repository;

import com.example.Rol;
import com.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class RolRepository extends GenericRepositoryImpl<Rol, Long> {
    public RolRepository(EntityManager em) {
        super(em, Rol.class);
    }
}
