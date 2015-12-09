/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement;

import edu.uci.ics.jung.algorithms.scoring.EigenvectorCentrality;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import graph.Lien;
import graph.MyGraph;
import graph.Noeud;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author IMRAN-DIALLO
 */
public class Algorithme1 extends Algo{
    public Algorithme1(Graph<Noeud, Lien> graphe, MyGraph mg) {
        super(graphe, mg);
        
    }  
    /**
     * Reçoi en parametre une table de hachage non ordonnée
     * @param map et renvoi une table triée par ordre décroissante des 
     * valeurs de centralités des noeuds
     * @return map_apres
     */
    
    public HashMap <Noeud, Double> trierParValeur(HashMap <Noeud, Double> map ){
       List<Map.Entry<Noeud, Double>> list = new LinkedList<>( map.entrySet() );
      
       Collections.sort(list, new Comparator<Map.Entry<Noeud, Double>>(){
          @Override
          public int compare( Map.Entry<Noeud, Double> o1, Map.Entry<Noeud, Double> o2 ){
              return (o2.getValue()).compareTo( o1.getValue());
          }
       });
       
       HashMap<Noeud, Double> map_apres = new LinkedHashMap<>();
       for (Map.Entry<Noeud, Double> entry : list)
         map_apres.put( entry.getKey(), entry.getValue() );
//       System.out.println("After sorting ...");
//       this.afficherMap(map_apres);
       return map_apres;
    } 
    /**
     * Reçoi en parametre les noeuds et leurs centralités
     * @param centralité ordonnés  par ordre décroissante des 
     * valeurs de leurs centralité pour renvoyé @return les noeuds leaders
     * accompagnés de leurs vois sous forme d'un hashtable
     * @return 
     */ 
    @Override
    public HashMap <Noeud, ArrayList <Noeud>> communauté (HashMap <Noeud, Double> centralité){        
        
        
        HashMap <Noeud, ArrayList <Noeud>> touslesVoisins = new HashMap<>() ;
        
        ArrayList <Noeud> keys = new ArrayList<>();
        for (HashMap.Entry <Noeud, Double> entry : centralité.entrySet())
        {
           keys.add(entry.getKey());
        }
//        System.out.println("=================================================");
//        for (Noeud key : keys) {
//            System.out.print(" "+key);
//        }
//        System.out.println("\n================================================");
        for (int ii = 0; ii < keys.size(); ii++)
        {
            
            //int i = 0 ;
            Noeud n = keys.get(ii);
            boolean trouvee = false ;
           
            for (HashMap.Entry <Noeud, ArrayList <Noeud>> entry : touslesVoisins.entrySet()){
                for (Noeud v1 : entry.getValue()) {
                    if(v1.equals(n))
                        trouvee =  true ;
                }
            } 
            
            if(trouvee == false){
                Collection <Noeud> lesVoisin = graphe.getNeighbors(keys.get(ii)) ;
                ArrayList <Noeud> v = new ArrayList <> (lesVoisin) ;
                if(v.size() !=0){
                    int i = 0 ;
                    for (HashMap.Entry<Noeud, ArrayList <Noeud>> entry : touslesVoisins.entrySet()){
                        for (Noeud v1 : entry.getValue()) {
                            if(i< v.size() && v1.equals(v.get(i)))
                                v.remove(v1) ;
                        }
                    } 
                touslesVoisins.put(keys.get(ii), v) ;
                }     
            }
        }

//        System.out.println("Avant");
//        this.afficheCom1(touslesVoisins);
//        System.out.println("Après");
        return touslesVoisins ;
    }  
    
    /**
     * Reçoi en parametre les communautés 
     * @param communauté et construite le réseau en fonction des communautés
     */
   
    /**
     * Reçoi en parametre les noeuds du graphe courant
     * @param noeuds
     * pour renvoyé @return sous forme de hashtable 
     * la liste des noeuds accompagné de leurs degré de centralité 
     * @return 
     */
    
    public HashMap <Noeud, Double> calculCentralité(Noeud[] noeuds) {
         try {
            EigenvectorCentrality <Noeud, Lien> evc;
            HashMap <Noeud, Double> lesCentralités = new HashMap<>() ;
            UndirectedSparseGraph <Noeud, Lien> myGraph = (UndirectedSparseGraph) graphe ;
            evc = new EigenvectorCentrality <>(myGraph);
            evc.acceptDisconnectedGraph(true);
            evc.evaluate();
            
		  
            Double score ;
            for(int ii=0; ii<myGraph.getVertexCount(); ii++){
                score = evc.getVertexScore(noeuds[ii]) ;
                lesCentralités.put(noeuds[ii], score) ;
            }
//               System.out.println("=========== Avant le tri : ============");
//               this.afficherMap(lesCentralités);
//               System.out.println("=========== Après le tri : ============");
               lesCentralités = this.trierParValeur(lesCentralités);
               return lesCentralités ;
        } catch (Exception e) {
             System.out.println("erreur : "+e.getMessage());
            return  null ;
        }
        
    }
    
    
}
