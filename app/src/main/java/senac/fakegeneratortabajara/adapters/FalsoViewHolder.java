package senac.fakegeneratortabajara.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import senac.fakegeneratortabajara.R;

public class FalsoViewHolder extends RecyclerView.ViewHolder {

    final TextView tipo;
    final TextView nome;
    final ImageView foto;

    public FalsoViewHolder(@NonNull View itemView) {
        super(itemView);
        tipo = itemView.findViewById(R.id.txtTipo);
        nome = itemView.findViewById(R.id.txtNome);
        foto = itemView.findViewById(R.id.imgFoto);
    }
}
