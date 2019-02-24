package data_structure_and_dalgorithm.pojo;

public class NodeDouble {

    private NodeDouble nodeDoublePre = this;
    private NodeDouble nodeDoubleNext = this;

    private String data;

    public NodeDouble(String data) {
        this.data = data;
    }


    public NodeDouble append(NodeDouble nodeDoubleNext) {
        //    当前节点的下一个节点为 传入参数
        //    传入参数的 前一个节点为 当前节点
        //    传入参数的节点为当前节点的前一个节点
        NodeDouble next = this.nodeDoubleNext;

        this.nodeDoubleNext = nodeDoubleNext;
        nodeDoubleNext.nodeDoublePre = this;

        nodeDoubleNext.nodeDoubleNext = next;
        //原来的节点的前一个节点作为现在的节点--为什么不用this.nodeNextPre??
        //因为
        //this.nodeDoublePre=nodeDoubleNext;
        next.nodeDoublePre = nodeDoubleNext;

        return nodeDoubleNext;
    }

    @Override
    public String toString() {
        return nodeDoublePre.data + "  " + nodeDoubleNext.data;
        //return null;
    }
}
