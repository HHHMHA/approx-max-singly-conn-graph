package org.thekiddos.operators;

import org.thekiddos.datastructures.EdgeType;
import org.thekiddos.datastructures.Graph;

public class ExtraSourceApproxMSCSF extends SourceApproxMSCSF {
    public ExtraSourceApproxMSCSF( Graph graph ) {
        super( graph );
    }

    @Override
    public Graph operate() {
        var treeEdgesGraph = super.operate();

        var classifiedEdges = new EdgeClassifyDFS( treeEdgesGraph ).operate();

        var result = new Graph();
        result.addVertices( treeEdgesGraph.size() );


        classifiedEdges.stream()
                .filter( e -> e.getType().equals( EdgeType.TREE ) )
                .forEach( e -> result.addEdge( e.getSource(), e.getDestination(), e.getWeight() ) );

        return result;
    }
}
