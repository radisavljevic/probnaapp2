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
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArtikliFragment extends Fragment {

    private RecyclerView.LayoutManager layoutManager;
    private List<ParentListItem> parentListItems;
    private List<GlavneGrupe> glavneGrupe;
    private List<Grupe> grupe;
    private List<Grupe> grupeTrenutne;
    private MyExpandableRecyclerAdapter myExpandableRecyclerAdapter;
    private RecyclerView recyclerView;
    private ProgressDialog pDialog;
    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public ArtikliFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_artikli, container, false);

        ((MainActivity) getActivity()).setActionBarTitle("ARTIKLI");

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewArtikli);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Loading…");
        pDialog.show();

        ucitajGrupe();


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(parentListItems != null){

            for (int i = 0; i < parentListItems.size(); i++) {
                @SuppressWarnings("unchecked")
                List<Grupe> grupes = (List<Grupe>) parentListItems.get(i).getChildItemList();
               // Log.e("grupes", grupes.toString());
                for (int j = 0; j < grupes.size(); j++) {

                    // grupes.get(j).setKolicina("33");

                    for (Grupe gru : Narudzbina.listaGrupeZaSlanje) {
                        if (gru.getSifra().equals(grupes.get(j).getSifra())) {
                            grupes.get(j).setKolicina(gru.getKolicina());

                        }

                    }

                }
                myExpandableRecyclerAdapter.notifyDataSetChanged();
            }

        }

    }

    public void ucitajGrupe() {

        Call<List<GlavneGrupe>> call = apiInterface.getGlavneGrupe();
        call.enqueue(new Callback<List<GlavneGrupe>>() {
            @Override
            public void onResponse(Call<List<GlavneGrupe>> call, Response<List<GlavneGrupe>> response) {

                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        glavneGrupe = new ArrayList<>();
                        parentListItems = new ArrayList<>();

                        glavneGrupe = response.body();
                        for (GlavneGrupe gGrupe : glavneGrupe) {
                            parentListItems.add(gGrupe);
                        }

                        //Log.e("glavnegrupe", glavneGrupe.toString());

                        ucitajPodGrupe();
                    } else {
                        Toast.makeText(getContext(), "Nije učitao glavne grupe!", Toast.LENGTH_SHORT).show();
                        //Log.e("glavnegrupeNull", glavneGrupe.toString() + response.code());
                        pDialog.dismiss();
                    }


                } else {
                    Toast.makeText(getContext(), "Nije učitao glavne grupe!", Toast.LENGTH_SHORT).show();
                   // Log.e("glavnegrupeError", glavneGrupe.toString() + response.code());
                    pDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<GlavneGrupe>> call, Throwable t) {
                //Log.e("Poruka", "Greska " + t.getCause() + " " + t.getMessage() + " " + t.getLocalizedMessage());
                Toast.makeText(getContext(), "Nemate internet konekciju!", Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
            }
        });

    }

    public void ucitajPodGrupe() {

        Call<List<Grupe>> call2 = apiInterface.getGrupe();
        call2.enqueue(new Callback<List<Grupe>>() {
            @Override
            public void onResponse(Call<List<Grupe>> call, Response<List<Grupe>> response) {

                if (response.isSuccessful()) {

                    grupe = response.body();
                    //Log.e("Poruka2", grupe.toString());
                    //Log.e("GLAVNE2", String.valueOf(glavneGrupe.size()));

                    for (int i = 0; i < glavneGrupe.size(); i++) {

                        //Log.e("GLAVNE", glavneGrupe.get(i).getGrupa());
                        grupeTrenutne = new ArrayList<Grupe>();

                        for (int j = 0; j < grupe.size(); j++) {

                            if (glavneGrupe.get(i).getSif_pgu().equals(grupe.get(j).getSifPgru())) {
                                Grupe gr = grupe.get(j);

                                for (Grupe gru : Narudzbina.listaGrupeZaSlanje) {
                                    if (gru.getSifra().equals(grupe.get(j).getSifra())) {
                                        gr.setKolicina(gru.getKolicina());

                                    }

                                }

                                grupeTrenutne.add(gr);

                            }
                            glavneGrupe.get(i).setChildItemList(grupeTrenutne);

                        }

                    }

                    myExpandableRecyclerAdapter = new MyExpandableRecyclerAdapter(getContext(), parentListItems);
                    myExpandableRecyclerAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(myExpandableRecyclerAdapter);
                    pDialog.dismiss();

                } else {
                    Toast.makeText(getContext(), "Nije učitao podgrupe!", Toast.LENGTH_SHORT).show();
                    //Log.e("podgrupeError", grupe.toString() + response.code());
                    pDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<Grupe>> call, Throwable t) {

                //Log.e("Poruka", "Greska2 " + t.getCause() + " " + t.getMessage() + " " + t.getLocalizedMessage());
                pDialog.dismiss();
            }
        });

    }



}
