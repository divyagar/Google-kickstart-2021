import java.util.*;

public class Solution
{
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		// for each test case
		for(int t = 1; t<=T; ++t) {
			
			// getting number of rows and columns
			int r = sc.nextInt(), c = sc.nextInt();
			int[][] matrix = new int[r][c];
			
			// getting whole matrix
			for(int i = 0; i<r; ++i) {
				for(int j = 0; j<c; ++j) {
					matrix[i][j] = sc.nextInt();
				}
			}
			
			// priority queue to sort heights in descending order.
			// queue contains integer arrays in the form [row, column, height].
			PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b) -> b[2] - a[2]);
			long ans = 0;
			
			// loop to add every cell of matrix in queue
			for(int i = 0; i<r; ++i) {
				for(int j = 0; j<c; ++j) {
					queue.add(new int[]{i, j, matrix[i][j]});
				}
			}
			
			// array to iterate over all the adjacent cells of current cell.
			int[] dir = {0, 1, 0, -1, 0};
			
			// boolean array that keeps track of which cells have been visited.
			boolean[][] visited = new boolean[r][c];
			
			// while queue is not empty we will fetch a cell from queue.
			// We'll then update adjacent cells of current cell to contain boxes such that difference is at most 1.
			while(!queue.isEmpty()) {
				
				// Fetching the cell which has maximum height.
				int arr[] = queue.remove();
				int row = arr[0], col = arr[1];
				
				// Calculate value of adjacent cells only if current cell is not visited.
				if(!visited[row][col]) {
					
					// for loop to iterate over all the adjcent cells in four directions
					for(int j = 0; j<4; ++j) {
						// newr -> row of adjacent cell, newc -> column of adjacent cell
						int newr = row+dir[j], newc = col+dir[j+1];
						
						// checking if the adjacent cell is within dimensions
						if(newr >= 0 && newr < r && newc >=0 && newc < c) {
							/*
							 * if difference in height of boxes of current cell and ajacent cell is
							 * greater than one we'll increase height of adjacent cell and update our
							 * answer.
							 */
							if((matrix[row][col] - matrix[newr][newc]) > 1){
								ans += (matrix[row][col] - matrix[newr][newc] - 1);
								matrix[newr][newc] = matrix[row][col] - 1;
								queue.add(new int[] {newr, newc, matrix[newr][newc]});
							}
						}
					}
				}
				
				// mark current cell as visited.
				visited[row][col] = true;
			}
			
			System.out.println("Case #" + t + ": " + ans);
		
		}
	}
}