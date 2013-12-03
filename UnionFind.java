/****************************************************************************
 *  Compilation:  javac WeightedQuickUnionPathCompressionUF.java
 *  Execution:  java WeightedQuickUnionPathCompressionUF < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *
 *  Weighted quick-union with path compression.
 *
 ****************************************************************************/
public class UnionFind 
{
    private int[] id;    // id[i] = parent of i
    private int[] sz;    // sz[i] = number of objects in subtree rooted at i
    private int count;   // number of components

    /**
     * Initializes an empty union-find data structure with N isolated components 0 through N-1.
     * @throws java.lang.IllegalArgumentException if N < 0
     * @param N the number of objects
     */
    public UnionFind(int N) 
	{
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    /**
     * Returns the number of components.
     * @return the number of components (between 1 and N)
     */
    public int count(){
        return count;
	}
  
    /**
     * Are the two sites p and q in the same component?
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return true if the two sites p and q
     *    are in the same component, and false otherwise
     * @throws java.lang.IndexOutOfBoundsException unless both 0 <= p < N and 0 <= q < N
     */
    public boolean connected(int p, int q){
		return find(p) == find(q);
	}


    /**
     * Returns the component identifier for the component containing site p.
     * @param p the integer representing one site
     * @return the component identifier for the component containing site p>
     * @throws java.lang.IndexOutOfBoundsException unless 0 <= p < N
     */
    public int find(int p){
        int root = p;
        while (root != id[root])
            root = id[root];
        while (p != root) {
            int newp = id[p];
            id[p] = root;
            p = newp;
        }
        return root;
    }

  
    /**
     * Merges the component containing site p with the component
     * containing site q.
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws java.lang.IndexOutOfBoundsException unless both 0 <= p < N and 0 <= q < N
     */
    public void union(int p, int q) 
	{
        int i = find(p);
        int j = find(q);
        if (i == j) return;

        // make smaller root point to larger one
        if   (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
        else                 { id[j] = i; sz[i] += sz[j]; }
        count--;
    }

    // public static void main(String[] args) 
	// {
        // int N = StdIn.readInt();
        // UnionFind uf = new UnionFind(N);
        // while (!StdIn.isEmpty()) 
		// {
            // int p = StdIn.readInt();
            // int q = StdIn.readInt();
            // if (uf.connected(p, q)) continue;
				// uf.union(p, q);
            // StdOut.println(p + " " + q);
        // }
        // StdOut.println(uf.count() + " components");
    // }

}