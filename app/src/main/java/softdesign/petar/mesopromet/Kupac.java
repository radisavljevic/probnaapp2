package softdesign.petar.mesopromet;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Petar on 8/3/2017.
 */

public class Kupac {

    @SerializedName("id")
    public String id;
    @SerializedName("sifk")
    public String sifk;
    @SerializedName("NAZK")
    public String NAZK;
    @SerializedName("sif_Rj")
    public String sif_Rj;
    @SerializedName("naz_rj")
    public String naz_Rj;
    @SerializedName("loz1")
    public String loz1;
    @SerializedName("loz2")
    public String loz2;


    public Kupac() {
    }

    public Kupac(String id, String sifk, String NAZK, String sif_Rj, String naz_Rj, String loz1, String loz2) {
        this.id = id;
        this.sifk = sifk;
        this.NAZK = NAZK;
        this.sif_Rj = sif_Rj;
        this.naz_Rj = naz_Rj;
        this.loz1 = loz1;
        this.loz2 = loz2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSifk() {
        return sifk;
    }

    public void setSifk(String sifk) {
        this.sifk = sifk;
    }

    public String getNazK() {
        return NAZK;
    }

    public void setNazK(String nazK) {
        this.NAZK = nazK;
    }

    public String getSif_Rj() {
        return sif_Rj;
    }

    public void setSif_rj(String sif_Rj) {
        this.sif_Rj = sif_Rj;
    }

    public String getNaz_rj() {
        return naz_Rj;
    }

    public void setNaz_rj(String naz_rj) {
        this.naz_Rj = naz_rj;
    }

    public String getLoz1() {
        return loz1;
    }

    public void setLoz1(String loz1) {
        this.loz1 = loz1;
    }

    public String getLoz2() {
        return loz2;
    }

    public void setLoz2(String loz2) {
        this.loz2 = loz2;
    }

    @Override
    public String toString() {
        return "Kupac{" +
                "id=" + id +
                ", sifk=" + sifk +
                ", NAZK='" + NAZK + '\'' +
                ", sif_Rj=" + sif_Rj +
                ", naz_Rj='" + naz_Rj + '\'' +
                ", loz1='" + loz1 + '\'' +
                ", loz2='" + loz2 + '\'' +
                '}';
    }
}
