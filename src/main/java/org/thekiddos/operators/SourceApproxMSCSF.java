package org.thekiddos.operators;

import org.thekiddos.datastructures.Graph;

public class SourceApproxMSCSF extends TreeApproxMSCSF {
    public SourceApproxMSCSF( Graph graph ) {
        super( graph, new EdgeClassifyDFS( graph ) );
    }

}
