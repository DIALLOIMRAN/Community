/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement;

import edu.uci.ics.jung.graph.Graph;
import graph.Lien;
import graph.MyGraph;
import graph.Noeud;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Imane
 */
public class Algorithme3 extends Algo{

    public Algorithme3(Graph<Noeud, Lien> graphe, MyGraph mg) {
        super(graphe, mg);
    }
    public ArrayList <Noeud> LocalNetwork(Noeud neaud){
//        ArrayList <Noeud> reseau = new ArrayList <>() ;
//        reseau.add(neaud) ;
//        for (Noeud reseau1 : new ArrayList<>(this.lesVoisins(neaud))) {
//            reseau.add(reseau1);
//        }
        return new ArrayList<>(this.lesVoisins(neaud));
    }
    public HashMap <Noeud, ArrayList <Noeud>> reseauLocal(Noeud neaud){
        HashMap <Noeud, ArrayList <Noeud>> reseau = new HashMap<>() ;
        reseau.put(neaud, new ArrayList<>(this.lesVoisins(neaud)));
        return reseau;
    }
    public Noeud leader(){
        //centralité = Centrality.trierParValeur(Centrality.pageRankCentralité());
        Set set1 = Centrality.trierParValeur(Centrality.pageRankCentralité()).entrySet() ;
        Iterator it = set1.iterator();
        Map.Entry me = (Map.Entry) it.next();
        Noeud n = (Noeud) me.getKey() ;
        return n ;
    }
    @SuppressWarnings("empty-statement")
    public int degreeNoeud(Noeud noeud){
        return this.lesVoisins(noeud).size() ;
    }
    public int degreReseauLocal(Noeud leader){
        int Tdegree = this.degreeNoeud(leader) ;
        for (Noeud noeud : this.lesVoisins(leader)) {
            Tdegree += this.degreeNoeud(noeud);
        }
        return Tdegree ;
    }
    
    public ArrayList <Double> ProbaLeader(Noeud noeud, Noeud leader){
        int d1 = this.degreeNoeud(leader) ;
        int d2 = this.degreReseauLocal(leader) ;
        
        ArrayList<Noeud> voisins = new ArrayList<>(this.lesVoisins(leader)); 
        ArrayList <Double> proba = new ArrayList <>(voisins.size());
        for (int j = 0; j < voisins.size(); j++) {
            proba.add(j, 0.0);
        }
        proba.add(((double) d1)/d2) ;
        ArrayList <Noeud> v = new ArrayList<>(this.lesVoisins(noeud)) ;
        for(int i=0; i < v.size(); i++){
            
                d1 = this.degreeNoeud(v.get(i)) ;
                d2 = this.degreReseauLocal(noeud) ;
                proba.set(i, ((double) d1)/d2) ;
            
        }
//        for (Noeud n : new ArrayList<>(this.lesVoisins(noeud))) {
//            d1 = this.degreeNoeud(n) ;
//            
//            proba.add(((double) d1)/d2) ;
//        }
        proba.sort(new Comparator<Double>(){
            @Override
            public int compare( Double o1, Double o2 ){
                return (o2).compareTo( o1);
            }
        });
        return proba ;
    }
    
    public ArrayList<ArrayList <Double>> lesProbas (ArrayList<Noeud> noeuds, Noeud leader){
        ArrayList<ArrayList <Double>> probas = new ArrayList <>();
        for (Noeud noeud : noeuds) {
            probas.add(this.ProbaLeader(noeud, leader)) ;
        }
        return probas ;
    }
    
    public void afficheProbas(ArrayList <ArrayList <Double>> allProbas){
        int i = 0 ;
        for(ArrayList <Double> proba : allProbas){
            System.out.print("P("+i+") = [ ");
            for (Double proba1 : proba) {
                java.text.DecimalFormat df = new java.text.DecimalFormat("0.###");
                System.out.print(df.format(proba1)+ "  ");
                //System.out.print(proba1+" ");
            }
            System.out.println(" ]");
            i++;
        }
    }
    public double entropieRelative (ArrayList <Double> P1, ArrayList <Double> P2){
        double ER = 0, val;
        
        for (int i=0; i < P1.size() ; i++) {
            val = ((double) P1.get(i))/ P2.get(i) ;
            if(P2.get(i)!=0 && val != 0)
                ER += P1.get(i)*Math.log(P1.get(i)/P2.get(i)) ;
        }
        return ER ;
    }
    
    public double similarité(Noeud n1, Noeud n2) {
        
        double er1, er2;
        
        er1 = this.entropieRelative(this.ProbaLeader(n1, n1), this.ProbaLeader(n2, n1)) ;
        er2 = this.entropieRelative(this.ProbaLeader(n2, n2), this.ProbaLeader(n2, n1)) ;
        return (1-(er1+er2)) ;
    }
    
    public ArrayList<Noeud> similaire(ArrayList <Noeud> lesNonVoisins, Noeud leader){
        ArrayList<Noeud> sim = new ArrayList<>() ;
        for (Noeud noeud : lesNonVoisins) {
            if(this.similarité(leader, noeud) == 1.0){
                sim.add(noeud);
            }
        }
        return sim ;
    }
    public HashMap <Noeud, ArrayList <Noeud>> reseauLocalLeader(){
       
        HashMap <Noeud, Double>  centralité;
        liens = this.lesLiens() ;
        Graph <Noeud, Lien> graphTmp = graphe ;
        
        Centrality.setGraph(graphTmp);
        centralité = Centrality.trierParValeur(Centrality.pageRankCentralité());
        Set set1 = centralité.entrySet() ;
        Iterator it = set1.iterator();
        Map.Entry me = (Map.Entry) it.next();
        Noeud n = (Noeud) me.getKey() ;
        ArrayList <Noeud> Neighbors = this.LocalNetwork(n) ;
        HashMap <Noeud, ArrayList <Noeud>> reseau = this.reseauLocal(n) ;
        ArrayList <Noeud> similaire = this.similaire(this.lesNonVoisins(n), n) ;
        if(similaire != null){
            for (Noeud similaire1 : similaire) {
                Neighbors.add(similaire1) ;
            }
        }
        reseau.put(n, Neighbors) ;
        graphTmp.removeVertex(n) ;
        
        for (Noeud voisin : Neighbors) {
            graphTmp.removeVertex(voisin) ;
        }
        return reseau ;
    } 
    
    
    
//    public HashMap <Noeud, ArrayList <Noeud>> communauté (){ 
//        HashMap <Noeud, ArrayList <Noeud>> communauté = new HashMap<>() ;
//        //do{
//        HashMap <Noeud, Double>  centralité;
//        liens = this.lesLiens() ;
//        Graph<Noeud, Lien> graphTmp = graphe ;
//        while (graphTmp.getVertexCount()!=0) { 
//            Centrality.setGraph(graphTmp);
//            centralité = Centrality.trierParValeur(Centrality.pageRankCentralité());
//            Set set1 = centralité.entrySet() ;
//            Iterator it = set1.iterator();
//            
//            Map.Entry me = (Map.Entry) it.next();
//            Noeud n = (Noeud) me.getKey() ;
//            ArrayList <Noeud> Neighbors = this.LocalNetwork(n) ;
//            
//            ArrayList <Noeud> similaire = this.similaire(this.lesNonVoisins(n), n) ;
//            if(similaire != null){
//                for (Noeud similaire1 : similaire) {
//                    Neighbors.add(similaire1) ;
//                }
//            }
//            communauté.put(n, Neighbors) ;
//              
//            //double val = (double) me.getValue() ;
//            //System.out.println("noeud : "+n+ " valeur :"+ val);
//            graphTmp.removeVertex(n) ;
//
//            for (Noeud voisin : Neighbors) {
//                graphTmp.removeVertex(voisin) ;
//            }
//        }
//        //this.afficheCom1(touslesVoisins);
//        return communauté ;
//        
//    } 

    @Override
    public HashMap<Noeud, ArrayList<Noeud>> communauté(HashMap<Noeud, Double> centralité) {
                HashMap <Noeud, ArrayList <Noeud>> communauté = new HashMap<>() ;
        //do{
        
        liens = this.lesLiens() ;
        Graph<Noeud, Lien> graphTmp = graphe ;
        while (graphTmp.getVertexCount()!=0) { 
            Centrality.setGraph(graphTmp);
            centralité = Centrality.trierParValeur(Centrality.pageRankCentralité());
            Set set1 = centralité.entrySet() ;
            Iterator it = set1.iterator();
            
            Map.Entry me = (Map.Entry) it.next();
            Noeud n = (Noeud) me.getKey() ;
            ArrayList <Noeud> Neighbors = this.LocalNetwork(n) ;
            
            ArrayList <Noeud> similaire = this.similaire(this.lesNonVoisins(n), n) ;
            if(similaire != null){
                for (Noeud similaire1 : similaire) {
                    Neighbors.add(similaire1) ;
                }
            }
            communauté.put(n, Neighbors) ;
              
            //double val = (double) me.getValue() ;
            //System.out.println("noeud : "+n+ " valeur :"+ val);
            graphTmp.removeVertex(n) ;

            for (Noeud voisin : Neighbors) {
                graphTmp.removeVertex(voisin) ;
            }
        }
        //this.afficheCom1(touslesVoisins);
        return communauté ;
    }
    
}
