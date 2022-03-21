package unipiloto.edu.co.appiclaje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Home_reciclador extends AppCompatActivity {
    private FirebaseAuth firbaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_reciclador);
        firbaseAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        TextView messageView = findViewById(R.id.userhome);
        String text = intent.getStringExtra("nickname");
        messageView.setText(text);

    }
    public void publicarSolicitud(View view) {
        Intent intent = new Intent(this, PublicarSolicitud.class);
        startActivity(intent);
    }

    public void logout(View view) {
        firbaseAuth.signOut();
        startActivity(new Intent(this, IngresarAplicacion.class));
        finish();
    }
}