import java.util.*;

class UserSolution {
	
	class Hanoi {
		int from, to, n;
		Hanoi next;
		Hanoi prev;

		public Hanoi(int from, int to, int n) {
			super();
			this.from = from;
			this.to = to;
			this.n = n;
			this.next = null;
		}
		
		@Override
		public String toString() {
			return "Hanoi [from=" + from + ", to=" + to + ", n=" + n + "]";
		}
		
	}
	
	Hanoi tail;
	int size;
	int[] nums;
	
	int[] froms;
	int[] tos;
	
	LinkedList<Integer>[] disks;
	
  void init(int N[], int mDisk[][]) {
    disks = new LinkedList[3];
    size = 0;
    for(int v : N) size += v;
    
    for(int i = 0; i < 3; i++) disks[i] = new LinkedList<>();  
    nums = new int[size];
    froms = new int[size];
    tos = new int[size];
    int a = 0, b = 0, c = 0;
    for(int i = 0; i < size; i++) {
      int min = Integer.MAX_VALUE;
      int from = -1;
      if(a < N[0] && min > mDisk[0][a]) {
        min = mDisk[0][a];
        from = 0;
      }
      if(b < N[1] && min > mDisk[1][b]) {
        min = mDisk[1][b];
        from = 1;
      }
      if(c < N[2] && min > mDisk[2][c]) {
        min = mDisk[2][c];
        from = 2;
      }
      
      if(from == 0) a++;
      else if(from == 1) b++;
      else if(from == 2) c++;
      else break;
      
      froms[i] = from;
      nums[i] = min;
    }
    
    tos[size-1] = 2;
    for(int i = size-1; i > 0; i--) {
      int from = froms[i];
      int to = tos[i];
      if(from == to) tos[i-1] = to;
      else tos[i-1] = 3-from-to;
    }
    
    tail = new Hanoi(0, 0, 0);
    for(int i = size-1; i > 0; i--) {
      int from = froms[i];
      int to = tos[i];
      addHanoi(new Hanoi(tos[i-1], to, i));
      addHanoi(new Hanoi(from, to, 1));
      disks[from].add(nums[i]);
    }
    addHanoi(new Hanoi(froms[0], tos[0], 1));
    disks[froms[0]].add(nums[0]);
  }

  private void addHanoi(Hanoi hanoi) {
    tail.next = hanoi;
    hanoi.prev = tail;
    tail = hanoi;
	}

	void destroy() { }

  void go(int k, int mTop[]) {
    	int cnt = 0;
    	while(cnt < k) {
    		Hanoi cur = tail;
    		if(cur == null) break;
    		tail = cur.prev;

  			int from = cur.from;
  			int to = cur.to;
  			int by = 3 - from - to;
  			int n = cur.n;
  			if(from == to) continue;
    		if(n > 1) {
    			addHanoi(new Hanoi(by, to, n-1));
    			addHanoi(new Hanoi(from, to, 1));
    			addHanoi(new Hanoi(from, by, n-1));
    		} else if(n == 1) {
    			cnt++;
    			int num = disks[from].removeLast();
    			disks[to].add(num);
    		}
    	}
      
      mTop[0] = (disks[0].isEmpty()) ? 0 : disks[0].peekLast();
      mTop[1] = (disks[1].isEmpty()) ? 0 : disks[1].peekLast();
      mTop[2] = (disks[2].isEmpty()) ? 0 : disks[2].peekLast();
}
}
