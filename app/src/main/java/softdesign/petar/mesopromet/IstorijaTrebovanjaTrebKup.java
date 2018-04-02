package softdesign.petar.mesopromet;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Petar on 5/12/2017.
 */

public class IstorijaTrebovanjaTrebKup {

    @SerializedName("id_Dok")
    private String id_Dok;
    @SerializedName("NAZ_ART")
    private String NAZ_ART;
    @SerializedName("kolic")
    private String kolic;
    @SerializedName("NAZ_JM")
    private String NAZ_JM;

    public IstorijaTrebovanjaTrebKup() {
    }

    public IstorijaTrebovanjaTrebKup(String id_Dok, String NAZ_ART, String kolic, String NAZ_JM) {
        this.id_Dok = id_Dok;
        this.NAZ_ART = NAZ_ART;
        this.kolic = kolic;
        this.NAZ_JM = NAZ_JM;
    }

    public String getId_Dok() {
        return id_Dok;
    }

    public void setId_Dok(String id_Dok) {
        this.id_Dok = id_Dok;
    }

    public String getNAZ_ART() {
        return NAZ_ART;
    }

    public void setNAZ_ART(String NAZ_ART) {
        this.NAZ_ART = NAZ_ART;
    }

    public String getKolic() {
        return kolic;
    }

    public void setKolic(String kolic) {
        this.kolic = kolic;
    }

    public String getNAZ_JM() {
        return NAZ_JM;
    }

    public void setNAZ_JM(String NAZ_JM) {
        this.NAZ_JM = NAZ_JM;
    }

    @Override
    public String toString() {
        return "IstorijaTrebovanjaTrebKup{" +
                "id_Dok='" + id_Dok + '\'' +
                ", NAZ_ART='" + NAZ_ART + '\'' +
                ", kolic='" + kolic + '\'' +
                ", NAZ_JM='" + NAZ_JM + '\'' +
                '}';
    }
}
