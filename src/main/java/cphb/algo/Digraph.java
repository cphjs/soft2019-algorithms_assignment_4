package cphb.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import edu.princeton.cs.algs4.Bag;

/**
 * Digraph
 */
public class Digraph<V, E extends Edge<V>> {

    private HashMap<V, Bag<E>> data;
    private int E;

    public Digraph(int len) {
        data = new HashMap<>(len);
    }
    public Digraph() {
        data = new HashMap<>();
    }

    public void addEdge(E edge) {
        addEdgeToVertex(edge.getSrc(), edge);
    }

    public Iterable<E> adj(V v) {
        Bag<E> adj = data.get(v);
        if (adj == null) {
            adj = new Bag<E>();
        }
        return adj;
    }

    public int E() {
        return E;
    }

    public int V() {
        return data.size();
    }

    public Iterable<E> edges() {
        List<E> edges = new ArrayList<>();
        for (V v : data.keySet()) {
            for (E e : data.get(v)) {
                if (edges.contains(e)) continue;
                edges.add(e);
            }
        }
        return edges;
    }

    public Set<V> vertices() {
        return data.keySet();
    }

    private void addEdgeToVertex(V v, E e) {
        Bag<E> edges = data.get(v);
        if (edges == null) {
            edges = new Bag<>();
            data.put(v, edges);
        }
        edges.add(e);
        E++;
    }
}