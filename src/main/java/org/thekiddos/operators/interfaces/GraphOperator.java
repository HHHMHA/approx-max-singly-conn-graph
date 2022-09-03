package org.thekiddos.operators.interfaces;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.thekiddos.datastructures.Edge;
import org.thekiddos.datastructures.Graph;

import java.util.List;


@AllArgsConstructor
public abstract class GraphOperator<T> {
    @Getter( AccessLevel.PROTECTED )
    private final Graph graph;

    public abstract T operate();

    protected int graphSize() {
        return graph.size();
    }

    protected List<Edge> getOutEdges( int w ) {
        return getGraph().getOutEdges( w );
    }
}
