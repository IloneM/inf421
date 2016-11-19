//import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.lang.Integer;

public class StableMatching implements StableMatchingInterface {

  public int[] constructStableMatching (
    int n,
    int[][] menPrefs,
    int[][] womenPrefs
	) {
		ArrayBlockingQueue<Integer> freeMens = new ArrayBlockingQueue<Integer>(n);
		int[] nextWomenToAsk = new int[n];
		int[] askedMens = new int[n];

		for(int i=0; i< n; i++) {
			freeMens.add(i);
			nextWomenToAsk[i] = 0;
			askedMens[i] = n;
		}
			
		while(freeMens.size() > 0) {
			int m = freeMens.peek();
			int wA = menPrefs[m][nextWomenToAsk[m]];


			boolean wAandMGotEngaged;
			if(askedMens[wA] > n/2) {
				wAandMGotEngaged = true;
				for(int i=askedMens[wA]; i< n; i++) {
					if(womenPrefs[wA][i] == m) {
						wAandMGotEngaged = false;
						break;
					}
				}	
			} else {
				wAandMGotEngaged = false;
				for(int i=0; i< askedMens[wA]; i++) {
					if(womenPrefs[wA][i] == m) {
						wAandMGotEngaged = true;
						break;
					}
				}	
			}

			if(wAandMGotEngaged) {
				freeMens.poll();
				if(askedMens[wA] < n) {
					freeMens.add(womenPrefs[wA][askedMens[wA]]);
				}
				askedMens[wA] = womenPrefs[wA][m];
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

