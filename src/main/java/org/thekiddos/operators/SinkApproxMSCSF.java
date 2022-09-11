package org.thekiddos.operators;

import org.thekiddos.datastructures.Graph;

public class SinkApproxMSCSF extends TreeApproxMSCSF {
    public SinkApproxMSCSF( Graph graph ) {
        super( graph, new SinkEdgeClassifyDFS( graph ) );
    }

}
