package com.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;

    @Column(unique = true)
    private String nombre; // ADMIN, INSTRUCTOR, ESTUDIANTE
    
    public Rol() {
    }

    public Rol(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    @Override
    public String toString() {
        return "Rol [idRol=" + idRol + ", nombre=" + nombre + "]";
    }

}
