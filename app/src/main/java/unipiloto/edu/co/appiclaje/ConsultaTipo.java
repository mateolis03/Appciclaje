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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ConsultaTipo extends AppCompatActivity {
    private String id;
    public FirebaseDatabase database;
    private TextView regId;
    private String nickname;
    private List<String> listTipo;
    private ListView listView;
    private Validation validation;
    private int cont =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_tipo);
        regId = findViewById(R.id.consulta_id);
        Intent intent = getIntent();
        nickname = intent.getStringExtra("nickname");
        listView = (ListView) findViewById(R.id.listTipo);
        listTipo = new ArrayList<>();
       AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ConsultaTipo.this, DetalleSolicitud.class);
                intent.putExtra("id", listTipo.get(i));
                startActivity(intent);
                finish();
            }
        };
        listView.setOnItemClickListener(itemClickListener);
    }
    public void validacion(String tipo){
        boolean cont =true;
        validation = new Validation();
        if (validation.isEmpty(tipo)) {
            Toast.makeText(this, "No deje este campo vacío", Toast.LENGTH_LONG).show();
            cont = false;
        }
        if (cont) {
            getSolicitudes();
        }
    }
    public void consultar(View view) {
        if (cont == 0) {
            cont++;
            id = regId.getText().toString();
            validacion(id);
        } else{
            Toast.makeText(ConsultaTipo.this, "Ya se listaron sus solicitudes", Toast.LENGTH_LONG).show();
        }
    }
    public void getSolicitudes(){
        Query query = FirebaseDatabase.getInstance().getReference("solicitudes").orderByChild("nickname").equalTo(nickname);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    List<String> values=new ArrayList<>();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        if (!dataSnapshot.getKey().isEmpty()) {
                            String tipo =dataSnapshot.child("tipo").getValue().toString();
                            if(tipo.contains(id)){
                                listTipo.add(dataSnapshot.getKey());
                            }
                        }
                    }
                }
                ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                        ConsultaTipo.this,
                        android.R.layout.simple_list_item_1,
                        listTipo);
                listView.setAdapter(listAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    }

