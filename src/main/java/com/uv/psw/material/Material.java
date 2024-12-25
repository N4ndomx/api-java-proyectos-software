/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uv.psw.material;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Ville
 */


@Entity
@Table(name = "Material")
public class Material {
    @Id
    @Column(name = "clave")
    private Integer clave;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "existencia")
    private Double existencia;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "unidadMedida")
    private String unidadMedida;

    @Column(name = "estado")
    private Boolean estado = true;

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

    public Double getExistencia() {
        return existencia;
    }

    public void setExistencia(Double existencia) {
        this.existencia = existencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    
    
    
}
