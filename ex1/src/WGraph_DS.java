package ex1.src;

import java.io.Serializable;
import java.util.*;

/*
 *  This class represents a weighted undirected graph data structure , this class mainly built from one HashMap data structure , which contains all the nodes in graph , this class is implementing two interfaces , the first one is weighted_grpah which is used to tell the general structure of the class and Serializable interface which is used in the algorithm class to stream an object of a type WGraph_DS to and from a file .Mainly this class contains 4 parameters: 1.HashMap<Integer, node_info> nodeHash - mainly used to contain the nodes in the graph and produce a search in O(1) complexity. 2. int numOfChanges - parameter which represents the number of changes which was provided on the graph (like adding/removing a node and connecting two nodes ) . 3. int numOfEdges - parameter which represents the number of edges in the graph . 4. int numOfNodes - parameter which represents the number of nodes in the graph . 5. String uniqueKey- unique string for any graph that is build from 7 randomly chosen characters which are being generated from range 0-128  ( so it is 128^7 possible combinations for every graph) every time a change is being committed to the graph. @author Victor.Kushnir.
 */
public class WGraph_DS implements weighted_graph, Serializable {

    private HashMap<Integer, node_info> nodeHash;
    private int numOfChanges;
    private int numOfEdges;
    private int numOfNodes;
    private String uniqueKey;

    /*
     *  Inner class which contains objects which are implementing node_info interface , used to represent the nodes which are being contained in the WGraph_DS "Master class" . Mainly works using two HashMap data structures (mainly to achieve O(1) time complexity) ,one for adjacent nodes and the second one for distances to theses adjacent nodes . More specifically this inner class contains the following parameters: 1. int key - represents a key , every node in the graph has its own specific key which is mainly used for identification of a node in the grpah . 2. String info - mainly used for algorithm purposes . 3. int tag - mainly used for algorithm purposes . 4. HashMap<node_info, Double> distancesToConnectedNodes - HashMap which helps us to search for a distance between to nodes in O(1) complexity . 5.node_info parent - mainly used by dijakstra algorithm to mark the closest parent node . 6. HashMap<Integer, node_info> connections - HashMap which is used to contain and search for connwcted nodes in O(1) complexity . @author Victor.Kushnir .
     */
    public class NodeInfo implements node_info, Serializable {
        private int key;
        private String info;
        private double tag;
        private HashMap<node_info, Double> distancesToConnectedNodes;
        private node_info parent;
        private HashMap<Integer, node_info> connections;

        /*
         *  NodeInfo class constructor which sets all the parameters to it's default values and sets a node specific key buy the inputted int value .
         */
        public NodeInfo(int key) {
            this.key = key;
            this.info = "";
            this.tag = -1;
            this.parent = null;
            this.distancesToConnectedNodes = new HashMap<node_info, Double>();
            this.connections = new HashMap<Integer, node_info>();
        }

        /*
         *  Returns this node's parent parameter .
         */
        public node_info getParent() {
            return this.parent;
        }

        /*
         *  Sets this node's parent parameter to the given node_info type value .
         */
        public void setParent(node_info newUpdatedParnet) {
            this.parent = newUpdatedParnet;
        }

        /*
         *  Return the key (id) associated with this node ,each node_data should have a unique key.
         */
        @Override
        public int getKey() {
            return this.key;
        }

        /*
         *  Returns the info parameter .
         */
        @Override
        public String getInfo() {
            return this.info;
        }

        /*
         *  Sets this node_info info parameter to the fiven one.
         */
        @Override
        public void setInfo(String s) {
            this.info = s + "";
        }

        /*
         *  Returns this node's tag parameter.
         */
        @Override
        public double getTag() {
            return this.tag;
        }

        /*
         *Allows to set this node tag parameter to the given value.
         */
        public void setTag(double t) {
            this.tag = t + 0;
        }

        /*
         *   Boolean method which returns true/false dependently on having given node as it's adjacent or not.
         */
        public boolean hasNi(node_info node) {
            return connections.containsKey(node.getKey());
        }

        /*
         * Method, which returns the HashMap which contains all connected to this node nodes.
         */
        public HashMap<Integer, node_info> getConnectionsHashMap() {
            return this.connections;
        }

        /*
         *  Method, which returns the HashMap which contains distances to connected to this node nodes.
         */
        public HashMap<node_info, Double> getDistancesHashMap() {
            return this.distancesToConnectedNodes;
        }

        /*
         *   Method which adds a node to this node as its adjacent and adds its distance to the relevant HashMap as well , doing the same procedure to the added node .
         */
        public void addNi(node_info t, double distance) {
            if (distance < 0) {
                System.out.println("Error: distance is zero or negative .");
                return;
            }
            if (t.getKey() == this.getKey()) {
                return;
            }
            if (this.hasNi(t)) {
                return;
            }
            ((NodeInfo) this).getDistancesHashMap().put(t, distance);
            ((NodeInfo) t).getDistancesHashMap().put(this, distance);
            ((NodeInfo) this).getConnectionsHashMap().put(((Integer) t.getKey()), t);
            ((NodeInfo) t).getConnectionsHashMap().put(((Integer) ((NodeInfo) this).getKey()), this);
        }

        /*
         *  Method which sets a distance to the given node at both directions .
         */
        public void setDistance(node_info t, double distance) {
            ((NodeInfo) t).getDistancesHashMap().remove(this);
            ((NodeInfo) t).getDistancesHashMap().put(this, distance);
            ((NodeInfo) this).getDistancesHashMap().remove(t);
            ((NodeInfo) this).getDistancesHashMap().put(t, distance);
        }

        /*
         *  Method which removes the given node from this node adjacents and distances HashMaps and providing the same procedure at the opposite direction.
         */
        public Object[] removeNode(node_info node) {
            if (hasNi(node)) {
                Object[] address = new Object[2];
                address[0] = distancesToConnectedNodes.remove(node);
                ((NodeInfo) node).getDistancesHashMap().remove(this);
                address[1] = connections.remove(node.getKey());
                ((NodeInfo) node).getConnectionsHashMap().remove(this.getKey());
            }
            return null;
        }

        /*
         *   Method which returnes a collection of a Collection interface type which contains all connected nodes . Mainly works as a helper to method in WGraph_DS class .
         */
        public Collection<node_info> getConnections() {
            return connections.values();
        }

        /*
         *   Method which returns the distance of this node to the given node .
         */
        public double getDistanceToAdjacentNode(node_info node) {
            return distancesToConnectedNodes.get(node);
        }

    }

    /*
     *   Constructor method for WGrpah_DS class mainly sets all the the parameters to their default values , generates a 7 digit 128-bit unique key for this graph and makes the HashMap of contained nodes a new data structure object .
     */
    public WGraph_DS() {
        this.nodeHash = new HashMap<Integer, node_info>();
        this.numOfChanges = 0;
        this.numOfEdges = 0;
        this.numOfNodes = 0;
        ranGenerateNewUniqueKey();
    }

    /*
     * Method which is generating a new unique key for the graph with every its change (numOfChanges parameter increment) , each time this method generates 7 characters 128-bit unique String, therfore there is 128^7 possibilities for each key .
     */
    private void ranGenerateNewUniqueKey() {
        Random rnd = new Random();
        this.uniqueKey = "";
        for (int i = 0; i < 7; i++) {
            int c = rnd.nextInt(256);
            this.uniqueKey = this.uniqueKey + "" + ((char) c);
        }
    }

    /**
     * Returns the node_info by the  given node_id, return null if there is no such node in the grpah . Runs in O(1) complexity .
     */
    @Override
    public node_info getNode(int key) {
        if (isContained(key)) {
            return this.nodeHash.get(key);
        }
        return null;
    }

    /**
     * Boolean method which return true iff (if and only if) there is an edge between node1 and node2 . Runs at O(1) complexity .
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if (this.getNode(node1) == null || this.getNode(node2) == null) {
            return false;
        }
        if (node1 == node2) {
            return true;
        }
        return ((NodeInfo) (this.getNode(node1))).hasNi(this.getNode(node2));
    }

    /**
     * Method returns the distance to the given node if the edge (node1, node2) exist. In case there is no such edge - should return -1 . Runs and O(1) complexity .
     */
    @Override
    public double getEdge(int node1, int node2) {
        if (hasEdge(node1, node2)) {
            if (node1 == node2) {
                return 0;
            }
            return ((NodeInfo) this.getNode(node1)).getDistancesHashMap().get(getNode(node2));
        }
        return -1.0;
    }

    /**
     * Method adds a new node to the graph with the given key. In case a node with that key exists in the graph no action would be performed . Runs at O(1) complexity .
     */
    @Override
    public void addNode(int key) {
        if (isContained(key)) {
            return;
        }
        node_info n = new NodeInfo(key);
        this.nodeHash.put(n.getKey(), n);
        ranGenerateNewUniqueKey();
        numOfNodes++;
        numOfChanges++;
    }

    /*
     *  Method which return true if the given node key is contained in this graph .
     */
    public boolean isContained(int key) {
        return this.nodeHash.containsKey(key);
    }

    /*
     * Method connects an edge between node1 and node2, with an edge with weight >=0. If this edge already exists , the method would only update the distance between these two nodes to the given parameter w .Runs in O(1) complexity .
     */
    @Override
    public void connect(int node1, int node2, double w) {

        if (isContained(node1) && isContained(node2)) {
            if (w < 0) {
                return;
            }
            if (node1 == node2) {
                return;
            }
            if (hasEdge(node1, node2)) {

                ((NodeInfo) getNode(node1)).setDistance(getNode(node2), w);
                numOfChanges++;
                return;
            }
            ((NodeInfo) getNode(node1)).addNi((getNode(node2)), w);
        }
        ranGenerateNewUniqueKey();
        numOfEdges++;
        numOfChanges++;
        return;
    }

    /*
     * This method returns a Collection<node_info> type data structure which returns all the contained nodes in the grpah . Runs at O(1) complexity .
     */
    @Override
    public Collection<node_info> getV() {
        return this.nodeHash.values();
    }

    /*
     * This method returns a Collection containing all the nodes distancesToConnectedNodes to node_id , uses NodeData.getNi() mwthod to obtain O(1) complexity .
     */
    @Override
    public Collection<node_info> getV(int node_id) {
        if (isContained(node_id)) {
            return ((NodeInfo) getNode(node_id)).getConnections();
        }
        return null;
    }

    /*
     * Delete the node (with the given ID) from the graph - and removes all edges which starts or ends at this node. First, the method remove edges with all of the adja@cent nodes and then removes the node from this graph contained nodes HashMap ,after the removal the method returns the removed node , if no nodes was removed the method simply returns null . Runs at O(n) complexity , where n is the number of all adjacent nodes to the node in the graph .
     */
    @Override
    public node_info removeNode(int key) {
        if (isContained(key)) {
            List<node_info> list = new ArrayList<node_info>(getV(key));
            for (int i = 0; i < list.size(); i++) {
                removeEdge(key, list.get(i).getKey());
            }
            ranGenerateNewUniqueKey();
            numOfNodes--;
            numOfChanges++;
            return this.nodeHash.remove(key);
        }
        return null;
    }

    /*
     * Method deletes an edge between 2 nodes, thanks to removeNode(int key) method from the NodeInfo class the method runs at O(1) complexity .
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if (isContained(node1) && isContained(node2)) {
            if (node1 == node2) {
                return;
            }
            if (hasEdge(node1, node2)) {
                ((NodeInfo) getNode(node1)).removeNode(getNode(node2));
                ranGenerateNewUniqueKey();
                numOfEdges--;
                numOfChanges++;
            }
        }
        return;
    }

    /*
     * Method returns numOfNodes parameter which represents the number of nodes in this graph . Runs at O(1) complexity .
     */
    @Override
    public int nodeSize() {
        return numOfNodes;
    }

    /*
     * Method returns numOfEdges parameter which represents the number of edges in this graph . Runs at O(1) complexity .
     */
    @Override
    public int edgeSize() {
        return numOfEdges;
    }

    /*
     * Method returns numOfChanges parameter which represents the number of changes that was performed in this graph . Runs at O(1) complexity .
     */
    @Override
    public int getMC() {
        return numOfChanges;
    }

    /*
     *  Method provides a comparison for this graph and the given graph in the function, Has a "short key" check - returns true if both graphs have the same number of nodes , same number of edges and both unique keys are identical , this was made to keep O(1) best-case and average case runtime - because each unique key is a 7 digit 128-bit String there is 1/(127^7) chance to make a mistake which is really unlikely to happen but in case the commited graph has the same number of nodes and and edges but keys are different it will provide a bruteforce comparison for nodes and edges, if these graphs are identical the method returns true , otherwise method returns false .
     */
    @Override
    public boolean equals(Object compared) {
        if (this == compared) {
            return true;
        }
        if (compared instanceof weighted_graph) {
            if (((weighted_graph) compared).nodeSize() == this.nodeSize() && ((weighted_graph) compared).edgeSize() == this.edgeSize()) {
                if (this.uniqueKey.equals(((WGraph_DS) compared).getUniqueKey())) {
                    return true;
                }
                Iterator<node_info> itr = this.getV().iterator();
                while (itr.hasNext()) {
                    node_info n = itr.next();
                    if (((weighted_graph) compared).getNode(n.getKey()) == null) {
                        return false;
                    }
                    Iterator<node_info> itr1 = this.getV(n.getKey()).iterator();
                    while (itr1.hasNext()) {
                        node_info n1 = itr1.next();
                        if (((weighted_graph) compared).getEdge(n.getKey(), n1.getKey()) != this.getEdge(n.getKey(), n1.getKey())) {
                            return false;
                        }
                    }

                }
                return true;
            }
        }
        return false;
    }

    /*
     * Method returns this graph 7 digit 128-bit unique key .
     */
    public String getUniqueKey() {
        return this.uniqueKey;
    }

    /*
     * Method used in .copy() method WGraph_Algo class to make the copied graph have identical 7 digit 128-bit key, has a security check to avoid illegal using of the mehtod.
     */
    public void setUniqueKey(String copiedKey, int securityKey) {
        if (securityKey != 221917241) {
            System.out.println("Error: stop trying to break my key system you little cheater !");
            return;
        }
        this.uniqueKey = copiedKey + "";
    }
}
