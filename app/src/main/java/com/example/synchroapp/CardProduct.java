package com.example.synchroapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class CardProduct extends AppCompatActivity {

    private TextView cartBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_card_product);

        // Info para card del producto
        ImageView imageView = findViewById(R.id.imageView7);
        TextView RelMarca = findViewById(R.id.marca);
        TextView RelDetalle = findViewById(R.id.detalle);
        TextView RelPrecio = findViewById(R.id.precio);

        Intent intent = getIntent();
        String marca = intent.getStringExtra("marca");
        String detalle = intent.getStringExtra("detalle");
        String precio = intent.getStringExtra("precio");
        String imagenUrl = intent.getStringExtra("imagen");

        RelMarca.setText(marca);
        RelDetalle.setText(detalle);
        RelPrecio.setText(precio);

        Glide.with(this).load(imagenUrl).into(imageView);

        // Boton Volver
        Button btnVolver = findViewById(R.id.BtnVolver);
        btnVolver.setOnClickListener(v -> {
            finish();
        });

        // Boton Comprar
        Button btnComprar = findViewById(R.id.comprar);

        btnComprar.setOnClickListener(v -> {
            CartManager.addItem();
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast_custom,
                    findViewById(R.id.toast_root));

            TextView text = layout.findViewById(R.id.toast_text);
            text.setText("Producto agregado al carrito");

            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 150);
            toast.show();
            v.postDelayed(this::finish, 500);
        });
        cartBadge = findViewById(R.id.cart_badge);
    }

    @Override
    protected void onResume() {
        super.onResume();

        int count = CartManager.getCount();
        if (count > 0) {
            cartBadge.setVisibility(View.VISIBLE);
            cartBadge.setText(String.valueOf(count));
        } else {
            cartBadge.setVisibility(View.GONE);
        }
    }
}