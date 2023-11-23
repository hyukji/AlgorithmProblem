from collections import deque

n, m = map(int, input().split())
graph = []
for _ in range(n):
    graph.append(input())

visited = [[float('inf') for _ in range(m)] for _ in range(n)]
directions = [(1, 0), (0, 1), (-1, 0), (0, -1)]

def dfs():
    x, y = 0, 0
    visited[0][0] = 1
    dq = deque([(y, x, 2)])
    while dq:
        y, x, cnt = dq.pop()
        for dy, dx in directions:
            ny , nx = y + dy, x + dx
            if ny < 0 or ny >= n or nx < 0 or nx >= m:
                continue

            if graph[ny][nx] == "0":
                continue

            if visited[ny][nx] <= cnt:
                continue
            else:
                visited[ny][nx] = cnt
                dq.append((ny, nx, cnt+1))
    
    print(visited[n-1][m-1])


# bfs
def bfs():
    x, y ,cnt = 0, 0, 1
    visited[0][0] = 1
    dq = deque([(y, x)])
    while dq:
        cnt += 1
        for _ in range(len(dq)):
            y, x = dq.popleft()
            for dy, dx in directions:
                ny, nx = y + dy, x + dx
                if ny < 0 or ny >= n or nx < 0 or nx >= m:
                    continue

                if graph[ny][nx] == '0':
                    continue
                
                if ny == n-1 and nx == m - 1:
                    print(cnt)
                    return

                if visited[ny][nx] == -1:
                    visited[ny][nx] = cnt
                    dq.append((ny, nx))
                else:
                    continue
            
dfs()