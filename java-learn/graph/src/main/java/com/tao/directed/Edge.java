package com.tao.directed;

/**
 * 一条边，可以根据需要继承此类
 *
 * @author DongTao
 * @since 2019-10-08
 */
public class Edge<V> {

    /**
     * 起点
     */
    private V src;

    /**
     * 终点
     */
    private V dest;

    /**
     * 权值
     */
    private double weight;

    /**
     * 不带权值的一条边
     */
    public Edge(V src, V dest) {
        this(src, dest, 0);
    }

    /**
     * 带权值的一条边
     */
    public Edge(V src, V dest, double weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    /**
     * 获取起点
     */
    public V getSource() {
        return this.src;
    }

    /**
     * 获取终点
     */
    public V getDest() {
        return this.dest;
    }

    /**
     * 获取权值
     */
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return String.format("src : %s , dest : %s , weight : %s", src, dest, weight);
    }
}
