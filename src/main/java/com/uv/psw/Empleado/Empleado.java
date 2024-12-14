package com.uv.psw.Empleado;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "\"Empleado\"")
public class Empleado {


    @Id
    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "salario", nullable = false)
    private BigDecimal salario;

    @Column(name = "seguroSocial", nullable = false)
    private String seguroSocial;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo")
    private String correo;

    @Column(name = "fechaN", nullable = false)
    private LocalDate fechaN;

    @Column(name = "clavePuesto")
    private Integer clavePuesto; // Suponiendo que es un entero

    @Column(name = "claveSupervisor")
    private String claveSupervisor;

    @Column(name = "añoContratacion", nullable = false)
    private LocalDate añoContratacion;

    @Column(name = "antiguedad")
    private Integer antiguedad; // Suponiendo que es un entero

    @Column(name = "estado", nullable = false)
    private boolean estado;

    // Constructor por defecto
    public Empleado() {
        this.estado = true; // Valor por defecto
    }

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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
