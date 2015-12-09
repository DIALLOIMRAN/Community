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
import java.util.HashMap;

/**
 *
 * @author Imane
 */
public class Algorithme2 extends Algo{
    
    public Algorithme2(Graph<Noeud, Lien> graphe, MyGraph mg) {
        super(graphe, mg);
    }
    

//    @Override
//    public HashMap <Noeud, ArrayList <Noeud>> communauté (){ 
//        HashMap <Noeud, ArrayList <Noeud>> touslesVoisins = new LinkedHashMap<>() ;
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
//            ArrayList <Noeud> Neighbors = new ArrayList<>(graphTmp.getNeighbors(n)) ;
//
//            touslesVoisins.put(n, Neighbors) ;
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
//        return touslesVoisins ;
//        
//    } 
    
    private void afficheCom1(HashMap <Noeud, ArrayList <Noeud>> communauté){
        
        
        for (HashMap.Entry <Noeud, ArrayList <Noeud>> entry : communauté.entrySet())
        {
            if(entry.getValue().size() != 0){
                System.out.print("noued : "+entry.getKey()+". Ses voisins : ");
               for (Noeud col : entry.getValue()) {
                   System.out.print(col+" ");
                }
                System.out.println(" ");
            }else{
                System.out.println("noued : "+entry.getKey()+" n'est pas un leader !");
            }
                
        }
        
        
    }
    
    
    
//    
}
