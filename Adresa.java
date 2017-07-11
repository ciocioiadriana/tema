
import org.w3c.dom.Element;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Adriana
 */
public class Adresa {
        
    private String Strada;
    private String Bloc;
    private int Scara;
    private int Etaj;
    private int Apartament;
    
    public String getStrada() {
        return Strada;
    }

    public void setStrada(String Nume) {
        this.Strada= Strada;
    }

    public String getBloc() {
        return Bloc;
    }

    public void setBloc(String Bloc) {
        this.Bloc = Bloc;
    }

    public int getEtaj() {
        return  Etaj;
    }

    public void setEtaj(int  Etaj) {
        this. Etaj=  Etaj;
    }
    
     public int getApartament() {
        return Apartament;
    }

    public void setApartament(int Apartament) {
        this.Apartament = Apartament;
    }
    
     public int getScara() {
        return Scara;
    }

    public void setScara(int Scara) {
        this.Scara = Scara;
    }
 
}
