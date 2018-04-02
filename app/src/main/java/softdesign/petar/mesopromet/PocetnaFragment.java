package softdesign.petar.mesopromet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class PocetnaFragment extends Fragment {

    Date today;
    SimpleDateFormat sdf;
    TextView textView, naziv, rj;
    String vreme;
    String nazivKup,radnaJed;


    public PocetnaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pocetna, container, false);

        ((MainActivity) getActivity()).setActionBarTitle("POČETNA");

        textView = (TextView) view.findViewById(R.id.vreme);
        naziv = (TextView)view.findViewById(R.id.naziv_kupca);
        rj = (TextView)view.findViewById(R.id.radna_jedinica_kupca);

        today = new Date();
        sdf = new SimpleDateFormat("EEEE dd-MM-yyyy");
        vreme = sdf.format(today);
        textView.setText(vreme);

        nazivKup = Narudzbina.trenutniKupac.getNazK();
        naziv.setText(nazivKup);

        radnaJed = Narudzbina.trenutniKupac.getNaz_rj();
        rj.setText(radnaJed);


        return view;
    }

}
