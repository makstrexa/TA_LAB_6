package Lab_6_Binary_Tree.Test;

import java.util.NoSuchElementException;

public class BinarySearchTree {

    BSTNode root = null;

    public BinarySearchTree() {

    }

    public void insert(int data) {
        BSTNode node = new BSTNode(data);
        if (root == null) {
            root = node;
            return;
        }

        BSTNode currentNode = root;
        BSTNode parentNode = null;

        while (true) {
            parentNode = currentNode;
            if (currentNode.data == data)
                throw new IllegalArgumentException("Duplicates nodes note allowed in Binary Search Tree");

            if (currentNode.data > data) {
                currentNode = currentNode.left;
                if (currentNode == null) {
                    parentNode.left = node;
                    return;
                }
            } else {
                currentNode = currentNode.right;
                if (currentNode == null) {
                    parentNode.right = node;
                    return;
                }
            }
        }
    }

    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(BSTNode node) {
        if (node == null) {
            return 0;
        } else {
            int count = 1;
            count += countNodes(node.left);
            count += countNodes(node.right);
            return count;
        }
    }

    public boolean searchNode(int data) {
        if (empty())
            return empty();
        return searchNode(data, root);
    }

    public boolean searchNode(int data, BSTNode node) {
        if (node != null) {
            if (node.data == data)
                return true;
            else if (node.data > data)
                return searchNode(data, node.left);
            else if (node.data < data)
                return searchNode(data, node.right);
        }
        return false;
    }

    public boolean delete(int data) {
        if (empty())
            throw new NoSuchElementException("Tree is Empty");

        BSTNode currentNode = root;
        BSTNode parentNode = root;
        boolean isLeftChild = false;

        while (currentNode.data != data) {
            parentNode = currentNode;
            if (currentNode.data > data) {
                isLeftChild = true;
                currentNode = currentNode.left;
            } else if (currentNode.data < data) {
                isLeftChild = false;
                currentNode = currentNode.right;
            }
            if (currentNode == null)
                return false;
        }

        // CASE 1: node with no child
        if (currentNode.left == null && currentNode.right == null) {
            if (currentNode == root)
                root = null;
            if (isLeftChild)
                parentNode.left = null;
            else
                parentNode.right = null;
        }

        // CASE 2: if node with only one child
        else if (currentNode.left != null && currentNode.right == null) {
            if (root == currentNode) {
                root = currentNode.left;
            }
            if (isLeftChild)
                parentNode.left = currentNode.left;
            else
                parentNode.right = currentNode.left;
        } else if (currentNode.right != null && currentNode.left == null) {
            if (root == currentNode)
                root = currentNode.right;
            if (isLeftChild)
                parentNode.left = currentNode.right;
            else
                parentNode.right = currentNode.right;
        }

        // CASE 3: node with two child
        else if (currentNode.left != null && currentNode.right != null) {

            // Now we have to find minimum element in rigth sub tree
            // that is called successor
            BSTNode successor = getSuccessor(currentNode);
            if (currentNode == root)
                root = successor;
            if (isLeftChild)
                parentNode.left = successor;
            else
                parentNode.right = successor;
            successor.left = currentNode.left;
        }

        return true;
    }

    private BSTNode getSuccessor(BSTNode deleteNode) {

        BSTNode successor = null;
        BSTNode parentSuccessor = null;
        BSTNode currentNode = deleteNode.left;

        while (currentNode != null) {
            parentSuccessor = successor;
            successor = currentNode;
            currentNode = currentNode.left;
        }

        if (successor != deleteNode.right) {
            parentSuccessor.left = successor.left;
            successor.right = deleteNode.right;
        }

        return successor;
    }

    public int parent(int data) {
        return parent(root, data);
    }

    private int parent(BSTNode node, int data) {
        if (empty())
            throw new IllegalArgumentException("Empty");
        if (root.data == data)
            throw new IllegalArgumentException("No Parent node found");

        BSTNode parent = null;
        BSTNode current = node;

        while (current.data != data) {
            parent = current;
            if (current.data > data)
                current = current.left;
            else
                current = current.right;
            if (current == null)
                throw new IllegalArgumentException(data + " is not a node in tree");
        }
        return parent.data;
    }

    public int sibling(int data) {
        return sibling(root, data);
    }

    private int sibling(BSTNode node, int data) {
        if (empty())
            throw new IllegalArgumentException("Empty");
        if (root.data == data)
            throw new IllegalArgumentException("No Parent node found");

        BSTNode cureent = node;
        BSTNode parent = null;
        boolean isLeft = false;

        while (cureent.data != data) {
            parent = cureent;
            if (cureent.data > data) {
                cureent = cureent.left;
                isLeft = true;
            } else {
                cureent = cureent.right;
                isLeft = false;
            }
            if (cureent == null)
                throw new IllegalArgumentException("No Parent node found");
        }
        if (isLeft) {
            if (parent.right != null) {
                return parent.right.data;
            } else
                throw new IllegalArgumentException("No Sibling is there");
        } else {
            if (parent.left != null)
                return parent.left.data;
            else
                throw new IllegalArgumentException("No Sibling is there");
        }
    }

    public void leafNodes() {
        if (empty())
            throw new IllegalArgumentException("Empty");
        leafNode(root);
    }

    private void leafNode(BSTNode node) {
        if (node == null)
            return;
        if (node.right == null && node.left == null)
            System.out.print(node.data + " ");
        leafNode(node.left);
        leafNode(node.right);
    }

    public int level(int data) {
        if (empty())
            throw new IllegalArgumentException("Empty");
        return level(root, data, 1);
    }

    private int level(BSTNode node, int data, int level) {
        if (node == null)
            return 0;
        if (node.data == data)
            return level;
        int result = level(node.left, data, level + 1);
        if (result != 0)
            return result;
        result = level(node.right, data, level + 1);
        return result;
    }

    public int depth() {
        return depth(root);
    }

    private int depth(BSTNode node) {
        if (node == null)
            return 0;
        else
            return 1 + Math.max(depth(node.left), depth(node.right));
    }

    public int height() {
        return height(root);
    }

    private int height(BSTNode node) {
        if (node == null)
            return 0;
        else
            return 1 + Math.max(height(node.left), height(node.right));
    }

    public void preorder() {
        preorder(root);
    }

    private void preorder(BSTNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    public void inorder() {
        inorder(root);
    }

    private void inorder(BSTNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }
    }

    public void postorder() {
        postorder(root);
    }

    private void postorder(BSTNode node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.data + " ");
        }
    }

    public boolean empty() {
        return root == null;
    }

    // Определяем, является ли двоичное дерево сбалансированным двоичным деревом
    public boolean isBalanced()
    {
        return isBalanced(root);
    }

    private boolean isBalanced(BSTNode root){

        if(root == null)
            return true;

        int balance = getBalance(root);
        if(Math.abs(balance)>1)
            return false;
        // сначала корень, а затем влево и вправо
        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int getBalance(BSTNode node){

        if(node == null)
            return 0;

        return height(node.left)-height(node.right);
    }

    public void DSW() {
        if (null != root) {
            createBackbone();// effectively: createBackbone( root)
            createPerfectBST();//effectively: createPerfectBST( root)
        }
    }

    /**
     * Time complexity: O(n)
     */
    private void createBackbone() {
        BSTNode grandParent = null;
        BSTNode parent = root;
        BSTNode leftChild;

        while (null != parent) {
            leftChild = parent.left;
            if (null != leftChild) {
                grandParent = rotateRight(grandParent, parent, leftChild);
                parent = leftChild;
            } else {
                grandParent = parent;
                parent = parent.right;
            }
        }
    }

    /************************************************************************
     *   Before      After
     *    Gr          Gr
     *     \           \
     *     Par         Ch
     *    /  \        /  \
     *   Ch   Z      X   Par
     *  /  \            /  \
     * X    Y          Y    Z
     ***********************************************************************/
    private BSTNode rotateRight(BSTNode grandParent, BSTNode parent, BSTNode leftChild) {
        if (null != grandParent) {
            grandParent.right = leftChild;
        } else {
            root = leftChild;
        }
        parent.left = leftChild.right;
        leftChild.right = parent;
        return grandParent;
    }

    /**
     * Time complexity: O(n)
     */
    private void createPerfectBST() {
        int n = 0;
        for (BSTNode tmp = root; null != tmp; tmp = tmp.right) {
            n++;
        }
        //m = 2^floor[lg(n+1)]-1, ie the greatest power of 2 less than n: minus 1
        int m = greatestPowerOf2LessThanN(n + 1) - 1;
        makeRotations(n - m);

        while (m > 1) {
            makeRotations(m /= 2);
        }
    }

    /**
     * Time complexity: log(n)
     */
    private int greatestPowerOf2LessThanN(int n) {
        int x = MSB(n);//MSB
        return (1 << x);//2^x
    }

    /**
     * Time complexity: log(n)
     * return the index of most significant set bit: index of
     * least significant bit is 0
     */
    public int MSB(int n) {
        int ndx = 0;
        while (1 < n) {
            n = (n >> 1);
            ndx++;
        }
        return ndx;
    }

    private void makeRotations(int bound) {
        BSTNode grandParent = null;
        BSTNode parent = root;
        BSTNode child = root.right;
        for (; bound > 0; bound--) {
            try {
                if (null != child) {
                    rotateLeft(grandParent, parent, child);
                    grandParent = child;
                    parent = grandParent.right;
                    child = parent.right;
                } else {
                    break;
                }
            } catch (NullPointerException convenient) {
                break;
            }
        }
    }

    private void rotateLeft(BSTNode grandParent, BSTNode parent, BSTNode rightChild) {
        if (null != grandParent) {
            grandParent.right = rightChild;
        } else {
            root = rightChild;
        }
        parent.right = rightChild.left;
        rightChild.left = parent;
    }

}
