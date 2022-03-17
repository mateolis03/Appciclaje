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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;
import java.util.Map;


public class registration extends AppCompatActivity {
    private Button regBtn;
    private DatabaseReference database;
    private DatabaseReference myRef;
    private TextView regName, regEmail, regUser, regPassword, regTelephone, regAddress;
    private Validation validation;
    public  FirebaseAuth firebaseAuth;
    private String name = "";
    private String email ="";
    private String nickname ="";
    private String password = "";
    private String telephone = "";
    private String address = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        regName = findViewById(R.id.nombre);
        regEmail = findViewById(R.id.email_address);
        regUser = findViewById(R.id.user);
        regPassword = findViewById(R.id.password);
        regTelephone = findViewById(R.id.number);
        regAddress = findViewById(R.id.address);
        regBtn = findViewById(R.id.registrarButton);
    }
    public void register(View view) {
        validation = new Validation();
        boolean cont = true;
        database = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        name = regName.getText().toString();
        email = regEmail.getText().toString();
        nickname = regUser.getText().toString();
        password = regPassword.getText().toString();
        telephone = regTelephone.getText().toString();
        address = regAddress.getText().toString();
       // Usuario usuario = new Usuario(name, email, password, telephone, address);
        if (validation.isEmpty(name) || validation.isNumeric(name)) {
            Toast.makeText(this, "Ingrese un nombre valido", Toast.LENGTH_LONG).show();
            cont = false;
        }
        if (validation.isEmpty(email) || !validation.isEmail(email)) {
            Toast.makeText(this, "Ingrese un correo valido", Toast.LENGTH_LONG).show();
            cont = false;
        }
        if (validation.isEmpty(nickname)) {
            Toast.makeText(this, "Ingrese una usuario", Toast.LENGTH_LONG).show();
            cont = false;
        }
        if (validation.containSpace(nickname)){
            Toast.makeText(this, "El usuario no puede tener espacios", Toast.LENGTH_LONG).show();
            cont = false;
        }
        if (validation.isEmpty(password)) {
            Toast.makeText(this, "Ingrese una contraseña", Toast.LENGTH_LONG).show();
            cont = false;
        }
        if (validation.isTeleTelephone(telephone)) {
            Toast.makeText(this, "Ingrese una número de telefono valido", Toast.LENGTH_LONG).show();
            cont = false;
        }
        if (validation.isEmpty(address)) {
            Toast.makeText(this, "Ingrese una dirección valida", Toast.LENGTH_LONG).show();
            cont = false;
        }
        if (cont) {
            registerUser(email,password);
        }
    }
    public void registerUser(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name",name);
                    map.put("email",email);
                    map.put("nickname",nickname);
                    map.put("telephone",telephone);
                    map.put("address",address);

                    String id = firebaseAuth.getCurrentUser().getUid();
                    database.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                                if (task2.isSuccessful()){
                                    Toast.makeText(registration.this, "Usuario registrado", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(registration.this,IngresarAplicacion.class));
                                    finish();
                                }

                        }
                    });
                }else
                    Toast.makeText(registration.this, "Este correo ya está registrados", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void back(View view) {
        Intent intent = new Intent(this, IngresarAplicacion.class);
        startActivity(intent);
        finish();
    }

}