package IHM;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import graph.* ;
import traitement.* ;
import edu.uci.ics.jung.graph.Graph ;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author IMRAN-DIALLO
 */
public class Accueil extends javax.swing.JFrame {

    
    private MyGraph mygraph;
    private Graph <Noeud,Lien> grInit ;
    private Algorithme1 hash;
    private Algorithme2 pagerank;
    private Algorithme3 similarité ;
    private int [][]tab = {  
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
    
    public void setGrInit(int [][] tab) {
        this.tab = tab ;
        mygraph = new MyGraph(tab) ;
        grInit = mygraph.getGraph();
    }
    
    public Accueil() {
            initComponents();
            this.setVisible(true);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            
            panel1.setVisible(false);
            panel2.setVisible(false);
    }
    public void refreshPanel(boolean var){
        if(var){
            panel2.setVisible(true);
            panel1.setVisible(false);
        }else{
            panel2.setVisible(false);
            panel1.setVisible(true);
        }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        panel1 = new javax.swing.JPanel();
        panel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtCommunaute = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtCentralite = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Social Network analysis");
        setBackground(new java.awt.Color(102, 102, 102));
        setBounds(new java.awt.Rectangle(100, 5, 0, 0));
        setMaximumSize(new java.awt.Dimension(1100, 650));
        setMinimumSize(new java.awt.Dimension(1100, 650));
        setPreferredSize(new java.awt.Dimension(1100, 650));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane1.setMaximumSize(new java.awt.Dimension(1010, 500));
        jLayeredPane1.setMinimumSize(new java.awt.Dimension(1010, 500));

        panel1.setMaximumSize(new java.awt.Dimension(1010, 500));
        panel1.setMinimumSize(new java.awt.Dimension(1010, 500));
        panel1.setOpaque(false);
        panel1.setPreferredSize(new java.awt.Dimension(1010, 560));

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        panel2.setOpaque(false);

        jScrollPane3.setBackground(new java.awt.Color(204, 204, 255));

        txtCommunaute.setEditable(false);
        txtCommunaute.setColumns(20);
        txtCommunaute.setFont(new java.awt.Font("Arial Unicode MS", 1, 13)); // NOI18N
        txtCommunaute.setRows(5);
        txtCommunaute.setOpaque(false);
        txtCommunaute.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCommunauteCaretUpdate(evt);
            }
        });
        jScrollPane3.setViewportView(txtCommunaute);

        jScrollPane4.setBackground(new java.awt.Color(204, 204, 255));

        txtCentralite.setEditable(false);
        txtCentralite.setColumns(20);
        txtCentralite.setFont(new java.awt.Font("Arial Unicode MS", 1, 13)); // NOI18N
        txtCentralite.setRows(5);
        txtCentralite.setOpaque(false);
        txtCentralite.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCentraliteCaretUpdate(evt);
            }
        });
        jScrollPane4.setViewportView(txtCentralite);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Communautés");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Communautés");

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(0, 36, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jLayeredPane1.setLayer(panel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(panel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 1050, 500));
        jLayeredPane1.getAccessibleContext().setAccessibleName("");

        jLabel2.setFont(new java.awt.Font("Arial Unicode MS", 1, 18)); // NOI18N
        jLabel2.setText("Détection des communautés dans les réseaux sociaux");
        jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153)), "ENSAT", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Arial Unicode MS", 1, 12), new java.awt.Color(153, 204, 255))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 550, 50));

        jLabel1.setBackground(new java.awt.Color(0, 102, 153));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg1.jpg"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 102, 153), new java.awt.Color(0, 102, 153), new java.awt.Color(0, 102, 153), new java.awt.Color(0, 102, 153)));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -30, 1090, 650));

        jMenuBar1.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jMenuBar1.setPreferredSize(new java.awt.Dimension(97, 30));

        jMenu5.setText("Réseau initial");
        jMenu5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu5.setFont(new java.awt.Font("Arial Unicode MS", 1, 12)); // NOI18N
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu5ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        jMenu1.setText("Communautés");
        jMenu1.setFont(new java.awt.Font("Arial Unicode MS", 1, 12)); // NOI18N

        jMenuItem1.setText("EigenVector");
        jMenuItem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem10.setText("PageRank");
        jMenuItem10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10);

        jMenuItem12.setText("Similarité");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem12);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("  Quitter");
        jMenu4.setFont(new java.awt.Font("Arial Unicode MS", 1, 12)); // NOI18N
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //constructGraph() ;
        //hash.communauté1(hash.trierParValeur(hash.calculCentralité(mygraph.getNoeuds()))) ;
        //hash.formerCommunauté(hash.communauté1(hash.trierParValeur(hash.calculCentralité(mygraph.getNoeuds()))));
        
        this.setGrInit(tab);
        hash = new Algorithme1(grInit, mygraph);
        this.setVisible(true);
        Centrality.setGraph(grInit);
        HashMap<Noeud,Double> cent = Centrality.trierParValeur(Centrality.eigenVectorCentralité());
        this.setCentralite(hash.tableCentralité(cent));
        HashMap<Noeud, ArrayList<Noeud>> comm = hash.communauté(cent);
        hash.formerCommunauté(comm);
        this.setComm(hash.tableCommunauté(comm));
        GraphView view =  new GraphView(hash.getGrapheCommunauté(), new MyGraph(hash.getGrapheCommunauté()), panel1) ;
        
        panel1.setVisible(true);
        panel2.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        //.setGraph(grInit);
        panel1.setVisible(true);
        panel2.setVisible(false);
        this.setGrInit(tab);
        Centrality.setGraph(grInit);
        pagerank = new Algorithme2(grInit, mygraph);
        this.setCentralite(pagerank.tableCentralité(Centrality.trierParValeur(Centrality.pageRankCentralité())));
        HashMap<Noeud, ArrayList<Noeud>> comm = pagerank.communauté("rank") ;
        pagerank.formerCommunauté(comm);
        this.setComm(pagerank.tableCommunauté(comm));
        GraphView view =  new GraphView(pagerank.getGrapheCommunauté(), new MyGraph(pagerank.getGrapheCommunauté()), panel1) ;
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed
//        mygraph = new MyGraph(tab) ;
//        grInit = mygraph.getGraph();
//        hash = new Algorithme1(grInit, mygraph) ;
//        GraphView view =  new GraphView(tab) ;
        //centralité.setVisible(true);
        //communauté.setVisible(true);
    }//GEN-LAST:event_jMenu5ActionPerformed

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
//      
        this.setGrInit(tab);
        GraphView view =  new GraphView(tab, panel1) ;
        panel1.setVisible(true);
        panel2.setVisible(false);
    }//GEN-LAST:event_jMenu5MouseClicked

    private void setCentralite(ArrayList <ArrayList<String>> arrays){
        panel1.setVisible(true);
        panel2.setVisible(true);
        try{
            this.setGrInit(tab);
            hash = new Algorithme1(grInit, mygraph);
            Centrality.setGraph(grInit);
            // ArrayList <ArrayList<String>> arrays = 
            // hash.tableCentralité(Centrality.trierParValeur(Centrality.eigenVectorCentralité())) ;
            String msg = "";
            if(arrays != null){
                
                for (int i=0; i<arrays.size() ; i++)
                {
                    
                    String noeud = arrays.get(i).get(0);

                    String Centralité = arrays.get(i).get(1);
                    
                    msg += noeud + " : "+Centralité+ " \n";
                }
                txtCentralite.setText(msg);
            }else{
                System.out.println("Aucune donnée n'est retourné !");
            }
            
            
        }catch(Exception ex){
            JOptionPane.showConfirmDialog(this, "une erreur est survenue : "+ex.getMessage()) ;
        }
    }
    
    private void setComm(ArrayList <ArrayList<String>> arrays){
        panel1.setVisible(true);
        panel2.setVisible(true);
        try{
            this.setGrInit(tab);
            hash = new Algorithme1(grInit, mygraph);
            Centrality.setGraph(grInit);
            // ArrayList <ArrayList<String>> arrays = 
            // hash.tableCentralité(Centrality.trierParValeur(Centrality.eigenVectorCentralité())) ;
            String msg = "";
            if(arrays != null){
                
                for (int i=0; i<arrays.size() ; i++)
                {
                    
                    String noeud = arrays.get(i).get(0);

                    String Centralité = arrays.get(i).get(1);
                    
                    msg += noeud + " : "+Centralité+ " \n";
                }
                txtCommunaute.setText(msg);
            }else{
                System.out.println("Aucune donnée n'est retourné !");
            }
            
            
        }catch(Exception ex){
            JOptionPane.showConfirmDialog(this, "une erreur est survenue : "+ex.getMessage()) ;
        }
    }
    
    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed

        this.setGrInit(tab);
        similarité = new Algorithme3 (grInit, mygraph);
        this.setCentralite(similarité.tableCentralité(Centrality.trierParValeur(Centrality.pageRankCentralité())));
        HashMap<Noeud, ArrayList<Noeud>> comm = similarité.communauté("rank") ;
        similarité.formerCommunauté(comm);
        this.setComm(similarité.tableCommunauté(comm));
        GraphView view = new GraphView(similarité.getGrapheCommunauté(), new MyGraph(similarité.getGrapheCommunauté()), panel1) ;
        
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void txtCommunauteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCommunauteCaretUpdate

    }//GEN-LAST:event_txtCommunauteCaretUpdate

    private void txtCentraliteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCentraliteCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCentraliteCaretUpdate

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
        dispose();
        Index idx = new Index();
        idx.setVisible(true);
    }//GEN-LAST:event_jMenu4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Accueil().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JTextArea txtCentralite;
    private javax.swing.JTextArea txtCommunaute;
    // End of variables declaration//GEN-END:variables
}
