package unipiloto.edu.co.appiclaje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class registrarUsuario extends AppCompatActivity {
    Button regBtn;
    FirebaseDatabase database;
    DatabaseReference myRef;
    TextView regName, regEmail, regUsuario, regPassword, regTelefono, regDireccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        regName = findViewById(R.id.nombre);
        regEmail = findViewById(R.id.email_address);
        regUsuario = findViewById(R.id.user);
        regPassword = findViewById(R.id.passwordRegister);
        regTelefono = findViewById(R.id.number);
        regDireccion = findViewById(R.id.address);
        regBtn = findViewById(R.id.registrarButton);
        regBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name = regName.getText().toString();
                String email = regEmail.getText().toString();
                String nickname = regUsuario.getText().toString();
                String password = regPassword.getText().toString();
                String telefono = regTelefono.getText().toString();
                String direccion = regDireccion.getText().toString();
                Usuario usuario = new Usuario(name, email, password, telefono, direccion);
                System.out.println(usuario.toString());
                HashMap<String, Usuario> set = new HashMap<>();
                if (!set.containsKey(nickname)) {
                    set.put(nickname, usuario);
                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference("users");
                    myRef.child(nickname).setValue(usuario);

                }
            }
        });

    }

    public void volver(View view) {
        Intent intent = new Intent(this, IngresarAplicacion.class);
        startActivity(intent);
    }

}