package com.dam2.accesodatos;
public class Animal {

    private String nombre;
    private String raza;
    private String caracteristica;
    private float peso;

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public String getRaza() {

        return this.raza;
    }

    public void setRaza(String raza) {

        this.raza = raza;
    }

    public String getCaracteristica() {

        return this.caracteristica;
    }

    public void setCaracteristica(String caracteristica) {

        this.caracteristica = caracteristica;
    }

    public float getPeso() {

        return this.peso;
    }

    public void setPeso(float peso) {

        this.peso = peso;
    }
}
