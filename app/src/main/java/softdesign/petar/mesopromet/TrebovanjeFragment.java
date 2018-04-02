package softdesign.petar.mesopromet;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrebovanjeFragment extends Fragment  {

    private RecyclerView.LayoutManager layoutManager;
    TextView textView;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    ArrayList<Grupe> lista;
    Button buttonSend;
    Date today;
    SimpleDateFormat sdf;
    ProgressDialog pDialog;
    String userTreb;
    dTreb_Kup dTreb_kup;
    Treb_Kup treb_kup;
    ArrayList<Treb_Kup> treb_kup_list;

    public TrebovanjeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_trebovanje, container, false);

        ((MainActivity) getActivity()).setActionBarTitle("TREBOVANJE");

        textView = (TextView) view.findViewById(R.id.datum_trebovanja);
        buttonSend = (Button) view.findViewById(R.id.naruci_db);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);


        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        today = new Date();
        sdf = new SimpleDateFormat("dd-MM-yyyy");
        String vreme = sdf.format(today);
        textView.setText(vreme);

        lista = Narudzbina.getListaGrupeZaSlanje();
        recyclerAdapter = new RecyclerAdapter(lista);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
        //Log.e("lista",String.valueOf(lista.size()));
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lista.isEmpty()) {

                    Toast.makeText(getContext(),
                            "Lista za slanje je prazna!", Toast.LENGTH_SHORT).show();


                } else {

                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    View mView = inflater.inflate(R.layout.user_input_dialog_box, null);
                    final AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getContext());
                    alertDialogBuilderUserInput.setView(mView);
                    final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);

                    alertDialogBuilderUserInput
                            .setCancelable(false)
                            .setPositiveButton("Potvrdi", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    userTreb = userInputDialogEditText.getText().toString();
                                    String lozTrim = Narudzbina.trenutniKupac.getLoz2().trim();

                                    if (userTreb.isEmpty()) {

                                        Toast.makeText(getContext(),
                                                "Niste uneli lozinku!", Toast.LENGTH_SHORT).show();
                                    } else {

                                        String userTrim = userTreb.trim();

                                        if (lozTrim.equals(userTrim)) {

                                            pDialog = new ProgressDialog(getContext());
                                            pDialog.setMessage("Loading…");
                                            pDialog.show();

                                            slanje();

                                        } else
                                            Toast.makeText(getContext(),
                                                    "Pogrešna lozinka!", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }).setNegativeButton("Otkaži", null);
                    AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                    alertDialogAndroid.show();

                }

            }
        });

        return view;
    }

    public void slanje() {

        String lozTrim = Narudzbina.trenutniKupac.getLoz2().trim();

        dTreb_kup = new dTreb_Kup();
        dTreb_kup.setLoz(lozTrim);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<dTreb_Kup> call = apiInterface.saljiDtrbKup(dTreb_kup);
        call.enqueue(new Callback<dTreb_Kup>() {
            @Override
            public void onResponse(Call<dTreb_Kup> call, Response<dTreb_Kup> response) {

                if(response.isSuccessful()){

                    if(response.body() != null){
                        dTreb_kup = new dTreb_Kup();
                        dTreb_kup = response.body();
                        //Log.e("dtreb", dTreb_kup.toString());
                        int id = dTreb_kup.getId();
                        String i = String.valueOf(id);
                        slanjeTreb(i);
                    }else{
                        //Log.e("dtreb", "nije ucitao dtreb, response je null");
                        pDialog.dismiss();
                    }

                }else{
                    //Log.e("dtreb", "nije ucitao dtreb, response neuspesan");
                    pDialog.dismiss();
                }


            }

            @Override
            public void onFailure(Call<dTreb_Kup> call, Throwable t) {
                //Log.e("dtreb", "failure " + t.getCause() + " " + t.getMessage() + " " + t.getLocalizedMessage());
                pDialog.dismiss();
            }
        });

    }

    public void slanjeTreb(String id) {

        treb_kup_list = new ArrayList<Treb_Kup>();

        for (Grupe grupe : Narudzbina.listaGrupeZaSlanje) {

            treb_kup = new Treb_Kup(id, grupe.getKolicina(), grupe.getSifra());
            treb_kup_list.add(treb_kup);

        }
        slanjeTreb2(treb_kup_list,id);
        //Log.e("treb_kup_list",String.valueOf(treb_kup_list));

    }
    public void slanjeTreb2(final List<Treb_Kup> treb_kup,final String id) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Treb_Kup>> call = apiInterface.saljiTrebovanjeListe(treb_kup);

        call.enqueue(new Callback<List<Treb_Kup>>() {
          @Override
          public void onResponse(Call<List<Treb_Kup>> call, Response<List<Treb_Kup>> response) {

              if(response.isSuccessful()){

                  if(response.body() != null){
                      List<Treb_Kup> listaTreb =  response.body();
                      //Log.e("listaok",listaTreb.toString());
                      slanjeStatus(id);
                  }else{
                      //Log.e("status","nije 1, response je null, STATUS: " + response.code());
                      Toast.makeText(getContext(), "Neuspešno slanje!", Toast.LENGTH_SHORT).show();
                      pDialog.dismiss();
                  }

              }else{
                  //Log.e("status","nije 1,server response neuspesan");
                  Toast.makeText(getContext(), "Neuspešno slanje!", Toast.LENGTH_SHORT).show();
                  pDialog.dismiss();
              }

          }

          @Override
          public void onFailure(Call<List<Treb_Kup>> call, Throwable t) {
              //Log.e("status failure",t.getMessage());
              Toast.makeText(getContext(), "Neuspešno slanje!", Toast.LENGTH_SHORT).show();
              pDialog.dismiss();
          }

      });

    }

    public void slanjeStatus(String id){

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.SaljiStatus(id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.isSuccessful()){
                    String potvrda = response.body();
                    if(potvrda.equals("1")){
                        //Log.e("treb",potvrda);
                        pDialog.dismiss();
                        Narudzbina.getListaGrupeZaSlanje().clear();
                        Toast.makeText(getContext(), "Uspešno slanje!", Toast.LENGTH_SHORT).show();
                    }else{
                        //Log.e("treb","nije 1");
                        Toast.makeText(getContext(), "Uspešno slanje, status nije 1!", Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();
                    }

                }else{
                    //Log.e("treb","neouspesno");
                    Toast.makeText(getContext(), "Uspešno slanje,status nije 1!", Toast.LENGTH_SHORT).show();
                    pDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("treb","onFailure" + t.getMessage());
                Toast.makeText(getContext(), "Uspešno slanje,status nije 1!", Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
    }


}
