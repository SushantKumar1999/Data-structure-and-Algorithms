import java.io.*;
import java.util.*;

public class Main {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static Node construct(Integer[] arr) {
        Node root = new Node(arr[0], null, null);
        Pair rtp = new Pair(root, 1);

        Stack < Pair > st = new Stack < > ();
        st.push(rtp);

        int idx = 0;
        while (st.size() > 0) {
            Pair top = st.peek();
            if (top.state == 1) {
                idx++;
                if (arr[idx] != null) {
                    top.node.left = new Node(arr[idx], null, null);
                    Pair lp = new Pair(top.node.left, 1);
                    st.push(lp);
                } else {
                    top.node.left = null;
                }

                top.state++;
            } else if (top.state == 2) {
                idx++;
                if (arr[idx] != null) {
                    top.node.right = new Node(arr[idx], null, null);
                    Pair rp = new Pair(top.node.right, 1);
                    st.push(rp);
                } else {
                    top.node.right = null;
                }

                top.state++;
            } else {
                st.pop();
            }
        }

        return root;
    }

    public static void display(Node node) {
        if (node == null) {
            return;
        }

        String str = "";
        str += node.left == null ? "." : node.left.data + "";
        str += " <- " + node.data + " -> ";
        str += node.right == null ? "." : node.right.data + "";
        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    public static int height(Node node) {
        if (node == null) {
            return -1;
        }

        int lh = height(node.left);
        int rh = height(node.right);

        int th = Math.max(lh, rh) + 1;
        return th;
    }



    //1. using static variable
    static int dia;
    public static int helper(Node node) {
        if (node == null) {
            return -1;
        }

        int lch = helper(node.left);
        int rch = helper(node.right);

        int dist = lch + rch + 2;

        if (dist > dia) {
            dia = dist;
        }

        int ht = Math.max(lch, rch) + 1;

        return ht;

    }

    public static int diameter1(Node node) {
        // write your code here
        dia = 0;
        helper(node);

        return dia;
    }



    //2. using return type pair
    public static class DiaPair {
        int dia;
        int ht;

        DiaPair() {

        }

        DiaPair(int dia, int ht) {
            this.dia = dia;
            this.ht = ht;
        }
    }

    public static DiaPair helper(Node node) {
        if (node == null) {
            return new DiaPair(0, -1);
        }

        DiaPair lp = helper(node.left);
        DiaPair rp = helper(node.right);

        int dist = lp.ht + rp.ht + 2;
        int dia = Math.max(Math.max(lp.dia, rp.dia), dist);
        int ht = Math.max(lp.ht, rp.ht) + 1;

        return new DiaPair(dia, ht);
    }

    public static int diameter1(Node node) {
        // write your code here

        DiaPair rp = helper(node);

        return rp.dia;
    }

    public static void main(String[] args) throws Exception {
      //input can be managed
    }

}