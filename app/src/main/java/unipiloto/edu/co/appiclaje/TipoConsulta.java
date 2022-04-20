package unipiloto.edu.co.appiclaje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class TipoConsulta extends AppCompatActivity {
    private FirebaseAuth firbaseAuth;
    public String nickname="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_consulta);
        firbaseAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        String id = firbaseAuth.getCurrentUser().getUid();
        nickname = intent.getStringExtra("nickname");
    }

    public void consultaID(View view) {
        Intent intent = new Intent(this, ConsultarId.class);
        intent.putExtra("nickname",nickname);
        startActivity(intent);
    }
    public void consultaTipo(View view) {
        Intent intent = new Intent(this, ConsultaTipo.class);
        intent.putExtra("nickname",nickname);
        startActivity(intent);
    }
}