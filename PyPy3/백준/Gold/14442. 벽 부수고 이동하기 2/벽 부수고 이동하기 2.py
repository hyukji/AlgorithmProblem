import sys
from collections import deque

input = sys.stdin.readline

n, m, k = map(int, input().split())
graph = []
for _ in range(n):
    graph.append(input()[:-1]) 
    
checked = [[k+1 for _ in range(m+1)] for _ in range(n+1)] 

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

def bfs(k):
    
    dq = deque()
    dq.append((0, 0))
    nk = 0
    cnt = 1
    checked[0][0] = nk
    
    while dq:
        cnt += 1
        for _ in range(len(dq)):
            y, x = dq.popleft()
            for d in range(4):
                ny = y + dy[d]
                nx = x + dx[d]
                if (ny, nx) == (n-1, m-1):
                    return cnt
                if 0 <= ny < n and 0 <= nx < m:
                    nk = checked[y][x] + int(graph[ny][nx])
                    if nk <= k and nk < checked[ny][nx]:
                        checked[ny][nx] = nk
                        dq.append((ny, nx))

    return -1

if (n, m) == (1, 1):
    print(1)
else:
    print(bfs(k))