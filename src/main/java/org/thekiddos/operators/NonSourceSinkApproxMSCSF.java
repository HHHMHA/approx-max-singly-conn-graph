package org.thekiddos.operators;

import org.thekiddos.datastructures.Graph;

public class NonSourceSinkApproxMSCSF extends TreeApproxMSCSF {
    public NonSourceSinkApproxMSCSF( Graph graph ) {
        super( graph, new NonSourceSinkEdgeClassifyDFS( graph ) );
    }

}
