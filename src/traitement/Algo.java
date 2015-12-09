/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import graph.Lien;
import graph.MyGraph;
import graph.Noeud;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Imane
 */
public abstract class Algo {
    
    ArrayList <Noeud> Leaders = new ArrayList<>();
    //ArrayList <ArrayList <Noeud>> voisins = new ArrayList<>();
    ArrayList <Color> lesCouleurs = new ArrayList<>();

    /**
     *
     */
    protected final Graph<Noeud,Lien> graphe ;
    protected int taille = 0;
    protected MyGraph mg ;
    protected Graph <Noeud, Lien> grapheCommunauté ;
    public Graph<Noeud, Lien> getGraphe() {
        return this.graphe;
    }
    public Algo(Graph<Noeud, Lien> graphe, MyGraph mg) {
        this.mg = mg ;
        this.graphe = graphe;
        this.taille = graphe.getVertexCount() ;
        //matriceFinal = new int[taille][taille] ;
    }
    protected Color genererCouleur(){
        Color randomColour;
        do {
            Random randomGenerator = new Random();
            int red = randomGenerator.nextInt(256);
            int green = randomGenerator.nextInt(256);
            int blue = randomGenerator.nextInt(256);
            randomColour = new Color(red,green,blue);


        } while(lesCouleurs.contains(randomColour) && randomColour.equals(Color.BLACK));
        return randomColour ;
    }
    public void setGrapheCommunauté (Graph<Noeud, Lien> grapheCommunauté) {
        this.grapheCommunauté = grapheCommunauté;
    }
    public Graph <Noeud, Lien> getGrapheCommunauté() {
        return grapheCommunauté;
    } 
    public Collection <Noeud> lesVoisins(Noeud n){
        Collection <Noeud> voisins = this.graphe.getNeighbors(n) ;
        return voisins ;
    }
    
    public ArrayList <Noeud> lesNonVoisins(Noeud n){
        ArrayList <Noeud> voisins = new ArrayList<>(this.graphe.getNeighbors(n)) ;
        ArrayList <Noeud> nonVoisins = new ArrayList<>() ;
        for (Noeud voisin : this.graphe.getVertices()) {
            if(!voisins.contains(voisin)){
                nonVoisins.add(voisin) ;
            }
        }
        return nonVoisins ;
    }
    protected HashMap <Noeud, ArrayList <Noeud>> lesLiens(){
        HashMap <Noeud, ArrayList <Noeud>> nVoisins = new HashMap<>() ;
        ArrayList <Noeud> noeuds = new ArrayList<>(graphe.getVertices()) ;
        
        for (Noeud noeud : noeuds) {
           nVoisins.put(noeud, new ArrayList<>(graphe.getNeighbors(noeud))) ;
        }
        return nVoisins ;
    }
    
    protected HashMap <Noeud, ArrayList <Noeud>> liens;
    
    public HashMap <Noeud, ArrayList <Noeud>> communauté (HashMap <Noeud, Double> centralité){ 
        HashMap <Noeud, ArrayList <Noeud>> touslesVoisins = new LinkedHashMap<>() ;
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
            ArrayList <Noeud> Neighbors = new ArrayList<>(graphTmp.getNeighbors(n)) ;

            touslesVoisins.put(n, Neighbors) ;

            //double val = (double) me.getValue() ;
            //System.out.println("noeud : "+n+ " valeur :"+ val);
            graphTmp.removeVertex(n) ;

            for (Noeud voisin : Neighbors) {
                graphTmp.removeVertex(voisin) ;
            }
        }
        //this.afficheCom1(touslesVoisins);
        return touslesVoisins ;
        
    } 
    
    public HashMap <Noeud, ArrayList <Noeud>> communauté (String type){ 
        HashMap <Noeud, ArrayList <Noeud>> touslesVoisins = new LinkedHashMap<>() ;
        //do{
        liens = this.lesLiens() ;
        Graph<Noeud, Lien> graphTmp = graphe ;
        HashMap <Noeud, Double> centralité ;
        while (graphTmp.getVertexCount()!=0) { 
            Centrality.setGraph(graphTmp);
            if(type.equals("eigen"))
                 centralité = Centrality.trierParValeur(Centrality.eigenVectorCentralité());
            else{
                centralité = Centrality.trierParValeur(Centrality.pageRankCentralité());
            }
            Set set1 = centralité.entrySet() ;
            Iterator it = set1.iterator();
            
            Map.Entry me = (Map.Entry) it.next();
            Noeud n = (Noeud) me.getKey() ;
            ArrayList <Noeud> Neighbors = new ArrayList<>(graphTmp.getNeighbors(n)) ;

            touslesVoisins.put(n, Neighbors) ;

            //double val = (double) me.getValue() ;
            //System.out.println("noeud : "+n+ " valeur :"+ val);
            graphTmp.removeVertex(n) ;

            for (Noeud voisin : Neighbors) {
                graphTmp.removeVertex(voisin) ;
            }
        }
        //this.afficheCom1(touslesVoisins);
        return touslesVoisins ;
        
    } 
    
    public ArrayList<ArrayList<String>> tableCommunauté(HashMap <Noeud, ArrayList <Noeud>> centralité) {
        
        ArrayList<ArrayList<String>> retour = new ArrayList<>() ;
        for (HashMap.Entry <Noeud, ArrayList <Noeud>> entry : centralité.entrySet())
        {
            ArrayList<String> lignes = new ArrayList<>() ;
            lignes.add(entry.getKey().toString());
            String voisins = "" ;
            int i=0;
            for (Noeud ligne : entry.getValue()) {
                if(i != 0)
                    voisins += ", ";
                voisins += ligne.toString();
                i++;
            }
            lignes.add(voisins) ;
            retour.add(lignes) ;
        }
        
        return retour;
    }
    
    public ArrayList <ArrayList <String>> tableCentralité(HashMap <Noeud, Double> centralité){
        ArrayList <ArrayList<String>> retour = new ArrayList<>() ;
        for (HashMap.Entry <Noeud, Double> entry : centralité.entrySet())
        {
            ArrayList<String> lignes = new ArrayList<>() ;
            lignes.add(entry.getKey().toString());
            //java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
            
            lignes.add(entry.getValue()+"");
            
            retour.add(lignes) ;
        }
        
        return retour;
    }
    
    public Graph<Noeud, Lien> formerCommunauté(HashMap <Noeud, ArrayList <Noeud>> communauté) {
        grapheCommunauté = new UndirectedSparseGraph <>();
        //int [][] mat = new MyGraph(matriceFinal);
        //GraphView graphView = new GraphView(this.getGraphe(), new MyGraph(this.getGraphe()));
        ArrayList <Noeud> noeuds = new ArrayList<>(this.getGraphe().getVertices()) ;
        
//        for (Noeud noeud : noeuds) {
//            System.out.println(noeud + " : " +noeud.getValue());
//        }
        
        int cpt = 0;
        //System.out.println("====================================================");
        Noeud tmp = null;
        ArrayList <Noeud> sansCommunautes = new ArrayList<>() ;
        for (HashMap.Entry <Noeud, ArrayList <Noeud>> entry : communauté.entrySet())
        {
            Noeud leader = entry.getKey() ;
            ArrayList <Noeud> followers = entry.getValue() ;
            
            Color couleur ;
            if(followers.isEmpty()){
                couleur = Color.WHITE ;
                sansCommunautes.add(leader);
                leader.setCouleur(couleur);
                sansCommunautes.add(leader);
                //System.out.println(leader + " : " +leader.getValue()+" : "+leader.getCouleur().toString());
               
            }else{
                
                couleur = this.genererCouleur();
                leader.setCouleur(couleur);
                leader.setValue("leader");
                lesCouleurs.add(couleur);
                //System.out.println(leader + " : " +leader.getValue()+" : "+leader.getCouleur().toString());
                
                for (Noeud noeud : entry.getValue()) {
                    noeud.setCouleur(leader.getCouleur());
                    //System.out.println(noeud + " : " +noeud.getValue()+" : "+noeud.getCouleur().toString());
                    grapheCommunauté.addVertex(noeud) ;
                    
                    if(cpt<noeuds.size()){
                    noeuds.set(cpt, leader) ;
                        cpt++;
                    }
                }
            }
        }
        cpt = 0;
        for (Noeud noeud : sansCommunautes) {
            noeud.setCouleur(lesCouleurs.get(0));
            grapheCommunauté.addVertex(noeud);
                if(cpt <noeuds.size()){
                    noeuds.set(cpt, noeud) ;
                    cpt++;
                }
        }
        //System.out.println("====================================================");
        int i = 0;
        
        //System.out.println("====================================");
        for (HashMap.Entry <Noeud, ArrayList <Noeud>> entry : liens.entrySet())
        {
               for (Noeud noeud : entry.getValue()) {
                   if(!entry.getKey().isWhite() && !noeud.isWhite()){
                       grapheCommunauté.addEdge(new Lien("e"+i), entry.getKey(), noeud);
                       //System.out.println("e"+i);
//                   }else{
//                       
//                       for(Noeud n: graphe.getNeighbors(entry.getKey())){
//                           grapheCommunauté.addEdge(new Lien("e"+i), entry.getKey(), n);
//                           ArrayList<Noeud> leaders = new ArrayList<>() ;
//                           for(Noeud e1 : noeuds){
//                               if(e1.getID() == n.getID()){
//                                   n.setCouleur(e1.getCouleur());
//                               }
//                               if(e1.getValue().equalsIgnoreCase("leader")){
//                                   leaders.add(e1) ;
//                               }
//                           }
//                       }
                   }else{
                       sansCommunautes.add(entry.getKey()) ;
//                       System.out.println(""+entry.getKey()+" : "+entry.getKey().getCouleur());
                   }
                   
                }
        }
        //System.out.println("====================================");
        return grapheCommunauté ;
    }
}
