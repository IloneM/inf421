//import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.lang.Integer;

public class StableMatching implements StableMatchingInterface {

  public int[] constructStableMatching (
    int n,
    int[][] menPrefs,
    int[][] womenPrefs
	) {
		if(n <= 0)
			return new int[0];

		ArrayBlockingQueue<Integer> freeMens = new ArrayBlockingQueue<Integer>(n);
		int[] nextWomenToAsk = new int[n];
		int[] askedMens = new int[n];

		int[][] invWomenPrefs = new int[n][n];

		for(int i=0; i< n; i++) {
			freeMens.add(i);
			nextWomenToAsk[i] = 0;
			askedMens[i] = n;
			for(int j=0; j< n; j++) {
				invWomenPrefs[i][womenPrefs[i][j]] = j;
			}
		}
			
		while(freeMens.size() > 0) {
			int m = freeMens.peek();
			int wA = menPrefs[m][nextWomenToAsk[m]];

			if(invWomenPrefs[wA][m] < askedMens[wA]) {
				freeMens.poll();
				if(askedMens[wA] < n) {
					freeMens.add(womenPrefs[wA][askedMens[wA]]);
				}
				askedMens[wA] = invWomenPrefs[wA][m];
			}
				
			nextWomenToAsk[m] = nextWomenToAsk[m] + 1;
		}

		int res[] = new int[n];

		for(int i=0; i< n; i++) {
			res[i] = menPrefs[i][nextWomenToAsk[i]-1];
		}

		return res;
	}
}

