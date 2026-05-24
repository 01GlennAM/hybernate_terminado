
package com.example;

import java.sql.Date;
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
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;

    private String titulo;
    private String descripcion;
    private String url;
    private double valor;
    private boolean estaPublicado;

    @ManyToOne
    @JoinColumn(name = "id_instructor")
    private Usuario instructor;

    private Date fechaCreacion;
    private Date fechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @OneToMany(mappedBy = "curso")
    private List<Modulo> modulos = new ArrayList<>();

    public Curso() {
    }

    public Curso(String titulo, String descripcion, String url, double valor, boolean estaPublicado,
            Usuario instructor, Date fechaCreacion, Date fechaActualizacion, Categoria categoria) {

        this.titulo = titulo;
        this.descripcion = descripcion;
        this.url = url;
        this.valor = valor;
        this.estaPublicado = estaPublicado;
        this.instructor = instructor;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.categoria = categoria;

    }

    public void agregarModulo(Modulo modulo) {
        if (modulo != null) {
            this.modulos.add(modulo);
            modulo.setCurso(this);
        }
    }

    public List<Modulo> getModulos() {
        return modulos;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isEstaPublicado() {
        return estaPublicado;
    }

    public void setEstaPublicado(boolean estaPublicado) {
        this.estaPublicado = estaPublicado;
    }

    public Usuario getInstructor() {
        return instructor;
    }

    public void setInstructor(Usuario instructor) {
        this.instructor = instructor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Curso [idCurso=" + idCurso + ", titulo=" + titulo + ", descripcion=" + descripcion + ", url=" + url
                + ", valor=" + valor + ", estaPublicado=" + estaPublicado + ", instructor=" + instructor
                + ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion=" + fechaActualizacion + ", categoria="
                + categoria + "]";
    }
}
