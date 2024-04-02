import sys
input = sys.stdin.readline

from collections import deque

n = int(input())
in_arr = [0 for _ in range(n+1)]
graph = [[] for _ in range(n+1)]

graph[1] = [0, 0, 0]
for i in range(2, n+1):
    t, a, p = input().split()
    sheep, wolf, nxt = int(a), 0, int(p)
    if(t == 'W'):
        wolf += sheep
        sheep = 0
        
    graph[i] = [sheep, wolf, nxt]
    in_arr[nxt] += 1
    
dq = deque()
for i in range(2, n+1):
    if(in_arr[i] == 0):
        dq.append(i)
        
while(dq):
    i = dq.popleft()
    sheep, wolf, nxt = graph[i]
    
    result = max(sheep-wolf, 0)
    graph[nxt][0] += result 
    
    in_arr[nxt] -= 1    
    if(in_arr[nxt] == 0 and nxt != 1):
        dq.append(nxt)
    
print(graph[1][0])