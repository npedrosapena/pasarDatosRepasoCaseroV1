package com.controlarduino.nelson.pasardatospreexamen;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class Activity2 extends ListActivity //ESTA CLASE EXTIENDE DE LISTACTIVITY
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        Intent intento = getIntent(); //recogemos el intent recibido
        Agenda agenda = (Agenda)intento.getSerializableExtra("Agenda"); //lo guardamos en un objeto tipo agenda

        //magicamente se crea un adaptador tipo lista con los datos que le enviamos
        setListAdapter(new ArrayAdapter<Contactos>(this, android.R.layout.simple_list_item_1, agenda.arrayContactos()));

        Button btnCancelar=(Button)findViewById(R.id.btnVolver);

        btnCancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }

    //metodo que tenemos que usar para poder controlar el click sobre el elemento
    //del listadapter
    public void onListItemClick(ListView parent, View v, int position, long id)
    {
        Intent intento= new Intent(Activity2.this,Activity3.class);
        Bundle bundle= new Bundle();

        Contactos contacto= Activity1.agenda.buscarContacto(position); //recogemos la agenda del activity1, position nos lo dá automaticamente android (fijarse en los parametros que entran)

        bundle.putSerializable("Posicion",position);//pasamos la posicion del elemento en la lista que es la misma que el array
        bundle.putSerializable("Contacto",contacto); //guardamos nuestro contacto en la cajita

        intento.putExtras(bundle); //lo guardamos en el intent para pasar alnuevo activity


        startActivityForResult(intento, 2);
    }


    //este método se ejecuta cuando el activity al que llamamos en el intent nos devuelve un ok
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Agenda agenda=Activity1.agenda;

        //al usar la agenda del activity1 para guardar los contactos, estos
        //ya se encuentran listos para usar, tan simple como crear un objeto agenda
        //con la agenda del activity1 y acceder al método que devuelve un array de datos
        //con los datos de la  misma. luego, usamos el método mágico para recargar
        //los datos de los contactos

        setListAdapter(new ArrayAdapter<Contactos>(this,android.R.layout.simple_list_item_1,agenda.arrayContactos()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
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
