/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uv.psw.empleados;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * @author ASUS
 */
@Entity()
@Table(name = "\"Empleado\"")
public class Empleado {

    @Id
    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "salario")
    private BigDecimal salario;

    @Column(name = " \"seguroSocial\" ")
    private String seguroSocial;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo")
    private String correo;

    @Column(name = "\"fechaN\"")
    private Date fechaN;

    @Column(name = "\"clavePuesto\"")
    private Integer clavePuesto;

    @Column(name = "\"claveSupervisor\"")
    private String claveSupervisor;

    @Column(name = "\"añoContratacion\"")
    private Date añoContratacion;

    @Column(name = "antiguedad")
    private Integer antiguedad;

    @Column(name = "estado")
    private boolean estado;

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

    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
