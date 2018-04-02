package softdesign.petar.mesopromet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Petar on 8/11/2017.
 */

public class AdapterIstorija extends RecyclerView.Adapter<AdapterIstorija.MyViewHolder> {

    private List<IstorijaTrebovanja> istorijaTrebovanja;
    private Context context;

    public AdapterIstorija(List<IstorijaTrebovanja> istorijaTrebovanja) {
        this.istorijaTrebovanja = istorijaTrebovanja;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_istorija, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.istorijaId.setText(istorijaTrebovanja.get(position).getId());
        holder.vreme.setText(istorijaTrebovanja.get(position).getDatumTrebovanja());

        View view = holder.itemView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context = v.getContext();

                String id = istorijaTrebovanja.get(position).getId();
                Intent intent = new Intent(context,IstorijaActivity.class);
                intent.putExtra("id",id);
                //Log.e("iddd", id);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return istorijaTrebovanja.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView istorijaId, vreme;

        public MyViewHolder(final View itemView) {
            super(itemView);
            istorijaId = (TextView) itemView.findViewById(R.id.listaIstorijeId);
            vreme = (TextView) itemView.findViewById(R.id.listaIstorijeVreme);
        }
    }
}
