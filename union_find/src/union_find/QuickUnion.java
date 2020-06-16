package union_find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnion {
	private int[] id;
	private int count;
	
	public QuickUnion(int N){
		count = N;
		id = new int[N];
		for(int i=0; i<N;i++) {
			id[i]=i;
		}
	}
	
	public void quickUnion(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		if (pRoot == qRoot) return;
		
		id[pRoot] = qRoot;
		
		count--;
	}
	public int count() {
		return count;
	}
	public boolean connected(int p, int q) {
		return find(p)==find(q);
	}
	public int find(int p) {
		while(p!=id[p]) p = id[p];
		return p;
	}
	
	public static void main(String[] args) {
		int N = StdIn.readInt();
		QuickUnion uf = new QuickUnion(N);
		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if(uf.connected(p, q)) continue;
			uf.quickUnion(p, q);
			StdOut.println(p+" "+q);
		}
		// click the console tab, enter CTRL-Z to output this line
		StdOut.println(uf.count() + "components");
	}
}
