package org.thekiddos;

import org.thekiddos.datastructures.Graph;
import org.thekiddos.operators.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.Instant;

public class AlgorithmsComparer {
    public static void main( String[] args ) throws URISyntaxException, IOException {
        Graph[] dataset = {
                Graph.fromPath(
                        Paths.get(
                                AlgorithmsComparer.class.getClassLoader().getResource( "dataset/custom-mini-acyclic.txt" ).toURI()
                        )
                ),
                Graph.fromPath(
                        Paths.get(
                                AlgorithmsComparer.class.getClassLoader().getResource( "dataset/p2p-Gnutella04-acyclic.txt" ).toURI()
                        )
                ),
                Graph.fromPath(
                        Paths.get(
                                AlgorithmsComparer.class.getClassLoader().getResource( "dataset/soc-Epinions1-acyclic.txt" ).toURI()
                        )
                ),
        };

        var outputFile = Paths.get( "output.csv" );
        try ( BufferedWriter bufferedWriter = Files.newBufferedWriter(
                outputFile, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE, StandardOpenOption.WRITE );
              PrintWriter printWriter = new PrintWriter( bufferedWriter ) ) {

            printWriter.println( "algo, nodes, edges, edges kept, time (ms)" );

            // TODO: we can use factory and poly here
            for ( var graph : dataset ) {
                printWriter.print( "SourceApproxMSCSF, " + graph.size() + ", " + graph.getEdges().size() + ", " );
                var start = Instant.now();
                var result = new SourceApproxMSCSF( graph ).operate();
                var end = Instant.now();
                printWriter.println( result.getEdges().size() + ", " + Duration.between( start, end ).toMillis() );

                printWriter.print( "SinkApproxMSCSF, " + graph.size() + ", " + graph.getEdges().size() + ", " );
                start = Instant.now();
                result = new SinkApproxMSCSF( graph ).operate();
                end = Instant.now();
                printWriter.println( result.getEdges().size() + ", " + Duration.between( start, end ).toMillis() );

                printWriter.print( "ExtraSourceApproxMSCSF, " + graph.size() + ", " + graph.getEdges().size() + ", " );
                start = Instant.now();
                result = new ExtraSourceApproxMSCSF( graph ).operate();
                end = Instant.now();
                printWriter.println( result.getEdges().size() + ", " + Duration.between( start, end ).toMillis() );

                printWriter.print( "NonSourceSinkApproxMSCSF, " + graph.size() + ", " + graph.getEdges().size() + ", " );
                start = Instant.now();
                result = new NonSourceSinkApproxMSCSF( graph ).operate();
                end = Instant.now();
                printWriter.println( result.getEdges().size() + ", " + Duration.between( start, end ).toMillis() );

                printWriter.print( "AddApproxMSCSF, " + graph.size() + ", " + graph.getEdges().size() + ", " );
                try {
                    start = Instant.now();
                    result = new AddApproxMSCSF( graph ).operate();
                    end = Instant.now();
                    printWriter.println( result.getEdges().size() + ", " + Duration.between( start, end ).toMillis() );
                }
                catch ( StackOverflowError e ) {
                    printWriter.println( "error, Stack Overflow" );
                }
            }
        }
    }
}
