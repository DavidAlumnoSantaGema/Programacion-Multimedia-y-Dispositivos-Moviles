package com.example.listadecompra;

public class Item
{
    private int imageID;
    private int nombre;
    private int cantidad;

    public Item(int nombre, int cantidad, int imageID)
    {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.imageID = imageID;
    }

    public Item(int nombre, int imageID)
    {
        this.nombre = nombre;
        this.cantidad = 1;
        this.imageID = imageID;
    }

    public int getNombre() {
        return nombre;
    }

    public int getImageID() {
        return imageID;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
