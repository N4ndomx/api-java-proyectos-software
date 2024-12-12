/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uv.psw.empleados.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class EmpleadoCreateDTO {
      
    private String matricula;
    private String nombre;
    private String apellido;
    private BigDecimal salario;
    private String seguroSocial;
    private String direccion;
    private String telefono;
    private String correo;
    private Date antiguedad;
    private Date fechaN;
    private Integer clavePuesto;
    private String claveSupervisor;
    private Date añoContratacion;

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

    public Date getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Date antiguedad) {
        this.antiguedad = antiguedad;
    }

    public Date getFechaN() {
        return fechaN;
    }

    public void setFechaN(Date fechaN) {
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

    public Date getAñoContratacion() {
        return añoContratacion;
    }

    public void setAñoContratacion(Date añoContratacion) {
        this.añoContratacion = añoContratacion;
    }
}
