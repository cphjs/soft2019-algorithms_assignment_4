# Links

[Assignment 4 definition](https://github.com/datsoftlyngby/soft2019spring-algorithms/blob/master/Weeklies/Week_11/04-assignment/04%20Assignment%20Airline%20Network.pdf)
[Booksite](https://algs4.cs.princeton.edu/home/)

# Assignment

Most relevant classes are contained within the [algo](src/main/java/cphb/algo) package, the others are just supporting classes to use the graphs in an airline context. The [main class](src/main/java/cphb/App.java) contains some example code on how to run this, most of it is commented out, but each block should answer one of the assignments questions.

## The directed graph

The digraph is using an **array of adjacency lists** implemented through a _hash map_, mapping from _vertices_ to _bags of edges_. The bag collection is taken from the algorithms library linked above. Any kind of map can be used, but a hash based on was chosen to guarantee constant access and insertion times, similarly to an array. 

The array(or hashmap) of adjacency lists was chosen because of optimal performance in memory, insertion, checking whether a vertices are adjacent and iterating over the adjacent vertices. The memory consumption can be expressed as **V+E** where V is the number of vertices and E is the number of edges. Adding and edge takes **constant time** since it only requires directly accessing the hash map and inserting another element. Both checking whether vertex v is adjacent to vertex w **takes time equal to the degree of v**. This is because finding all edges adjacent to a vertex requires a single hash map access, which only leaves iterating over all the adjacent vertices.

### Other data structures

Other considered data structures to represent the directed graph were: a list of edges and a adjacency matrix.

A list of edges has the advantage of taking less space (equal to the number of edges) compared to an array of adjacency lists, but is less perfomant in checking whether a vertex is adjacent to another one and iterating over adjacent vertices. This is caused by the fact that both of these operations require iterating over all edges. This data structure could be optimal for extremely sparse graphs.

The second discarded data structure is an adjacency matrix: keeping track of whether each vertex is adjacent to each other vertex. This allows us to determine whether one vertex is adjacent to another in constant time but requires iterating all vertices to find vertices they are adjacent to. Even worse is the space consumption, requiring **V^2** amount of memory.
