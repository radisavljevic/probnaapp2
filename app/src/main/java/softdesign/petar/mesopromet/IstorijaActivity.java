package softdesign.petar.mesopromet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IstorijaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterIstorijaTrebKup adapterIstorijaTrebKup;
    RecyclerView.LayoutManager layoutManager;
    List<IstorijaTrebovanjaTrebKup> listaIstorijeTrebKup = new ArrayList<>();
    ProgressDialog pDialog;
    String id;
    TextView textViewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istorija);

        getSupportActionBar().setTitle("ISTORIJA");

        recyclerView = (RecyclerView)findViewById(R.id.listaIstorijeTrebKup);
        textViewId = (TextView)findViewById(R.id.naslovId);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Učitavanje…");
        pDialog.show();

        Intent i = getIntent();
        id = i.getStringExtra("id");

        textViewId.setText(" pod id-jem " + id);
        slanje(id);
        //Log.e("idi", id);

    }
    public void slanje(String id){

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<IstorijaTrebovanjaTrebKup>> call = apiInterface.dajIstorijuTreb(id);
        call.enqueue(new Callback<List<IstorijaTrebovanjaTrebKup>>() {
            @Override
            public void onResponse(Call<List<IstorijaTrebovanjaTrebKup>> call, Response<List<IstorijaTrebovanjaTrebKup>> response) {


                if(response.isSuccessful()) {

                    listaIstorijeTrebKup = response.body();
                    Log.e("listaIstorijeTreb", listaIstorijeTrebKup.toString());
                    adapterIstorijaTrebKup = new AdapterIstorijaTrebKup(listaIstorijeTrebKup);
                    recyclerView.setAdapter(adapterIstorijaTrebKup);
                    pDialog.dismiss();

                }else {
                    Log.e("listaIstorijeTreb", String.valueOf(response.code()));
                    pDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<IstorijaTrebovanjaTrebKup>> call, Throwable t) {

                Log.e("listaIstorije", t.getMessage());
                pDialog.dismiss();

            }
        });

    }
}
