package softdesign.petar.mesopromet;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TrebovanjeActivity extends AppCompatActivity {

    TextView naziv1, sifra1, jMere1, sifGru1, sifPgru1;
    Button trebovanjeButton, otkazi;
    EditText editText;
    String naziv, sifra, jMere, sifGrup, sifPodgrup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trebovanje);

        naziv1 = (TextView) findViewById(R.id.nazivTreb);
        sifra1 = (TextView) findViewById(R.id.sifraTreb);
        jMere1 = (TextView) findViewById(R.id.jmTreb);
        sifGru1 = (TextView) findViewById(R.id.sifraGrupeTreb);
        sifPgru1 = (TextView) findViewById(R.id.sifraPgrupeTreb);
        trebovanjeButton = (Button) findViewById(R.id.trebButton);
        otkazi = (Button) findViewById(R.id.otkaz);
        editText = (EditText) findViewById(R.id.kolicina);
        editText.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(7, 2)});

        Intent i = getIntent();
        naziv = i.getStringExtra("naziv");
        sifra = i.getStringExtra("sifArt");
        jMere = i.getStringExtra("jM");
        sifGrup = i.getStringExtra("sifGru");
        sifPodgrup = i.getStringExtra("sifPgru");
        naziv1.setText("Naziv: " + naziv);
        sifra1.setText("šifra: " + sifra);
        jMere1.setText("j.mere: " + jMere);
        sifGru1.setText("šifra grupe: " + sifGrup);
        sifPgru1.setText("šifra podgrupe: " + sifPodgrup);

        trebovanjeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Grupe grupe = new Grupe(naziv, sifra, jMere);
                String s = editText.getText().toString();
                grupe.setKolicina(s);

                if (grupe.getKolicina().isEmpty()) {

                    Toast toast = Toast.makeText(getApplicationContext(),
                            "NISTE UNELI KOLIČINU!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Trebovanje artikla")
                            .setMessage("Da li želite da pošaljete artikal " + naziv + " u listu za trebovanje?")
                            .setPositiveButton("DA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Narudzbina.trenutnaGrupa.setNaziv(grupe.getNaziv());
                                    Narudzbina.trenutnaGrupa.setSifra(grupe.getSifra());
                                    Narudzbina.trenutnaGrupa.setjMere(grupe.getjMere());
                                    Narudzbina.trenutnaGrupa.setKolicina(grupe.getKolicina());
                                    //Log.e("trentnaGrupa", Narudzbina.trenutnaGrupa.getNaziv());
                                    Narudzbina.listaGrupeZaSlanje.add(grupe);
                                    //Log.e("trentnaGrupaLiSTA", String.valueOf(Narudzbina.getListaGrupeZaSlanje().size()));


                                    finish();

                                }
                            })

                            .setNegativeButton("NE", null)
                            .setCancelable(false);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();


                }

            }
        });

        otkazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
