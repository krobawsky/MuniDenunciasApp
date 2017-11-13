package com.ricardo.munidenunciasapp.models;


public class Denuncia {

    private Integer id;
    private String usuario_id;
    private String titulo;
    private String nombre_usuario;
    private String descripcion;
    private String latitud;
    private String longitud;
    private String imagen;

    public Denuncia() {}

    public Denuncia(Integer id, String usuario_id, String titulo, String nombre_usuario, String descripcion, String latitud, String longitud, String imagen) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.titulo = titulo;
        this.nombre_usuario = nombre_usuario;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Denuncia{" +
                "id=" + id +
                ", usuario_id='" + usuario_id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", nombre_usuario='" + nombre_usuario + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
