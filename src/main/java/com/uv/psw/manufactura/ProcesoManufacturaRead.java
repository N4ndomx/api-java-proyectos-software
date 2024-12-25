package com.uv.psw.manufactura;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "\"ProcesoManufactura\"")
public class ProcesoManufacturaRead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clave")
    private Integer clave;

    @Column(name = "\"claveMaterial\"")
    private Integer claveMaterial;

    @Column(name = "\"clavePieza\"")
    private Integer clavePieza;

    @Column(name = "\"costoFabricacion\"", columnDefinition = "MONEY")
    private String costoFabricacion;

    @Column(name = "tiempo")
    private Time tiempo;

    @Column(name = "\"numeroProduccion\"")
    private Integer numeroProduccion;

    @Column(name = "\"unidadMedida\"")
    private String unidadMedida;

    // Getters y setters
    public String getCostoFabricacion() {
        return costoFabricacion;
    }

    public void setCostoFabricacion(String costoFabricacion) {
        this.costoFabricacion = costoFabricacion;
    }

    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public Integer getClaveMaterial() {
        return claveMaterial;
    }

    public void setClaveMaterial(Integer claveMaterial) {
        this.claveMaterial = claveMaterial;
    }

    public Integer getClavePieza() {
        return clavePieza;
    }

    public void setClavePieza(Integer clavePieza) {
        this.clavePieza = clavePieza;
    }

    public Time getTiempo() {
        return tiempo;
    }

    public void setTiempo(Time tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getNumeroProduccion() {
        return numeroProduccion;
    }

    public void setNumeroProduccion(Integer numeroProduccion) {
        this.numeroProduccion = numeroProduccion;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    
}