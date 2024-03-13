import java.util.*;

class UserSolution {
	
	static class Result implements Comparable<Result> {
		int idx;
		int[] nums;
		public Result(int idx, ArrayDeque<Integer> dq) {
			super();
			this.idx = idx;
			this.nums = dqToArray(dq);
		}
		
		private int[] dqToArray(ArrayDeque<Integer> dq) {
			int[] nums = new int[4];
			int i = 0;
			for(int num : dq) nums[i++] = num;
			return nums;
		}

		@Override
		public int compareTo(Result o) {
			return Integer.compare(idx, o.idx);
		}
		
	}
	
	static ArrayDeque<Result>[][] fResults;
	static ArrayDeque<Result>[][] bResults;
	static ArrayDeque<Integer> front;
	static ArrayDeque<Integer> back;
	int f, fSum, fJocker, b, bSum, bJocker, jocker;

    void init(int mJoker, int mNumbers[]) {
    	bResults = new ArrayDeque[5][20];
    	fResults = new ArrayDeque[5][20];
    	for(int i = 0; i < 5; i++) {
    		for(int j = 0; j < 20; j++) {
    			bResults[i][j] = new ArrayDeque<>();
    			fResults[i][j] = new ArrayDeque<>();
    		}
    	}
    	front = new ArrayDeque<>();
    	back = new ArrayDeque<>();
    	jocker = mJoker;
    	
    	for(int i = 0; i < 3; i++) {
    		front.offer(mNumbers[i]);
    		back.offer(mNumbers[i]);
    	}
    	
    	int sum = 0;
    	int jockerCnt = 0;
    	for(int num : back) {
    		if(num == -1) {
    			jockerCnt++; 
    			continue;
    		}
    		sum += num;
    	}

    	f = 0; 
    	b = 1; 
    	fSum = sum;
    	bSum = sum;
    	fJocker = jockerCnt;
    	bJocker = jockerCnt;
    	
    	for(int i = 3; i < 5; i++) {
        	int cur = mNumbers[i];
        	
        	back.offer(cur);
        	if(cur == -1) bJocker++;
        	else bSum += cur;

        	bSum = (bSum + 20) % 20;
        	bResults[bJocker][bSum].add(new Result(b++, back));
        	
        	int bef = back.pollFirst();
        	if(bef == -1) bJocker--;
        	else bSum -= bef;
    	}
    	
    }

    void putCards(int mDir, int mNumbers[]) {
    	if(mDir == 1) { // 뒤 
        	for(int i = 0; i < 5; i++) {
            	int cur = mNumbers[i];
            	
            	back.offer(cur);
            	if(cur == -1) bJocker++;
            	else bSum += cur;

            	bSum = bSum % 20;
            	if(bSum < 0) bSum += 20;
            	
            	
//            	for(int num : back) System.out.print(num + " ");
//            	System.out.println();
//            	System.out.println(bJocker + " " + bSum);
//            	System.out.println();
            	
            	bResults[bJocker][bSum].add(new Result(b++, back));
            	
            	int bef = back.pollFirst();
            	if(bef == -1) bJocker--;
            	else bSum -= bef;
        	}
    	} else { // 앞 
        	for(int i = 4; i >= 0; i--) {
            	int cur = mNumbers[i];
            	
            	front.offerFirst(cur);
            	if(cur == -1) fJocker++;
            	else fSum += cur;

            	fSum = fSum % 20;
            	if(fSum < 0) fSum += 20;
            	

//            	for(int num : front) System.out.print(num + " ");
//            	System.out.println();
//            	System.out.println(fJocker + " " + fSum);
//            	System.out.println();
//            	
            	fResults[fJocker][fSum].add(new Result(f--, front));
            	
            	int bef = front.pollLast();
            	if(bef == -1) fJocker--;
            	else fSum -= bef;
        	}
    	}
    }

    int findNumber(int mNum, int mNth, int ret[]) {
    	List<Result> fTargets = new LinkedList<>();
    	List<Result> bTargets = new LinkedList<>();
    	for(int j = 0; j < 5; j++) {
    		int num = (mNum - jocker * j);
    		while(num < 0) num += 20;
    		fTargets.addAll(fResults[j][num]);
    		bTargets.addAll(bResults[j][num]);
    	}

    	if(fTargets.size() + bTargets.size() < mNth) return 0;
    	
    	List<Result> targets = null;
    	if(mNth <= fTargets.size()) {
        	Collections.sort(fTargets);
        	targets = fTargets;
    	} else {
        	Collections.sort(bTargets);
        	targets = bTargets;
        	
        	mNth -= fTargets.size();
    	}

    	int[] target = targets.get(mNth-1).nums;
    	for(int i = 0; i < 4; i++) ret[i] = target[i];
    	
        return 1;
    }

    void changeJoker(int mValue) {
    	jocker = mValue;
    }
}
