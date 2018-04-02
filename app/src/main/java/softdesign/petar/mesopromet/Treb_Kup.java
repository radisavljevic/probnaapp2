package softdesign.petar.mesopromet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Petar on 8/3/2017.
 */

public class Treb_Kup {

    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("id_Dok")
    @Expose
    String id_Dok;
    @SerializedName("kolic")
    @Expose
    String kolic;
    @SerializedName("sif_Art")
    @Expose
    String sif_Art;

    public Treb_Kup() {
    }

    public Treb_Kup(String id_Dok, String kolic, String sif_Art) {
        this.id_Dok = id_Dok;
        this.kolic = kolic;
        this.sif_Art = sif_Art;
    }

    public Treb_Kup(String id, String id_Dok, String kolic, String sif_Art) {
        this.id = id;
        this.id_Dok = id_Dok;
        this.kolic = kolic;
        this.sif_Art = sif_Art;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_Dok() {
        return id_Dok;
    }

    public void setId_Dok(String id_Dok) {
        this.id_Dok = id_Dok;
    }

    public String getKolic() {
        return kolic;
    }

    public void setKolic(String kolic) {
        this.kolic = kolic;
    }

    public String getSif_Art() {
        return sif_Art;
    }

    public void setSif_Art(String sif_Art) {
        this.sif_Art = sif_Art;
    }

    @Override
    public String toString() {
        return "Treb_Kup{" +
                "id=" + id +
                ", id_Dok='" + id_Dok + '\'' +
                ", kolic='" + kolic + '\'' +
                ", sif_Art='" + sif_Art + '\'' +
                '}';
    }
}

