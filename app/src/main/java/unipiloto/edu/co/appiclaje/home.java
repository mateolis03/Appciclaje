package unipiloto.edu.co.appiclaje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {
    private   FirebaseAuth firbaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firbaseAuth= FirebaseAuth.getInstance();
        Intent intent = getIntent();
        TextView messageView= findViewById(R.id.userhome);
        String text = intent.getStringExtra("nickname");
        messageView.setText(text);

    }

    public void logout(View view){
        firbaseAuth.signOut();
        startActivity(new Intent(this, IngresarAplicacion.class));
        finish();
    }
}