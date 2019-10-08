package com.tao.undirected;

import java.util.ArrayList;
import java.util.List;

/**
 * 无向简单图的节点
 *
 * @author DongTao
 * @since 2019-09-30
 */
public class GraphNode<T> {

    T data;
    List<GraphNode<T>> neighborList;
    boolean visited;

    public GraphNode(T data) {
        this.data = data;
        neighborList = new ArrayList<GraphNode<T>>();
        visited = false;
    }

    public boolean equals(GraphNode<T> node) {
        return this.data.equals(node.data);
    }

    /**
     * 还原图中所有节点为未访问
     */
    public void restoreVisited() {
        restoreVisited(this);
    }

    /**
     * 还原node的图所有节点为未访问
     */
    private void restoreVisited(GraphNode<T> node) {
        if (node.visited) {
            node.visited = false;
        }
        List<GraphNode<T>> neighbors = node.neighborList;
        for (GraphNode<T> neighbor : neighbors) {
            restoreVisited(neighbor);
        }

    }
}