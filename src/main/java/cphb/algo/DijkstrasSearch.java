package cphb.algo;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * DijkstrasSearch
 */
public class DijkstrasSearch<V extends Vertex, E extends Edge<V>> {

    private V source;
    private Map<V, Double> distTo;
    private Map<V, E> edgeTo;
    private Digraph<V, E> graph;
    private PriorityQueue<V> closestVertices;


    public DijkstrasSearch(Digraph<V, E> graph, V source) {
        this.graph = graph;
        this.source = source;
        this.edgeTo = new HashMap<>(graph.V());
        this.distTo = new HashMap<>(graph.V());
        for (E edge : graph.edges()) {
            distTo.put(edge.getDest(), Double.POSITIVE_INFINITY);
        }
        distTo.put(source, 0d);

        this.closestVertices = new PriorityQueue<>(graph.V(), (v1, v2) -> Double.compare(distTo.get(v1), distTo.get(v2)));
        this.closestVertices.add(source);
        while (closestVertices.size() > 0) {
            relax(closestVertices.poll());
        }
    }

    public double distTo(V v) {
        return distTo.get(v);
    }

    public Iterable<E> pathTo(V v) {
        Stack<E> path = new Stack<>();
        V vertex = v;
        while (vertex != source) {
            E e = edgeTo.get(vertex);
            vertex = e.getSrc();
            path.add(e);
        }
        return path;
    }

    private void relax(V vertex) {
        for (E edge : graph.adj(vertex)) {
            V adj = edge.getDest();
            double dist = distTo.get(vertex) + edge.getWeight() + vertex.getWeight();
            // If found new lowest distance to vertex adj
            if (distTo.get(adj) > dist) {

                distTo.put(adj, dist);
                edgeTo.put(adj, edge);
                if (!closestVertices.contains(adj)) {
                    closestVertices.add(adj);
                }
            }
        }
    }
}