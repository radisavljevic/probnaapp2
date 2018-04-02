package softdesign.petar.mesopromet;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import java.util.List;

/**
 * Created by Petar on 8/3/2017.
 */

public class MyExpandableRecyclerAdapter extends
        ExpandableRecyclerAdapter<MyExpandableRecyclerAdapter.MyParentViewHolder, MyExpandableRecyclerAdapter.MyChildViewHolder> {

    private List<GlavneGrupe> glavneGrupe;
    private List<Grupe> grupe;
    private Context context;
    private LayoutInflater mInflater;

    public MyExpandableRecyclerAdapter(Context context, List<ParentListItem> itemList) {
        super(itemList);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View view = mInflater.inflate(R.layout.row_item, parentViewGroup, false);

        return new MyParentViewHolder(view);

    }

    @Override
    public MyChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View view = mInflater.inflate(R.layout.row_item2, childViewGroup, false);

        return new MyChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(MyParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {

        GlavneGrupe glavneGrupe = (GlavneGrupe)parentListItem;
        parentViewHolder.nazivGrupe.setText(glavneGrupe.getGrupa());
        parentViewHolder.sifra.setText(glavneGrupe.getSif_pgu());
    }

    @Override
    public void onBindChildViewHolder(final MyChildViewHolder childViewHolder, final int position, final Object childListItem) {

        final Grupe grupe = (Grupe)childListItem;
        childViewHolder.naziv.setText(grupe.getNaziv());
        childViewHolder.jMere.setText(grupe.getjMere());
        childViewHolder.sifraArt.setText(grupe.getSifra());
        childViewHolder.sifGru.setText(grupe.getSifGru());
        childViewHolder.sifPgru.setText(grupe.getSifPgru());

        childViewHolder.trenKol.setText(grupe.getKolicina() != null ? grupe.getKolicina() + " KG" : "");
        //Log.e("porukaChildViewHolder",grupe.getNaziv());

        View view = childViewHolder.itemView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context = v.getContext();

                if(grupe.getKolicina() == null){

                    String naziv = grupe.getNaziv();
                    String sifra = grupe.getSifra();
                    String jMere = grupe.getjMere();
                    String sifGru = grupe.getSifGru();
                    String sifPgru = grupe.getSifPgru();
                    Log.e("log1",grupe.getNaziv());
                    Intent intent = new Intent(context, TrebovanjeActivity.class);

                    intent.putExtra("naziv",naziv);
                    intent.putExtra("sifArt",sifra);
                    intent.putExtra("jM",jMere);
                    intent.putExtra("sifGru",sifGru);
                    intent.putExtra("sifPgru",sifPgru);
                    context.startActivity(intent);

                }else{
                    Toast.makeText(context, "Artikal je poslat u listu za trebovanje!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    public class MyParentViewHolder extends ParentViewHolder {

        TextView nazivGrupe, sifra;

        public MyParentViewHolder(View itemView) {
            super(itemView);
            nazivGrupe = (TextView)itemView.findViewById(R.id.nazivGrupe);
            sifra = (TextView)itemView.findViewById(R.id.sifraGrupe);


        }
    }

    public class MyChildViewHolder extends ChildViewHolder {

        public TextView naziv,sifraArt,jMere,sifGru,sifPgru, trenKol;

        public MyChildViewHolder(View itemView) {
            super(itemView);

            naziv = (TextView) itemView.findViewById(R.id.naziv);
            sifraArt = (TextView) itemView.findViewById(R.id.sifraArt);
            jMere = (TextView) itemView.findViewById(R.id.jMere);
            sifGru = (TextView) itemView.findViewById(R.id.sifGru);
            sifPgru = (TextView) itemView.findViewById(R.id.sifPgru);
            trenKol = (TextView) itemView.findViewById(R.id.trenutnaKolicina);
        }
    }
}