package com.example.synchroapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import android.view.View;


public class RegisterActivity extends AppCompatActivity {

    // Inicializacion variables firebase
    FirebaseAuth fAuth;
    FirebaseFirestore db;

    TextInputEditText etUsuario, etNombre, etEmail, etPassword;
    Button btnRegistrar, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // inicio firebase
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        etUsuario = findViewById(R.id.etRegUsuario);
        etNombre = findViewById(R.id.etRegNombre);
        etEmail = findViewById(R.id.etRegEmail);
        etPassword = findViewById(R.id.etRegPassword);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnVolver = findViewById(R.id.btnVolver);

        //  boton volver al login
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // boton de registrar al usuario
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });
    }

    private void registrarUsuario() {
        String usuario = etUsuario.getText().toString();
        String nombre = etNombre.getText().toString();
        String email = etEmail.getText().toString();
        String pass = etPassword.getText().toString();


        if (usuario.isEmpty() || nombre.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Completá todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // primero creo el usuario en firebase auth
        fAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Guardo al neuvo usaurio en la base de datos
                        guardarEnFirestore(usuario, nombre, email);
                    } else {
                        // Aviso hubo algun error al registrar el ususario
                        etEmail.setError("No se pudo registrar");
                    }
                });
    }

    // guardo los datos del usuario en firestore
    private void guardarEnFirestore(String usuario, String nombre, String email) {

        Usuario nuevoUsuario = new Usuario(usuario, nombre, email);

        db.collection("usuarios").add(nuevoUsuario).addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Cuenta creada, ya podes iniciar sesion", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
                });
    }
}