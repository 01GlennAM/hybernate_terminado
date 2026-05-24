package com.example;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "progreso_Lecciones")
public class ProgresoLeccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProgreso;

    @ManyToOne
    private Inscripcion inscripcion;

    @ManyToOne
    private Leccion leccion;
    private boolean completado;
    private Date fechaCompletado;

    public ProgresoLeccion() {
    }

    public ProgresoLeccion(Inscripcion inscripcion, Leccion leccion, boolean completado,
            Date fechaCompletado) {
        this.inscripcion = inscripcion;
        this.leccion = leccion;
        this.completado = completado;
        this.fechaCompletado = fechaCompletado;
    }

    public Long getIdProgreso() {
        return idProgreso;
    }

    public void setIdProgreso(Long idProgreso) {
        this.idProgreso = idProgreso;
    }

    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }

    public Leccion getLeccion() {
        return leccion;
    }

    public void setLeccion(Leccion leccion) {
        this.leccion = leccion;
    }

    public boolean isCompletado() {
        return completado;
    }

    // para q la fecha se ponga automaticamente cuando se haya completado el 
    // curso de resto no.
    public void setCompletado(boolean completado) {
        this.completado = completado;
            if (completado) {
                this.fechaCompletado = new Date(System.currentTimeMillis());
            } else {
                this.fechaCompletado = null;
        }
    }

    public Date getFechaCompletado() {
        return fechaCompletado;
    }

    public void setFechaCompletado(Date fechaCompletado) {
        this.fechaCompletado = fechaCompletado;
    }

    @Override
    public String toString() {
        return "ProgresoLeccion [idProgreso=" + idProgreso + ", inscripcion=" + inscripcion + ", leccion=" + leccion
                + ", completado=" + completado + ", fechaCompletado=" + fechaCompletado + "]";
    }

}