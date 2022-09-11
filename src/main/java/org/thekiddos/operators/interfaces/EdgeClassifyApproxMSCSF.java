package org.thekiddos.operators.interfaces;

import org.thekiddos.datastructures.Edge;
import org.thekiddos.datastructures.EdgeType;
import org.thekiddos.datastructures.Graph;
import org.thekiddos.operators.EdgeClassifier;

import java.util.List;

public abstract class EdgeClassifyApproxMSCSF extends GraphOperator<Graph> {
    protected final List<Edge> edges;
    protected final EdgeClassifier edgeClassifier;

    public EdgeClassifyApproxMSCSF( Graph graph, EdgeClassifier edgeClassifier ) {
        super( graph );

        edges = edgeClassifier.operate();
        this.edgeClassifier = edgeClassifier;

        if ( edges.stream().anyMatch( e -> e.getType().equals( EdgeType.BACK ) ) )
            throw new IllegalArgumentException( "Input graph can't contain back edges!" );

    }
}
