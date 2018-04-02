package softdesign.petar.mesopromet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Petar on 8/3/2017.
 */

public interface ApiInterface {

    @GET("kupac/{sifra}")
    Call<Kupac> dajKupca(@Path("sifra") String sifra);

    @GET("artikli/glavnegrupe")
    Call<List<GlavneGrupe>> getGlavneGrupe();

    @GET("artikli/podgrupe")
    Call<List<Grupe>> getGrupe();

    @GET("istorija/dajdtrebkup/{loz}")
    Call<List<IstorijaTrebovanja>> dajIstorijuDtreb(@Path("loz") String loz);

    @GET("istorija/dajtrebkup/{id}")
    Call<List<IstorijaTrebovanjaTrebKup>> dajIstorijuTreb(@Path("id") String id);


    @POST("treb/trebkup")
    Call<Treb_Kup> saljiTrebovanje(@Body Treb_Kup treb_kup);

    @POST("treb/dtrebkup")
    Call<dTreb_Kup> saljiDtrbKup(@Body dTreb_Kup dTreb_kup);

    //@Headers({ "Content-Type: text/plain;charset=UTF-8"})
    @POST("treb/dtrebkupstatus")
    Call<String> SaljiStatus(@Body String id);

    @POST("treb/trebkuplist")
    Call<List<Treb_Kup>> saljiTrebovanjeListe(@Body List<Treb_Kup> treb_kup_list);

}
