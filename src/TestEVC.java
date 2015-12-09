
import IHM.GraphView;
import graph.MyGraph;
import graph.Noeud;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import traitement.Algorithme3;
import traitement.Centrality;
import traitement.MyFilter;

/**
 * 
 */

/**
 * 
 * @author IMRAN-DIALLO
 */
    public class TestEVC {
	public static void main(String[] args){
            int [][] matr ={
                                {0,1,1,1,1,1,1,1,0,0,0,1,1,0},
                                {1,0,0,0,0,0,0,0,0,0,0,1,1,1},
                                {1,0,0,0,0,0,0,0,0,0,0,0,0,0}, 
                                {1,0,0,0,1,0,0,0,0,0,0,0,0,1},
                                {1,0,0,1,0,1,1,0,0,0,0,0,0,0},
                                {1,0,0,1,1,0,0,0,0,0,0,0,0,0},
                                {1,0,0,0,0,0,0,0,0,0,0,1,0,1},
                                {1,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                {0,0,0,0,0,0,0,0,0,0,1,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,0,0,1,0}, 
                                {0,0,0,0,0,0,0,0,0,0,0,1,0,0},
                                {1,1,0,0,0,0,0,0,0,0,1,0,1,1},
                                {1,1,0,0,1,0,0,0,0,0,0,1,0,1},
                                {0,1,0,0,0,0,0,0,0,0,1,1,1,0}
                            } ;
            int [][] matr1 ={   {0,1,1},
                                {1,0,0},
                                {1,0,0}
                            } ;
            
            int [][] matr3 ={   {1,1,0,0,1,0},
                                {1,0,1,0,1,0},
                                {0,1,0,1,0,0},
                                {0,0,1,0,1,1},
                                {1,1,0,1,0,0},
                                {0,0,0,1,0,0}
                            } ;
            int [][] matr4 = {
                                {0,1,0,0,0},
                                {1,0,1,0,1},
                                {0,1,0,1,0},
                                {0,0,1,0,1},
                                {0,1,0,1,0}
                            };
                    //MyGraph g = new MyGraph(matr) ;
            
            int [][] matr5 = {  
                                {0,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0},
                                {0,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
                                {1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0},
                                {1,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0},
                                {1,0,0,0,0,1,0,1,1,1,0,0,0,0,1,0,1,1,1,0,0,0,0,1,0,1,1,1,0,0,0,0,1,0,1,1},
                                {1,0,0,0,1,0,1,0,1,1,0,0,0,1,0,1,0,1,1,0,0,0,1,0,1,0,1,1,0,0,0,1,0,1,0,1},
                                {1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0},
                                {0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1},
                                {0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0},
                                {0,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0},
                                {0,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
                                {1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0},
                                {1,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0},
                                {1,0,0,0,0,1,0,1,1,1,0,0,0,0,1,0,1,1,1,0,0,0,0,1,0,1,1,1,0,0,0,0,1,0,1,1},
                                {1,0,0,0,1,0,1,0,1,1,0,0,0,1,0,1,0,1,1,0,0,0,1,0,1,0,1,1,0,0,0,1,0,1,0,1},
                                {1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0},
                                {0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1},
                                {0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0},
                                {0,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0},
                                {0,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
                                {1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0},
                                {1,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0},
                                {1,0,0,0,0,1,0,1,1,1,0,0,0,0,1,0,1,1,1,0,0,0,0,1,0,1,1,1,0,0,0,0,1,0,1,1},
                                {1,0,0,0,1,0,1,0,1,1,0,0,0,1,0,1,0,1,1,0,0,0,1,0,1,0,1,1,0,0,0,1,0,1,0,1},
                                {1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0},
                                {0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1},
                                {0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0},
                                {0,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0},
                                {0,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
                                {1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0},
                                {1,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0},
                                {1,0,0,0,0,1,0,1,1,1,0,0,0,0,1,0,1,1,1,0,0,0,0,1,0,1,1,1,0,0,0,0,1,0,1,1},
                                {1,0,0,0,1,0,1,0,1,1,0,0,0,1,0,1,0,1,1,0,0,0,1,0,1,0,1,1,0,0,0,1,0,1,0,1},
                                {1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0},
                                {0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1},
                                {0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0}
                            } ;
            //Graph <Noeud, Lien> graph = g.createGraph();
            GraphView view = new GraphView(matr5);
            Centrality.setGraph(view.getG());
            
//          Centrality.trierParValeur(Centrality.eigenVectorCentralité()) ;
//          HashMap <Noeud, Double> rank = Centrality.trierParValeur(Centrality.pageRankCentralité()) ;
            Algorithme3 algo = new Algorithme3(view.getG(), view.getMygraph());
           
            
//          algo.afficheCom1(algo.communauté());
            ArrayList<ArrayList<Double>> probas = algo.lesProbas(algo.lesNonVoisins(algo.leader()), algo.leader()) ;
            algo.formerCommunauté(algo.communauté("rank"));
            
            GraphView view1 = new GraphView(algo.getGrapheCommunauté(), new MyGraph(algo.getGrapheCommunauté())) ;
//            System.out.println("\n===================");
//            System.out.println("degre("+algo.leader()+") = "+algo.degreeNoeud(algo.leader()));
//            System.out.println("\n===================");
//            System.out.println("degreReseau("+algo.leader()+") = "+algo.degreReseauLocal(algo.leader()));
//            System.out.println("\n===================");
//            double proba = ((double) algo.degreeNoeud(algo.leader()))/algo.degreReseauLocal(algo.leader()) ;
//            System.out.println("\n===================");
//            System.out.println("proba("+algo.leader()+") = "+proba);
//            System.out.println("\n===================");
//            algo.afficheProbas(probas);
//            double er ;
//            System.out.println("\n=========================================");
//            for(int i=0; i < probas.size(); i++){
//                er = algo.entropieRelative(algo.ProbaLeader(algo.leader(), algo.leader()), probas.get(i));
//                System.out.println("ER(P("+algo.leader().getID()+")/P("+i+") = "+er);
//            }
//            System.out.println("\n==========================================");
//            System.out.println("\n=========================================");
//            
//            for(int i=0; i < probas.size(); i++){
//                er = algo.entropieRelative(probas.get(i), algo.ProbaLeader(algo.leader(), algo.leader())) ;
//                System.out.println("ER(P("+i+")/P("+algo.leader().getID()+") = "+er);
//            }
//            System.out.println("\n==========================================");
	}
        private static int[][]choisir(){
            JFileChooser fileChooser = new JFileChooser() ;
            fileChooser.setFileFilter(new MyFilter());
            int returnVal = fileChooser.showOpenDialog(null);
            int [][]matr = null ;
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                BufferedReader br = null;
                try {
                    File file = fileChooser.getSelectedFile() ;

                    BufferedReader filebr = new BufferedReader(new FileReader(file));
                    int linesCount = 1;
                    int columnsCount = 0;

                    String getline = "";
                    getline = filebr.readLine();
                    String[] splitResult = getline.split(";");

                    int taille = splitResult.length;
  //                System.out.println("nbMachines: " + nbMachines);

                    while(filebr.readLine() != null)  {
                        linesCount++;
                    }

                        String line = "";
                        String cvsSplitBy = ";";

                        matr = new int[taille][taille];


                        br = new BufferedReader(new FileReader(file));
                        int j = 0;
                        while ((line = br.readLine()) != null) {
                            String[] split = line.split(cvsSplitBy);
                            for (int i = 0; i < split.length; i++) {
                                if(Integer.parseInt(split[i])== 1){
                                    matr[j][i] = Integer.parseInt(split[i]);
                                }
                            }
                            j++;
                        }
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "Ce fichier n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return  null ;
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Erreur de lecture", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return  null ;
                    } finally {
                        if (br != null) {
                            try {
                                br.close();
                                
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "Erreur de lecture", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
           return matr ;
        }
        
}
