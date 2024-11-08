/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uv.psw.productos;

import com.uv.psw.piezas.Pieza;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author ASUS
 */
@Entity()
@Table(name = "\"Producto\"")
public class Producto {
    @Id
    @Column(name = "clave")
    private Integer clave;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "precio")
    private BigDecimal precio;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "estado")
    private boolean estado;
    @ManyToMany
    @JoinTable(
        name = "\"ProductosPieza\"", // Nombre de la tabla de unión existente en la base de datos
          joinColumns = @JoinColumn(name = "\"claveProducto\""),
        inverseJoinColumns = @JoinColumn(name = "\"clavePieza\"")
    )
    private Set<Pieza> piezas = new HashSet<>();

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
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

    public Set<Pieza> getPiezas() {
        return piezas;
    }

    public void setPiezas(Set<Pieza> piezas) {
        this.piezas = piezas;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
