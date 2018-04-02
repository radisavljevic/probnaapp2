package softdesign.petar.mesopromet;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Petar on 4/28/2017.
 */

public class IstorijaTrebovanja {

    @SerializedName("id")
    private String id;
    @SerializedName("loz")
    private String lozinka;
    @SerializedName("dat")
    private String datumTrebovanja;

    public IstorijaTrebovanja() {
    }

    public IstorijaTrebovanja(String id, String lozinka, String datumTrebovanja) {
        this.id = id;
        this.lozinka = lozinka;
        this.datumTrebovanja = datumTrebovanja;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getDatumTrebovanja() {
        return datumTrebovanja;
    }

    public void setDatumTrebovanja(String datumTrebovanja) {
        this.datumTrebovanja = datumTrebovanja;
    }

    @Override
    public String toString() {
        return "IstorijaTrebovanja{" +
                "id='" + id + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", datumTrebovanja='" + datumTrebovanja + '\'' +
                '}';
    }
}

