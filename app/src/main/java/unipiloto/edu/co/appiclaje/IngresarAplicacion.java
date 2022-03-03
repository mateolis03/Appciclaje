package unipiloto.edu.co.appiclaje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IngresarAplicacion extends AppCompatActivity {
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_aplicacion);
    }

    public void registrarUsuario(View view) {
        Intent intent = new Intent(this, registrarUsuario.class);
        startActivity(intent);
    }
}