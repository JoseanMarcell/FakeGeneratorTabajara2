package senac.fakegeneratortabajara;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import senac.fakegeneratortabajara.adapters.AdapterFalso;
import senac.fakegeneratortabajara.models.Gerador;

public class MainActivity extends AppCompatActivity {

    Spinner spTipo;
    EditText txtQuantidade;
    RecyclerView listaFakes;
    public static Gerador gerador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spTipo = findViewById(R.id.spFakes);
        txtQuantidade = findViewById(R.id.txtQuantidade);
        listaFakes = findViewById(R.id.ListaFakes);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String tipo = spTipo.getSelectedItem().toString();
                    int quantidade = Integer.parseInt(txtQuantidade.getText().toString());

                    gerador = new Gerador(tipo, quantidade);

                    listaFakes.setAdapter(new AdapterFalso(gerador.gerarFakes(), getBaseContext()));

                    RecyclerView.LayoutManager layout = new LinearLayoutManager(getBaseContext(),
                            RecyclerView.VERTICAL, false);

                    listaFakes.addItemDecoration(
                            new DividerItemDecoration(getBaseContext(), DividerItemDecoration.VERTICAL));

                    listaFakes.setLayoutManager(layout);
                } catch (Exception ex) {
                    Log.e("OnClick", ex.getMessage());
                    Snackbar.make(view, ex.getMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
