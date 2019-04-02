package cphb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import cphb.airport.RouteEdge;
import cphb.airport.WeightAlgorithm;
import cphb.algo.BreathFirstSearch;
import cphb.algo.DepthFirstSearch;
import cphb.algo.Digraph;
import cphb.algo.DijkstrasSearch;
import cphb.models.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args) throws IOException
    {
        String folder = "data/test";
        List<Airline> airlines = readCsv(folder + "/airlines.txt", (data) -> new Airline(data[0], data[1], data[2]));
        List<Airport> airports = readCsv(folder + "/airports.txt", data -> new Airport(data[0], data[1], data[2], data[3], Double.parseDouble(data[4]), Double.parseDouble(data[5])));
        List<Route> routes = readCsv(folder + "/routes.txt", data -> makeRoute(airlines, airports, data));
     
        Airport src = find(airports, "CPH");
        Airport dest = find(airports, "BLL");
        
        // System.out.println("Airlines travelling between " + src.getCode() + " and " + dest.getCode() + "(using DFS)");
        // for(Airline airline : getAirlinesTravellingBetween(airlines, routes, src, dest, 1)) {
        //     System.out.println(airline.getName());
        // }
        
        // System.out.println("Airlines travelling between " + src.getCode() + " and " + dest.getCode() + "(using BFS)");
        // for(Airline airline : getAirlinesTravellingBetween(airlines, routes, src, dest, 0)) {
        //     System.out.println(airline.getName());
        // }

        // Digraph<Airport, RouteEdge> airRouteMap = new Digraph<Airport, RouteEdge>(routes.size());
        // for (Route route : routes) {
        //     airRouteMap.addEdge(new RouteEdge(route));
        // }
        // DijkstrasSearch<Airport, RouteEdge> dSearch = new DijkstrasSearch<>(airRouteMap, src);
        // System.out.println("Shortest path between " + src.getCode() + " and " + dest.getCode() + " is " + dSearch.distTo(dest));
        // for (RouteEdge edge : dSearch.pathTo(dest)) {
        //     System.out.print(edge.getSrc().getCode() + "->" + edge.getDest().getCode() + " ");
        // }
        // System.out.println("");

        Digraph<Airport, RouteEdge> airRouteMap = new Digraph<Airport, RouteEdge>(routes.size());
        for (Route route : routes) {
            airRouteMap.addEdge(new RouteEdge(route, WeightAlgorithm.TIME_BASED));
        }
        DijkstrasSearch<Airport, RouteEdge> dSearch = new DijkstrasSearch<>(airRouteMap, src);
        System.out.println("Quickest path between " + src.getCode() + " and " + dest.getCode() + " is " + dSearch.distTo(dest));
        for (RouteEdge edge : dSearch.pathTo(dest)) {
            System.out.print(edge.getSrc().getCode() + "->" + edge.getDest().getCode() + " ");
        }
        System.out.println("");
        
    }

    private static Iterable<Airline> getAirlinesTravellingBetween(Iterable<Airline> airlines, Collection<Route> routes, 
        Airport source, Airport destination, int method)  {    
        Collection<Airline> resultAirlines = new ArrayList<>();
        for (Airline airline : airlines) {
            List<Route> airlineRoutes = routes.stream().filter(r -> r.getAirline().getCode().equals(airline.getCode())).collect(Collectors.toList());

            // Construct graph for airline routes
            Digraph<Airport, RouteEdge> graph = new Digraph<>(airlineRoutes.size());
            for(Route route : airlineRoutes) {
                graph.addEdge(new RouteEdge(route));
            }

            if (method == 1) {
                DepthFirstSearch<Airport, RouteEdge> dfs = new DepthFirstSearch<>(graph, source);
                if (dfs.marked(destination)) {
                    resultAirlines.add(airline);
                }
            } else {
                BreathFirstSearch<Airport, RouteEdge> bfs = new BreathFirstSearch<>(graph, source);
                if (bfs.marked(destination)) {
                    resultAirlines.add(airline);
                }
            }
        }
        return resultAirlines;
    }

    private static Airport find(Collection<Airport> airports, String code) {
        Optional<Airport> op = airports.stream().filter(a -> a.getCode().equals(code)).findFirst();
        return op.isPresent() ? op.get() : null;
    }

    private static Route makeRoute(Collection<Airline> airlines, Collection<Airport> airports, String[] data) {
        Optional<Airline> airline = airlines.stream().filter(a -> a.getCode().equals(data[0])).findFirst();
        Optional<Airport> src = airports.stream().filter(a -> a.getCode().equals(data[1])).findFirst();
        Optional<Airport> dest = airports.stream().filter(a -> a.getCode().equals(data[2])).findFirst();
        if (!airline.isPresent() || !src.isPresent() || !dest.isPresent()) 
            throw new RuntimeException("Missing data " + airline.isPresent() + " " + src.isPresent() + " " + dest.isPresent());

        return new Route(airline.get(), src.get(), dest.get(), Double.parseDouble(data[3]), Double.parseDouble(data[4]));
    }

    private static <T> List<T> readCsv(String file, Function<String[], T> producer) throws IOException {
        ArrayList<T> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine(); // skip header
            String line = null;
            while ((line = reader.readLine()) != null) {
                try {
                    items.add(producer.apply(line.split(";")));
                } catch (Throwable e) { // hax
                    System.err.println("Skipping invalid input " + line + " reason: " + e.getMessage());
                }
            }
        }
        return items;
    }
}
