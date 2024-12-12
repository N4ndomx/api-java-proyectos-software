package com.uv.psw.prestaciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

@Entity
@Table(name = "\"Prestaciones\"")
@TypeDefs({
    @TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
    )
})
public class Prestacion {

    @Id
    @Column(name = "clave")
    private Integer clave;

    @Column(name = "prestacion")
    private String prestacion;

    @Column(name = "valor")
    private Integer valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "\"tipoValor\"")
    @Type(type = "pgsql_enum")
    private TipoValor tipoValor;

    @Column(name = "estado")
    private boolean estado;

    public enum TipoValor {
        dia,
        año,
        porcentaje
    }

    public Prestacion() {
    }

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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
