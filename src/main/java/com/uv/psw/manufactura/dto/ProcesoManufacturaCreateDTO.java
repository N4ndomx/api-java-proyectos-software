package com.uv.psw.manufactura.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.sql.Time;

public class ProcesoManufacturaCreateDTO {
    private Integer claveMaterial;
    private Integer clavePieza;
//    private BigDecimal costoFabricacion;
    @JsonProperty
    public void setCostoFabricacion(String costoFabricacion) {
        if (costoFabricacion != null && !costoFabricacion.isEmpty()) {
            // Limpia el string de caracteres especiales y convierte comas en puntos
            String cleanValue = costoFabricacion.replace("$", "")
                                              .replace(" ", "")
                                              .replace(",", "");
            this.costoFabricacion = new BigDecimal(cleanValue).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    }
    private BigDecimal costoFabricacion;
    private Time tiempo;
    private Integer numeroProduccion;
    private String unidadMedida;

    // Getters and Setters
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

    public BigDecimal getCostoFabricacion() {
        return costoFabricacion;
    }

    public void setCostoFabricacion(BigDecimal costoFabricacion) {
        this.costoFabricacion = costoFabricacion;
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