package unipiloto.edu.co.appiclaje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ConsultarSolicitud extends AppCompatActivity {
    private String id;
    public FirebaseDatabase database;
    private DatabaseReference myRef;
    private TextView regId;
    private ListView regList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_solicitud);
        regId = findViewById(R.id.consulta_id);
        regList = findViewById(R.id.listView);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("solicitudes");
    }


    public void consultar(View view) {
        id = regId.getText().toString();
        ArrayList<String>list=new ArrayList<>();
        ArrayAdapter adapter= new ArrayAdapter<String>(this,R.layout.list_item,list);
        regList.setAdapter(adapter);
        DatabaseReference idDb = myRef.child(id);
        idDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot cs : snapshot.getChildren()) {
                    list.add(cs.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}