package com.uv.psw.Asistencia;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "\"Asistencia\"")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "clave")
    private Integer clave;

    @Column(name = "\"claveEmpleado\"", nullable = false)
    private String claveEmpleado;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    @Enumerated(EnumType.STRING)
    @Column(name = "\"tipoAsistencia\"", nullable = false)
    private TipoAsistencia tipoAsistencia;


    // Constructor por defecto
    public Asistencia() {
    }

    // Getters y Setters
    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public String getClaveEmpleado() {
        return claveEmpleado;
    }

    public void setClaveEmpleado(String claveEmpleado) {
        this.claveEmpleado = claveEmpleado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public TipoAsistencia getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(TipoAsistencia tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }

}
