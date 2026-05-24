package com.example;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
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
@Table(name = "usuarios")
public final class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombre;
    private String email;
    private String contrasena;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private boolean activo;

    // RELACION CON ROL
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @OneToMany(mappedBy = "usuario")
    private List<Pago> pago;

    @OneToMany(mappedBy = "usuario")
    private List<Inscripcion> inscripcion;

    public Usuario() {
    }

    public Usuario(String nombre, String email, String contrasena, Rol rol) {

        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol;
        this.fechaCreacion = new Date(System.currentTimeMillis());
        this.fechaActualizacion = new Date(System.currentTimeMillis());

        this.activo = true;

        this.pago = new ArrayList<>();
        this.inscripcion = new ArrayList<>();

    }

    /*
     * Permite ver los pagos, pero no permite hacer .add() ni .clear() en las listas
     */
    public List<Pago> getPago() {
        return Collections.unmodifiableList(pago);
    }

    public void setPago(List<Pago> pago) {
        this.pago = pago;
    }

    public List<Inscripcion> getInscripcion() {
        return Collections.unmodifiableList(inscripcion);
    }

    public void setInscripcion(List<Inscripcion> inscripcion) {
        this.inscripcion = inscripcion;
    }

    public void agregarPago(Pago pago) {
        if (pago != null) {
            this.pago.add(pago);
        }
    }

    public void agregarInscripcion(Inscripcion inscripcion) {
        if (inscripcion != null) {
            this.inscripcion.add(inscripcion);
        }
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    /* confirmamos que el email debe llevar @ */
    public void setEmail(String email) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
    return "Usuario [idUsuario=" + idUsuario
            + ", nombre=" + nombre
            + ", email=" + email
            + ", rol=" + (rol != null ? rol.getNombre() : "SIN ROL")
            + ", activo=" + activo + "]";
}
}