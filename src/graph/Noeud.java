package graph;

import java.awt.Color;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author IMRAN-DIALLO
 */
public class Noeud {
    private int id; // good coding practice would have this as private
    private String mValue;
    private Color couleur;

    public Noeud(int id) {
        this.id = id;
        this.couleur = Color.WHITE;
        mValue = "n"+id ;
    }
    public Noeud(int id, Color couleur) {
        this(id) ;
        this.couleur = couleur ;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
    
    public boolean isWhite(){
        return this.couleur == Color.WHITE ;
    }
    
    public Noeud(int id, String value, Color color)
    {
        this(id, color) ;
        mValue = value ;
    }
    public int getID()
    {
        return id;
    }

    public void setValue(String value)
    {
        this.mValue = value;
    }

    public String getValue()
    {
        return (mValue);
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        Noeud n = (Noeud) o;
        return this.id==n.id; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.mValue);
        hash = 47 * hash + Objects.hashCode(this.couleur);
        return hash;
    }
    @Override
    public String toString() {
        return this.mValue; 
    }
}
