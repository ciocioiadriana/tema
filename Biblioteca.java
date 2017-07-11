/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Adriana
 */
public class Biblioteca {
    
    private String Titlul;
    private String Autor;
    private String Categoria;
    private String Editura;
    private int Numar_Raft;
    
    public String getAutor() {
        return Autor;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getEditura() {
        return Editura;
    }

    public void setEditura(String Editura) {
        this.Editura = Editura;
    }
    
     public String getTitlul() {
        return Titlul;
    }

    public void setTitlul(String Titlul) {
        this.Titlul = Titlul;
    }
    
     public int getNumar_Raft() {
        return Numar_Raft;
    }

    public void setNumar_Raft(int Numar_Raft) {
        this.Numar_Raft = Numar_Raft;
    }
}
