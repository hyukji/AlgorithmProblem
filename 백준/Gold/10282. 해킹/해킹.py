from heapq import heappush, heappop
from collections import defaultdict


import sys
input = sys.stdin.readline


tc = int(input())

for _ in range(tc):
    n, d, c = map(int, input().split())
    ddict = defaultdict(list)
    for _ in range(d):
        a, b, s = map(int, input().split())
        ddict[b].append([a, s])

    # print(ddict)

    visited = [-1 for _ in range(n+1)]
    visited[c] = 0
    graph = [(0, c)]

    while(graph):
        # print(graph)
        s, v = heappop(graph)
        if(visited[v] < s):
            continue
        
        for nv, ds in ddict[v]:
            ns = s + ds
            if(visited[nv] == -1 or visited[nv] > ns):
                visited[nv] = ns
                heappush(graph, [ns, nv])

    maxTime = 0
    visitedCnt = 0
    for time in visited:
        if(time == -1):
            continue

        maxTime = max(time, maxTime)
        visitedCnt += 1

    print(visitedCnt,  maxTime)