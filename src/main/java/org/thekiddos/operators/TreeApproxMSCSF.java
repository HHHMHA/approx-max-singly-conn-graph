package org.thekiddos.operators;

import org.thekiddos.datastructures.EdgeType;
import org.thekiddos.datastructures.Graph;
import org.thekiddos.operators.interfaces.EdgeClassifier;
import org.thekiddos.operators.interfaces.EdgeClassifyApproxMSCSF;

public abstract class TreeApproxMSCSF extends EdgeClassifyApproxMSCSF {
    public TreeApproxMSCSF( Graph graph, EdgeClassifier edgeClassifier ) {
        super( graph, edgeClassifier );
    }

    @Override
    public Graph operate() {
        Graph result = new Graph();
        result.addVertices( graphSize() );

        edges.stream()
                .filter( e -> e.getType().equals( EdgeType.TREE ) )
                .forEach( e -> result.addEdge( e.getSource(), e.getDestination(), e.getWeight() ) );

        return result;
    }
}
