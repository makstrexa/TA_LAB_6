package Lab_6_Binary_Tree.Test;

class BSTNode {

    BSTNode left = null;
    BSTNode right = null;
    int data = 0;

    public BSTNode() {
        super();
    }

    public BSTNode(int data) {
        this.left = null;
        this.right = null;
        this.data = data;
    }

    @Override
    public String toString() {
        return "BSTNode [left=" + left + ", rigth=" + right + ", data=" + data + "]";
    }

}

