# OOP Course Exercise 1 Commitment

##### @author: Victor.Kushnir

The following project represents a data structure for an undirected, weighted graph.

2 classes are implementing 3 interfaces. The main data structure that was used in this project is a HashMap due to the O(1) best-average case searching runtime complexity.

Also, the following project is implementing Dijkstra and BFS algorithm, the description of the implementation is described at the relevant place in this README file.

The infrastructure of the project is located in the src file and the JUNIT test files are located in the test file.

## The main idea of the project : 
The most basic object in this project is the Node which is implementing the node_info interface and shows up as a static inner class in the Graph class. The node the most important part of this project, each node contains 2 HashMaps - one for connected nodes and the other for the weights of those edges in addition to several other parameters which are described below in the relevant section. 

The Graph is an undirected, weighted graph that implements the weighted__graph interface and is located in the WGraph_DS.java file. This object main parameter is a HashMap data structure which holds nodes as value and their keys as key (:drum:) , a unique 7 symbols 128-bit key which updates at every change of the graph - mainly used for more effective equals method implementation (the details are listed below in the relevant section) and few more parameters which are listed as well in the relevant section of this readme file.

And a class for providing special algorithms on the graph which is located in the WGraph__Algo.java file which contains only a graph that the following methods are being produced on, and implements the weighted_graph_algorithms interface. Mainly this class provides algorithms like deep copying of a graph, BFS algorithm, Dijkstra algorithm, and few more methods which description could be found in a more detailed way in the relevant section in this ReadMe file. 

# Contained Classes Detalization :

## NodeInfo

###                 Abstract Description :
A static inner class that contains objects which are implementing node_info interface, used to represent the nodes which are being contained in the WGraph_DS "Master class". 
Mainly works using two HashMap data structures (mainly to achieve O(1) time complexity), one for adjacent nodes and the second one for distances to these adjacent nodes. 

###                 Contained parameters :

1. int key - represents a key, every node in the graph has its specific key which is mainly used for identification of a node in the graph. 
2. String info - mainly used for algorithm purposes. 
3. int tag - mainly used for algorithm purposes. 
4. HashMap<node_info, Double> distancesToConnectedNodes - HashMap which helps us to search for a distance between 2 nodes in O(1) complexity. 
5. node_info parent - mainly used by the Dijkstra algorithm to mark the closest parent node. 
6. HashMap<Integer, node_info> connections - HashMap which is used to contain and search for connected nodes in O(1) complexity. 

###                 Methods :
* NodeInfo(int key)  -  NodeInfo class constructor which sets all the parameters to its default values and sets a node-specific key buy the inputted int value.
* node_info getParent() - Returns this node's parent parameter .
* void setParent(node__info newUpdatedParnet) - Sets this node's parent parameter to the given node_info type value .
* int getKey() - Return the key (id) associated with this node, each node_data should have a unique key.
* String getInfo() - Returns the info parameter .
* void setInfo(String s) - Sets this node_info info parameter to the given one.
* double getTag() - Returns this node's tag parameter .
* setTag(double t) - Allows setting this node tag parameter to the given value.
* hasNi(node_info node) - A boolean method that returns true/false dependently on having given node as it's adjacent or not.
* HashMap<Integer, node_info> getConnectionsHashMap() - Method, which returns the HashMap which contains all connected to this node nodes .
* HashMap<node_info, Double> getDistancesHashMap() - Method, which returns the HashMap which contains distances to connected to this node nodes .
* void addNi(node_info t, double distance) - Method which adds a node to this node as its adjacent and adds its distance to the relevant HashMap as well, doing the same procedure to the added node.
* void setDistance(node_info t, double distance) - Method which sets a distance to the given node at both directions.
* Object[] removeNode(node_info node) - Method which removes the given node from this node adjacents and distances HashMaps and providing the same procedure in the opposite direction.
* Collection<node_info> getConnections() - Method which returns a collection of a Collection interface type which contains all connected nodes . Mainly works as a helper to a method in WGraph_DS class.
* double getDistanceToAdjacentNode(node_info node) - Method which returns the distance of this node to the given node .

