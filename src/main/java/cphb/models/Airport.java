package cphb.models;

import cphb.algo.Vertex;

/**
 * Airport
 */
public class Airport implements Vertex {

    private String code;
    private String name;
    private String city;
    private String country;
    private double latitude;
    private double longitude;

    public Airport(String code, String name, String city, String country, double lat, double lon) {
        this.code = code;
        this.name = name;
        this.city = city;
        this.country = country;
        this.latitude = lat;
        this.longitude = lon;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

 
    public String toString() {
        return this.getCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Airport)) return false;
        return ((Airport)o).getCode() == getCode();
    }

	@Override
	public double getWeight() {
		return 1;
	}
}