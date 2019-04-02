package cphb.models;

/**
 * Route
 */
public class Route {

    private Airline airline;
    private Airport source;
    private Airport destination;
    private double distance;
    private double time;
    
    public Route(Airline airline, Airport source, Airport destination, double distance, double time)
    {
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.time = time;
    }

    /**
     * @return the airline
     */
    public Airline getAirline() {
        return airline;
    }

    /**
     * @param airline the airline to set
     */
    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    /**
     * @return the source
     */
    public Airport getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(Airport source) {
        this.source = source;
    }

    /**
     * @return the destination
     */
    public Airport getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    /**
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * @return the time
     */
    public double getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(double time) {
        this.time = time;
    }

    
}