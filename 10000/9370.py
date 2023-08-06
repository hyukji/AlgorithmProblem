from collections import defaultdict
from heapq import heappop, heappush

def dijkstra(s, ddict):
    dist = [float('inf') for _ in range(n+1)]
    dist[s] = 0

    pq = []
    heappush(pq, (0, s))
    while pq:
        w, node = heappop(pq)

        if w > dist[node]:
            continue

        for next_node, nw in ddict[node]:
            weight = nw + w
            if weight < dist[next_node]:
                dist[next_node] = weight
                heappush(pq, (weight, next_node))

    return dist


T = int(input())
for _ in range(T):
    n, m, t = map(int, input().split())
    s, g, h = map(int, input().split())

    ddict = defaultdict(list)
    for _ in range(m):
        a, b, d = map(int, input().split())
        ddict[a].append((b, d))
        ddict[b].append((a, d))

    targets = []
    for _ in range(t):
        targets.append(int(input()))

    dist_s = dijkstra(s, ddict)
    dist_g = dijkstra(g, ddict)
    dist_h = dijkstra(h, ddict)

    answer = []
    
    for target in targets:
        if dist_s[target] == float('inf'):
            continue
        
        if dist_s[target] == min(dist_s[g] + dist_g[h] + dist_h[target], dist_s[h] + dist_h[g] + dist_g[target]):
            answer.append(target)

    answer.sort()

    for i in answer:
        print(i, end=" ")