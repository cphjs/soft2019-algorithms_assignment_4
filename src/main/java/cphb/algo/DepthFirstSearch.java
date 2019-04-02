package cphb.algo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * DepthFirstSearch
 */
public class DepthFirstSearch<V, E extends Edge<V>> {

    private Collection<V> marked;

    public DepthFirstSearch(Digraph<V, E> graph, V source) {
        marked = new HashSet<>(graph.V());
        dfs(graph, source);
    }

    private void dfs(Digraph<V, E> graph, V source) {
        marked.add(source);
        for (E edge : graph.adj(source)) {
            V adj =  edge.getDest();
            if (marked.contains(adj)) continue;

            dfs(graph, adj);
        }
    }

    public boolean marked(V vertex) {
        return marked.contains(vertex);
    }
}