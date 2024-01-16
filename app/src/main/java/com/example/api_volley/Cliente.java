package com.example.api_volley;
public class Cliente {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String createat;

    public Cliente(Long id, String nombre, String apellido, String email,String createat) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.createat = createat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateat() {
        return createat;
    }

    public void setCreateat(String createat) {
        this.createat = createat;
    }
}
