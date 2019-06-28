package senac.fakegeneratortabajara;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.List;

import senac.fakegeneratortabajara.adapters.AdapterFalso;
import senac.fakegeneratortabajara.models.Falso;
import senac.fakegeneratortabajara.models.Gerador;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Falso>> {

    Spinner spTipo;
    EditText txtQuantidade;
    RecyclerView listaFakes;
    ProgressBar loading;
    LoaderManager loaderManager;
    public static Gerador gerador;

    public static final String TIPO_TEXT_KEY = "tipos"; // chave para guardar os dados da telinha
    public static final String QTD_TEXT_KEY = "quantidade";// chave para guardar os dados da telinha

    public static final int OPERATION_SEARCH_LOADER = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spTipo = findViewById(R.id.spFakes);
        txtQuantidade = findViewById(R.id.txtQuantidade);
        listaFakes = findViewById(R.id.ListaFakes);
        loading = findViewById(R.id.progressBar);
        loading.setVisibility(View.GONE);

        if (savedInstanceState != null) { // complemento da telinha que gira
            if (savedInstanceState.containsKey(TIPO_TEXT_KEY)) {
                spTipo.setSelection(savedInstanceState.getInt(TIPO_TEXT_KEY));
            }
            if (savedInstanceState.containsKey(QTD_TEXT_KEY)) {
                txtQuantidade.setText(savedInstanceState.getString(QTD_TEXT_KEY));
            }
        }

        RecyclerView.LayoutManager layout = new LinearLayoutManager(getBaseContext(),
                RecyclerView.VERTICAL, false);

        listaFakes.addItemDecoration(
                new DividerItemDecoration(getBaseContext(), DividerItemDecoration.VERTICAL));

        listaFakes.setLayoutManager(layout);

        loaderManager = getSupportLoaderManager();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String tipo = spTipo.getSelectedItem().toString();
                    int quantidade = Integer.parseInt(txtQuantidade.getText().toString());

                    gerador = new Gerador(tipo, quantidade);

                    Loader<List<Falso>> loader = loaderManager.getLoader(OPERATION_SEARCH_LOADER);

                    if (loader == null) {
                        loaderManager.initLoader(OPERATION_SEARCH_LOADER, null, MainActivity.this);
                    } else {
                        loaderManager.restartLoader(OPERATION_SEARCH_LOADER, null, MainActivity.this);
                    }

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

    @Override
    protected void onSaveInstanceState(Bundle outState) { // usado para girar a telinha do celular
        super.onSaveInstanceState(outState);

        outState.putInt(TIPO_TEXT_KEY, spTipo.getSelectedItemPosition());
        outState.putString(QTD_TEXT_KEY, txtQuantidade.getText().toString());

    }


    @NonNull
    @Override
    public Loader<List<Falso>> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<List<Falso>>(this) {
            @Nullable
            @Override
            public List<Falso> loadInBackground() {
                return gerador.gerarFakes();
            }

            @Override
            protected void onStartLoading() {
                listaFakes.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.VISIBLE);
                forceLoad();
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Falso>> loader, List<Falso> data) {
        loading.setVisibility(View.GONE);

        listaFakes.setVisibility(View.VISIBLE);

        listaFakes.setAdapter(new AdapterFalso(data, getBaseContext()));
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Falso>> loader) {
        //Leave it for now as it is
    }
}
