/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uv.psw.piezas.dto;

import java.math.BigDecimal;

/**
 *
 * @author ASUS
 */
public class PiezaCreateDTO {
    private Integer clave;
    private String nombre;
    private String descripcion;
    private int existencia;
    private BigDecimal costo;
    
    public BigDecimal getCosto() {
        return costo;
    }

    // Getters y setters
    public void setCosto(BigDecimal costo) {    
        this.costo = costo;
    }

    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }
}
