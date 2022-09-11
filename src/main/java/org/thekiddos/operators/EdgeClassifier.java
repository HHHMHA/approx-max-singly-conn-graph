package org.thekiddos.operators;

import org.thekiddos.datastructures.Edge;
import org.thekiddos.datastructures.Graph;
import org.thekiddos.operators.interfaces.GraphOperator;

import java.util.List;

public abstract class EdgeClassifier extends GraphOperator<List<Edge>> {
    public EdgeClassifier( Graph graph ) {
        super( graph );
    }

    @Override
    public abstract List<Edge> operate();
}
