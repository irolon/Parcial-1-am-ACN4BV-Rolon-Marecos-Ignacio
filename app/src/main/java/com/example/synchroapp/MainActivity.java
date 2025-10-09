package com.example.synchroapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnLogin = findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            EditText etUsuario = findViewById(R.id.etUsuario);
            EditText etContrasena = findViewById(R.id.etContrasena);
            @Override
            public void onClick(View v) {
                String user = etUsuario.getText().toString();
                String pass = etContrasena.getText().toString();

                if(!user.isEmpty() && !pass.isEmpty()){
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("Extra_User", user);
                    startActivity(intent);
                    Log.i("Btn Inicio Sesion", "Usuario: " + user + " Password: " + pass );
                }
            }
        });

    }
}