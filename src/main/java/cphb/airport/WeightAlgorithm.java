package cphb.airport;

import cphb.models.Route;

/**
 * WeightAlgorithm
 */
public interface WeightAlgorithm {
    
    public static final WeightAlgorithm DISTANCE_BASED = r -> r.getDistance();
    public static final WeightAlgorithm DEFAULT = DISTANCE_BASED;
    public static final WeightAlgorithm TIME_BASED = r -> r.getTime();

    public double getWeight(Route route);
}