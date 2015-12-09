package graph;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

/**
 * 
 * @author IMRAN-DIALLO
 */
public class MyGraph {
    private int [][] matriceAdjacence ;
    private Noeud [] noeuds ;
    private Graph <Noeud,Lien> graph ;
    public Noeud[] getNoeuds() {
        return noeuds;
    }
    public void setNoeuds(Noeud[] noeuds) {
        this.noeuds = noeuds;
    }
    public void setMatriceAdjacence(int[][] matriceAdjacence) {
        this.matriceAdjacence = matriceAdjacence;
        
    }
    public MyGraph(Graph <Noeud, Lien> graph) {
        this.graph = graph ;
        noeuds = new Noeud[this.graph.getVertexCount()] ;
        int i=0 ;
        for (Noeud noeud: this.graph.getVertices()) 
        {
            noeuds [i] = noeud ;
            i++ ;
        }
        
    }
    public int[][] getMatriceAdjacence() {
        return matriceAdjacence;
    }
    public MyGraph(int[][] matriceAdjacence) {
        this.matriceAdjacence = matriceAdjacence;
        noeuds = new Noeud[matriceAdjacence.length] ;
    }
    public Graph<Noeud, Lien> getGraph() {
        return this.createGraph();
    }
    private Graph <Noeud,Lien> createGraph() {
        
        graph = new UndirectedSparseGraph<>();
    
        for(int i=0; i <matriceAdjacence.length; i++){
            Noeud n = new Noeud(i+1);
            graph.addVertex(n) ;
            noeuds [i] = n ;
        }
        
        int edge = 1 ;
        for(int i=0; i<matriceAdjacence.length; i++){
            for(int j=0; j<matriceAdjacence[0].length; j++){
                if(matriceAdjacence[i][j] == 1){
                    if (graph.getVertices().contains("e"+edge) == false){
                        graph.addEdge(new Lien("e"+edge*matriceAdjacence.length+j), noeuds[i], noeuds[j]);
                        edge++ ;
                    }
                    
                }
            }
        }
        return graph;
	}
    public void afficheNoeud(){
        for (Noeud noeud : noeuds) {
            System.out.print(noeud+"  ");
        }
        System.out.println("  ");
    }
}