package com.example;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Table(name = "inscripciones")
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInscripcion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    private Curso curso;

    private Date fechaInscripcion;

    @Enumerated(EnumType.STRING)
    private EstadoInscripcion estado;

    private double porcentajeProgreso;
    @Enumerated(EnumType.STRING)
    private Nivel nivel;
    private double valor;

    public Inscripcion() {
    }

    public Inscripcion(Usuario usuario, Curso curso, Date fechaInscripcion, EstadoInscripcion estado,
            double porcentajeProgreso, Nivel nivel, double valor) {
        this.usuario = usuario;
        this.curso = curso;
        this.fechaInscripcion = fechaInscripcion;
        this.estado = estado;
        this.porcentajeProgreso = porcentajeProgreso;
        this.nivel = nivel;
        this.valor = valor;
    }

    // ENUMS es como un menú
    public enum EstadoInscripcion {
        ACTIVA,
        CANCELADA,
        COMPLETADA
    }

    public enum Nivel {
        BASICO,
        INTERMEDIO,
        AVANZADO
    }

    public Long getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(Long idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public EstadoInscripcion getEstado() {
        return estado;
    }

    public boolean isActivo() {
        return estado == EstadoInscripcion.ACTIVA;
    }

    public void setEstado(EstadoInscripcion estado) {
        this.estado = estado;
    }

    public double getPorcentajeProgreso() {
        return porcentajeProgreso;
    }

    public void setPorcentajeProgreso(double porcentajeProgreso) {
        this.porcentajeProgreso = porcentajeProgreso;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    

    @Override
    public String toString() {
        return "Inscripcion [idInscripcion=" + idInscripcion + ", usuario=" + usuario + ", curso=" + curso
                + ", fechaInscripcion=" + fechaInscripcion + ", estado=" + estado + ", porcentajeProgreso="
                + porcentajeProgreso + ", nivel=" + nivel + ", valor=" + valor + "]";
    }

}
