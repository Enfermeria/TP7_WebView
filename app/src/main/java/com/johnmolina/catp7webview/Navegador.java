package com.johnmolina.catp7webview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.widget.TextView;

public class Navegador extends AppCompatActivity {
    WebView wvWeb;
    TextView tvDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegador);

        tvDireccion = (TextView)findViewById(R.id.tv_direccion);

        Intent intent = getIntent();
        String urlPasado = intent.getDataString();
        wvWeb = (WebView)findViewById(R.id.wv_web);
        /*wvWeb.setWebViewClient(new WebViewClient(){
            @Override  // para que al iniciar una pagina copie la direccion al svBusqueda
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                svBusqueda.setQuery(url, false); //pongo el query pero no inicio busqueda
            } // onPageStarted
        } ); //setWebViewClient*/
        wvWeb.getSettings().setJavaScriptEnabled(true); // opcional para habilitar javascript
        tvDireccion.setText(urlPasado);
        wvWeb.loadUrl(urlPasado);
    } //onCreate

    public void volver(View view) {
        finish();
    } //volver

    @Override //define lo que hace cuando se va hacia atras
    public void onBackPressed() {
        if ( wvWeb.canGoBack() )  //si la web puede retroceder
            wvWeb.goBack();       // retrocede
        else
            super.onBackPressed(); // sino sale (opcion por defecto)
    } // onBackPressed

    /*private void cargarUrl(String s){
        if (!s.isEmpty()) { //si el string ingresado en svBusqueda no es vacio
            if (URLUtil.isValidUrl(s))  // y es una URL valiaa
                wvWeb.loadUrl(s);       // cargo esa URL
            else if (URLUtil.isValidUrl("https://" + s))
                wvWeb.loadUrl("https://" + s);
            else if (URLUtil.isValidUrl("https://www." + s))
                wvWeb.loadUrl("https://www." + s);
            else if (URLUtil.isValidUrl("http://" + s))
                wvWeb.loadUrl("http://" + s);
            else if (URLUtil.isValidUrl("http://www." + s))
                wvWeb.loadUrl("http://www." + s);
            else
                wvWeb.loadUrl(MainActivity.GOOGLE + MainActivity.SEARCH + s); //sino hago que google busque esa frase
        } //if !isEmpty
    } //cargarUrl*/
} // class Navegadpr