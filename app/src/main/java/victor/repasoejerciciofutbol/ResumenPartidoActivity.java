package victor.repasoejerciciofutbol;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import victor.repasoejerciciofutbol.databinding.ActivityCrearPartidoBinding;
import victor.repasoejerciciofutbol.databinding.ActivityResumenPartidoBinding;
import victor.repasoejerciciofutbol.modelos.Partido;

public class ResumenPartidoActivity extends AppCompatActivity {

    private ActivityResumenPartidoBinding binding;
    private TextView lbllocal;
    private TextView lblvisitante;
    private TextView lblresultado;
    private TextView lbldescripcion;
    private EditText txtModRes1;
    private EditText txtModRes2;
    private Button btnMod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_partido);
        binding = ActivityResumenPartidoBinding.inflate(getLayoutInflater());

        Intent intent = getIntent();
        inicializa();
        Partido partido = (Partido) intent.getExtras().getSerializable("PARTIDO");
        lbllocal.setText(partido.getEquipoLocal());
        lblvisitante.setText(partido.getEquipoVisitante());
        lblresultado.setText(String.valueOf(partido.getGolesEquipoLocal()+" - "+partido.getGolesEquipoVisitante()));
        lbldescripcion.setText(partido.getDescripcion());

        //MODIFICAR
        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtModRes1.getText().toString().isEmpty() && !txtModRes2.getText().toString().isEmpty()) {
                    Partido partido1 = new Partido();
                    partido1.setGolesEquipoLocal(Integer.parseInt(txtModRes1.getText().toString()));
                    partido1.setGolesEquipoVisitante(Integer.parseInt(txtModRes2.getText().toString()));
                }
            }
        });


        //inicializarVistas(partido);

    }
    private void inicializa() {
        lbllocal = findViewById(R.id.lbllEquipoLocalResumenActivity);
        lblvisitante = findViewById(R.id.lblEquipoVisitanteResumenActivity);
        lblresultado = findViewById(R.id.lblResulResumenActivity);
        lbldescripcion = findViewById(R.id.lblresumenResumenActivity);
        txtModRes1 = findViewById(R.id.txtModificarResult1ResumenActivity);
        txtModRes2 = findViewById(R.id.txtModificarResult2ResumenActivity);
        btnMod = findViewById(R.id.btnMod);
    }

    private void inicializarVistas(Partido partido) {
        binding.lbllEquipoLocalResumenActivity.setText(partido.getEquipoLocal());
        binding.lblEquipoVisitanteResumenActivity.setText(partido.getEquipoVisitante());
        binding.lblResulResumenActivity.setText(String.valueOf(partido.getGolesEquipoLocal()+" - "+partido.getGolesEquipoVisitante()));
        binding.lblresumenResumenActivity.setText(partido.getDescripcion());
    }

}