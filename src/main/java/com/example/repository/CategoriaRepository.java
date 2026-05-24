package com.example.repository;

import com.example.Categoria;
import com.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class CategoriaRepository extends GenericRepositoryImpl<Categoria, Long> {
    public CategoriaRepository(EntityManager em) {
        super(em, Categoria.class);
    }
}
