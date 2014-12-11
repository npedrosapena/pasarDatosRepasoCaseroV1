package com.controlarduino.nelson.pasardatospreexamen;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Activity3 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3);

         final Intent intento=getIntent();
         final Contactos contacto= (Contactos) intento.getSerializableExtra("Contacto");

        //instanciamos los objetos para acceder a los textos del activity3
        final EditText txtEditarNombre=(EditText)findViewById(R.id.txtEditNombre);
        final EditText txtEditarTelefono=(EditText)findViewById(R.id.txtEditTelefono);

        //ponemos los datos en los textos
        txtEditarNombre.setText(((Contactos) intento.getSerializableExtra("Contacto")).getNombre().toString());
        txtEditarTelefono.setText(((Contactos)intento.getSerializableExtra("Contacto")).getTelefono().toString());

        //instanciamos los botones
        Button btnGuardar=(Button)findViewById(R.id.btnGuardar);
        Button btnCancelar=(Button)findViewById(R.id.btnCancelar);

        btnCancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
             finish();
            }
        });


        btnGuardar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //cambiamos el nombre y telefono del contacto.
                contacto.setNombre(txtEditarNombre.getText().toString());
                contacto.setTelefono(Integer.parseInt(txtEditarTelefono.getText().toString()));

                Activity1.agenda.actualizarContacto(Integer.parseInt(intento.getSerializableExtra("Posicion").toString()),contacto);
                mensaje(R.string.GuardadoCorrectamente);

                setResult(RESULT_OK,intento); //le decimos al activity que nos llamó que to-do está OK
                finish(); //cerramos esta activity

            }
        });


        /******************************************************/
        /******             ZONA     EXAMEN           *********/
        /******************************************************/

        //instanciamos el botón borrar
        Button btnBorrar=(Button)findViewById(R.id.btnMostrarBorrar);

        btnBorrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intento2 = new Intent(Activity3.this,ActivityBorrar.class);
                Bundle bundle= new Bundle();

                bundle.putSerializable("Contacto",contacto);
                bundle.putSerializable("Posicion",Integer.parseInt(intento.getSerializableExtra("Posicion").toString()));

                intento2.putExtras(bundle);

                startActivityForResult(intento2,1);

            }
        });



    }

    public void mensaje(int msg)
    {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    //este método se ejecuta cuando el activity al que llamamos en el intent nos devuelve un ok
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity3, menu);
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
