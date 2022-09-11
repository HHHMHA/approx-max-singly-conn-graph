package org.thekiddos.operators;

import org.thekiddos.datastructures.Edge;
import org.thekiddos.datastructures.EdgeType;
import org.thekiddos.datastructures.Graph;

public class AddApproxMSCSF extends SourceApproxMSCSF {
    public AddApproxMSCSF( Graph graph ) {
        super( graph );
    }

    @Override
    public Graph operate() {
        Graph result = super.operate();

        if ( result == null )
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
