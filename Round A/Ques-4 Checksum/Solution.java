import java.util.*;

public class Solution{
	static int[] uf;
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t = 1; t<=T; ++t) {
			int n = sc.nextInt();
			
			// defected matrix
			int[][] a = new int[n][n];
			
			// hours to recover original value of that cell
			int[][] b = new int[n][n];
			for(int i = 0; i<n; ++i) {
				for(int j= 0; j<n; ++j) {
					a[i][j] = sc.nextInt();
				}
			}
			
			for(int i = 0; i<n; ++i) {
				for(int j= 0; j<n; ++j) {
					b[i][j] = sc.nextInt();
				}
			}
			
			// r = checksums along each row, c = checksums along each column
			int[] r = new int[n], c = new int[n];
			for(int i = 0; i<n; ++i)
				r[i] = sc.nextInt();

			for(int i = 0; i<n; ++i)
				c[i] = sc.nextInt();
			
			// queue that contains integer arrays in the form [hours, row, col+n]
			// It sorts on the basis of heights in descending order.
			PriorityQueue<int[]> queue = new PriorityQueue<int[]>((d, e) -> e[0] - d[0]);
			
			for(int i = 0; i<n; ++i) {
				for(int j =  0; j<n; ++j) {
					if(a[i][j] == -1) {
						queue.add(new int[] {b[i][j], i, j+n});
					}
				}
			}
			
			// array for union-find
			uf = new int[2*n];
			for(int i = 0; i<2*n; ++i)
				uf[i] = i;
			
			long ans = 0;
			
			/*
			 * We need to find those edges whose removal will result in breaking of cycle
			 * in disjoint set.
			 * If two elements are in single segment that means addition of this edge will
			 * result in a cycle so we will not add current edge in the graph and we will
			 * add number of hours required to find current cell to answer.
			 */
			while(!queue.isEmpty()) {
				int[] arr = queue.remove();
				int row = arr[1], col = arr[2];
				int para = find(row), parb = find(col);
				if(para != parb)
					union(para, parb);
				else {
					ans += arr[0];
				}
			}
			
			System.out.println("Case #" + t + ": " + ans);
		}
		
	}
	
	// helper function to find parent of an element in union-find array
	private static int find(int a) {
		if(a == uf[a])
			return a;
		
		return uf[a] = find(uf[a]);
	}
	
	// helper function to union two elements in union-find array
	private static void union(int a, int b) {
		uf[a] = uf[b];
	}
}