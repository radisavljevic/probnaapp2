package softdesign.petar.mesopromet;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Petar on 8/3/2017.
 */

public class dTreb_Kup {

    @SerializedName("dat")
    String dat;
    @SerializedName("id")
    int id;
    @SerializedName("loz")
    String loz;

    public dTreb_Kup() {
    }

    public dTreb_Kup(String dat, int id, String loz) {
        this.dat = dat;
        this.id = id;
        this.loz = loz;
    }

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoz() {
        return loz;
    }

    public void setLoz(String loz) {
        this.loz = loz;
    }

    @Override
    public String toString() {
        return "dTreb_Kup{" +
                "dat='" + dat + '\'' +
                ", id=" + id +
                ", loz='" + loz + '\'' +
                '}';
    }
}
