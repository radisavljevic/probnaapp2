package softdesign.petar.mesopromet;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Petar on 8/7/2017.
 */

public class BackgroundService extends IntentService {

    Treb_Kup treb_kup;

    public BackgroundService() {
        super("Backgrund");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        String id = intent.getStringExtra("id");
        String kolicina = intent.getStringExtra("kolicina");
        String sifra = intent.getStringExtra("sifra");


        treb_kup = new Treb_Kup();

        treb_kup.setId_Dok(id);
        treb_kup.setKolic(kolicina);
        treb_kup.setSif_Art(sifra);
        Log.e("background", treb_kup.toString());
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Treb_Kup> call = apiInterface.saljiTrebovanje(treb_kup);

        try {
            Response<Treb_Kup> treb_kupResponse = call.execute();
            Log.e("backgroundRes", treb_kupResponse.body().toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("backgroundRes", "Greska");
        }


    }
}
