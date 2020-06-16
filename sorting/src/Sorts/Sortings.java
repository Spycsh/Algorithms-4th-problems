package Sorts;

import edu.princeton.cs.algs4.*;

import Sorts.Date;

public class Sortings {

    /* selection sort
    * every time exchange the smallest one with the first one
    * time cost is irrelevant to the input O(N^2)
    * times of exchanges are the fewest */
    public static void selectionSort(Comparable[] a){
        int N = a.length;
        for(int i=0; i<N; i++){
            int min = i;
            for(int j=i+1; j<N; j++){
                if(less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }

    /* insertion sort
    * for every i from 1 to N-1, exchange a[i]
    * with every element from a[0] to a[i-1]
    * best O(n) worst O(n^2)  */
    public static void insertSort(Comparable[] a){
        int N = a.length;
        for(int i=0; i<N; i++){
            for(int j=i; j>0 && less(a[j], a[j-1]); j--){
                exch(a, j, j-1);
            }
        }
    }

    /* Shell Sort
    * make the elements that have an interval of h to be in order
    * */
    public static void shellSort(Comparable[] a){
        int N = a.length;
        int h = 1;
        while(h<N/3) h = 3*h +1;
        while(h>=1){
            for(int i = h; i< N; i++){
                /* insert a[i] into a[j-h], a[j-2*h]...*/
                for(int j=i; j>=h && less(a[j], a[j-h]); j-=h)
                    exch(a, j, j-h);
            }
            h=h/3;
        }
    }

    /*quickSort only performs well on smaller arrays
    * 1. a[j] is in the location correctly
    * 2. elements from a[lo] to a[j-1] <= a[j]
    * 3. elements from a[j+1] to a[j] >= a[j] */
    public static void quickSort(Comparable[] a){
        StdRandom.shuffle(a);
        quickSort(a, 0, a.length-1);
    }

    public static void quickSort(Comparable[] a, int lo, int hi){
        if(hi <= lo) return;
        int j = partition(a, lo, hi);
        quickSort(a, lo, j-1);
        quickSort(a, j+1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi){
        int i = lo, j = hi+1;   // two pointer
        Comparable v = a[lo];   // set v to be the partitioning point
        while(true){
            while(less(a[++i], v)) if(i == hi) break;   // in order
            while(less(v, a[--j])) if(j == lo) break;   // in order
            if(i>=j) break;
            exch(a, i, j);  // [bigger, v, smaller] exchange the pair to be in order
        }
        exch(a, lo, j);
        return j;
    }

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }

    private static void show(Comparable[] a){
        for(int i=0; i<a.length; i++) StdOut.print(a[i]+" ");
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public static void main(String[] args){
//        Date dateA = new Date(19,3,1998);
//        Date dateB = new Date(6,5,1998);
//        System.out.println(dateA.compareTo(dateB));
//        Date[] a = new Date[2];
//        a[0] = dateA;
//        a[1] = dateB;
//        selectionSort(a);
//        show(a);
        String[] a = In.readStrings();
        quickSort(a);
        assert isSorted(a);
        show(a);
    }

}
