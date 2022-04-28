package unipiloto.edu.co.appiclaje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetalleAceptar extends AppCompatActivity {
    private String id;
    private List<String> listaSolicitud;
    private  ListView listView;
    private String nickname="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_aceptar);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        nickname = intent.getStringExtra("nickname");
        listView = (ListView) findViewById(R.id.listaSolicitud);
        listaSolicitud = new ArrayList<>();
        detalleSolicitud();

    }

    public void detalleSolicitud() {
        Query query = FirebaseDatabase.getInstance().getReference("solicitudes").child(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String tipo =snapshot.child("tipo").getValue().toString();
                    String peso =snapshot.child("peso").getValue().toString();
                    String direccion =snapshot.child("address").getValue().toString();
                    String user =snapshot.child("nickname").getValue().toString();
                    listaSolicitud.add(0,"Tipo: "+tipo);
                    listaSolicitud.add(1,"Peso: " +peso);
                    listaSolicitud.add(2,"Dirección: "+direccion);
                    listaSolicitud.add(3,"Usuario: "+user);
                    ArrayAdapter<String> adapterSolicitud = new ArrayAdapter<>(
                            DetalleAceptar.this,
                            android.R.layout.simple_list_item_1,
                            listaSolicitud);
                    listView.setAdapter(adapterSolicitud);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void aceptarSolicitud(View view) {
        List<String> listaSolicitud= new ArrayList<>();
        String tipo =listaSolicitud.get(0).replace("Tipo: ","");
        String peso =listaSolicitud.get(1).replace("Peso: ","");
        String address =listaSolicitud.get(2).replace("Dirección: ","");
        String nick =listaSolicitud.get(3).replace("Usuario: ","");
        Solicitud solicitud = new Solicitud();
        solicitud.setAsignado(nickname);
        solicitud.setEstado("Aceptado");
        solicitud.setTipo(tipo);
        solicitud.setAddress(address);
        solicitud.setPeso(peso);
        solicitud.setNickname(nick);
        FirebaseDatabase.getInstance().getReference("solicitudes").child(id).setValue(solicitud);
        Intent intent = new Intent(this, Home_reciclador.class);
        startActivity(intent);
        finish();
        Toast.makeText(DetalleAceptar.this, "Solicitud aceptada", Toast.LENGTH_LONG).show();
    }
}
