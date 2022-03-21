package unipiloto.edu.co.appiclaje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PublicarSolicitud extends AppCompatActivity {
    private Button regBtn;
    private DatabaseReference database;
    private DatabaseReference myRef;
    private TextView regOrigen, regDestino;
    private Validation validation;
    public FirebaseAuth firebaseAuth;
    private String origen = "";
    private String destino = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_solicitud);
        regOrigen = findViewById(R.id.origen);
        regDestino = findViewById(R.id.destino);
    }


    public void publicar(View view) {
        validation = new Validation();
        boolean cont = true;
        database = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        origen = regOrigen.getText().toString();
        destino = regDestino.getText().toString();
        if (validation.isEmpty(origen) || validation.isNumeric(origen)) {
            Toast.makeText(this, "Ingrese un nombre valido", Toast.LENGTH_LONG).show();
            cont = false;
        }
        if (validation.isEmpty(destino) || validation.isNumeric(destino)) {
            Toast.makeText(this, "Ingrese un nombre valido", Toast.LENGTH_LONG).show();
            cont = false;
        }
        if (cont) {
            publicarSolicitud();
        }
    }

    public void publicarSolicitud() {
        Map<String, Object> map = new HashMap<>();
        map.put("origen", origen);
        map.put("destino", destino);
        database.child("solicitudes").setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(PublicarSolicitud.this, "Solicitud publicada", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}