package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "lecciones")
public class Leccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLeccion;

    private String titulo;
    private String contenido;
    private String url;
    private int duracion;
    //private int orden;

    @ManyToOne
    @JoinColumn(name = "id_modulo")
    private Modulo modulo; // relación con Modulo

    public Leccion() {
            }

    public Leccion(String titulo, String contenido, String url, int duracion, Modulo modulo) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.url = url;
        this.duracion = duracion;
        //this.orden = orden;
        this.modulo = modulo;
    }

    public Long getIdLeccion() {
        return idLeccion;
    }

    public void setIdLeccion(Long idLeccion) {
        this.idLeccion = idLeccion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

  /*   public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    } */

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    @Override
    public String toString() {
        return "Leccion [idLeccion=" + idLeccion + ", titulo=" + titulo + ", contenido=" + contenido + ", url=" + url
                + ", duracion=" + duracion + ", orden=" + ", modulo=" + modulo + "]";
    }
     
}

