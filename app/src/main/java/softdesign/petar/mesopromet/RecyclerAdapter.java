package softdesign.petar.mesopromet;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Petar on 8/3/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Grupe> grupe;
    private Listener listener;

    public RecyclerAdapter(List<Grupe> grupe) {
        this.grupe = grupe;
    }

    public static interface Listener {
        public void onClick(int position);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_treb, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.nazivGrupe.setText(grupe.get(position).getNaziv());
        holder.sifra.setText(grupe.get(position).getSifra());
        holder.kolicina.setText(grupe.get(position).getKolicina());
        holder.jMera.setText(grupe.get(position).getjMere());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Da li ste sigurni da želite da izbrišete Artikal?")
                        .setPositiveButton("DA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                grupe.remove(position);
                                notifyItemRemoved(position);
                                notifyItemChanged(position, grupe.size());

                            }
                        })
                        .setNegativeButton("NE", null)
                        .setCancelable(false);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });

        View view = holder.itemView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return grupe.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nazivGrupe, sifra, kolicina, jMera;
        ImageButton button;

        public MyViewHolder(final View itemView) {
            super(itemView);
            nazivGrupe = (TextView) itemView.findViewById(R.id.nazivGrupeTreb);
            sifra = (TextView) itemView.findViewById(R.id.sifraGrupeTreb);
            kolicina = (TextView) itemView.findViewById(R.id.kolicinaTreb);
            jMera = (TextView) itemView.findViewById(R.id.jMerTreb);
            button = (ImageButton) itemView.findViewById(R.id.delete_btn);


        }
    }
}

