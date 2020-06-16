package PriorityQueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;   // 基于堆的完全二叉堆
    private int N = 0;  // 存储于pq[1..N]中,pq[0]没有使用

    public MaxPQ(int maxN){
        pq = (Key[]) new Comparable[maxN+1];
    }

    public void insert(Key v){
        pq[++N] = v;
        swim(N);
    }

    public void swim(int k){
        while(k>1 && less(k/2, k)){
            exch(k/2, k);
            k = k/2;
        }
    }

    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    public Key delMax(){
        Key max = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        return max;
    }

    private void sink(int k){
        while(2*k <= N){
            int j = 2*k;
            if(j<N && less(j, j+1)) j++;
            if(!less(k, j)) break;
            exch(k,j);
            k = j;
        }
    }

    public int size() {
        return N;
    }

    private boolean isEmpty() {
        return N==0;
    }

    /**
     * A test client.
     */
    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<String>(230);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
        while(!pq.isEmpty()){
            StdOut.print(pq.delMax() + " ");
        }
    }

}
