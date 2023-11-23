from collections import defaultdict, deque

n, s, e = map(int, input().split())

ddict = defaultdict(list)
for _ in range(n-1):
    i, j, d = map(int, input().split())
    ddict[i].append((j, d))
    ddict[j].append((i, d))

# 예외 처리
if n == 1 or s == e:
    print(0)
    exit()

# bfs
dq = deque([(s, 0, 0)]) # loc, total_dist, max_dist
visited = [0 for _ in range(n+1)]
while dq:
    l, dist, M = dq.popleft()

    for nl, nd in ddict[l]:
        if visited[nl] == 1:
            continue

        ndist, nM = dist + nd, max(M, nd)

        if nl == e: # 종료
            print(ndist - nM)
            exit()

        visited[nl] = 1
        dq.append((nl, ndist, nM))