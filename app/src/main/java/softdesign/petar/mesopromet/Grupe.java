package softdesign.petar.mesopromet;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Petar on 8/3/2017.
 */

public class Grupe {

    @SerializedName("ID")
    String id;
    @SerializedName("NAZ_ART")
    String naziv;
    @SerializedName("NAZ_JM")
    String jMere;
    @SerializedName("SIF_ART")
    String sifra;
    @SerializedName("SIF_GRU")
    String sifGru;
    @SerializedName("SIF_PGRU")
    String sifPgru;

    String kolicina;



    public Grupe() {
    }

    public Grupe(String naziv, String sifra, String jMere) {
        this.naziv = naziv;
        this.sifra = sifra;
        this.jMere = jMere;
    }

    public Grupe(String id, String naziv, String jMere, String sifra, String sifGru, String sifPgru) {
        this.id = id;
        this.naziv = naziv;
        this.jMere = jMere;
        this.sifra = sifra;
        this.sifGru = sifGru;
        this.sifPgru = sifPgru;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getjMere() {
        return jMere;
    }

    public void setjMere(String jMere) {
        this.jMere = jMere;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getSifGru() {
        return sifGru;
    }

    public void setSifGru(String sifGru) {
        this.sifGru = sifGru;
    }

    public String getSifPgru() {
        return sifPgru;
    }

    public void setSifPgru(String sifPgru) {
        this.sifPgru = sifPgru;
    }

    public String getKolicina() {
        return kolicina;
    }

    public void setKolicina(String kolicina) {
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        return "Grupe{" +
                "id='" + id + '\'' +
                ", naziv='" + naziv + '\'' +
                ", jMere='" + jMere + '\'' +
                ", sifra='" + sifra + '\'' +
                ", sifGru='" + sifGru + '\'' +
                ", sifPgru='" + sifPgru + '\'' +
                ", kolicina='" + kolicina + '\'' +
                '}';
    }
}
