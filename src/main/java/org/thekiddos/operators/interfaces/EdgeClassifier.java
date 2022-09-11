package org.thekiddos.operators.interfaces;

import org.thekiddos.datastructures.Edge;
import org.thekiddos.datastructures.Graph;

import java.util.List;

public abstract class EdgeClassifier extends GraphOperator<List<Edge>> {
    public EdgeClassifier( Graph graph ) {
        super( graph );
    }

    @Override
    public abstract List<Edge> operate();
}
