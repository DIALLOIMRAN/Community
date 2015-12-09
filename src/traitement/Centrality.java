package traitement ;

import edu.uci.ics.jung.algorithms.scoring.EigenvectorCentrality;
import java.util.HashMap;
import java.util.Map;
import edu.uci.ics.jung.algorithms.scoring.PageRank;
import edu.uci.ics.jung.graph.Graph;
import graph.Lien;
import graph.Noeud;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * Program that computes PageRank for a graph using the <a
 * href="http://jung.sourceforge.net/">JUNG</a> package (2.0 alpha1). Program
 * takes two command-line arguments: the first is a file containing the graph
 * data, and the second is the random jump factor (a typical setting is 0.15).
 * </p>
 * 
 * <p>
 * The graph should be represented as an adjacency list. Each line should have
 * at least one token; tokens should be tab delimited. The first token
 * represents the unique id of the source node; subsequent tokens represent its
 * link targets (i.e., outlinks from the source node). For completeness, there
 * should be a line representing all nodes, even nodes without outlinks (those
 * lines will simply contain one token, the source node id).
 * </p>
 */
public class Centrality {
    private static Graph <Noeud, Lien> graph;
    public static void setGraph(Graph<Noeud, Lien> graph) {
        Centrality.graph = graph;
    }
    public static Graph<Noeud, Lien> getGraph() {
        return graph;
    }
    public static HashMap <Noeud, Double> pageRankCentralité (){
        if(graph == null)
            return null ;
        //int maxIterations = 30;
        //double tolerance = 0.0000001d;
        double alpha = 0.15d;

        PageRank<Noeud, Lien> ranker = new PageRank<>(graph, alpha);
        //ranker.setTolerance(tolerance) ;
        //ranker.setMaxIterations(maxIterations);

        ranker.evaluate();

        double score ;	
        //System.out.println("========== PageRang Centrality (JUNG) =========");
        HashMap <Noeud, Double> rankers = new HashMap<>();
        for (Noeud v : graph.getVertices()) {
            score = ranker.getVertexScore(v) ;
            rankers.put(v, score);
            //System.out.println("pagerang(" + v + ") = " + score);
        }
        //System.out.println("===============================================");
        return rankers;
    }
    public static HashMap <Noeud, Double> eigenVectorCentralité () {
     try {
        if(graph == null)
            return null ;
        EigenvectorCentrality <Noeud, Lien> evc;
        HashMap <Noeud, Double> lesCentralités = new HashMap<>() ;

        evc = new EigenvectorCentrality <> (graph);
        evc.evaluate();

        double score;

        Collection<Noeud> noeuds = graph.getVertices();
        Iterator<Noeud> nodeItr = noeuds.iterator();
        Noeud tmp;
        System.out.println("======== Eigenvector Centrality (JUNG) =========");
        while(nodeItr.hasNext())
        {
            tmp = nodeItr.next();
            score = evc.getVertexScore(tmp);

            System.out.println("Eigenvector(" + tmp + ") = " + score);
            lesCentralités.put(tmp, score) ;
        }
        System.out.println("================================================");
        return lesCentralités ;
    } catch (Exception e) {
         System.out.println("erreur : "+e.getMessage());
        return  null ;
    }

}
    public static HashMap <Noeud, Double> trierParValeur(HashMap <Noeud, Double> map ){
    List<Map.Entry <Noeud, Double>> list = new LinkedList<>(map.entrySet());

    Collections.sort(list, new Comparator<Map.Entry <Noeud, Double>>(){
       @Override
       public int compare( Map.Entry<Noeud, Double> o1, Map.Entry<Noeud, Double> o2 ){
           return (o2.getValue()).compareTo( o1.getValue());
       }
    });
//   System.out.println("=============== Ater sorting ==================");
   HashMap<Noeud, Double> map_apres = new LinkedHashMap <>();
   for (Map.Entry<Noeud, Double> entry : list){
       map_apres.put( entry.getKey(), entry.getValue() );
//       System.out.println("centrality(" + entry.getKey() + ") = " + entry.getValue() );
   }
//   System.out.println("===============================================");  
   return map_apres;
}
    private static void afficherMap (HashMap <Noeud, Double> centralité) {
            Set set1 = centralité.entrySet() ;
            System.out.println("--------------------------------");
                Iterator it = set1.iterator();
                while(it.hasNext()) {
                    Map.Entry me = (Map.Entry)it.next();
                    System.out.print(me.getKey() + ": ");
                    java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
                    System.out.println(df.format(me.getValue()));
                }
            System.out.println("--------------------------------");
        }
}