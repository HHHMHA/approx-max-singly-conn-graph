package org.thekiddos.operators;

import org.thekiddos.datastructures.Edge;
import org.thekiddos.datastructures.EdgeType;
import org.thekiddos.datastructures.Graph;
import org.thekiddos.datastructures.VertexState;
import org.thekiddos.operators.interfaces.GraphOperator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EdgeClassifyDFS extends GraphOperator<List<Edge>> {
    private VertexState[] state;
    private int[] dfsNumber;
    private int dfsCounter;
    List<Edge> classifiedEdges;

    public EdgeClassifyDFS( Graph graph ) {
        super( graph );
    }

    @Override
    public List<Edge> operate() {
        DFSInit();

        for ( int i = 0; i < graphSize(); ++i ) {
            if ( state[ i ] == VertexState.NEW ) {
                DFS( i );
            }
        }

        return classifiedEdges;
    }

    private void DFSInit() {
        dfsCounter = 0;
        state = new VertexState[ graphSize() ];
        dfsNumber = new int[ graphSize() ];
        Arrays.fill( state, VertexState.NEW );
        Arrays.fill( dfsNumber, -1 );
        classifiedEdges = new ArrayList<>();
    }

    private void DFS( int v ) {
        state[ v ] = VertexState.ACTIVE;
        dfsNumber[ v ] = dfsCounter++;

        for ( Edge edge : getOutEdges( v ) ) {
            int end = edge.getDestination();

            Edge classifiedEdge = new Edge( v, end, edge.getWeight() );
            if ( state[ end ] != VertexState.NEW ) {
                if ( unclassifiedEdge( classifiedEdges, edge ) ) {
                    classifyEdge( classifiedEdge );
                    classifiedEdges.add( classifiedEdge );
                }
                continue;
            }

            classifiedEdge.setType( EdgeType.TREE );
            classifiedEdges.add( classifiedEdge );

            DFS( end );
        }

        state[ v ] = VertexState.FINISHED;
    }

    private void classifyEdge( Edge edge ) {
        VertexState currentState = state[ edge.getDestination() ];
        if ( currentState == VertexState.ACTIVE ) {
            edge.setType( EdgeType.BACK );
        } else if ( currentState == VertexState.FINISHED && dfsNumber[ edge.getSource() ] < dfsNumber[ edge.getDestination() ] ) {
            edge.setType( EdgeType.FORWARD );
        } else edge.setType( EdgeType.CROSS );
    }

    private boolean unclassifiedEdge( List<Edge> result, Edge edge ) {
        return !result.contains( edge );
    }
}
