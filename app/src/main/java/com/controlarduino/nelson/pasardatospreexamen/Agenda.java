package com.controlarduino.nelson.pasardatospreexamen;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by nelson on 10/12/14.
 */
public class Agenda implements Serializable
{
    static ArrayList<Contactos> lista= new ArrayList<Contactos>(); //array donde guardamos los contactos

    public Agenda(){} //constructor

    //guardamos contacto
    public Boolean guardarContacto(Contactos contacto)
    {
        if(lista.add(contacto)==true)
        {
            return true;
        }else
        {
            return false;
        }
    }

    public void actualizarContacto(Integer posicion,Contactos c)
    {
        lista.set(posicion,c);
    }


    public ArrayList arrayContactos()
    {
        return this.lista;
    }

    public Contactos buscarContacto(Integer posicion)
    {
      return lista.get(posicion);
    }

    public Integer longitud()
    {
        return lista.size();
    }
}
