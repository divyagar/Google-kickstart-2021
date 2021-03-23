import java.util.*;
public class Solution {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t = 1; t<=t; ++t) {
			/*
			 *  n = length of string
			 *  k = goodness score
			 */
			int n = sc.nextInt(), k = sc.nextInt();
			
			String s = sc.next();
			int i = 0, j = n-1, count = 0;
			while(i <= j) {
				/*
				 *  if ith character is not equal to jth character,
				 *  goodness score of string will be increased 
				 */
				if(s.charAt(i) != s.charAt(j))
					++count;
				++i;
				--j;
			}
			
			System.out.println("Case #" + t + ": " + Math.abs(count - k));
		}
	}
}
