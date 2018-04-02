package softdesign.petar.mesopromet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button start;
    ProgressBar progressBar;
    EditText lozinka;
    Kupac kupac;
    String loz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("MESOPROMET");

        lozinka = (EditText) findViewById(R.id.konekcijab);
        start = (Button) findViewById(R.id.startdugmeb);
        progressBar = (ProgressBar) findViewById(R.id.progressbarb);
        progressBar.setVisibility(View.GONE);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loz = lozinka.getText().toString();

                if (loz.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Niste uneli lozinku!", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    slanje(loz);
                }

            }
        });
    }
    public  void slanje(String urlKupac){

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Kupac> call = apiInterface.dajKupca(urlKupac);

        call.enqueue(new Callback<Kupac>() {
            @Override
            public void onResponse(Call<Kupac> call, Response<Kupac> response) {

                if(response.isSuccessful()){
                    kupac = response.body();
                    progressBar.setVisibility(View.GONE);
                    Log.e("kupac",kupac.toString());
                    Log.e("code",String.valueOf(response.code()));
                    Narudzbina.trenutniKupac = kupac;
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    //Log.e("code2",String.valueOf(response.code()));
                    Toast.makeText(getApplicationContext(), "Pogrešna lozinka!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<Kupac> call, Throwable t) {
                //Log.e("kupacGreska",t.getMessage());
                Toast.makeText(getBaseContext(), "Nemate internet konekciju!", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        lozinka.setText("");
    }
}

