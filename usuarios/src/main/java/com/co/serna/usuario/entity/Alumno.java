package com.co.serna.usuario.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private String apellido;
    private String email;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @PrePersist
    private void prePersiste() {
        this.createAt = new Date();
    }


    public Alumno() {}

    // Constructor con todos los campos
    public Alumno(Long Id, String nombre, String apellido, String email, Date createAt) {
        this.Id = Id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.createAt = createAt;
    }

    // Getters y Setters
    public Long getId() { return Id; }
    public void setId(Long Id) { this.Id = Id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Date getCreateAt() { return createAt; }
    public void setCreateAt(Date createAt) { this.createAt = createAt; }

    @Override
    public String toString() {
        return "Alumno{" +
                "Id=" + Id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
