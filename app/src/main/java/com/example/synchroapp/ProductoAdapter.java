package com.example.synchroapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private final Context context;
    private final List<Producto> productos;

    public ProductoAdapter(Context context, List<Producto> productos) {
        this.context = context;
        this.productos = productos;
    }

    // 1) Crea la vista de una tarjeta (infla item_producto.xml)
    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(vista);
    }

    // 2) Llena una tarjeta con los datos del producto de esa posición
    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto p = productos.get(position);

        holder.txtMarca.setText(p.getMarca());
        holder.txtPrecio.setText(p.getPrecio());

        // La imagen viene como nombre ("img_reloj_1"); lo convertimos en recurso drawable
        int idImagen = context.getResources().getIdentifier(
                p.getImagen(), "drawable", context.getPackageName());
        holder.imgProducto.setImageResource(idImagen);

        // Al tocar la tarjeta, abrimos el detalle pasando los datos del producto
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CardProduct.class);
            intent.putExtra("marca", p.getMarca());
            intent.putExtra("detalle", p.getDetalle());
            intent.putExtra("precio", p.getPrecio());
            intent.putExtra("imagen", idImagen);
            context.startActivity(intent);
        });
    }

    // 3) Cuántos elementos tiene la lista
    @Override
    public int getItemCount() {
        return productos.size();
    }

    // ViewHolder: guarda las referencias a las vistas de una tarjeta
    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProducto;
        TextView txtMarca;
        TextView txtPrecio;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProducto = itemView.findViewById(R.id.img_producto);
            txtMarca = itemView.findViewById(R.id.txt_marca);
            txtPrecio = itemView.findViewById(R.id.txt_precio);
        }
    }
}