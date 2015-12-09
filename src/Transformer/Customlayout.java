/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transformer;

import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.graph.Graph;
import java.awt.Dimension;

/**
 *
 * @author DIALLO-IMRAN
 * @param <V>
 * @param <E>
 */
public class Customlayout <V,E> extends KKLayout <V,E> {

    public Customlayout(Graph<V,E> g) {
        super(g);
    }
    public Customlayout(Graph<V,E> g , Dimension size) {
        this(g);
        this.setSize(size);
    }
    
    private void layoutNodes() {
    	int clearance = 20;
    	
        Dimension d = getSize();
        int width = d.width - clearance * 2;
        int height = d.height - clearance * 2;

        for (V v : getGraph().getVertices()) {
            if (isLocked(v)) continue;
            double x = width + clearance;
            double y = height + clearance;
            transform(v).setLocation(x, y);
        }
    }
}
