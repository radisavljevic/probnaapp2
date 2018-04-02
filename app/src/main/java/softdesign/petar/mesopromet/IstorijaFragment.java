package softdesign.petar.mesopromet;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class IstorijaFragment extends Fragment {

    RecyclerView recyclerView;
    AdapterIstorija adapterIstorija;
    RecyclerView.LayoutManager layoutManager;
    List<IstorijaTrebovanja> listaIstorije = new ArrayList<>();
    ProgressDialog pDialog;

    public IstorijaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_istorija, container, false);

        ((MainActivity) getActivity()).setActionBarTitle("ISTORIJA");

        recyclerView = (RecyclerView)view.findViewById(R.id.listaIstorije);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Učitavanje…");
        pDialog.show();

        getIstoriju(Narudzbina.trenutniKupac.getLoz2().trim());

    }

    public void getIstoriju(String loz){


        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<IstorijaTrebovanja>> call = apiInterface.dajIstorijuDtreb(loz);
        call.enqueue(new Callback<List<IstorijaTrebovanja>>() {
            @Override
            public void onResponse(Call<List<IstorijaTrebovanja>> call, Response<List<IstorijaTrebovanja>> response) {

                if(response.isSuccessful()){

                    listaIstorije = response.body();
                    //Log.e("listaIstorije", listaIstorije.toString());
                    adapterIstorija = new AdapterIstorija(listaIstorije);
                    recyclerView.setAdapter(adapterIstorija);
                    pDialog.dismiss();

                }else {
                    //Log.e("listaIstorije", String.valueOf(response.code()));
                    pDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<IstorijaTrebovanja>> call, Throwable t) {

                //Log.e("listaIstorije", t.getMessage());
                pDialog.dismiss();

            }
        });

    }
}
