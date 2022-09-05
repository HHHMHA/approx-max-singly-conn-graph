package org.thekiddos.operators;

import org.thekiddos.datastructures.Edge;
import org.thekiddos.datastructures.EdgeType;
import org.thekiddos.datastructures.Graph;
import org.thekiddos.operators.interfaces.GraphOperator;

import java.util.List;

public class ApproxMaxSinglyConnectedSubgraphFinder extends GraphOperator<Graph> {
    private final List<Edge> edges;

    public ApproxMaxSinglyConnectedSubgraphFinder( Graph graph ) {
        super( graph );

        edges = new EdgeClassifyDFS( graph ).operate();

        if ( edges.stream().anyMatch( e -> e.getType().equals( EdgeType.BACK ) ) )
            throw new IllegalArgumentException( "Input graph can't contain back edges!" );

    }

    @Override
    public Graph operate() {
        Graph result = new Graph();
        result.addVertices( graphSize() );

        edges.stream()
                .filter( e -> e.getType().equals( EdgeType.TREE ) )
                .forEach( e -> result.addEdge( e.getSource(), e.getDestination(), e.getWeight() ) );

        if ( !new SinglyConnectedTester( result ).operate() )
            return null;

        edges.stream()
                .filter( e -> !e.getType().equals( EdgeType.TREE ) )
                .forEach( e -> tryAdd( result, e ) );

        return result;
    }

    private void tryAdd( Graph result, Edge e ) {
        Graph temp = result.clone();

        temp.addEdge( e.getSource(), e.getDestination(), e.getWeight() );

        if ( new SinglyConnectedTester( temp ).operate() )
            result.addEdge( e.getSource(), e.getDestination(), e.getWeight() );
    }
}
