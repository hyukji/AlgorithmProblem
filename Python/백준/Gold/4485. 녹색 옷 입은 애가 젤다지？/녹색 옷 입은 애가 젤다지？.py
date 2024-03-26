import sys
input = sys.stdin.readline

from collections import deque

answer = []
p = 1
while(True):
    n = int(input())
    if(n == 0):
        break
    
    graph = []
    for _ in range(n):
        graph.append(list(map(int, input().split())))

    visited = [[float('inf') for _ in range(n)] for _ in range(n)]
    directions = [(-1, 0), (1, 0), (0,-1), (0, 1)]

    dq = deque()
    dq.append((0, 0, graph[0][0]))
    visited[0][0] = graph[0][0]
    while(dq):
        r, c, v = dq.popleft()
        if(v > visited[r][c]):
            continue

        for dr, dc in directions:
            nr = r + dr
            nc = c + dc
            if(nr < 0 or nr >= n or nc < 0 or nc >= n):
                continue
            
            nv = v + graph[nr][nc]
            if(nv < visited[nr][nc]):
                visited[nr][nc] = nv
                dq.append((nr, nc, nv))
    
    print("Problem " + str(p) + ": ", end="")
    print(visited[n-1][n-1]) 
    p+=1