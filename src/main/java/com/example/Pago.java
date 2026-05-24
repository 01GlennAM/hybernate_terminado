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
@Table(name = "pagos")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;
    private double valor;

    // “Guarda el enum como texto en la base de datos en vez de numeros”.
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    // “Guarda el enum como texto en la base de datos en de numeros”.
    @Enumerated(EnumType.STRING)
    private EstadoPago estado;

    private String idTransaccion;
    private Date fechaPago;

    // enum es un tipo de dato especial que solo permite valores definidos por ti
    // se crea un tipo de dato llamado metodopago y puede ser cualquier valor de
    // metodopago
    // no necesitan instanciar como un objeto por que ya estan
    // estado = EstadoPago.APROBADO; // no necesitas new pero en categoria si
    // necesito new
    public enum MetodoPago {
        TARJETA,
        PAYPAL,
        EFECTIVO
    }

    public enum EstadoPago {
        APROBADO,
        RECHAZADO
    }

    public Pago() {
    }

    // no se pone el idPago porque es automatico
    public Pago(
            Usuario usuario,
            Curso curso,
            double valor,
            MetodoPago metodoPago,
            EstadoPago estado,
            String idTransaccion) {

        this.usuario = usuario;
        this.curso = curso;
        this.valor = valor;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.idTransaccion = idTransaccion;
        this.fechaPago = new Date(System.currentTimeMillis()); // crea la fecha automaticamente por eso no se llama en el main
    }

    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public EstadoPago getEstado() {
        return estado;
    }

    public void setEstado(EstadoPago estado) {
        this.estado = estado;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Override
    public String toString() {
        return "Pago [idPago=" + idPago +
                ", usuario=" + usuario.getNombre() +
                ", curso=" + curso.getTitulo() +
                ", valor=" + valor +
                ", metodoPago=" + metodoPago +
                ", estado=" + estado +
                ", fechaPago=" + fechaPago +
                "]";
    }

}