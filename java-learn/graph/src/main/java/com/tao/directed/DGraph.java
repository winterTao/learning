package com.tao.directed;


import java.util.Iterator;

/**
 * 有向图接口，定义需要实现的各个方法，可以选择使用邻接矩阵或者邻接链表来实现
 *
 * @param <V> V代表端点，可以根据需要设置器数据类型
 * @author DongTao
 * @since 2019-10-08
 */
public interface DGraph<V> {

    /**
     * 深度优先遍历
     */
    public static final int ITERATOR_TYPE_DFS = 0;
    /**
     * 广度优先遍历
     */
    public static final int ITERATOR_TYPE_BFS = 0;

    /**
     * 添加一个端点
     *
     * @return 新增端点的编号，-1表示插入失败
     */
    public int add(V v);

    /**
     * 添加一个边
     */
    public void add(Edge<V> e);

    /**
     * 删除一个顶点，与其相连的边也会被删除
     *
     * @return 被删除的顶点，如果找不到对应顶点则返回null
     */
    public V remove(V v);

    /**
     * 删除一条边
     *
     * @return 被删除的边，如果找不到对应的边则返回null
     */
    public Edge<V> remove(Edge<V> e);

    /**
     * 获得一个顶点
     *
     * @param index 顶点的编号
     */
    public V get(int index);

    /**
     * 获得一条边
     *
     * @param src 起点的编号
     * @param dest 终点的编号
     */
    public Edge<V> get(int src, int dest);

    /**
     * 得到当前图的迭代器，用于对图进行遍历
     *
     * @param type 遍历类型，深度优先或者广度优先
     * @param root 从哪个点开始遍历
     */
    public Iterator<V> iterator(int type, V root);

    /**
     * 将图转换为无环图
     */
    public void convertDAG();
}
