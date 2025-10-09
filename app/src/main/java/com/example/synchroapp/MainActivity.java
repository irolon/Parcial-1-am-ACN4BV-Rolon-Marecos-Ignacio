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
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.google.android.material.checkbox.MaterialCheckBox;

public class MainActivity extends AppCompatActivity {
    //
    private int dp(int value) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

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