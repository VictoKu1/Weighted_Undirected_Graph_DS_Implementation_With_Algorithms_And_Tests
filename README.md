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


## WGraph_Algo

###                 Abstract Description :

WGraph__Algo class which is implementing weighted_graph_algorithms interface mainly represents a class with a set of basic methods that might get applied to a weighted graph data structure.

###                 Contained parameters : 

1. Parameter of a WGraph_DS class which is implementing weighted_grpah_algorithms.

###                 Methods :

* **WGraph_Algo()** - Constructor method which initializes as new WGraph_DS object the weighted_graph g parameter .
* **void init(weighted_graph g)** - Method which makes the parameter this.g to point on the inputted graph g.
* **weighted_graph getGraph()** - Method which returns a pointer to the this.g graph this class is working on.
* **weighted_graph copy()** - Method computes and returns a deep copy of this.g graph, using two helping method , void copyNodes(weighted_graph target)- which copy this.g graph nodes to the given graph . and void copyEdges(weighted_graph target) - which copy this.g edges to the given graph, also fakes the unique key of the graph by providing the secret security key to the setUniqueKey(String copiedKey, int security) method in WGraph_DS class.
* **void copyNodes(weighted_graph target)** - Helping method which copy this.g graph nodes to the given graph .
* **void copyEdges(weighted_graph target)** - Helping method which copy this.g edges to the given graph .
* **boolean isConnected()** - Returns true if and only if (iff) there is a valid path from EVREY node to each other node. The method uses BFSFromNode(int src) method to try to pass all over the nodes and mark its Info parameter to "V", and afterward check if the nodes in the graph have changed their Info parameter to "V" using allTheInfosAreV() helper checking method. The method is using the Iterator interface.
* **boolean allTheInfosAreV()** - Privately accessed helper method, used to check if all nodes in the graph changed their Info parameter to "V" as a mark of being checked in the BFSFromNode(int src) algorithm to know if there is a path from every node to every other node.
* **void BFSFromNode(int src)** - Helper method that is implementing BFS algorithm from a node which key is src, using LinkedList data structure as a Queue data structure.
* **void adjacentsEnque(LinkedList<node_info> nodeQueue, Collection<node_info> nodeArray)** - Helper method that is used in BFSFromNode(int src) method to add the nodeArray contained parameters to nodeQueue, the adding is produced using Iterator interface .
* **void setParentOfEveryNodeInGraphToDefault()** - Helper method that it used after the Dijkstra algorithm to set all the parent parameters to default value null .
* **void setTagAndInfoOfEveryNodeInGraphToDefault()** - Helper method that is used after the BFS algorithm to set all Node's Infos and Tags parameters to default ("") and (-1) .
* **void setTagAndInfo(node_info n)** - Helper method to a helper method that sets all Node's Infos and Tags parameters to default ("") and (-1) .
* **double shortestPathDist(int src, int dest)** - Returns the length of the shortest path between src to dest using Dijkstra(int src) method and as a little help also the BFS algorithm to avoid entering nodes that have no path to the destination node.
* **void Dijkstra(int src)** - Method is implementing Dijkstra algorithm from the given node key all over the graph. It starts on the given node key, changing it tag parameter to 0 and then adding it to the queue with all of its adjacent nodes, avoiding those who are marked by the previously used BFS algorithm as unconnected and sets all the connected nodes which tag is higher than the parent tag summarized with the weight of the edge between them, tag parameter to the tag parameter of their current parent node summarized with the weight of the node between them, and getting the parent node out of the queue, and providing the same procedure with the new head of the queue, and so on until the queue is empty, then the method stops.
* **void addToQueueEachAdjacentNodeWhichIsConnectedSomehowToTheDest(LinkedList<node_info> queue, node_info parent)** - Helping method for Dijkstra(int src) method which adds new nodes to the algorithm queue dependently if its tag parameter is lower than the tag of parent summarized with the weight of the node between them and if the node is determined as valid by the previously produced BFS algorithm. Mainly exist to make Dijkstra(int src) method more readable.
* **void setTheDistancesToInf()** - Helping method to the Dijkstra(int src) method, which is marking all the tags of all nodes in the graph to infinity.
* **List<node_info> shortestPath(int src, int dest)** - Returns the shortest path between src to dest - as an ordered list of nodes, mainly using the Dijkstra(int src) method, works the same as the shortestPathDist(int src, int dest) method but instead of returning the distance it returns a List<node_info> which is built by passing from the dest to the src of the path right after the Dijkstra(int src) method stops working, each node_info has a node_info parent parameter which points to the parent node which was determined as the best option to find the shortest path by  Dijkstra(int src) method, at the end of the method all of the node parameters like info, tag and parent are being set to their default values.
* **void buildPath(LinkedList<node_info> path, int src, int dest)** - Helping method which is used after the running of Dijkstra(int src) method in shortestPath(int src, int dest), this method is building a path from the destination node to the source, using LinkedList ability to add parameters to the end of the list and therefore "inverting" the order so it will return as a list of a route from the source to the destination node. The building is simply adding each node (starting at the destination node) parent parameter to the list which is being determined by the Dijkstra(int src) method.
* **boolean save(String file)** - Saves this weighted (undirected) graph to the given file name using Serialized interface implemented by WGraph_DS and NodeInfo classes.
* **boolean load(String file)** - This method load a graph to this graph algorithm. If the file was successfully loaded - the underlying graph of this class will be changed (to the loaded one), in case the graph was not loaded the original graph should remain "as is". This method is using the equals method in WGraph_DS class, in case the uploaded graph is equal to the already contained in this class graph the graph will not get updated. Returns true if the graph was uploaded successfully, false otherwise. 
