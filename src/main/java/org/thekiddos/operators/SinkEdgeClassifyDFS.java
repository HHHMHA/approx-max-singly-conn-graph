package org.thekiddos.operators;

import org.thekiddos.datastructures.Edge;
import org.thekiddos.datastructures.Graph;

import java.util.List;
import java.util.stream.Collectors;

public class SinkEdgeClassifyDFS extends EdgeClassifyDFS {
    private boolean isReversed = false;
    public SinkEdgeClassifyDFS( Graph graph ) {
        super( graph );

        int numOfSources = 0;
        int numOfSinks = 0;

        for ( int i = 0; i < graph.size(); ++i ) {
            if ( graph.isSource( i ) )
                ++numOfSources;
            if ( graph.isSink( i ) )
                ++numOfSinks;
        }

        if (numOfSinks > numOfSources) {
            isReversed = true;
            setGraph( graph.reverse() );
        }
    }

    @Override
    public List<Edge> operate() {
        super.operate();
        if (!isReversed)
            return classifiedEdges;

        return classifiedEdges.stream()
                .map( edge -> new Edge( edge.getDestination(), edge.getSource(), edge.getWeight(), edge.getType() ) )
                .collect( Collectors.toList() );
    }
}
