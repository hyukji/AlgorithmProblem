from collections import deque, defaultdict
from itertools import combinations

n, m = map(int, input().split())
ddict = defaultdict(list)
for _ in range(m):
    i, j = map(int, input().split())
    ddict[i-1].append(j-1)
    ddict[j-1].append(i-1)
    
answer_combi = 0, 0
answer_dist = float('inf') 

combies = combinations(range(n), 2)
for combi in list(combies):
    dq = deque(list(combi)) # deque([2 6 4 5])
    visited = [0 for _ in range(n)]
    for b in dq:
        visited[b] = 1
    
    sub_answer = 0
    dist = 1
    while dq:
        for _ in range(len(dq)):
            b = dq.popleft()
            
            for nb in ddict[b]:
                if visited[nb] == 0:
                    visited[nb] = 1
                    
                    dq.append(nb)
                    sub_answer += dist
                    
        dist += 1
     
    if sub_answer < answer_dist:
        answer_combi = combi
        answer_dist = sub_answer
        
print(answer_combi[0]+1, answer_combi[1]+1, answer_dist*2)