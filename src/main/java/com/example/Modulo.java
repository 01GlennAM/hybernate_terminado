package com.example;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@Table(name = "modulos")
public class Modulo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModulo;

    private String titulo;
    private String descripcion;
    /* private int orden; */

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso; // relación con Curso

    @OneToMany(mappedBy = "modulo")
    private List<Leccion> lecciones;

    public Modulo() {
    }

    public Modulo(String titulo, String descripcion, Curso curso) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        /* this.orden = orden; */
        this.curso = curso;
        this.lecciones = new ArrayList<>();
    } 
     // se agrega por este metodo
    public void agregarLeccion(Leccion leccion) {
    if (leccion != null) {

        this.lecciones.add(leccion);

        leccion.setModulo(this); // esto significa q este modulo(el q sea el modulo
        //pertenece a este curso)
    }
}

    public Long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

   /*  public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    } */

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Leccion> getLecciones() {
        return lecciones;
    }

    public void setLecciones(List<Leccion> lecciones) {
        this.lecciones = lecciones;
    }

    @Override
    public String toString() {
        return "Modulo [idModulo=" + idModulo + ", titulo=" + titulo + ", descripcion=" + descripcion + 
                ", curso=" + curso + ", lecciones=" + lecciones + "]";
    } 
}