package com.example.synchroapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private TextView cartBadge;
    private RecyclerView recyclerProductos;
    private ProductoAdapter adapter;
    private List<Producto> listaProductos;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        // Mostrar el nombre del usuario (viene del login por Intent)
        TextView textoInicio = findViewById(R.id.usuario);
        String usuario = getIntent().getStringExtra("Extra_User");
        textoInicio.setText(usuario);

        cartBadge = findViewById(R.id.cart_badge);

        // Configurar el RecyclerView (lista vertical)
        recyclerProductos = findViewById(R.id.recycler_productos);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));
        listaProductos = new ArrayList<>();
        adapter = new ProductoAdapter(this, listaProductos);
        recyclerProductos.setAdapter(adapter);

        // Traer los productos desde Firestore
        db = FirebaseFirestore.getInstance();
        cargarProductos();
    }

    private void cargarProductos() {
        db.collection("productos").get()
                .addOnSuccessListener(querySnapshot -> {
                    listaProductos.clear();
                    for (QueryDocumentSnapshot doc : querySnapshot) {
                        Producto p = doc.toObject(Producto.class);
                        listaProductos.add(p);
                    }
                    adapter.notifyDataSetChanged();
                });
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