package com.example.synchroapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        TextView textoInicio = findViewById(R.id.usuario);
        String usuario = getIntent().getStringExtra("Extra_User");
        textoInicio.setText(usuario);

    }
}