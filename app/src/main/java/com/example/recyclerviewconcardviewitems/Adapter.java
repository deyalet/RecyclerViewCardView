package com.example.recyclerviewconcardviewitems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Revistas> revistas;
    private Context contexto;

    public Adapter(Context ctx, List<Revistas> revistas){
        this.inflater = LayoutInflater.from(ctx);
        this.revistas = revistas;
        this.contexto = ctx;
    }

    public void setRevista(List<Revistas> R){
        revistas = R;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.publicaciones_list_layout, parent,false);
        return new Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Adapter.ViewHolder holder, final int position) {
        holder.bData(revistas.get(position));
         Picasso.get().load(revistas.get(position).getCover()).into(holder.revistaCover);
    }

    @Override
    public int getItemCount() {
        return revistas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView revistaIsseu, revistaVolumen, revistaNumero, revistaAnio, revistaFecha, revistaTitulo, revistaDoi;
        ImageView revistaCover;

        public ViewHolder(View itemView){
            super(itemView);

          //  revistaIsseu = itemView.findViewById(R.id.txtIsseu);
         //   revistaVolumen = itemView.findViewById(R.id.txtVolumen);
          //  revistaNumero = itemView.findViewById(R.id.txtNumero);
         //   revistaAnio = itemView.findViewById(R.id.txtAnio);
            //revistaFecha = itemView.findViewById(R.id.txtFecha);
            revistaTitulo = itemView.findViewById(R.id.txtTitulo);
            revistaDoi = itemView.findViewById(R.id.txtDoi);
            revistaCover = itemView.findViewById(R.id.imagen);
        }
        void bData(final Revistas revist){
            revistaTitulo.setText(revist.getTitulo());
           // revistaIsseu.setText(revist.getIsseu_id());
           // revistaVolumen.setText(revist.getVolumen());
          //  revistaNumero.setText(revist.getNumero());
            //revistaFecha.setText(revist.getFecha());
           // revistaAnio.setText(revist.getAnio());
            revistaDoi.setText(revist.getDoi());

        }
    }

}
