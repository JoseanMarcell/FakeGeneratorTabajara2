package senac.fakegeneratortabajara.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URISyntaxException;
import java.util.List;

import senac.fakegeneratortabajara.R;
import senac.fakegeneratortabajara.models.Falso;

public class AdapterFalso extends RecyclerView.Adapter {

    private List<Falso> falsos;
    private Context context;

    public AdapterFalso(List<Falso> falsos, Context context) {
        this.falsos = falsos;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_falso, parent, false);

        FalsoViewHolder holder = new FalsoViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FalsoViewHolder viewHolder = (FalsoViewHolder) holder;

        Falso falso = falsos.get(position);

        viewHolder.tipo.setText(falso.getTipo());
        viewHolder.nome.setText(falso.getNome());
        viewHolder.foto.setImageResource(falso.getFoto());
    }

    @Override
    public int getItemCount() {
        return falsos.size();
    }
}
