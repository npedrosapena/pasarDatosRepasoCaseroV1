package com.controlarduino.nelson.pasardatospreexamen;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Activity1 extends ActionBarActivity
{
    public static Agenda agenda= new Agenda(); //agenda con la que jugaremos en el programa

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity1);

        //definicion de los botones sobre los que vamos a actuar
        Button btnAgregar=(Button)findViewById(R.id.btnAdd);
        Button btnListar=(Button)findViewById(R.id.btnListar);

        btnAgregar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText txtNombre=(EditText)findViewById(R.id.txtNombre);
                EditText txtTelefono=(EditText)findViewById(R.id.txtTelefono);

                //creamos el objeto para guardar los datos
                Contactos contacto=new Contactos(txtNombre.getText().toString(),Integer.parseInt(txtTelefono.getText().toString()),agenda.longitud());

                if(agenda.guardarContacto(contacto)==true)
                {
                  mensaje(R.string.GuardadoCorrectamente);//recogemos la cadena del xml
                }else
                {
                    mensaje(R.string.ErrorGuardado);
                }
            }
        });


        //programamos el boton listar
        btnListar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundle= new Bundle();//cajita para guardar cosas
                Intent intento= new Intent(Activity1.this,Activity2.class); //del activity1 pasamos al 2

                bundle.putSerializable("Agenda",agenda); //guardamos la agenda en nuestra cajita

                intento.putExtras(bundle); //lo pasamos a nuestro intent para que lo pase al otro activity

                startActivityForResult(intento,1);
            }
        });
    }


    //muestra un toast con el mensaje que tenemos en strings xml
    public void mensaje(int msg)
    {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity1, menu);
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
