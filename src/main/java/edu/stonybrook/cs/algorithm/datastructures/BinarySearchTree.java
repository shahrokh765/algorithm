package edu.stonybrook.cs.algorithm.datastructures;

import com.sun.source.tree.BinaryTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**Binary Search Tree implementation which is not a good option if you insert/remove data
 * randomly because the data structure is not balanced and O(log(n)) cannot be guarantied.
 *
 * @author Mohammad Ghaderibaneh, <mghaderibane@cs.stonybrook.edu>*/
public class BinarySearchTree<T extends Comparable<T>>{
    private class Node {
        Node left, right;
        T data;

        public Node(T data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;
    private int size;

    public boolean isEmpty(){ return size == 0; }

    public int size(){ return size; }

    /**Add new element to the data structure.
     * If element already exist, false is returned meaning adding will not proceed.
     * @return returns if adding is done successfully. */
    public boolean add(T elm){
        if (contains(elm)) return false;        // duplicate is not allowed
        root = add(root, elm);
        size++;
        return true;
    }

    private Node add(Node node, T elm){
        if (node == null) return new Node(elm, null, null);     // reach to leaf
        if (elm.compareTo(node.data) < 0){
            node.left = add(node.left, elm);
        }else{
            node.right = add(node.right, elm);
        }
        return node;
    }

    /**Check if elm is already exists
     * @return a boolean value*/
    public boolean contains(T elm){
        return contains(root, elm);
    }

    private boolean contains(Node node, T elm){
        if (node == null) return false;

        int comp = elm.compareTo(node.data);

        if (comp < 0) return contains(node.left, elm);          // elm < node.data, go left
        else if (comp > 0) return contains(node.right, elm);    // elm > node.data, go right
        else return true;                                       // elm == node.data
    }

    /**Return maximum value. null if BinarySearchTree is empty*/
    public T findMax(){
        if (root == null) return null;      // data-structure is empty
        return findMax(root).data;
    }

    private Node findMax(Node node){
        if (node.right == null) return node;   // reach to maximum value for node sub-tree
        return findMax(node.right);
    }

    /**Return minimum value. null if BinarySearchTree is empty*/
    public T findMin(){
        if (root == null) return null;      // data-structure is empty
        return findMin(root).data;
    }

    private Node findMin(Node node){
        if (node.left == null) return node;   // reach to maximum value for node sub-tree
        return findMin(node.left);
    }

    /**Returns the height of the tree*/
    public int height(){
        return height(root);
    }

    private int height(Node node){
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**Remove element from the BinarySearchTree*/
    public boolean remove(T elm){
        if (!contains(elm)) return false;
        root = remove(root, elm);
        size--;
        return true;
    }

    private Node remove(Node node,T elm){
        int comp = elm.compareTo(node.data);
        if (comp < 0){  // node.data < elm, go left
            node.left = remove(node.left, elm);
        }else if (comp > 0){    // node.data > elm, go eight
            node.right = remove(node.right, elm);
        }else{  // node is equal to elm
            if (node.left == null && node.right == null){   // node has no children, easily remove it
                node = null;
            }else if (node.left == null){                   // node has only right child, replace it with that
                node = node.right;
            }else if (node.right == null){                  // node has only left child, replace it with that
                node = node.left;
            }else{                                // has both children, find successor in the longer sub-tree
                int leftHeight = height(node.left);
                int rightHeight = height(node.right);
                Node successor;
                if (leftHeight > rightHeight){      // find max element in the left sub-tree
                    successor = findMax(node.left);
                }else{                              // find min element in the right sub-tree
                    successor = findMin(node.right);
                }
                remove(node, successor.data);
                node.data = successor.data;     //swap the data
            }

        }
        return node;
    }

    /**Returns a list of elements inside the BinarySearchTree based on the given order.
     * @param order TreeTraversalOrder of the output
     * @return List of elements*/
    public List<T> traverse(TreeTraversalOrder order){
        List<T> tree = new ArrayList<>();
        switch (order){
            case PRE_ORDER -> preOrderTraversal(root, tree);
            case POST_ORDER -> postOrderTraversal(root, tree);
            case IN_ORDER -> inOrderTraversal(root, tree);
            case LEVEL_ORDER -> levelOrderTraversal(root, tree);
            default -> {}
        };
        return tree;
    }

    private void preOrderTraversal(Node node, List<T> list){
        if (node == null) return;
        list.add(node.data);
        preOrderTraversal(node.left, list);
        preOrderTraversal(node.right, list);
    }

    private void postOrderTraversal(Node node, List<T> list){
        if (node == null) return;
        postOrderTraversal(node.left, list);
        postOrderTraversal(node.right, list);
        list.add(node.data);
    }

    private void inOrderTraversal(Node node, List<T> list){
        if (node == null) return;
        inOrderTraversal(node.left, list);
        list.add(node.data);
        inOrderTraversal(node.right, list);
    }

    private void levelOrderTraversal(Node root, List<T> list){
        Queue<Node> queue = new ArrayQueue<>(size());
        queue.enqueue(root);
        while (!queue.isEmpty()){
            Node node = queue.dequeue();
            list.add(node.data);
            if (node.left != null) queue.enqueue(node.left);
            if (node.right != null) queue.enqueue(node.right);
        }
    }
}
