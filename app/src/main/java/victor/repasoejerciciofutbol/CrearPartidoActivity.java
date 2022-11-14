package victor.repasoejerciciofutbol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import victor.repasoejerciciofutbol.databinding.ActivityCrearPartidoBinding;
import victor.repasoejerciciofutbol.modelos.Partido;

public class CrearPartidoActivity extends AppCompatActivity {
    private ActivityCrearPartidoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_partido);
        binding = ActivityCrearPartidoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCancelarPartidoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        binding.btnCrearPartidoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Partido partido = crearPartido();
                if(partido != null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PARTIDO", partido);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    Toast.makeText(CrearPartidoActivity.this, "Faltan datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Partido crearPartido() {
        if (binding.txtDescripcionEquipoActivity.getText().toString().isEmpty() ||
        binding.txtResultadoLocalEquipoActivity.getText().toString().isEmpty() ||
        binding.txtResultadoVisitanteEquipoActivity.getText().toString().isEmpty())
            return null;
        if (binding.spEquipo1CrearPartidoActivity.getSelectedItemPosition() == 0 ||
        binding.spEquipo2CrearPartidoActivity.getSelectedItemPosition() == 0)
            return null;

        String equipo1 = (String) binding.spEquipo1CrearPartidoActivity.getSelectedItem();
        String equipo2 = (String) binding.spEquipo2CrearPartidoActivity.getSelectedItem();
        int resulLocal = Integer.parseInt(binding.txtResultadoLocalEquipoActivity.getText().toString());
        int resulVisitante = Integer.parseInt(binding.txtResultadoVisitanteEquipoActivity.getText().toString());

        return new Partido(equipo1,equipo2,resulLocal, resulVisitante,binding.txtDescripcionEquipoActivity.getText().toString());
    }
}