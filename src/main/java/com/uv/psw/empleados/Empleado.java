package com.uv.psw.empleados;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "\"Empleado\"")
public class Empleado {

    @Id
    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 50, message = "El apellido no puede tener más de 50 caracteres")
    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private double salario;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Column(nullable = false)
    private String direccion;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "\\d{10}", message = "El teléfono debe tener 10 dígitos")
    @Column(nullable = false)
    private String telefono;

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email(message = "El correo electrónico no tiene un formato válido")
    @Column(nullable = false, unique = true)
    private String correoE;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean verificado = false;

    @Column
    private String codigoVerificacion;

    @Enumerated(EnumType.STRING)
    private Rol rol;
    @JsonFormat(pattern = "yyyy-MM-dd")  // Establece el formato de fecha
    @Column(name = "\"fechaN\"", nullable = false)
    private Date fechaN;

    @Column(name = "\"clavePuesto\"", nullable = true)
    private Integer clavePuesto; // Suponiendo que es un entero

    @Column(name = "\"claveSupervisor\"", nullable = true)
    private String claveSupervisor;
    @JsonFormat(pattern = "yyyy-MM-dd")  // Establece el formato de fecha
    @Column(name = "\"añoContratacion\"", nullable = false)
    private Date añoContratacion;

    @Column(name = "antiguedad", nullable = true)
    private Integer antiguedad; // Suponiendo que es un entero

    @Column(name = "estado", nullable = false)
    private boolean estado;

      @Column(name = "\"seguroSocial\"", nullable = false)
    private String seguroSocial;

    public String getSeguroSocial() {
        return seguroSocial;
    }

    public void setSeguroSocial(String seguroSocial) {
        this.seguroSocial = seguroSocial;
    }
    
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getFechaN() {
        return fechaN;
    }

    public void setFechaN(Date fechaN) {
        this.fechaN = fechaN;
    }

    public Integer getClavePuesto() {
        return clavePuesto;
    }

    public void setClavePuesto(Integer clavePuesto) {
        this.clavePuesto = clavePuesto;
    }

    public String getClaveSupervisor() {
        return claveSupervisor;
    }

    public void setClaveSupervisor(String claveSupervisor) {
        this.claveSupervisor = claveSupervisor;
    }

    public Date getAñoContratacion() {
        return añoContratacion;
    }

    public void setAñoContratacion(Date añoContratacion) {
        this.añoContratacion = añoContratacion;
    }

    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // Constructor por defecto
    public Empleado() {
        this.estado = true; // Valor por defecto
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoE() {
        return correoE;
    }

    public void setCorreoE(String correoE) {
        this.correoE = correoE;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public String getCodigoVerificacion() {
        return codigoVerificacion;
    }

    public void setCodigoVerificacion(String codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
