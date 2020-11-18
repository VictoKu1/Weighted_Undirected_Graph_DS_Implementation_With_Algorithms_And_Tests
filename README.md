# OOP Course Exercise 1 Commitment


##### @author: Victor.Kushnir

The following project represents a data structure for an undirected, weighted graph.
2 classes are implementing 3 interfaces. The main data structure that was used in this project is a HashMap due to the O(1) best-average case searching runtime complexity.
Also, the following project is implementing Dijkstra and BFS algorithm, the description of the implementation is described at the relevant place in this README file.
The infrastructure of the project is located in the src file and the JUNIT test files are located in the test file.

## The main idea of the project : 
The most basic object in this project is the Node which is implementing the node_info interface and shows up as a static inner class in the Graph class. The node the most important part of this project, each node contains 2 HashMaps - one for connected nodes and the other for the weights of those edges in addition to several other parameters which are described below in the relevant section. 
The Graph is an undirected, weighted graph that implements the weighted__graph interface and is located in the WGraph_DS.java file. This object main parameter is a HashMap data structure which holds nodes as value and their keys as key (:drum:), a unique 7 symbols 128-bit key which updates at every change of the graph - mainly used for more effective equals method implementation (the details are listed below in the relevant section) and few more parameters which are listed as well in the relevant section of this readme file.
And a class for providing special algorithms on the graph which is located in the WGraph__Algo.java file which contains only a graph that the following methods are being produced on, and implements the weighted_graph_algorithms interface. Mainly this class provides algorithms like deep copying of a graph, BFS algorithm, Dijkstra algorithm, and few more methods which description could be found in a more detailed way in the relevant section in this ReadMe file. 
