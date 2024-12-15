package com.uv.psw.Asistencia.dto;


import com.uv.psw.Asistencia.TipoAsistencia;
import java.time.LocalDate;
import java.time.LocalTime;

public class AsistenciaCreateDTO {


    private String claveEmpleado;

    private LocalDate fecha;

    private LocalTime hora;

    private TipoAsistencia tipoAsistencia;


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

    public  TipoAsistencia getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(TipoAsistencia tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }
}
