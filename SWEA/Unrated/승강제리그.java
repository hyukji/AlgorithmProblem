package 승강제리그;

import java.util.*;

class UserSolution {
	
	// 리그마다 4개의 pq를 만들어서 문제 해결
	// best, worst, mid를 위한 2개의 pq
	
	static class Member implements Comparable<Member>  {
		int idx, ability;
		Integer v ;

		public Member(int idx, int ability) {
			super();
			this.idx = idx;
			this.ability = ability;
			this.v = ++versions[idx];
		}
		public Member(Member member) {
			this(member.idx, member.ability);
		}

		@Override
		public int compareTo(Member o) {
			int r = -Integer.compare(ability, o.ability);
			if(r == 0) r = Integer.compare(idx, o.idx);
			return r;
		}

		@Override
		public String toString() {
			return "Member [idx=" + idx + ", v=" + v + "]";
		}
		
	}
	
	static class League {
		PriorityQueue<Member> best, rBest, worst, rWorst;

		public League(Member[] members) {
			super();
			best = new PriorityQueue<>();
			rBest = new PriorityQueue<>(Collections.reverseOrder());
			worst = new PriorityQueue<>(Collections.reverseOrder());
			rWorst = new PriorityQueue<>();
			
			for(int i = 0; i < members.length/2 + 1; i++) {
				best.add(members[i]);
				worst.add(members[i]);
				rBest.add(members[i]);
			}
			for(int i = members.length/2 + 1; i < members.length; i++) {
				best.add(members[i]);
				worst.add(members[i]);
				rWorst.add(members[i]);
			}
		}
		
		public Member pollBest() {
			Member member = best.poll();
			while(member.v != versions[member.idx]) member = best.poll();
			return member;
		}

		public Member pollMid() {
			Member member = rBest.poll();
			while(member.v != versions[member.idx]) member = rBest.poll();
			return member;
		}

		public Member pollWorst() {
			Member member = worst.poll();
			while(member.v != versions[member.idx]) member = worst.poll();
			return member;
		}
		
		

		public void tradeArrange() {
			Member rw = rWorst.peek();
			Member rb = rBest.peek();

			while(rw != null && rw.v != versions[rw.idx]) {
				rWorst.poll(); 
				rw = rWorst.peek();
			}
			while(rb != null && rb.v != versions[rb.idx]) {
				rBest.poll();
				rb = rBest.peek();
			}
			
			if(rw == null) {
				rWorst.add(rBest.poll());
				rw = rb;
				rb = rBest.peek();
			}
			
			if(rw.compareTo(rb) < 0) {
				rWorst.poll(); 
				rBest.poll();
				
				rWorst.add(rb);
				rBest.add(rw);
			}
		}
		
	}
	
	
	League[] league;
	int L;
	static int[] versions = new int[40000];
	
    void init(int N, int L, int mAbility[]) {
		league = new League[L];
    	this.L = L;
		
		int m = N / L;
		int idx = 0;
		for(int i = 0; i < L; i++) {
			Member[] members = new Member[m];
			for(int j = 0; j < m; j++) {
				members[j] = new Member(idx, mAbility[idx++]);
			}
			Arrays.sort(members);
			league[i] = new League(members);
		}
    }

    int move() {
    int answer = 0;
		Member[] worsts = new Member[L-1];
		Member[] bests = new Member[L-1];
    	for(int i = 0; i < L-1; i++) {
    		worsts[i] = league[i].pollWorst();
    		bests[i] = league[i+1].pollBest();
    	}
    	for(int i = 0; i < L-1; i++) {
    		Member worst = worsts[i];
    		Member best = bests[i];
    		answer += worst.idx;
    		answer += best.idx;
    		
    		Member nWorst = new Member(worst);
    		Member nBest = new Member(best);
    		
    		league[i].best.add(nBest);
    		league[i].worst.add(nBest);
    		league[i].rBest.add(nBest);
    		league[i+1].best.add(nWorst);
    		league[i+1].worst.add(nWorst);
    		league[i+1].rBest.add(nWorst);

    	}

    	for(int i = 0; i < L-1; i++) {
    		league[i].rWorst.add(league[i].pollMid());
    	}
      return answer;
    }

    int trade() {
    	int answer = 0;

		Member[] mids = new Member[L-1];
		Member[] bests = new Member[L-1];
    	for(int i = 0; i < L-1; i++) {
    		mids[i] = league[i].pollMid();
    		bests[i] = league[i+1].pollBest();
    	}
    		
    	for(int i = 0; i < L-1; i++) {
    		Member mid = mids[i];
    		Member best = bests[i];
    		answer += mid.idx;
    		answer += best.idx;
    		
    		Member nMid = new Member(mid);
    		Member nBest = new Member(best);
    		
    		league[i].best.add(nBest);
    		league[i].worst.add(nBest);
    		league[i].rBest.add(nBest);
    		league[i+1].best.add(nMid);
    		league[i+1].worst.add(nMid);
    		league[i+1].rBest.add(nMid);

    		league[i].tradeArrange();
    		league[i+1].tradeArrange();
    	}
        return answer;
    }

}
