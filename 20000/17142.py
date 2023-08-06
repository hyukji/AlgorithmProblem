from itertools import combinations
from collections import deque

n, m = map(int, input().split())

graph = []
virus = []
for c in range(n):
    row = list(map(int, input().split()))
    graph.append(row)
    for r, el in enumerate(row):
        if el == 2:
            virus.append((c, r))

# 조합을 이용해서 어떤 바이러스를 선택할지 결정.
combination = list(combinations(range(len(virus)), m)) #m = 2 01 02 03 ....
directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

def bfs(combi):
    dq = deque([virus[c] for c in combi])
    check = [[0 for _ in range(n)] for _ in range(n)]
    for c, r in dq:
        check[c][r] = 1

    cnt = 0
    while dq:
        cnt += 1
        for _ in range(len(dq)):
            c, r = dq.popleft()
            for dc, dr in directions:
                nc, nr = c + dc, r + dr
                if 0 <= nc < n and 0 <= nr < n:
                    if graph[nc][nr] == 1:
                        continue

                    if check[nc][nr] == 0:
                        check[nc][nr] = cnt
                        dq.append((nc, nr))
                
    sub_answer = 0
    for c in range(n):
        for r in range(n):
            if graph[c][r] != 0:
                continue

            if check[c][r] == 0:
                return -1
            else:
                sub_answer = max(sub_answer, check[c][r])
    
    return sub_answer

# bfs
answer = float('inf')
for combi in combination:
    sub_answer = bfs(combi)
    if sub_answer != -1:
        answer = min(answer, sub_answer)

if answer != float('inf'):
    print(answer)
else:
    print(-1)
