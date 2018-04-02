package softdesign.petar.mesopromet;

import java.util.ArrayList;

/**
 * Created by Petar on 8/3/2017.
 */

public class Narudzbina {

    public static Kupac trenutniKupac  = new Kupac();
    public static ArrayList<Kupac> listaKupaca = new ArrayList<>();

    public static Grupe trenutnaGrupa = new Grupe();
    public static ArrayList<Grupe> listaGrupeZaSlanje = new ArrayList<Grupe>();



    public static Kupac getTrenutniKupac() {
        return trenutniKupac;
    }

    public static void setTrenutniKupac(Kupac trenutniKupac) {
        Narudzbina.trenutniKupac = trenutniKupac;
    }

    public static ArrayList<Kupac> getListaKupaca() {
        return listaKupaca;
    }

    public static void setListaKupaca(ArrayList<Kupac> listaKupaca) {
        Narudzbina.listaKupaca = listaKupaca;
    }




    public static Grupe getTrenutnaGrupa() {
        return trenutnaGrupa;
    }

    public static void setTrenutnaGrupa(Grupe trenutnaGrupa) {
        Narudzbina.trenutnaGrupa = trenutnaGrupa;
    }

    public static ArrayList<Grupe> getListaGrupeZaSlanje() {
        return listaGrupeZaSlanje;
    }

    public static void setListaGrupeZaSlanje(ArrayList<Grupe> listaGrupeZaSlanje) {
        Narudzbina.listaGrupeZaSlanje = listaGrupeZaSlanje;
    }

}
