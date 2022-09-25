package org.thekiddos.operators;

import org.thekiddos.datastructures.Edge;
import org.thekiddos.datastructures.EdgeType;
import org.thekiddos.datastructures.Graph;
import org.thekiddos.datastructures.VertexState;
import org.thekiddos.operators.interfaces.EdgeClassifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NonSourceSinkEdgeClassifyDFS extends EdgeClassifyDFS {
    public NonSourceSinkEdgeClassifyDFS( Graph graph ) {
        super( graph );
    }

    @Override
    public List<Edge> operate() {
        DFSInit();

        for ( int i = 0; i < graphSize(); ++i ) {
            if ( getState( i ) == VertexState.NEW && !getGraph().isSource( i ) && !getGraph().isSink( i ) ) {
                DFS( i );
            }
        }

        return classifiedEdges;
    }
}
