package com.example.synchroapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    //Manejo de autenticacion
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private int dp(int value) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Inicializacion de la autenticacion
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Contenedor LinearLayout horizontal
        LinearLayout form = findViewById(R.id.linearLayout3);
        LinearLayout fila = new LinearLayout(this);
        fila.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams lpFila = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, dp(50));
        lpFila.topMargin = dp(25);
        fila.setLayoutParams(lpFila);

        // CheckBox Recordar Usuario
        MaterialCheckBox chkRemember = new MaterialCheckBox(this);
        chkRemember.setId(R.id.chkRemember);
        chkRemember.setText(R.string.recordar);
        chkRemember.setTextColor(ContextCompat.getColor(this, R.color.white));
        chkRemember.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        chkRemember.setUseMaterialThemeColors(false);
        chkRemember.setButtonTintList(ColorStateList.valueOf(
                ContextCompat.getColor(this, R.color.white)));

        LinearLayout.LayoutParams lpChk =
                new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        chkRemember.setLayoutParams(lpChk);

        // TextView "Olvidar contraseña"
        TextView olvidarPass = new TextView(this);
        olvidarPass.setId(R.id.olvidar);
        olvidarPass.setText(getString(R.string.olvidar_contra));
        olvidarPass.setTextColor(ContextCompat.getColor(this, R.color.white));
        olvidarPass.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        olvidarPass.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);


        // Agregar al layout
        fila.addView(chkRemember);
        fila.addView(olvidarPass);
        form.addView(fila);




        // Login
        Button btnLogin = findViewById(R.id.btnLogin);
        EditText etUsuario = findViewById(R.id.etUsuario);
        EditText etContrasena = findViewById(R.id.etContrasena);

        btnLogin.setOnClickListener (v-> {
            String usuario = etUsuario.getText().toString().trim();
            String pass = etContrasena.getText().toString();

            if (usuario.isEmpty() || pass.isEmpty()) {
                if (usuario.isEmpty()) etUsuario.setError("Ingresá tu usuario");
                if (pass.isEmpty()) etContrasena.setError("Ingresá tu contraseña");
                return;
            }

            // Busco en Firestore el documento con el campo "usuario" que coincida
            db.collection("usuarios").whereEqualTo("usuario", usuario).get()
                    .addOnSuccessListener(querySnapshot -> {
                        if (querySnapshot.isEmpty()) {
                            etUsuario.setError("El usuario no existe");
                            return;
                        }
                        // Tomo el documento que coincide ('usuario)
                        DocumentSnapshot doc = querySnapshot.getDocuments().get(0);
                        String email = doc.getString("email");
                        String nombre = doc.getString("nombre");

                        // Con el email encontrado, hago login normal de Firebase
                        mAuth.signInWithEmailAndPassword(email, pass)
                                .addOnCompleteListener(MainActivity.this, task -> {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                                        intent.putExtra("Extra_User", nombre);
                                        startActivity(intent);
                                        Log.i("Login", "OK. Usuario: " + usuario);
                                    } else {
                                        etContrasena.setError("Contraseña incorrecta");
                                    }
                                });
                    })
                    .addOnFailureListener(e -> {
                        etUsuario.setError("Error al conectar con la base");
                        Log.e("Login", "Firestore error", e);
                    });
        });

        Button btnCrearCuenta = findViewById(R.id.btnCrearCuenta);
        btnCrearCuenta.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

    }
}