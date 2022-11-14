package victor.repasoejerciciofutbol.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import victor.repasoejerciciofutbol.R;
import victor.repasoejerciciofutbol.ResumenPartidoActivity;
import victor.repasoejerciciofutbol.modelos.Partido;

public class PartidoAdapter extends RecyclerView.Adapter<PartidoAdapter.PartidoVH> {

    private List<Partido> objects;
    private int linea;
    private Context context;

    public PartidoAdapter(List<Partido> objects, int linea, Context context) {
        this.objects = objects;
        this.linea = linea;
        this.context = context;
    }

    @NonNull
    @Override
    public PartidoAdapter.PartidoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //se encarga de instanciar o inflar tantos elementos como quepan en la pantalla
        View partidoView = LayoutInflater.from(context).inflate(linea,null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        partidoView.setLayoutParams(lp);
        return new PartidoVH(partidoView);
    }

    @Override
    public void onBindViewHolder(@NonNull PartidoAdapter.PartidoVH holder, int position) {
        //es llamado por el adapter para modificar el contenido de un viewholder ya creado
        Partido partido = objects.get(position); //numero de posicion que recibe
        holder.lbllocal.setText(partido.getEquipoLocal());
        holder.lblVisitante.setText(partido.getEquipoVisitante());
        holder.lblResultado.setText(String.valueOf(partido.getGolesEquipoLocal()+" - "+partido.getGolesEquipoVisitante()));

        holder.btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoAlert(partido).show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ResumenPartidoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("PARTIDO",partido);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class PartidoVH extends RecyclerView.ViewHolder{
        TextView lbllocal, lblVisitante, lblResultado;
        ImageButton btnInfo;

        public PartidoVH(@NonNull View itemView) {
            super(itemView);
            lbllocal = itemView.findViewById(R.id.lblEquipoLocalViewHolder);
            lblVisitante = itemView.findViewById(R.id.lblEquipoVisitanteViewHolder);
            lblResultado = itemView.findViewById(R.id.lblResultadoViewHolder);
            btnInfo = itemView.findViewById(R.id.btnInfoViewHolder);
        }
    }
    public AlertDialog infoAlert(Partido partido){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setTitle("El ganador es: ");
        builder.setCancelable(false);

        View partidoDialogModel = LayoutInflater.from(context).inflate(R.layout.partido_dialog_model, null);
        TextView lblGanador = partidoDialogModel.findViewById(R.id.lblGanadorPartidoDialogModel);

        int local = partido.getGolesEquipoLocal();
        int visit = partido.getGolesEquipoVisitante();

        if(local > visit){
            lblGanador.setText(partido.getEquipoLocal());
        }else if (visit > local){
            lblGanador.setText(partido.getEquipoVisitante());
        }else if(local == visit){
            lblGanador.setText("Empate");
        }
        builder.setView(partidoDialogModel);
        builder.setNegativeButton("SALIR", null);

        return builder.create();
    }
}
