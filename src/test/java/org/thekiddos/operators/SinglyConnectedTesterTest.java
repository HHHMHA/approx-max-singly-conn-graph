package org.thekiddos.operators;

import org.junit.jupiter.api.Test;
import org.thekiddos.datastructures.Graph;

import static org.junit.jupiter.api.Assertions.*;

class SinglyConnectedTesterTest {

    @Test
    void operate() {
        // Graphs from https://ijarcce.com/wp-content/uploads/2015/12/IJARCCE-85.pdf

        Graph g1 = new Graph();
        g1.addVertices( 6 );

        g1.addEdge( 0, 1, 1 );
        g1.addEdge( 0, 2, 1 );

        g1.addEdge( 2, 3, 1 );
        g1.addEdge( 2, 4, 1 );

        g1.addEdge( 4, 5, 1 );
        g1.addEdge( 4, 0, 1 );

        assertEquals( true, new SinglyConnectedTester( g1 ).operate() );

        Graph g2 = new Graph();
        g2.addVertices( 5 );

        g2.addEdge( 0, 3, 1 );

        g2.addEdge( 1, 0, 1 );
        g2.addEdge( 1, 2, 1 );

        g2.addEdge( 2, 3, 1 );
        g2.addEdge( 2, 4, 1 );

        g2.addEdge( 3, 1, 1 );

        assertEquals( true, new SinglyConnectedTester( g2 ).operate() );

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

        assertEquals( false, new SinglyConnectedTester( g3 ).operate() );

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

        assertEquals( false, new SinglyConnectedTester( g4 ).operate() );
    }

}
