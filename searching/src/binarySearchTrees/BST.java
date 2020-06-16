package binarySearchTrees;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BST<Key extends Comparable<Key>, Value>{
    private Node root;

    private class Node{
        private Key key;    // sorted by key
        private Value val;
        private Node left, right;   // left and right subtrees
        private int N;  // number of nodes in subtree

        public Node(Key key, Value val, int N){
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public boolean isEmpty(){ return size() == 0; }

    public int size(){ return size(root); }

    private int size(Node x){
        if(x==null) return 0;
        else return x.N;
    }


    /*
    * Search BST for given key, and return associated value if found
    * return null if not found
    * */
    public boolean contains(Key key){return get(root, key)!=null;}

    // 从root节点开始寻找
    public Value get(Key key){return get(root, key); }

    // 从x这个节点递归往下寻找key
    private Value get(Node x, Key key){
        if(x==null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return get(x.left, key);
        else if(cmp>0) return get(x.right, key);
        else return x.val;
    }

    /*
    * Insert key-value pair into BST
    * If key already exists, update with new value
    * */
    public void put(Key key, Value val){
        if(val == null) {delete(key); return;}
        root = put(root, key, val);
    }

    // 从x节点递归地向下寻找插入一对key-value pair
    private Node put(Node x, Key key, Value val){
        if(x==null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if(cmp<0) x.left = put(x.left, key, val);
        else if(cmp>0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = 1+size(x.left)+size(x.right);
        return x;
    }

    /*
    * delete
    * */
    public void deleteMin(){
        root = deleteMin(root);
    }

    private Node deleteMin(Node x){
        if(x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) +1;
        return x;
    }

    public void delete(Key key){
        root = delete(root, key);
    }

    private Node delete(Node x, Key key){
        if(x==null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp<0) x = delete(x.left, key);
        else if(cmp>0) x = delete(x.right, key);
        // 删除该节点，把它的某个子节点连接到上层节点
        else{
            if(x.right == null) return x.left;
            if(x.left == null) return x.right;
            Node t = x; // t为会被删除的节点，备份
            x = min(t.right); // 先取右子树，然后不断检查左子树，直到遇到空的左链接
            x.right = deleteMin(t.right); // 把新节点的后继节点换为
            x.left = t.left;
        }
        x.N = size(x.left)+size(x.right)+1;
        return x;
    }

    public Key min(){
        if(isEmpty()) return null;
        return min(root).key;
    }

    private Node min(Node x){
        if(x.left == null) return x;
        else return min(x.left);
    }

    public Key max() {
        if (isEmpty()) return null;
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else                 return max(x.right);
    }


    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        // 左子树=>根节点=>右子树
        if (cmplo < 0) keys(x.left, queue, lo, hi); // 先序遍历 先到最左深处
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key); // 在区间内，加入队列
        if (cmphi > 0) keys(x.right, queue, lo, hi);    // 右子树在区间内，加入队列
    }


    /*
    * Test client
    * */
    public static void main(String[] args){
        BST<String, Integer> st = new BST<String, Integer>();
        for(int i = 0; !StdIn.isEmpty(); i++){
            String key = StdIn.readString();
            st.put(key, i);
        }
        for(String s: st.keys())
            StdOut.println(s+" "+st.get(s));
    }


}
