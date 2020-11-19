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

1. **int key** - represents a key, every node in the graph has its specific key which is mainly used for the identification of a node in the graph. 
2. **String info** - mainly used for algorithm purposes. 
3. **int tag** - mainly used for algorithm purposes. 
4. **HashMap<node_info, Double> distancesToConnectedNodes** - HashMap which helps us to search for a distance between 2 nodes in O(1) complexity. 
5. **node_info parent** - mainly used by the Dijkstra algorithm to mark the closest parent node. 
6. **HashMap<Integer, node_info> connections** - HashMap which is used to contain and search for connected nodes in O(1) complexity. 

###                 Methods :
* **NodeInfo(int key)**  -  NodeInfo class constructor which sets all the parameters to its default values and sets a node-specific key buy the inputted int value.
* **node_info getParent()** - Returns this node's parent parameter .
* **void setParent(node__info newUpdatedParnet)** - Sets this node's parent parameter to the given node_info type value .
* **int getKey()** - Return the key (id) associated with this node, each node_data should have a unique key.
* **String getInfo()** - Returns the info parameter .
* **void setInfo(String s)** - Sets this node_info info parameter to the given one.
* **double getTag()**- Returns this node's tag parameter .
* **setTag(double t)** - Allows setting this node tag parameter to the given value.
* **hasNi(node_info node)** - A boolean method that returns true/false depending on having given node as it's adjacent or not.
* **HashMap<Integer, node_info> getConnectionsHashMap()** - Method, which returns the HashMap which contains all connected to this node nodes .
* **HashMap<node_info, Double> getDistancesHashMap()** - Method, which returns the HashMap which contains distances to connected to this node nodes .
* **void addNi(node_info t, double distance)** - Method which adds a node to this node as its adjacent and adds its distance to the relevant HashMap as well, doing the same procedure to the added node.
* **void setDistance(node_info t, double distance)** - Method which sets a distance to the given node at both directions.
* **Object[] removeNode(node_info node)** - Method which removes the given node from this node adjacents and distances HashMaps and providing the same procedure in the opposite direction.
* **Collection<node_info> getConnections()** - Method which returns a collection of a Collection interface type which contains all connected nodes . Mainly works as a helper to a method in WGraph_DS class.
* **double getDistanceToAdjacentNode(node_info node)** - Method which returns the distance of this node to the given node .

## WGraph_DS

###                 Abstract Description :

 This class represents a weighted undirected graph data structure, this class mainly built from one HashMap data structure, which contains all the nodes in the graph, this class is implementing two interfaces, the first one is weighted__grpah which is used to tell the general structure of the class and Serializable interface which is used in the algorithm class to stream an object of a type WGraph_DS to and from a file.

###                 Contained parameters :

1. **HashMap<Integer, node_info> nodeHash** - mainly used to contain the nodes in the graph and produce a search in O(1) complexity. 
2. **int numOfChanges** - a parameter which represents the number of changes which was provided on the graph (like adding/removing a node and connecting two nodes ). 
3. **int numOfEdges** - a parameter that represents the number of edges in the graph. 
4. **int numOfNodes** - a parameter that represents the number of nodes in the graph. 
5. **String uniqueKey** - unique string for any graph that is built from 7 randomly chosen characters which are being generated from range 0-128  ( so it is 128^7 possible combinations for every graph) every time a change is being committed to the graph. 

###                 Methods :
* **WGraph_DS()** - Constructor method for WGrpah_DS class mainly sets all the parameters to their default values, generates a 7 digit 128-bit unique key for this graph, and makes the HashMap of contained nodes a new data structure object.
* **void ranGenerateNewUniqueKey()** - Method which is generating a new unique key for the graph with every its change (numOfChanges parameter increment), each time this method generates 7 characters 128-bit unique String, therefore there are 128^7 possibilities for each key.
* **node_info getNode(int key)** - Returns the node_info by the  given node_id, return null if there is no such node in the grpah . Runs in O(1) complexity .
* **boolean hasEdge(int node1, int node2)** - Boolean method which return true iff (if and only if) there is an edge between node1 and node2 . Runs at O(1) complexity . 
* **double getEdge(int node1, int node2)** - Method returns the distance to the given node if the edge (node1, node2) exist. In case there is no such edge - should return -1 . Runs and O(1) complexity .
* **void addNode(int key)** - Method adds a new node to the graph with the given key. In case a node with that key exists in the graph no action would be performed. Runs at O(1) complexity.
* **boolean isContained(int key)** - Method which returns true if the given node key is contained in this graph, false otherwise.
* **void connect(int node1, int node2, double w)** - Method connects an edge between node1 and node2, with an edge with weight >=0. If this edge already exists, the method would only update the distance between these two nodes to the given parameter w. Runs in O(1) complexity.
* **Collection<node_info> getV()** - This method returns a Collection<node_info> type data structure which returns all the contained nodes in the grpah . Runs at O(1) complexity .
* **Collection<node_info> getV(int node_id)** - This method returns a Collection containing all the nodes distancesToConnectedNodes to node_id , uses NodeData.getNi() mwthod to obtain O(1) complexity .
* **node_info removeNode(int key)** -  Delete the node (with the given ID) from the graph - and removes all edges which start or end at this node. First, the method removes edges with all of the adja@cent nodes and then removes the node from this graph contained nodes HashMap, after the removal, the method returns the removed node, if no nodes were removed the method simply returns null. Runs at O(n) complexity, where n is the number of all adjacent nodes to the node in the graph.
* **void removeEdge(int node1, int node2)** - Method deletes an edge between 2 nodes, thanks to removeNode(int key) method from the NodeInfo class the method runs at O(1) complexity .
* **int nodeSize()** - Method returns numOfNodes parameter which represents the number of nodes in this graph . Runs at O(1) complexity .
* **int edgeSize()** - Method returns numOfEdges parameter which represents the number of edges in this graph . Runs at O(1) complexity .
* **int getMC()** - Method returns numOfChanges parameter which represents the number of changes that was performed in this graph. Runs at O(1) complexity.
* **boolean equals(Object compared)** - Method provides a comparison for this graph and the given graph in the function, Has a "short key" check - returns true if both graphs have the same number of nodes, the same number of edges and both unique keys are identical, this was made to keep O(1) best-case and average-case runtime - because each unique key is a 7 digit 128-bit String there is 1/(127^7) chance to make a mistake which is unlikely to happen but in case the committed graph has the same number of nodes and edges but keys are different it will provide a brute force comparison for nodes and edges, if these graphs are identical the method returns true, otherwise the method returns false.
* **String getUniqueKey()** - Method returns this graph 7 digit 128-bit unique key .
* **void setUniqueKey(String copiedKey, int securityKey)** - Method used in .copy() method WGraph_Algo class to make the copied graph have identical 7 digits 128-bit key, has a security check to avoid illegal using of the method.




 

