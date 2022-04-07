package unipiloto.edu.co.appiclaje;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConsultarSolicitud extends AppCompatActivity {
    public DatabaseReference database;
    private DatabaseReference myRef;
    private TextView regId;
    private ListView regList;
    private ListView listView;
    private FirebaseAuth firbaseAuth;
    private String nickname;
    private List<String> listaId;
    private List<String> listaSolicitud;
    public int cont =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_solicitud);
        firbaseAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        nickname = intent.getStringExtra("nickname");
        listView = (ListView) findViewById(R.id.listId);
        listaId = new ArrayList<>();
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id =listaId.get(i);
                Query query = FirebaseDatabase.getInstance().getReference("solicitudes").child(id);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {
                           String tipo =snapshot.child("tipo").getValue().toString();
                           String peso =snapshot.child("peso").getValue().toString();
                           String direccion =snapshot.child("address").getValue().toString();
                            listaSolicitud.add(0,tipo);
                            listaSolicitud.add(0,peso);
                            listaSolicitud.add(2,direccion);
                            ArrayAdapter<String> adapterSolicitud = new ArrayAdapter<>(
                                    ConsultarSolicitud.this,
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
        };
        listView.setOnItemClickListener(itemClickListener);
    }





    public void consultar(View view) {
        if (cont==0){
        cont++;

        Query query = FirebaseDatabase.getInstance().getReference("solicitudes").orderByChild("nickname").equalTo(nickname);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        if (!dataSnapshot.getKey().isEmpty()) {
                            listaId.add(dataSnapshot.getKey());

                        }
                    }
                }
                ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                        ConsultarSolicitud.this,
                        android.R.layout.simple_list_item_1,
                        listaId);
                listView.setAdapter(listAdapter);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }else{
            Toast.makeText(ConsultarSolicitud.this, "Ya se listaron sus solicitudes", Toast.LENGTH_LONG).show();
        }
    }
}