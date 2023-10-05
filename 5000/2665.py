from collections import deque

n = int(input())
graph = []
for _ in range(n):
    row = list(input())
    graph.append(row)

visited = [[float('inf') for _ in range(n)] for _ in range(n)]
directions = [(1, 0), (0, 1), (-1, 0), (0, -1)]

v = 0
if graph[0][0] == '0':
    v += 1
visited[0][0] = v

dq = deque([(0, 0, v)])
while dq:
    for _ in range(len(dq)):
        x, y, v = dq.popleft()
        for dx, dy in directions:
            nx, ny, nv = x + dx, y + dy, v

            if 0 <= nx < n and 0 <= ny < n:
                if graph[nx][ny] == '0':
                    nv += 1
                
                if nv < visited[nx][ny]:
                    dq.append((nx, ny, nv))
                    visited[nx][ny] = nv

print(visited[n-1][n-1])