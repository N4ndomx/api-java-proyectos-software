
package com.uv.psw.empleados;

import javax.persistence.*;

@Entity
@Table(name = "Empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String clave;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private double salario;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String correoE;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreoE() { return correoE; }
    public void setCorreoE(String correoE) { this.correoE = correoE; }
}
