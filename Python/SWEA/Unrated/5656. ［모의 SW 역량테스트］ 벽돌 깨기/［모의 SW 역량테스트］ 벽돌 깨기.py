from itertools import product
from collections import deque
import copy

directions = ((-1, 0), (1, 0), (0, -1), (0, 1))

def rearrange(nGraph, w, h):
    for c in range(w):
        nr = h-1
        for r in range(h-1, -1, -1):
            if nGraph[r][c] != 0:
                nGraph[nr][c] = nGraph[r][c]
                nr -= 1

        for r in range(nr, -1, -1):
            nGraph[r][c] = 0
        

def drop(nGraph, c, w, h):
    cnt = 0
    dq = deque()
    for r in range(0, h):
        if nGraph[r][c] == 1:
            nGraph[r][c] = 0
            return 1
        elif nGraph[r][c] > 0:
            dq.append((r, c, nGraph[r][c]-1))
            nGraph[r][c] = 0
            cnt += 1
            break

    while(dq):
        size = len(dq)
        for _ in range(size):
            r, c, v = dq.popleft()
            for nv in range(1, v+1):
                for dr, dc in directions:
                    nr = r + dr * nv
                    nc = c + dc * nv
                    if nr < 0 or nr >= h or nc < 0 or nc >= w:
                        continue

                    if nGraph[nr][nc] == 1:
                        nGraph[nr][nc] = 0
                        cnt += 1
                    elif nGraph[nr][nc] > 0:
                        dq.append((nr, nc, nGraph[nr][nc]-1))
                        nGraph[nr][nc] = 0
                        cnt += 1
    
    return cnt

T = int(input())
for tc in range(1, T+1):
    n, w, h = map(int, input().split())
    graph = [list(map(int, input().split())) for _ in range(h)]
    tot = 0
    for r in range(h):
        for c in range(w):
            if graph[r][c] != 0:
                tot += 1

    answer = 0
    nGraph = copy.deepcopy(graph)
    for ps in product(range(w), repeat=n):
        nGraph = copy.deepcopy(graph)
        result = 0
        for p in ps:
            result += drop(nGraph, p, w, h)
            rearrange(nGraph, w, h)

        answer = max(answer, result)
        if answer == tot:
            break

    print(f"#{tc} {tot -answer}")