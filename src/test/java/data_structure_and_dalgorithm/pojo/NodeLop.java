package data_structure_and_dalgorithm.pojo;

public class NodeLop {


    private String data;
    NodeLop next = this;

    public NodeLop(String data) {
        this.data = data;
    }

    public NodeLop append(NodeLop next) {
    //public void append(NodeLop next) {
        //如果下一个节点是本身说明只有一个节，否则
        NodeLop first = this.next;
        this.next = next;

        next.next = first;

        return this.next;
    }

    public NodeLop getNext() {
        return next;
    }

    @Override
    public String toString() {
        return this.data;
    }
}
