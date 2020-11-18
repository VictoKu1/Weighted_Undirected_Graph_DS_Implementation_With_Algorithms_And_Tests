package ex1;

import java.io.*;
import java.util.*;

/*
 *  WGraph_Algo class which is implementing weighted_graph_algorithms interface mainly represents a class with a set of basic methods that might gert applied to a waighted graph data structure . The class in mainly contains:  1. Parameter of a WGraph_DS class which is implementing weighted_grpah_algorithms . @author Victor.Kushnir .
 */
public class WGraph_Algo implements weighted_graph_algorithms {
    private weighted_graph g;

    /*
     *   Constructor method which initializes as new WGraph_DS object the weighted_graph g parameter .
     */
    public WGraph_Algo() {
        g = new WGraph_DS();
    }

    /**
     * Method which make the parameter this.g to point on the inputted graph g .
     */
    @Override
    public void init(weighted_graph g) {
        this.g = g;
    }

    /**
     * Method which returns a pointer to the this.g graph this class is working on .
     */
    @Override
    public weighted_graph getGraph() {
        return this.g;
    }

    /**
     * Method computes and returns a deep copy of this.g graph, using two helping method , void copyNodes(weighted_graph target)- which copy this.g graph nodes to the given graph . and void copyEdges(weighted_graph target) - which copy this.g edges to the given graph , aslo fakes the unique key of the graph by providing the secret security key to the setUniqueKey(String copiedKey, int security) method in WGraph_DS class .
     */
    @Override
    public weighted_graph copy() {
        weighted_graph g1 = new WGraph_DS();
        copyNodes(g1);
        copyEdges(g1);
        ((WGraph_DS) g1).setUniqueKey(((WGraph_DS) (this.g)).getUniqueKey(), 221917241);
        return g1;
    }

    /*
     *  Helping method which copy this.g graph nodes to the given graph .
     */
    private void copyNodes(weighted_graph target) {
        Iterator<node_info> itr = this.g.getV().iterator();
        while (itr.hasNext()) {
            target.addNode(itr.next().getKey());
        }
    }

    /*
     *  Helping method which copy this.g edges to the given graph .
     */
    public void copyEdges(weighted_graph target) {
        Iterator<node_info> itr1 = this.g.getV().iterator();
        while (itr1.hasNext()) {
            int f = itr1.next().getKey();
            Iterator<node_info> itr2 = this.g.getV(f).iterator();
            while (itr2.hasNext()) {
                int t = itr2.next().getKey();
                if (!target.hasEdge(f, t)) {
                    target.connect(f, t, this.g.getEdge(f, t));
                }
                if (target.edgeSize() == this.g.edgeSize()) {
                    return;
                }
            }
        }
    }

    /*
     * Returns true if and only if (iff) there is a valid path from EVREY node to each other node. The method uses BFSFromNode(int src) method to try to pass all over the nodes and mark its Info parameter to "V" , and afterwards check if the nodes in the graph have changed their Info parameter to "V" using allTheInfosAreV() helper checking method. The method is using Iterator interface .
     */
    @Override
    public boolean isConnected() {
        if (this.g.nodeSize() == 0) {
            return true;
        }
        boolean answer = false;
        if (this.g.getV().iterator().hasNext()) {
            BFSFromNode(this.g.getV().iterator().next().getKey());
            answer = allTheInfosAreV();
            setTagAndInfoOfEveryNodeInGraphToDefault();
        }
        return answer;
    }

    /*
     * Privately accessed helper method , used to check if all nodes in the graph changed their Info parameter to "V" as a mark of being checked in the BFSFromNode(int src) algorithm to know if there is a path from every node to every other node .
     */
    private boolean allTheInfosAreV() {
        Iterator<node_info> it1 = this.g.getV().iterator();
        while (it1.hasNext()) {
            if (((WGraph_DS.NodeInfo) (it1.next())).getInfo().equals("")) {
                return false;
            }
        }
        return true;
    }

    /*
     *  Helper method that is implementing BFS algorithm from a node which key is src , using LinkedList data structure as a Queue data structure .
     */
    private void BFSFromNode(int src) {
        int dist = 0;
        LinkedList<node_info> nodeQueue = new LinkedList<node_info>();
        nodeQueue.add(this.g.getNode(src));
        int containsOnThisLevel = 1;
        int added = 0;
        while (!nodeQueue.isEmpty()) {
            if (containsOnThisLevel == 0) {
                dist++;
                containsOnThisLevel = added;
                added = 0;
            }
            if (((WGraph_DS.NodeInfo) (nodeQueue.peek())).getInfo().equals("")) {
                ((WGraph_DS.NodeInfo) (nodeQueue.peek())).setInfo("V");
                ((WGraph_DS.NodeInfo) (nodeQueue.peek())).setTag(dist);
                added = added + ((WGraph_DS.NodeInfo) (nodeQueue.peek())).getConnections().size();
                adjacentsEnque(nodeQueue, ((WGraph_DS.NodeInfo) (nodeQueue.peek())).getConnections());
            }
            nodeQueue.pop();
            containsOnThisLevel--;
        }
    }

    /*
     *  Helper method that is used in BFSFromNode(int src) method to add the nodeArray contained parameters to nodeQueue, the adding is produced using Iterator interface .
     */
    private void adjacentsEnque(LinkedList<node_info> nodeQueue, Collection<node_info> nodeArray) {
        Iterator<node_info> itr = nodeArray.iterator();
        while (itr.hasNext()) {
            nodeQueue.add(itr.next());
        }
    }

    /*
     *  Helper method that it used after the Dijkstra algorithm to set all the parent parameters to default value null .
     */
    private void setParentOfEveryNodeInGraphToDefault() {
        Iterator<node_info> itr = this.g.getV().iterator();
        while (itr.hasNext()) {
            ((WGraph_DS.NodeInfo) (itr.next())).setParent(null);
        }
    }

    /*
     *  Helper method that is used after the BFS algorithm to set all Node's Infos and Tags parameters to default ("") and (-1) .
     */
    private void setTagAndInfoOfEveryNodeInGraphToDefault() {
        Iterator<node_info> itr = this.g.getV().iterator();
        while (itr.hasNext()) {
            setTagAndInfo((WGraph_DS.NodeInfo) (itr.next()));
        }
    }

    /*
     * Helper method to a helper method that sets all Node's Infos and Tags parameters to default ("") and (-1) .
     */
    private void setTagAndInfo(node_info n) {
        ((WGraph_DS.NodeInfo) (n)).setTag(-1);
        ((WGraph_DS.NodeInfo) (n)).setInfo("");
    }

    /**
     * Returns the length of the shortest path between src to dest using Dijkstra(int src) method and as a little help also the BFS algorithm to avoid entering nodes that have no path to the destination node .
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        if (((WGraph_DS) (this.g)).isContained(src) && ((WGraph_DS) (this.g)).isContained(dest)) {
            BFSFromNode(dest);
            if (this.g.getNode(dest).getInfo().equals("")) {
                return -1;
            }
            Dijkstra(src);
            double answer = this.g.getNode(dest).getTag();
            setTagAndInfoOfEveryNodeInGraphToDefault();
            setParentOfEveryNodeInGraphToDefault();
            return answer;
        }
        return -1;
    }

    /*
     * Method is implementing Dijkstra algorithm from the given node key all over the graph. It start on the given node key, changing it tag parameter to 0 and them adding it to the queue with all of it adjacent nodes, avoiding those who are marked by the previously used BFS algoritm as unconnected and sets all the connected nodes which tag is higher than the parent tag summarized with the weight of the edge between them, tag parameter to the tag parameter of their current parent node summarized with the weight of the node between them, and getting the parent node out of the queue, and providing the same procedure with the new head of the queue, and so on until the queue is empty, then the method stops.
     */
    private void Dijkstra(int src) {
        if (this.g.getNode(src).getInfo().equals("V")) {
            setTheDistancesToInf();
            this.g.getNode(src).setTag(0);
            LinkedList<node_info> queue = new LinkedList<node_info>();
            ((WGraph_DS.NodeInfo) (this.g.getNode(src))).setParent(null);
            queue.addLast(this.g.getNode(src));
            while (!queue.isEmpty()) {
                node_info parent = queue.pop();
                addToQueueEachAdjacentNodeWhichIsConnectedSomehowToTheDest(queue, parent);
            }
        }
        return;
    }

    /*
     *  Helping method for Dijkstra(int src) method which adds new nodes to the algorithm queue dependently if its tag parameter is lower than the tag of parent summarized with the weight of the node between them and if the node is determined as valid by the previously produced BFS algorithm. Mainly exist to make Dijkstra(int src) method more readable .
     */
    private void addToQueueEachAdjacentNodeWhichIsConnectedSomehowToTheDest(LinkedList<node_info> queue, node_info parent) {
        Iterator<node_info> itr = ((WGraph_DS.NodeInfo) (parent)).getConnections().iterator();
        while (itr.hasNext()) {
            node_info node = itr.next();
            if (!queue.contains(node)) {
                if (node.getInfo().equals("V")) {
                    double distance = parent.getTag() + this.g.getEdge(parent.getKey(), node.getKey());
                    if (distance < node.getTag()) {
                        node.setTag(distance);
                        ((WGraph_DS.NodeInfo) (node)).setParent(parent);
                        queue.addLast(node);
                    }
                }
            } else {
                node_info containedNode = queue.get(queue.indexOf(node));
                double distance = parent.getTag() + this.g.getEdge(parent.getKey(), containedNode.getKey());
                if (distance < containedNode.getTag()) {
                    containedNode.setTag(distance);
                    ((WGraph_DS.NodeInfo) (containedNode)).setParent(parent);
                    queue.addLast(containedNode);
                }
            }
        }
    }

    /*
     *  Helping method to the Dijkstra(int src) method , which is marking all the tags of all nodes in the graph to infinity .
     */
    private void setTheDistancesToInf() {
        Iterator<node_info> itr = this.g.getV().iterator();
        while (itr.hasNext()) {
            itr.next().setTag(Double.POSITIVE_INFINITY);
        }
    }

    /*
     * Returns the the shortest path between src to dest - as an ordered List of nodes,mainly using the Dijkstra(int src) method, works the same as the shortestPathDist(int src, int dest) method but instead of returning the distance it returns a List<node_info> which is built by passing from the dest to the src of the path right afer the Dijkstra(int src) method stops working , each node_info has a node_info parent parameter which points to the parent node which was determined as the best option to find the shortest path by  Dijkstra(int src) method , at the end of the method all of the node parameters like info,tag and parent are being set to their default values.
     */
    @Override
    public List<node_info> shortestPath(int src, int dest) {
        LinkedList<node_info> path = new LinkedList<node_info>();
        if (((WGraph_DS) (this.g)).isContained(src) && ((WGraph_DS) (this.g)).isContained(dest)) {
            if (src == dest) {
                path.add(this.g.getNode(src));
                return path;
            }
            BFSFromNode(dest);
            if (this.g.getNode(dest).getInfo().equals("")) {
                return path;
            }
            Dijkstra(src);
            buildPath(path, src, dest);
            setTagAndInfoOfEveryNodeInGraphToDefault();
            setParentOfEveryNodeInGraphToDefault();
            return path;
        }
        return path;
    }

    /*
     * Helping method which is used after the running of Dijkstra(int src) method in shortestPath(int src, int dest), this method is building a path from the destination node to the source, using LinkedList ability to add parameters to the end of the list and therefore "inverting" the order so it will return as a list of a route from the source to the destination node. The building is simply adding each node (starting at the destination node) parent parameter to the list which is being determined by the Dijkstra(int src) method.
     */
    public void buildPath(LinkedList<node_info> path, int src, int dest) {
        if (src == dest) {
            path.addFirst(this.g.getNode(src));
            return;
        }
        path.addFirst(this.g.getNode(dest));
        buildPath(path, src, ((WGraph_DS.NodeInfo) (this.g.getNode(dest))).getParent().getKey());
    }

    /**
     * Saves this weighted (undirected) graph to the give file name using Serialized interface implemented by WGraph_DS and NodeInfo classes .
     */
    @Override
    public boolean save(String file) {
        try {
            FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this.g);
            fout.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /*
     * This method load a graph to this graph algorithm. If the file was successfully loaded - the underlying graph of this class will be changed (to the loaded one), in case the graph was not loaded the original graph should remain "as is". This method is using equals method in WGraph_DS class, in case the uploaded graph is equal to the already contained in this class graph the graph will not get updated. Returns true if the graph was uploaded successfully, false otherwise.
     */
    @Override
    public boolean load(String file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object ldg = (ois.readObject());
            if ((this.g).equals((weighted_graph) ldg)) {
                ois.close();
                return true;
            }
            this.g = (weighted_graph) ldg;
            ois.close();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
