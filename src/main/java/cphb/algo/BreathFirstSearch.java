package cphb.algo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;

/**
 * BreathFirstSearch
 */
public class BreathFirstSearch<V, E extends Edge<V>> {

    private Collection<V> marked;

    public BreathFirstSearch(Digraph<V, E> graph, V source) {
        marked = new ArrayList<>();
        dfs(graph, source);
    }

    private void dfs(Digraph<V, E> graph, V v) {
        Queue<V> queue = new ArrayDeque<>();
        queue.add(v);
        while (!queue.isEmpty()) {

            V vertex = queue.poll();
            for (E e : graph.adj(vertex)) {
                V adj = e.getDest();
                if (marked.contains(adj)) continue;

                marked.add(adj);
                queue.add(adj);
            }

        }
    }

    public boolean marked(V v) {
        return marked.contains(v);
    }
}