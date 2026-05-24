package com.example.repository;

import com.example.Pago;
import com.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class PagoRepository extends GenericRepositoryImpl<Pago, Long> {
    public PagoRepository(EntityManager em) {
        super(em, Pago.class);
    }
}
