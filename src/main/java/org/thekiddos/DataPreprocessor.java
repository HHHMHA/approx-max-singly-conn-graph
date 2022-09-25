package org.thekiddos;

import org.thekiddos.datastructures.EdgeType;
import org.thekiddos.datastructures.Graph;
import org.thekiddos.operators.EdgeClassifyDFS;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

public class DataPreprocessor {
    public static void main( String[] args ) throws IOException {

        if ( args.length == 0 ) {
            help();
            return;
        }

        Path[] files = new Path[ args.length ];
        for ( int i = 0; i < args.length; ++i ) {
            files[ i ] = Paths.get( args[ i ] );
        }

        for ( var file : files ) {
            System.out.println("[INFO]: Processing file: " + file.getFileName() );

            Graph graph = Graph.fromPath( file );
            System.out.println("[INFO]: Built Graph" );
            var nonBackEdges = new EdgeClassifyDFS( graph ).operate().stream().filter( e -> !e.getType().equals( EdgeType.BACK ) ).collect( Collectors.toList() );
            System.out.println("[INFO]: Classified Edges" );

            var processedFileName = file.getFileName().toString().substring( 0, file.getFileName().toString().length() - 3 ) + "-acyclic.txt";
            var processedFilePath = file.toAbsolutePath().toString().substring( 0, file.toAbsolutePath().toString().length() - 3 ) + "-acyclic.txt";
            var processedFile = Paths.get( processedFilePath );

            try ( BufferedWriter bufferedWriter = Files.newBufferedWriter(
                    processedFile, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE, StandardOpenOption.WRITE );
                  PrintWriter printWriter = new PrintWriter( bufferedWriter ) ) {

                printWriter.println( "# Directed graph (each unordered pair of nodes is saved once): " + processedFileName );
                printWriter.println( "# Nodes: " + graph.size() + " Edges: " + nonBackEdges.size() );
                printWriter.println( "# FromNodeId\tToNodeId" );

                for ( int i = 0; i < nonBackEdges.size(); ++ i ) {
                    var e = nonBackEdges.get( i );
                    printWriter.println( e.getSource() + "\t" + e.getDestination() );
                    System.out.println( "[INFO] Processed " + i + "/" + nonBackEdges.size() );
                }

                System.out.println("[INFO]: Finished file output is: " + processedFilePath );
            }

        }
    }

    private static void help() {
        System.out.println( "Usage: org.thekiddos.DataPreprocessor first_file_path second_file_path ...etc\n" +
                "example: java org.thekiddos.DataPreprocessor c:\\Amazon0312.txt\n" +
                "Output: Same path with 'acyclic' appended to name." );
    }
}
