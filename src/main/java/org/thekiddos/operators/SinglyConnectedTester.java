package org.thekiddos.operators;

import org.thekiddos.datastructures.Edge;
import org.thekiddos.datastructures.Graph;
import org.thekiddos.operators.interfaces.GraphOperator;

import java.util.Arrays;

public class SinglyConnectedTester extends GraphOperator<Boolean> {
    boolean[] visited;

    public SinglyConnectedTester( Graph graph ) {
        super( graph );
    }

    @Override
    public Boolean operate() {
        visited = new boolean[ graphSize() ];

        return isSinglyConnected();
    }

    private Boolean isSinglyConnected() {
        for ( int v = 0; v < graphSize(); ++v ) {
            Arrays.fill( visited, false );

            for ( int w = 0; w < graphSize(); ++w ) {
                if ( v == w ) continue;

                if ( getNumberOfPaths( v, w ) > 1 ) {
                    return false;
                }
            }
        }

        return true;
    }

    private int getNumberOfPaths( int v, int w ) {
        if ( v == w ) return 1;

        visited[ w ] = true;

        return getOutEdges( w ).stream()
                .map( Edge::getDestination )
                .filter( end -> !visited[ end ] )
                .map( end -> getNumberOfPaths( v, end ) )
                .mapToInt( Integer::intValue )
                .sum();
    }

}
