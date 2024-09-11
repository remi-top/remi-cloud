package ai.remi.comm.domain.tree;

import ai.remi.comm.util.collection.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class TreeBuild<T extends TreeNode> {
    /**
     * rootId
     */
    public String rootId;
    /**
     * 保存参与构建树形的所有数据（通常数据库查询结果）
     */
    public List<T> nodeList = new ArrayList<>();

    /**
     * 构造方法
     *
     * @param nodeList 将数据集合赋值给nodeList，即所有数据作为所有节点。
     */
    public TreeBuild(String rootId, List<T> nodeList) {
        this.rootId = rootId;
        this.nodeList = nodeList;
    }

    /**
     * 获取需构建的所有根节点（顶级节点） "0"
     *
     * @return 所有根节点List集合
     */
    public List<T> getRootNode() {
        // 保存所有根节点（所有根节点的数据）
        List<T> rootNodeList = new ArrayList<>();
        // treeNode：查询出的每一条数据（节点）
        for (T treeNode : nodeList) {
            // 判断当前节点是否为根节点，此处注意：若parentId类型是String，则要采用equals()方法判断。
            if (this.rootId.equals(treeNode.getParentId())) {
                // 是，添加
                rootNodeList.add(treeNode);
            }
        }
        return rootNodeList;
    }

    /**
     * 根据每一个顶级节点（根节点）进行构建树形结构
     *
     * @return 构建整棵树
     */
    public List<T> buildTree() {
        // treeNodes：保存一个顶级节点所构建出来的完整树形
        List<T> treeNodes = new ArrayList<T>();
        // getRootNode()：获取所有的根节点
        for (T treeRootNode : getRootNode()) {
            // 将顶级节点进行构建子树
            treeRootNode = buildChildTree(treeRootNode);
            // 完成一个顶级节点所构建的树形，增加进来
            treeNodes.add(treeRootNode);
        }
        // 这里对父节点排序
        CollectionUtils.sort(treeNodes, "sort");
        // for循环结束，即获取所有的根节点，树形构建结束，设置树结果
        return treeNodes;
    }

    /**
     * 递归-----构建子树形结构
     *
     * @param pNode 根节点（顶级节点）
     * @return 整棵树
     */
    public T buildChildTree(T pNode) {
        List<T> childTree = new ArrayList<T>();
        // nodeList：所有节点集合（所有数据）
        for (T treeNode : nodeList) {
            // 判断当前节点的父节点ID是否等于根节点的ID，即当前节点为其下的子节点
            if (treeNode.getParentId().equals(pNode.getId())) {
                // 再递归进行判断当前节点的情况，调用自身方法
                childTree.add(buildChildTree(treeNode));
            }
        }
        // 这里对子节点排序
        CollectionUtils.sort(childTree, "sort");
        // for循环结束，即节点下没有任何节点，树形构建结束，设置树结果
        pNode.setChildren(childTree);
        return pNode;
    }
}
