package ex1;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_Algo {

    weighted_graph pathTestGraphCreator() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(7);
        g.addNode(1);
        g.addNode(48);
        g.addNode(4);
        g.addNode(5);
        g.addNode(14);
        g.addNode(179);
        g.addNode(11);
        g.addNode(25);
        g.addNode(75);
        g.connect(0, 7, 10.5);
        g.connect(0, 1, 7.2);
        g.connect(7, 48, 2);
        g.connect(1, 48, 1);
        g.connect(0, 4, 4);
        g.connect(1, 4, 15.9);
        g.connect(1, 5, 57.1);
        g.connect(4, 25, 1.07);
        g.connect(48, 11, 7);
        g.connect(25, 5, 0.1);
        g.connect(11, 14, 5);
        g.connect(179, 11, 0);
        g.connect(14, 179, 1);
        g.connect(5, 14, 8);
        g.connect(25, 75, 1.05);
        return g;
    }

    @Test
    void copy() {
        weighted_graph g = pathTestGraphCreator();
        weighted_graph ge1 = new WGraph_DS();
        weighted_graph_algorithms wga = new WGraph_Algo();
        assertNotEquals(ge1, null);
        wga.init(ge1);
        assertEquals(ge1, wga.copy());
        wga.init(g);
        assertEquals(g, wga.copy());
    }

    @Test
    void isConnected() {
        weighted_graph g = pathTestGraphCreator();
        weighted_graph_algorithms wga1 = new WGraph_Algo();
        assertTrue(wga1.isConnected());
        weighted_graph_algorithms wga = new WGraph_Algo();
        wga.init(g);
        assertTrue(wga.isConnected());
        wga.getGraph().removeEdge(11, 179);
        assertTrue(wga.isConnected());
        wga.getGraph().addNode(801);
        assertFalse(wga.isConnected());
        wga.getGraph().removeEdge(25, 75);
        assertFalse(wga.isConnected());

    }

    @Test
    void shortestPathDist() {
        weighted_graph g = pathTestGraphCreator();
        weighted_graph_algorithms wga = new WGraph_Algo();
        wga.init(g);
        assertEquals(wga.shortestPathDist(1, 48), 1);
        assertEquals(wga.shortestPathDist(7, 7), 0);
        assertEquals(wga.shortestPathDist(0, 7), 10.2);
        assertEquals(wga.shortestPathDist(0, 11), 14.17);
        assertEquals(wga.shortestPathDist(7, 75), 16.32);
        assertEquals(wga.shortestPathDist(179, 11), 0);
        assertEquals(wga.shortestPathDist(14, 179), 1);
    }

    @Test
    void shortestPath() {
        weighted_graph g = pathTestGraphCreator();
        weighted_graph_algorithms wga = new WGraph_Algo();
        wga.init(g);
        List<node_info> path = new LinkedList<node_info>();
        assertEquals(path, wga.shortestPath(795, 401));
        path.add(g.getNode(0));
        path.add(g.getNode(4));
        path.add(g.getNode(25));
        path.add(g.getNode(5));
        path.add(g.getNode(14));
        path.add(g.getNode(179));
        path.add(g.getNode(11));
        assertEquals(path, wga.shortestPath(0, 11));
    }

    @Test
    void saveLoad() {
        weighted_graph g = pathTestGraphCreator();
        weighted_graph_algorithms wga = new WGraph_Algo();
        wga.init(g);
        String str = "g0.obj";
        wga.save(str);
        weighted_graph g1 = wga.copy();
        weighted_graph_algorithms wga1 = new WGraph_Algo();
        wga.load(str);
        assertEquals(g1,wga.getGraph());
        g1.removeNode(0);
        assertNotEquals(g1,wga.getGraph());
    }
}
