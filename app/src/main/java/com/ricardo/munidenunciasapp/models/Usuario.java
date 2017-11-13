package com.ricardo.munidenunciasapp.models;


public class Usuario {

    private Integer id;
    private String username;
    private String password;
    private String nombres;
    private String correo;
    private String imagen;
    private String estado;

    public Usuario() {}

    public Usuario(Integer id, String username, String password, String nombres, String correo, String imagen, String estado) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nombres = nombres;
        this.correo = correo;
        this.imagen = imagen;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nombres='" + nombres + '\'' +
                ", correo='" + correo + '\'' +
                ", imagen='" + imagen + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}


