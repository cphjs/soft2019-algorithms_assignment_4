package cphb.airport;

import cphb.algo.Edge;
import cphb.models.Airport;
import cphb.models.Route;

/**
 * RouteEdge
 */
public class RouteEdge implements Edge<Airport>, Comparable<RouteEdge> {

    private Route route;
    private WeightAlgorithm weightAlgo;

    public RouteEdge(Route route) {
        this(route, WeightAlgorithm.DEFAULT);
    }

    public RouteEdge(Route route, WeightAlgorithm alg) {
        this.route = route;
        this.weightAlgo = alg;
    }


    @Override
    public Airport getSrc() {
        return route.getSource();
    }

    @Override
    public Airport getDest() {
        return route.getDestination();
    }

    /**
     * @return the route
     */
    public Route getRoute() {
        return route;
    }

    /**
     * @param route the route to set
     */
    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public double getWeight() {
        return weightAlgo.getWeight(route);
    }

    @Override
    public int compareTo(RouteEdge o) {
        return Double.compare(getWeight(), ((RouteEdge)o).getWeight());
    }

    
}