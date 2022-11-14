package victor.repasoejerciciofutbol;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import victor.repasoejerciciofutbol.Adapter.PartidoAdapter;
import victor.repasoejerciciofutbol.databinding.ActivityMainBinding;
import victor.repasoejerciciofutbol.modelos.Partido;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> AddPartidoLauncher;
    private ArrayList<Partido> listaPartidos;
    private PartidoAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        listaPartidos = new ArrayList<>();
        adapter = new PartidoAdapter(listaPartidos, R.layout.partido_view_holder, MainActivity.this);
        binding.contentMain.contenedor.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        binding.contentMain.contenedor.setLayoutManager(layoutManager);
        inicializaLauncher();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPartidoLauncher.launch(new Intent(MainActivity.this, CrearPartidoActivity.class));
            }
        });
    }
    private void inicializaLauncher(){
        AddPartidoLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            if(result.getData() != null){
                                if (result.getData().getExtras() != null){
                                    Partido partido = (Partido) result.getData().getExtras().getSerializable("PARTIDO");//CREAMOS UNA CLASE PARA LOS TAGS DE LOS BUNDLES
                                    if(partido != null){
                                        listaPartidos.add(partido);
                                        adapter.notifyDataSetChanged();
                                    }else{
                                        Toast.makeText(MainActivity.this, "no hay partido en el bundle", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(MainActivity.this, "no hay bundle en el intent", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(MainActivity.this, "no tiene intent", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "VENTANA CANCELADA", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}