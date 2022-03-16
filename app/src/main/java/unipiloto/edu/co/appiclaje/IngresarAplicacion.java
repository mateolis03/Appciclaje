package unipiloto.edu.co.appiclaje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class IngresarAplicacion extends AppCompatActivity {
    Button boton;
    FirebaseDatabase database;
    DatabaseReference myRef;
    TextView regUsuario, regPassword;
    String dbuser, dbpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_aplicacion);

    }

    public void registrarUsuario(View view) {
        Intent intent = new Intent(this, registrarUsuario.class);
        startActivity(intent);
    }

    public void home(View view) {
        regUsuario = findViewById(R.id.user);
        regPassword = findViewById(R.id.password);
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    dbuser = String.valueOf(task.getResult().getValue());
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });
        String nickname = regUsuario.getText().toString();
        String password = regPassword.getText().toString();

    }
}