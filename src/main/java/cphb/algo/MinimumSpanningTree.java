package cphb.algo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * MinimumSpanningTree
 */
public class MinimumSpanningTree<V, E extends Edge<V>> {

    private Digraph<V, E> graph;
    private Collection<V> marked;
    private PriorityQueue<E> unvisited;
    private Queue<E> mst;

    public MinimumSpanningTree(Digraph<V, E> graph) {
        this.graph = graph;
        this.marked = new ArrayList<>();
        this.mst = new ArrayDeque<>();
        this.unvisited = new PriorityQueue<>(graph.E());
        
        Optional<V> v =graph.vertices().stream().findFirst();
        if (v.isPresent()) {
            visit(v.get());
        }
        while (unvisited.size() > 0) {

            E edge = unvisited.poll();
            V v1 = edge.getSrc();
            V v2 = edge.getDest();
            if (marked.contains(v1) && marked.contains(v2)) continue;
            mst.add(edge);
            if (!marked.contains(v1)) visit(v1);
            if (!marked.contains(v2)) visit(v2);
        }
    }

    public int size() {
        return mst.size();
    }

    public Queue<E> getPath() {
        return mst;
    }

    public void visit(V vertex) {
        marked.add(vertex);
        for (E e : graph.adj(vertex)) {
            V adj = vertex == e.getDest() ? e.getSrc() : e.getDest(); // comparing references is acceptable here
            if (!marked.contains(adj)) {
                unvisited.add(e);
            }
        }
    } 
}