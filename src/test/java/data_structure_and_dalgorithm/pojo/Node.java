package data_structure_and_dalgorithm.pojo;

public class Node {
    //下一个节点
    private Node next;
    private String data;

    public Node(String data) {
        this.data = data;
    }

    //添加下一个节点
    public void appendNext(Node current, Node next) {
        //当前节点的下一个节点
        //如果当前节点与下一个节点的地址值相同 此处的递归就没有出口
        if (current.next != null && current.next != next) {
            //下一个节点有引用---再查询下一个节点的下一个节点--再如此判断
            //使下一个节点作为当前节点，next节点作为下下个节点
            appendNext(current.next, next);
        } else {
            //    下一个节点不为null直接赋值【引用地址值】

            current.next = next;

        }
    }

    /**
     * 记录当前节点
     * 循环体：
     * 获取下一节点 如果为空 就给声明赋地址值
     * 如果不为空 就获取下一个节点 继续判断
     *
     * @param next
     */
    public void appendNextSingle(Node next) {

        Node current = this;

        while (true) {
            if (current.next == null) {

                current.next = next;
                break;
            }
            //    下一个节点不为空-就继续获取下下个节点
            if (current != current.next) {
                //避免死循环，跳出当前方法
                current = current.next;

            } else {
                break;
            }
        }
    }

    /**
     * 链式
     *
     * @param next
     */
    public Node appendNextLian(Node next) {

        Node current = this;

        while (true) {
            if (current.next == null) {
                current.next = next;
                break;
            }
            //    下一个节点不为空-就继续获取下下个节点
            //如果吧自己添加到自己上就是个死循环--自己的后面是自己
            if (current == current.next) {
                //避免死循环，结束当前方法
                break;
            } else {
                current = current.next;

            }
        }
        //返回的是最后一个节点
        return current;
    }

    /**
     * 判断是否是最后一个节点
     *
     * @return
     */
    public Boolean isLast(Node node) {

        return false;
    }

    //    获取下一个节点
    public Node getNext() {
        return this.next;
    }

    @Override
    public String toString() {
        return this.data;
    }
}
