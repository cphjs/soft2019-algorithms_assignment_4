package cphb.algo;

/**
 * Edge
 */
public interface Edge<V> {
    V getSrc();
    V getDest();

    double getWeight();
}