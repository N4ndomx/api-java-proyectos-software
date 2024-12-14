package com.uv.psw.Asistencia.dto;


import java.time.LocalDate;
import java.time.LocalTime;

public class AsistenciaCreateDTO {


    private String claveEmpleado;

    private LocalDate fecha;

    private LocalTime hora;

    private String tipoAsistencia;


    // Getters y Setters
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

    public String getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(String tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }
}
