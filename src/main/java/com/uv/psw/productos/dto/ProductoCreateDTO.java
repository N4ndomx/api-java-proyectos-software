/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uv.psw.productos.dto;

import java.math.BigDecimal;
import java.util.Set;

/**
 *
 * @author ASUS
 */
public class ProductoCreateDTO {
      private Integer clave;
    private String descripcion;
    private BigDecimal precio;
    private int cantidad;
    private Set<Integer> piezasIds; 

    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Set<Integer> getPiezasIds() {
        return piezasIds;
    }

    public void setPiezasIds(Set<Integer> piezasIds) {
        this.piezasIds = piezasIds;
    }
}
