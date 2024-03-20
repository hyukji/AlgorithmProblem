import sys
input = sys.stdin.readline

from collections import deque

def score(l, nl):
    if(l == 0):
        return 2
    
    diff = abs(l-nl)
    if(diff == 0):
        return 1
    elif(diff == 2):
        return 4
    else:
        return 3

MAX_POWER = 400001

arr = list(map(int, input().split()))

dq = deque()
dq.append([0, 0, 0])

for loc in arr:
    if(loc == 0):
        break
    
    visited = [[MAX_POWER for _ in range(5)] for _ in range(5)]
    
    size = len(dq)
    for _ in range(size):
        l, r, v = dq.popleft()
        visited[loc][r] = min(visited[loc][r], v+score(l, loc))
        visited[l][loc] = min(visited[l][loc], v+score(r, loc))

    for i in range(5):
        if(visited[loc][i] != MAX_POWER):
            dq.append([loc, i, visited[loc][i]])
        if(visited[i][loc] != MAX_POWER):
            dq.append([i, loc, visited[i][loc]])

M = MAX_POWER
for _, _, v in dq:
    M = min(M, v)
print(M)