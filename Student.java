/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Adriana
 */
public class Student {
    
  private int ID;
  private String Nume;
  private String Prenume;
  private String Grupa;
  private double Medie;
  
    public String getNume() {
        return Nume;
    }

    public void setNume(String Nume) {
        this.Nume = Nume;
    }

    public String getPrenume() {
        return Prenume;
    }

    public void setPrenume(String Prenume) {
        this.Prenume = Prenume;
    }

    public String getGrupa() {
        return Grupa;
    }

    public void setGrupa(String Grupa) {
        this.Grupa= Grupa;
    }
    
     public double getMedie() {
        return Medie;
    }

    public void setMedie(double Medie) {
        this.Medie = Medie;
    }
    
     public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
 
}
