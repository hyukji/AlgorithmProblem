from collections import deque

# https://swexpertacademy.com/main/solvingProblem/solvingProblem.do

T = int(input())
for tc in range(1, T+1):
    n = int(input())

    graph = []
    for _ in range(n):
        row = list(map(int, input()))
        graph.append(row)

    visit = [[float("inf") for _ in range(n)] for _ in range(n)]
    directions = [(-1, 0), (0, -1), (0, 1), (1, 0)]

    q = deque()
    q.append((0, 0, 0))

    while q:
        r, c, v = q.popleft()
        for d in range(4):
            dr, dc = directions[d]
            nr, nc = r + dr, c + dc
            if nr < 0 or nr >= n or nc < 0 or nc >= n:
                continue

            nv = graph[nr][nc] + v
            if nv < visit[nr][nc]:
                visit[nr][nc] = nv
                q.append((nr, nc, nv))

    print(f"#{tc} {visit[-1][-1]}")
