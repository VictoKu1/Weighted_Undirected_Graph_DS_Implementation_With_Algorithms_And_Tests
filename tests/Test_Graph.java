package ex1.tests;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class Test_Graph {
    public weighted_graph oneCenterGraphConstructor(int numOfNodes, int numOfEdges, double basicDistanceBetween) {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        for (int i = 1; i < numOfNodes; i++) {
            g.addNode(i);
            if (numOfEdges > 0) {
                g.connect(0, i, basicDistanceBetween);
                numOfEdges--;
            }
        }
        return g;
    }

    @Test
    void nodeSize() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(1);
        g.addNode(2);
        g.removeNode(0);
        g.removeNode(1);
        g.removeNode(2);
        assertEquals(g.nodeSize(), 0);
        assertEquals(g.getMC(), 6);
    }

    @Test
    void edgeSize() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.connect(0, 1, 1);
        g.connect(0, 2, 2);
        g.connect(0, 3, 3);
        g.connect(0, 1, 1);
        int e_size = g.edgeSize();
        assertEquals(3, e_size);
        double w03 = g.getEdge(0, 3);
        double w30 = g.getEdge(3, 0);
        assertEquals(w03, w30);
        assertEquals(w03, 3);
        assertEquals(g.getMC(), 8);
    }

    @Test
    void hasEdge() {
        weighted_graph g = oneCenterGraphConstructor(149, 25, 15);
        g.removeEdge(0, 2);
        assertEquals(g.hasEdge(0, 1), true);
        assertEquals(g.hasEdge(0, 2), false);
        assertEquals(g.hasEdge(1, 2), false);
        assertEquals(g.edgeSize(), 24);
        assertEquals(g.getMC(), 175);
    }

    @Test
    void getEdge() {
        weighted_graph g = oneCenterGraphConstructor(149, 25, 15);
        assertEquals(g.getEdge(17, 95), -1);
        assertEquals(g.getEdge(0, 1), 15);
        g.removeEdge(0, 1);
        assertEquals(g.getEdge(0, 1), -1);
        assertEquals(g.getEdge(159, 1), -1);
        g.connect(0, 1, 7);
        assertEquals(g.getEdge(0, 1), 7);
    }

    @Test
    void getV() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.connect(0, 1, 1);
        g.connect(0, 2, 2);
        g.connect(0, 3, 3);
        g.connect(0, 1, 1);
        Collection<node_info> v = g.getV();
        Iterator<node_info> iter = v.iterator();
        while (iter.hasNext()) {
            node_info n = iter.next();
            assertNotNull(n);
        }
    }

    @Test
    void getVadjacents() {
        weighted_graph g = oneCenterGraphConstructor(149, 25, 15);
        Iterator<node_info> itr = g.getV().iterator();
        while (itr.hasNext()) {
            Iterator<node_info> itr1 = g.getV(itr.next().getKey()).iterator();
            while (itr1.hasNext()) {
                node_info n = itr1.next();
                assertNotNull(n);
            }
        }
    }

    @Test
    void removeNode() {
        weighted_graph g = oneCenterGraphConstructor(149, 25, 15);
        g.removeNode(179);
        assertEquals(g.nodeSize(), 149);
        assertEquals(g.edgeSize(), 25);
        g.removeNode(1);
        assertEquals(g.nodeSize(), 148);
        assertEquals(g.edgeSize(), 24);
        g.removeNode(0);
        assertEquals(g.nodeSize(), 147);
        assertEquals(g.edgeSize(), 0);
        g.removeNode(0);
        assertEquals(g.nodeSize(), 147);
        assertEquals(g.edgeSize(), 0);
        assertNull(g.getNode(0));
    }

    @Test
    void removeEdge() {
        weighted_graph g = oneCenterGraphConstructor(149, 25, 15);
        assertEquals(g.nodeSize(), 149);
        assertEquals(g.edgeSize(), 25);
        g.removeEdge(1, 2);
        assertEquals(g.nodeSize(), 149);
        assertEquals(g.edgeSize(), 25);
        g.removeEdge(0, 1);
        g.removeEdge(0, 1);
        g.removeEdge(0, 1);
        g.removeEdge(0, 1);
        g.removeEdge(0, 1);
        assertEquals(g.nodeSize(), 149);
        assertEquals(g.edgeSize(), 24);
        g.removeEdge(0, 795);
        assertEquals(g.nodeSize(), 149);
        assertEquals(g.edgeSize(), 24);
        g.removeEdge(0, 0);
        assertEquals(g.nodeSize(), 149);
        assertEquals(g.edgeSize(), 24);
        g.removeEdge(-5, 715);
        assertEquals(g.nodeSize(), 149);
        assertEquals(g.edgeSize(), 24);
    }

    @Test
    void equals() {
        weighted_graph g1 = oneCenterGraphConstructor(149, 25, 15);
        weighted_graph g2 = oneCenterGraphConstructor(149, 25, 15);
        weighted_graph g3 = oneCenterGraphConstructor(149, 27, 15);
        weighted_graph g4 = oneCenterGraphConstructor(149, 25, 7);
        weighted_graph g5 = oneCenterGraphConstructor(147, 25, 15);
        assertTrue(g1.equals(g1));
        assertTrue(g1.equals(g2));
        assertFalse(g1.equals(g3));
        assertFalse(g1.equals(g4));
        assertFalse(g1.equals(g5));
    }


}
