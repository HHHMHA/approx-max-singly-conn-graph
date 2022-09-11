package org.thekiddos.operators;

import org.junit.jupiter.api.Test;
import org.thekiddos.datastructures.Edge;
import org.thekiddos.datastructures.EdgeType;
import org.thekiddos.datastructures.Graph;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class EdgeClassifyDFSTest {

    @Test
    void operate() {
        Graph g = new Graph();
        g.addVertices( 7 );

        g.addEdge( 0, 4, 1 );

        g.addEdge( 1, 4, 1 );
        g.addEdge( 1, 6, 1 );

        g.addEdge( 2, 1, 1 );

        g.addEdge( 3, 1, 1 );

        g.addEdge( 4, 3, 1 );
        g.addEdge( 4, 5, 1 );

        g.addEdge( 5, 0, 1 );


        assertEquals(
                List.of( EdgeType.TREE, EdgeType.TREE, EdgeType.TREE, EdgeType.BACK, EdgeType.TREE, EdgeType.TREE, EdgeType.BACK, EdgeType.TREE ),
                new EdgeClassifyDFS( g ).operate().stream().map( Edge::getType ).collect( Collectors.toList() )
        );
    }
}
