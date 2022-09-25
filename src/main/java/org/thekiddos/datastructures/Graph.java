package org.thekiddos.datastructures;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Graph implements Cloneable {
    private final List<List<Edge>> adjacencyList = new ArrayList<>();
    private final List<Integer> inDegree = new ArrayList<>();

    public static Graph fromPath( Path path ) throws IOException {
        Graph graph = new Graph();

        try ( Scanner scanner = new Scanner( path ) ) {
            scanner.useDelimiter( "\n" );

            while ( scanner.hasNext() ) {
                var line = scanner.next();

                if ( hasNodeCount( line ) ) {
                    graph.addVertices( getNodeCount( line ) );
                }

                if ( isComment( line ) || line.isEmpty() ) {
                    continue;
                }

                var splitLine = line.split( "\\s+" );
                var source = Integer.parseInt( splitLine[ 0 ] );
                var dest = Integer.parseInt( splitLine[ 1 ] );
                var weight = 1;
                if ( splitLine.length >= 3 ) {
                    weight = Integer.parseInt( splitLine[ 2 ] );
                }
                graph.addEdge( source, dest, weight );
            }
        }

        return graph;
    }

    // BEGIN HELPER METHODS FOR READING GRAPH FROM FILE
    // Consider a subclass for that or an operator
    private static int getNodeCount( String line ) {
        int nodeCountIndex = 2;
        return Integer.parseInt( line.split( "\\s+" )[ nodeCountIndex ] );
    }

    private static boolean hasNodeCount( String line ) {
        return line.contains( "Nodes:" );
    }

    private static boolean isComment( String line ) {
        return line.startsWith( "#" );
    }
    // END HELPER METHODS FOR READING GRAPH FROM FILE

    public void addVertex() {
        adjacencyList.add( new ArrayList<>() );
        inDegree.add( 0 );
    }

    public void addVertices( int number ) {
        for ( int i = 0; i < number; ++i ) {
            adjacencyList.add( new ArrayList<>() );
            inDegree.add( 0 );
        }
    }

    public void addEdge( int source, int destination, int weight ) throws IllegalArgumentException {
        validateVertices( source, destination );
        List<Edge> sourceOutEdges = getOutEdges( source );
        sourceOutEdges.add( new Edge( source, destination, weight ) );
        inDegree.set( destination, inDegree.get( destination ) + 1 );
    }

    public List<Edge> getOutEdges( int vertexIndex ) throws IndexOutOfBoundsException {
        return adjacencyList.get( vertexIndex );
    }

    private void validateVertices( int... vertices ) throws IllegalArgumentException {
        // TODO: we can simply check largest vertex and compare with size
        // TODO: use Validators with context instead
        for ( int vertex : vertices ) {
            try {
                // get will check if vertex exists here since it will throw an exception
                adjacencyList.get( vertex );
            } catch ( IndexOutOfBoundsException e ) {
                throw new IllegalArgumentException( "Vertex " + vertex + " doesn't exist" );
            }
        }
    }

    public void addBidirectionalEdge( int source, int destination, int weight ) throws IllegalArgumentException {
        addEdge( source, destination, weight );
        addEdge( destination, source, weight );
    }

    public int size() {
        return adjacencyList.size();
    }

    @Override
    public Graph clone() {
        Graph result = new Graph();
        result.addVertices( this.size() );

        for ( int i = 0 ; i < this.size(); ++i ) {
            this.getOutEdges( i ).forEach( e -> result.addEdge( e.getSource(), e.getDestination(), e.getWeight() ) );
        }

        return result;
    }

    public List<Edge> getEdges() {
        return adjacencyList.stream().flatMap( Collection::stream ).collect( Collectors.toList() );
    }

    @Override
    public String toString() {
        return "Graph{" +
                "adjacencyList=" + adjacencyList +
                '}';
    }


    /**
     * @param vertex the vertex index
     * @return True if the in-degree is 0
     */
    public boolean isSource( int vertex ) {
        return inDegree.get( vertex ) == 0;
    }

    /**
     * @param vertex the vertex index
     * @return True if the out-degree is 0
     */
    public boolean isSink( int vertex ) {
        return getOutEdges( vertex ).isEmpty();
    }

    public Graph reverse() {
        Graph result = new Graph();
        result.addVertices( this.size() );

        for ( int i = 0 ; i < this.size(); ++i ) {
            this.getOutEdges( i ).forEach( e -> result.addEdge( e.getDestination(), e.getSource(), e.getWeight() ) );
        }

        return result;
    }
}
