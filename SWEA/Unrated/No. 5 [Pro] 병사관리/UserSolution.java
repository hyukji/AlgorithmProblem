package B형특강;

class UserSolution {
	
	class Soldier {
		int id;
		int version;
		
		Soldier next;
		
		public Soldier(int id, int version) {
			super();
			this.id = id;
			this.version = version;
		}
		
		@Override
		public String toString() {
			return "[id: " + id + ", version: " + version + "]";
		}
	}

	class SoldierLL {
		Soldier head;
		Soldier tail;
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("LL : ").append(head).append(" ").append(tail).append(" = ");
			
			Soldier s = head;
			while(s != null) {
				sb.append(s.toString()).append(", ");
				s = s.next;
			}
			return sb.toString();
		}
		
		public void add(int mID, int version) {
			Soldier soldier = new Soldier(mID,  version);
			
			if(head == null) {
				head = soldier;
				tail = soldier;
				return;
			}
			
			if(head == tail) {
				head.next = soldier;
			} else {
				tail.next = soldier;
			}
			
			tail = soldier;
		}

		public void addLL(SoldierLL soldierLL) {
			if(soldierLL.head == null) return;
			
			if(tail == null) {
				this.head = soldierLL.head;
				this.tail = soldierLL.tail;
				return;
			}
			
			this.tail.next = soldierLL.head;
			this.tail = soldierLL.tail;
		}

		public int find() {
			int maxId = -1;
			
			Soldier s = head;
			while(s != null) {
				if(s.version == versions[s.id]) {
					maxId = Math.max(s.id, maxId);
				}
				s = s.next;
			}
			return maxId;
		}
		
	}
	
	SoldierLL[][] soldiers;
	int[] versions = new int[100_001];
	int[] teams = new int[100_001];
	
	public void print(int mTeam) {
		System.out.println(mTeam + "팀 전체에 대한 내용 ");
		for(int mScore = 1; mScore <= 5; mScore++) {
			System.out.println(soldiers[mTeam][mScore]);
		}
	}
	
	public void print(int mTeam, int mScore) {
		System.out.println(soldiers[mTeam][mScore]);
	}
	
	public void init() {
		soldiers = new SoldierLL[6][6];
		for(int i = 1; i < 6; i++) {
			for(int j = 1; j < 6; j++) {
				soldiers[i][j] = new SoldierLL();
			}
		}
	}
	
	public void hire(int mID, int mTeam, int mScore) {
		soldiers[mTeam][mScore].add(mID, versions[mID]);
		teams[mID] = mTeam;
	}
	
	public void fire(int mID) {
		versions[mID]++;
	}

	public void updateSoldier(int mID, int mScore) {
		int version = ++versions[mID];
		int mTeam = teams[mID];
		
		soldiers[mTeam][mScore].add(mID, version);
	}

	public void updateTeam(int mTeam, int mChangeScore) {
		if(mChangeScore < 0) {
			for(int i = 2; i< 6; i++) {
				int nScore = i + mChangeScore;
				if(nScore <= 1) {
					soldiers[mTeam][1].addLL(soldiers[mTeam][i]);
					soldiers[mTeam][i] = new SoldierLL();
				} else {
					soldiers[mTeam][nScore].addLL(soldiers[mTeam][i]);
					soldiers[mTeam][i] = new SoldierLL();
				}
			} 
		} else if(mChangeScore > 0) {
			for(int i = 4; i > 0; i--) {
				int nScore = i + mChangeScore;
				if(nScore >= 5) {
					soldiers[mTeam][5].addLL(soldiers[mTeam][i]);
					soldiers[mTeam][i] = new SoldierLL();
				} else {
					soldiers[mTeam][nScore].addLL(soldiers[mTeam][i]);
					soldiers[mTeam][i] = new SoldierLL();
				}
			} 
		}
	}
	
	public int bestSoldier(int mTeam) {
//		print(mTeam);
		for(int i = 5; i > 0; i--) {
			int mId = soldiers[mTeam][i].find();
			if(mId != -1) {
//				System.out.println("mID " + mId);
				return mId;
			}
		}
		return -1;
	}
}