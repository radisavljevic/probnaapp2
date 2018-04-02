package softdesign.petar.mesopromet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Petar on 8/29/2017.
 */

public class AdapterIstorijaTrebKup extends RecyclerView.Adapter<AdapterIstorijaTrebKup.MyViewHolder> {

    private List<IstorijaTrebovanjaTrebKup> istorijaTrebovanjaTrebKup;
    private Context context;

    public AdapterIstorijaTrebKup(List<IstorijaTrebovanjaTrebKup> istorijaTrebovanjaTrebKup) {
        this.istorijaTrebovanjaTrebKup = istorijaTrebovanjaTrebKup;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_istorija_2, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.naziv.setText(istorijaTrebovanjaTrebKup.get(position).getNAZ_ART());
        holder.kolicina.setText(istorijaTrebovanjaTrebKup.get(position).getKolic());
        holder.jMere.setText(istorijaTrebovanjaTrebKup.get(position).getNAZ_JM());

    }

    @Override
    public int getItemCount() {
        return istorijaTrebovanjaTrebKup.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView naziv, kolicina, jMere;

        public MyViewHolder(final View itemView) {
            super(itemView);
            naziv = (TextView) itemView.findViewById(R.id.nazivTrebovanja);
            kolicina = (TextView) itemView.findViewById(R.id.kolicinaTrebovanja);
            jMere = (TextView) itemView.findViewById(R.id.jmTrebovanja);
        }
    }
}
