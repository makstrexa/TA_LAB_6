package Lab_6_Binary_Tree.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        BinarySearchTree tree1 = new BinarySearchTree();

        long start = System.currentTimeMillis();

        for (int i = 1_000_000; i <= 1_005_000; i++) {
            tree.insert(i);
        }
        long finish = System.currentTimeMillis() - start;

        System.out.println("\u001B[34mIs balance: " + tree.isBalanced());
        System.out.println("\u001B[35mAdding 5_000 serial elems in range (1_000_000 to 1_005_000): " + finish + " ms.");

        long startSearch1 = System.nanoTime();
        tree.searchNode(1_002_500);
        long finishSearch1 = System.nanoTime() - startSearch1;
        System.out.println("\u001B[36mSearch in non balance tree: " + finishSearch1 + " ns.");

        long start1 = System.currentTimeMillis();
        for (int i = 1_005_000; i >= 1_000_000; i--) {
            tree.delete(i);
        }
        long finish1 = System.currentTimeMillis() - start1;
        System.out.println("\u001B[33mDeleting 5_000 serial elements(non-balance): " + finish1 + " ms.");

        for (int i = 1_000_000; i <= 1_005_000; i++) {
            tree.insert(i);
        }

        BinarySearchTree treeSer = new BinarySearchTree();
        for (int i = 1_000_000; i <= 1_005_000; i++) {
            treeSer.insert(i);
        }
        long start2 = System.nanoTime();
        treeSer.DSW();
        long finish2 = System.nanoTime() - start2;
        System.out.println(" ");
        System.out.println("\u001B[34mIs balance: " + treeSer.isBalanced());
        System.out.println("\u001B[31mBalance with DSW algorithm(serial nums): " + finish2 + " ns.\n");

        long startSearch2 = System.nanoTime();
        treeSer.searchNode(1_004_555);
        long finishSearch2 = System.nanoTime() - startSearch2;
        System.out.println("\u001B[36mSearch in balance tree: " + finishSearch2 + " ns.");

        long start6 = System.currentTimeMillis();
        for (int i = 1_005_000; i >= 1_000_000; i--) {
            treeSer.delete(i);
        }
        long finish6 = System.currentTimeMillis() - start6;
        System.out.println("\u001B[33mDeleting 5_000 serial elements(balance tree): " + finish6 + " ms.");




        // for tree
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            arr.add(i * 8888);
        }
        Collections.shuffle(arr);
        //

        long start3 = System.currentTimeMillis();
        for (int i = 0; i < 5_000; i++) {
            tree1.insert(arr.get(i));
        }
        long finish3 = System.currentTimeMillis() - start3;
        System.out.println("\u001B[35mAdding 5_000 rand elems: " + finish3 + " ms.");

        long start4 = System.nanoTime();
        tree1.DSW();
        long finish4 = System.nanoTime() - start4;
        System.out.println("\u001B[31mBalance with DSW algorithm(rand nums): " + finish4 + " ns.\n");
    }



}
