package com.johnmolina.catp7webview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {
    SearchView svBusqueda;
    protected static final String GOOGLE = "https://www.google.com";
    protected static final String SEARCH = "/search?q=";  // lo que usa google para buscar un string
    // protected static final String MENSAJE_EXTRA = "com.johnmolina.catp7webview.extra.MENSAJE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        svBusqueda = (SearchView)findViewById(R.id.sv_busqueda);
        svBusqueda.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) { //que hace si pulsa enter o el icono de buscar
                String urlApasar;
                if (!s.isEmpty()) { //si el string ingresado en svBusqueda no es vacio
                    if (URLUtil.isValidUrl(s))  // y es una URL valiaa
                        urlApasar = s;       // cargo esa URL
                    else
                        urlApasar = GOOGLE + SEARCH + s; // urlApasar =  URLUtil.guessUrl(s);

                    /*else if (URLUtil.isValidUrl("https://"+s))
                        urlApasar = "https://"+s;
                    else if (URLUtil.isValidUrl("https://www."+s))
                        urlApasar = "https://www."+s;
                    else if (URLUtil.isValidUrl("http://"+s))
                        urlApasar = "http://"+s;
                    else if (URLUtil.isValidUrl("http://www."+s))
                        urlApasar = "http://www."+s;
                    else
                        urlApasar = GOOGLE + SEARCH + s; //sino hago que google busque esa frase
                    */
                    lanzar2aActividad(urlApasar);
                    return true;
                }
                return false; //si era vacia 
            } //onQueryTextSubmit

            @Override
            public boolean onQueryTextChange(String s) { //que hace si modifica el texto. Por ahora no hacemos nada
                return false;
            }
        }); //setOnQueryTextListener
    } //onCreate

    public void lanzar2aActividad(String urlAPasar) {
        Intent intent = new Intent(this, Navegador.class);
        intent.setData(Uri.parse(urlAPasar));
        startActivity(intent);

        /*Intent intent = new Intent(this, Navegador.class);
        intent.putExtra(MENSAJE_EXTRA, svBusqueda.getQuery().toString());
        startActivity(intent);
        // startActivityForResult(intent, SOLICITUD_RESPUESTA);*/
    } // lanzar2aActividad
} //class MainActivity
