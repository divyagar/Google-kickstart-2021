import java.util.*;

public class Solution {
	private static int[][][] dp;
	private static int[][] m;
	private static int r, c;
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t = 1; t<=T; ++t){
			r = sc.nextInt();
			c = sc.nextInt();
			
			// matrix that contains original array
			m = new int[r][c];
			for(int j = 0; j<r; ++j){
				for(int k = 0; k<c; ++k){
					m[j][k] = sc.nextInt();
				}
			}

			/* 
			 * 3-dimenstional array that will contains number
			 * of consecutive ones at all four directions of
			 * a cell.
			 * 
			 * 0th index - number of ones above the cell
			 * 1st index - number of ones below the cell
			 * 2nd index - number of ones on left side of the cell
			 * 3rd index - number of ones on right side of the cell
			 */
			dp = new int[r][c][4];
			
			// calculating number of ones above a cell
			for(int j = 1; j<r; ++j) {
				for(int k = 0; k<c; ++k) {
					if(m[j][k] == 1 && m[j-1][k] == 1)
						dp[j][k][0] = dp[j-1][k][0] + 1;
				}
			}

			// calculating number of ones below a cell
			for(int j = r-2; j>=0; --j) {
				for(int k = 0; k<c; ++k) {
					if(m[j][k] == 1 && m[j+1][k] == 1)
						dp[j][k][1] = dp[j+1][k][1] + 1;
				}
			}
			

			// calculating number of ones on left side of the cell
			for(int j = 0; j<r; ++j) {
				for(int k = 1; k<c; ++k) {
					if(m[j][k] == 1 && m[j][k-1] == 1)
						dp[j][k][2] = dp[j][k-1][2] + 1;
				}
			}

			// calculating number of ones on right side of the cell
			for(int j = 0; j<r; ++j) {
				for(int k = c-2; k>=0; --k) {
					if(m[j][k] == 1 && m[j][k+1] == 1)
						dp[j][k][3] = dp[j][k+1][3] + 1;
				}
			}
			
			int ans = 0;
			
			for(int j = 0; j<r; ++j){
				for(int k = 0; k<c; ++k){
					if(m[j][k] == 1){
						/*
						 * up - number of ones above the cell
						 * down - number of ones below the cell
						 * left - number of ones on left side of the cell
						 * right - number of ones on right side of the cell
						 * 
						 * Now that we have four segments on all the four
						 * sides of a cell, we want to find number of L shapes
						 * by taking current cell as their meet points.
						 */
						int up = dp[j][k][0]+1, down = dp[j][k][1]+1, left = dp[j][k][2]+1, right = dp[j][k][3]+1;
						
						/*
						 * Now that we have four segments on all the four
						 * sides of a cell, we want to find number of L shapes
						 * by taking current cell as their meet points.
						 */
						
						// Calculating number of L points by taking number of ones above the
						// cell as longer segment
						ans += (cal(up, right, left));

						// Calculating number of L points by taking number of ones on right side
						// of the cell as longer segment
						ans += (cal(right, down, up));

						// Calculating number of L points by taking number of ones below the
						// cell as longer segment
						ans += (cal(down, left, right));

						// Calculating number of L points by taking number of ones on left side
						// of the cell as longer segment
						ans += (cal(left, up, down));
					}
				}
			}
			
			System.out.println("Case #" + t + ": " + ans);
			
		}
			
	}
	
	/*
	 * helper function to calculate number of L shapes
	 * 
	 * l = length of longer segment
	 * s1 = length of shorter segment on right hand side of longer segment
	 * s2 = length of shorter segment on left hand side of longer segment
	 */
	private static int cal(int l, int s1, int s2){
		int ans = 0;
		int min = Math.min(l/2, s1);
		if(min > 1)
			ans += min-1;
		min = Math.min(l/2, s2);
		if(min > 1)
			ans += min-1;		
		
		return ans;
	}
}
