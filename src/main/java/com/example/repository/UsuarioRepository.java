package com.example.repository;

import java.util.List;

import com.example.Usuario;
import com.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class UsuarioRepository extends GenericRepositoryImpl<Usuario, Long> {
    public UsuarioRepository(EntityManager em) {
        super(em, Usuario.class);
    }

      // BUSCAR USUARIOS POR ROL
    public List<Usuario> findByRol(String nombreRol) {

       return em.createQuery(//para q lo encuentre si se pone minuscula o mayus
                "SELECT u FROM Usuario u WHERE LOWER(u.rol.nombre) = LOWER(:rol)",
                Usuario.class)
                .setParameter("rol", nombreRol)
                .getResultList();
    }
}

