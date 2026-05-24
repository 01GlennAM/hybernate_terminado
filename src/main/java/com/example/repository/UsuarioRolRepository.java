package com.example.repository;

import com.example.UsuarioRol;
import com.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class UsuarioRolRepository extends GenericRepositoryImpl<UsuarioRol, Long> {
    public UsuarioRolRepository(EntityManager em) {
        super(em, UsuarioRol.class);
    }
}
