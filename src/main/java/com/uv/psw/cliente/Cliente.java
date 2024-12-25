package com.uv.psw.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity()
@Table(name = "\"Cliente\"")
public class Cliente {

    @Id()
    @Column(name = "clave")
    private Integer clave;
    
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "\"RFC\"")
    private String RFC;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Column(name = "direccion",nullable = false)
    private String direccion;

    @NotBlank(message = "El estado no puede estar vacío")
    @Column(name = "estado",nullable = false)
    private boolean estado;

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

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
