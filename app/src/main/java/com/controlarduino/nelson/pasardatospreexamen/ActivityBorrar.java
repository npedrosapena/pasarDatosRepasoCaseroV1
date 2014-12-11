package com.controlarduino.nelson.pasardatospreexamen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityBorrar extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_borrar);

        //recogemos valores pasados por intent
        final Intent intento=getIntent();
        final Integer posicion=(int)intento.getSerializableExtra("Posicion"); //posicion que ocupa el elemento en el array

        //instanciamos los objetos textview

        TextView txtViewNombre= (TextView)findViewById(R.id.txtViewNombre);
        TextView txtViewTelefono=(TextView)findViewById(R.id.txtViewTelefono);

        //instanciamos los button

        Button btnVolver=(Button)findViewById(R.id.btnCancelarBorrado);
        Button btnBorrar=(Button)findViewById(R.id.btnBorrar);




        txtViewNombre.setText(((Contactos)intento.getSerializableExtra("Contacto")).getNombre().toString());
        txtViewTelefono.setText(((Contactos)intento.getSerializableExtra("Contacto")).getTelefono().toString());

       btnVolver.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View v)
           {
               finish();
           }
       });

        btnBorrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //creamos una alarma con 2 botones
                final AlertDialog.Builder alarma= new AlertDialog.Builder(v.getContext());

                alarma.setTitle("Borrado de contacto");
                alarma.setMessage("Se va a eliminar el contacto, ¿Realmente desea continuar?");

                //programamos el boton aceptar
                alarma.setPositiveButton("aceptar",new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        Agenda agenda=Activity1.agenda;

                        agenda.BorrarContacto((Contactos)intento.getSerializableExtra("Contacto"));

                        setResult(RESULT_OK,intento);//damos ok
                        finish(); //cerramos activity

                    }
                });
                alarma.setNegativeButton("cancelar",null);

                alarma.show();



            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_borrar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
