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

dq = deque([(0, 0)])
while dq:
    for _ in range(len(dq)):
        x, y = dq.popleft()
        v = visited[x][y]

        for dx, dy in directions:
            nx, ny = x + dx, y + dy

            if 0 <= nx < n and 0 <= ny < n:
                nv = v
                if graph[nx][ny] == '0': # 검은색
                    nv += 1
                
                if nv < visited[nx][ny]:
                    visited[nx][ny] = nv
                    dq.append((nx, ny))

print(visited[n-1][n-1])