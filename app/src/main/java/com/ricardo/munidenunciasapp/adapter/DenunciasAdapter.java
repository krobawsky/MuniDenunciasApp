package com.ricardo.munidenunciasapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ricardo.munidenunciasapp.R;
import com.ricardo.munidenunciasapp.models.Denuncia;

import java.util.ArrayList;
import java.util.List;

public class DenunciasAdapter {

    private List<Denuncia> productos;

    public DenunciasAdapter(){
        this.productos = new ArrayList<>();
    }

    public void setDenuncias(List<Denuncia> denuncias){
        this.productos = denuncias;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView fotoImage;
        public TextView tituloText;
        public TextView nombreText;
        public TextView ubicacionText;

        public ViewHolder(View itemView) {
            super(itemView);

        }
    }
    /*
    @Override
    public ProductosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductosAdapter.ViewHolder viewHolder, int position) {

        Producto producto = this.productos.get(position);

        viewHolder.nombreText.setText(producto.getNombre());
        viewHolder.precioText.setText("S/. " + producto.getPrecio());

        String url = ApiService.API_BASE_URL + "/images/" + producto.getImagen();
        Picasso.with(viewHolder.itemView.getContext()).load(url).into(viewHolder.fotoImage);

    }

    @Override
    public int getItemCount() {
        return this.productos.size();
    }*/

}
