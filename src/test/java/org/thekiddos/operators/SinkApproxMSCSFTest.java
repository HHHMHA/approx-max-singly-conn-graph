package org.thekiddos.operators;

import org.junit.jupiter.api.Test;
import org.thekiddos.datastructures.Graph;

import static org.junit.jupiter.api.Assertions.*;

class SinkApproxMSCSFTest {

    @Test
    void operate() {
        Graph g3 = new Graph();
        g3.addVertices( 8 );

        g3.addEdge( 0, 1, 1 );
        g3.addEdge( 0, 2, 1 );
        g3.addEdge( 0, 3, 1 );

        g3.addEdge( 1, 2, 1 );

        g3.addEdge( 2, 4, 1 );
        g3.addEdge( 2, 5, 1 );

        g3.addEdge( 3, 4, 1 );

        g3.addEdge( 4, 5, 1 );

        g3.addEdge( 6, 1, 1 );
        g3.addEdge( 6, 7, 1 );

        Graph g3Expected = new Graph();
        g3Expected.addVertices( 8 );

        g3Expected.addEdge( 0, 1, 1 );
        g3Expected.addEdge( 0, 3, 1 );

        g3Expected.addEdge( 1, 2, 1 );

        g3Expected.addEdge( 2, 4, 1 );

        g3Expected.addEdge( 4, 5, 1 );

        // order important or we can compare sets instead of lists in assert
        g3Expected.addEdge( 6, 7, 1 );

        assertEquals( g3Expected.getEdges(), new SourceApproxMSCSF( g3 ).operate().getEdges() );

        Graph g4 = new Graph();
        g4.addVertices( 8 );

        g4.addEdge( 0, 1, 1 );
        g4.addEdge( 0, 7, 1 );

        g4.addEdge( 1, 2, 1 );

        g4.addEdge( 2, 4, 1 );

        g4.addEdge( 3, 1, 1 );
        g4.addEdge( 3, 2, 1 );

        g4.addEdge( 4, 0, 1 );
        g4.addEdge( 4, 3, 1 );

        g4.addEdge( 5, 0, 1 );
        g4.addEdge( 5, 6, 1 );
        g4.addEdge( 5, 7, 1 );

        g4.addEdge( 6, 1, 1 );

        g4.addEdge( 7, 1, 1 );

        assertThrows( IllegalArgumentException.class, () -> new SourceApproxMSCSF( g4 ).operate() );
    }
}
