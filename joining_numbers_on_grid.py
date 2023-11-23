from collections import deque

T = int(input())
for tc in range(1, T+1):
    graph = []
    for _ in range(4):
        row = list(input().split())
        graph.append(row)

    directions = [(-1, 0), (0, -1), (0, 1), (1, 0)]

    q = deque()
    for r in range(4):
        for c in range(4):
            q.append((r, c, graph[r][c]))

    routes = set()
    while q:
        r, c, route = q.popleft()

        for d in range(4):
            dr, dc = directions[d]
            nr, nc = r + dr, c + dc
            if nr < 0 or nr >= 4 or nc < 0 or nc >= 4:
                continue

            new_route = route + graph[nr][nc]
            if len(new_route) == 7:
                routes.add(new_route)
                continue

            q.append((nr, nc, new_route))

    print(f"#{tc} {len(routes)}")
