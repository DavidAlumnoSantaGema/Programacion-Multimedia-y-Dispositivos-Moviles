package com.example.listadecompra;

public class Item
{
    private String nombre;
    private int cantidad;

    public Item(String nombre, int cantidad)
    {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public Item(String nombre)
    {
        this.nombre = nombre;
        this.cantidad = 1;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
