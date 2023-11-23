from collections import deque

n, m, k = map(int, input().split())

graph = [[0 for _ in range(m)] for _ in range(n)]
for _ in range(k):
    c, r = map(int, input().split())
    graph[c-1][r-1] = 1
    
def bfs(c, r):
    cnt = 1
    dq = deque([(c, r)])
    while dq:
        y, x = dq.popleft()
        for dy, dx in directions:
            ny, nx = y + dy, x + dx
            if ny < 0 or ny >= n or nx < 0 or nx >= m:
                continue
            
            if graph[ny][nx] == 0:
                continue
            
            graph[ny][nx] = 0
            dq.append((ny, nx))
            cnt += 1
            
    return cnt

directions = [(0, 1), (1, 0), (-1, 0), (0, -1)]
answer = 0
for c in range(n):
    for r in range(m):
        if graph[c][r] == 1:
            graph[c][r] = 0
            answer = max(answer, bfs(c, r))

print(answer)