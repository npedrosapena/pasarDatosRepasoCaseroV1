package com.controlarduino.nelson.pasardatospreexamen;

import java.io.Serializable;

/**
 * Created by nelson on 10/12/14.
 */
public class Contactos implements Serializable
{
    private String nombre;
    private static Integer id=0;
    private Integer telefono;

    public Contactos(String n, Integer t, Integer id)
    {
        this.setNombre(n);
        this.setTelefono(t);
        this.setId(id);
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public Integer getTelefono()
    {
        return telefono;
    }


    public void setTelefono(Integer telefono)
    {
        this.telefono = telefono;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    //se sobreescribe este método para la muestra de los datos en el ListView
    @Override
    public String toString()
    {
        return "Nombre: "+getNombre()+"     "+"Telefono: "+getTelefono();
    }
}
