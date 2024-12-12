package com.uv.psw.prestaciones.dto;

import com.uv.psw.prestaciones.Prestacion.TipoValor;

/*
 * @author Berna
 */

public class PrestacionCreateDTO {
    private Integer clave;
    private String prestacion;
    private Integer valor;
    private TipoValor tipoValor;

    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public String getPrestacion() {
        return prestacion;
    }

    public void setPrestacion(String prestacion) {
        this.prestacion = prestacion;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public TipoValor getTipoValor() {
        return tipoValor;
    }

    public void setTipoValor(TipoValor tipoValor) {
        this.tipoValor = tipoValor;
    }
}