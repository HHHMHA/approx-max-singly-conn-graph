package org.thekiddos.operators.interfaces;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.thekiddos.datastructures.Graph;


@AllArgsConstructor
public abstract class GraphOperator<T> {
    @Getter( AccessLevel.PROTECTED )
    private final Graph graph;

    public abstract T operate();
}
