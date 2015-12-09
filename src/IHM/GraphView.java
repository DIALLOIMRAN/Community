/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Transformer.*;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import graph.Lien;
import graph.MyGraph;
import graph.Noeud;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Hashtable;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author IMRAN-DIALLO
 */
public final class GraphView {
    private Hashtable <Noeud, Noeud> nodeTable = new Hashtable<>();
    private Hashtable <Noeud, Color> nodeCTable = new Hashtable<>();
    private int [][] matriceAdjacence ;
    private MyGraph mygraph ;
    private JPanel panelGraphe ;
    private BasicVisualizationServer <Noeud, Lien> vv ;
    public JPanel getPanelGraphe() {
        return panelGraphe;
    }

    public void setPanelGraphe(JPanel panelGraphe) {
        this.panelGraphe = panelGraphe;
    }
    
    
    Graph <Noeud, Lien> g ;

    public Graph<Noeud, Lien> getG() {
        return g;
    }

    public void setMygraph(MyGraph mygraph) {
        this.mygraph = mygraph;
    }
    
    
    public MyGraph getMygraph() {
        return mygraph;
    }
    
    public int[][] getMatriceAdjacence() {
        return matriceAdjacence;
    }

    public void setMatriceAdjacence(int[][] matriceAdjacence) {
        this.matriceAdjacence = matriceAdjacence;
    }
    
    
    public Hashtable <Noeud, Noeud> getNodeTable() {
        return nodeTable;
    }

    public void setNodeTable(Hashtable <Noeud, Noeud> nodeTable) {
        this.nodeTable = nodeTable;
    }


    private void PopulateNodeDataTable()
    {
        //ArrayList<Noeud> n = new ArrayList<>(g.getVertices()) ;
        Noeud [] noeuds = mygraph.getNoeuds() ;
        for (Noeud noeud : noeuds) {
           nodeTable.put(noeud, noeud) ;
           System.out.println(noeud+ " "+noeud.getValue());
        }
    }

    private void refreshPanel(JPanel pan){
        pan.setSize(800, 500);
        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panelGraphe);
        pan.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,800, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vv, javax.swing.GroupLayout.DEFAULT_SIZE, 500, javax.swing.GroupLayout.DEFAULT_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        panelGraphe.setVisible(true);
        panelGraphe.setBackground(new java.awt.Color(0, 162, 232));
        panelGraphe.setOpaque(false);
    }

    public void setVv(VisualizationViewer<Noeud, Lien> vv) {
        this.vv = vv;
        this.vv.getRenderContext().setVertexLabelTransformer(new labelNoeud(nodeTable));
        this.vv.getRenderContext().setVertexFillPaintTransformer(new PaintNoeud(nodeTable));
            
        this.vv.setBackground(new java.awt.Color(0,120,178));
        this.vv.setForeground(Color.WHITE);
        DefaultModalGraphMouse <String, Number> def = new DefaultModalGraphMouse<>() ;
        vv.setGraphMouse(def);
        def.setMode(ModalGraphMouse.Mode.PICKING);
    }
    
    public GraphView(Graph<Noeud, Lien> gr, MyGraph my, JPanel pan) {
        this.panelGraphe = pan ;
        this.panelGraphe.setSize(800, 500);
        this.mygraph = my ;
        g = gr ;
        PopulateNodeDataTable();
        Dimension viewArea = new Dimension(800, 500);
        Layout <Noeud, Lien> layout ;
        
            //Layout <Noeud, Lien> layout = new RandomLayout <>(gr, viewArea);
            layout = new RandomLayout<>(gr,viewArea);
            //layout.setSize(viewArea);

            
        
        this.setVv(new VisualizationViewer<>(layout, viewArea));
        vv.getRenderContext().setVertexShapeTransformer(new NoeudShape(nodeTable));
        // panel.setBounds(20, 20, 0, 0);
        // panel.setBackground(Color.yellow);
        // panel.getRenderContext().setVertexIconTransformer(new NoeudIcon());

        this.refreshPanel(panelGraphe);
    }
    
    public GraphView(int [][] matriceAdjacence, JPanel pan) {
        this.panelGraphe = pan ;
       this.panelGraphe.setSize(800, 500);
        this.matriceAdjacence = matriceAdjacence ;
        mygraph = new MyGraph(matriceAdjacence) ;
        g = mygraph.getGraph();
        
        PopulateNodeDataTable();
        Dimension viewArea = new Dimension(800, 500);
        
        //Graph <Noeud,Lien> graph = g.createGraph();
        Layout <Noeud,Lien> layout = new RandomLayout<>(g, viewArea);
        this.setVv(new VisualizationViewer<>(layout, viewArea));
        
        this.refreshPanel(panelGraphe);
    } 
    
    public GraphView(Graph<Noeud, Lien> gr, MyGraph my) {
        this.mygraph = my ;
        PopulateNodeDataTable();
        Dimension viewArea = new Dimension(800, 500);
        Layout <Noeud,Lien> layout ;
        if(gr.getVertices().size() < 50){
            layout = new FRLayout<>(gr, viewArea);
            layout.setSize(viewArea);
        }
            
        else{
            layout = new RandomLayout<>(gr, viewArea);
        }
            
        
        VisualizationViewer<Noeud, Lien> panel = new VisualizationViewer<>(layout, viewArea);
            //panel.getRenderContext().setVertexIconTransformer(new NoeudIcon());
        DefaultModalGraphMouse <String, Number> def = new DefaultModalGraphMouse<>() ;
        panel.setGraphMouse(def);
        def.setMode(ModalGraphMouse.Mode.PICKING);
        panel.getRenderContext().setVertexShapeTransformer(new NoeudShape(nodeTable));
        
        
        

        //panel.getRenderContext().setVertexIconTransformer(new NoeudIcon());

        this.constructFrame(panel);
    }
    
    public GraphView(int [][] matriceAdjacence) {
        this.matriceAdjacence = matriceAdjacence ;
        mygraph = new MyGraph(matriceAdjacence) ;
        g = mygraph.getGraph();
        
        
        PopulateNodeDataTable();
        Dimension viewArea = new Dimension(800, 500);
        
        
            //Graph <Noeud,Lien> graph = g.createGraph();
            Layout <Noeud,Lien> layout = new FRLayout<>(g);
            layout.setSize(viewArea);
            //BasicVisualizationServer <Noeud, Lien> panel = new BasicVisualizationServer<>(layout, viewArea);
           
            VisualizationViewer<Noeud, Lien> panel = new VisualizationViewer<>(layout, viewArea);
            //panel.getRenderContext().setVertexIconTransformer(new NoeudIcon());
            DefaultModalGraphMouse <String, Number> def = new DefaultModalGraphMouse<>() ;
            panel.setGraphMouse(def);
            def.setMode(ModalGraphMouse.Mode.PICKING);
            this.constructFrame(panel);
    } 
    
    private void constructFrame(BasicVisualizationServer <Noeud, Lien> panel){
            
        panel.getRenderContext().setVertexLabelTransformer(new labelNoeud(nodeTable));
        panel.getRenderContext().setVertexFillPaintTransformer(new PaintNoeud(nodeTable));
            
        panel.setBackground(new java.awt.Color(0, 162, 232));
        panel.setForeground(Color.WHITE);
        final JFrame frame = new JFrame("Graph View");
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setForeground(Color.WHITE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
