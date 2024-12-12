package com.uv.psw.Empleado.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmpleadoCreateDto {

    private String matricula;
    private String nombre;
    private String apellido;
    private BigDecimal salario;
    private String seguroSocial;
    private String direccion;
    private String telefono;
    private String correo;
    private LocalDate fechaN;
    private Integer clavePuesto;
    private String claveSupervisor;
    private LocalDate añoContratacion;
    private Integer antiguedad;
    private Boolean estado;

    // Getters y Setters

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getSeguroSocial() {
        return seguroSocial;
    }

    public void setSeguroSocial(String seguroSocial) {
        this.seguroSocial = seguroSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getFechaN() {
        return fechaN;
    }

    public void setFechaN(LocalDate fechaN) {
        this.fechaN = fechaN;
    }

    public Integer getClavePuesto() {
        return clavePuesto;
    }

    public void setClavePuesto(Integer clavePuesto) {
        this.clavePuesto = clavePuesto;
    }

    public String getClaveSupervisor() {
        return claveSupervisor;
    }

    public void setClaveSupervisor(String claveSupervisor) {
        this.claveSupervisor = claveSupervisor;
    }

    public LocalDate getAñoContratacion() {
        return añoContratacion;
    }

    public void setAñoContratacion(LocalDate añoContratacion) {
        this.añoContratacion = añoContratacion;
    }

    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


}
