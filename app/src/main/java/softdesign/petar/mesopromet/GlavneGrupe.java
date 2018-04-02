package softdesign.petar.mesopromet;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Petar on 8/3/2017.
 */

public class GlavneGrupe implements ParentListItem {


    @SerializedName("grupa")
    private String grupa;
    @SerializedName("sif_pgu")
    private String sif_pgu;

    private List<Grupe> grupes;

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public String getSif_pgu() {
        return sif_pgu;
    }

    public void setSif_pgu(String sif_pgu) {
        this.sif_pgu = sif_pgu;
    }
    public void setChildItemList(List<Grupe> grupes){
        this.grupes = grupes;
    }

    @Override
    public List<Grupe> getChildItemList() {
        return grupes;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    @Override
    public String toString() {
        return "GlavneGrupe{" +
                "grupa='" + grupa + '\'' +
                ", sif_pgu='" + sif_pgu + '\'' +
                '}';
    }
}
