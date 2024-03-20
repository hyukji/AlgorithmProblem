import sys
input = sys.stdin.readline

from heapq import heapify, heappop

def find(a):
    if(parents[a] != a):
        parents[a] = find(parents[a])
    return parents[a]

def union(a, b):
    pa = find(a)
    pb = find(b)

    if(pa == pb):
        return True

    if(pa > pb):
        parents[pa] = pb
    else:
        parents[pb] = pa
        
    return False

    

V, E = map(int, input().split())
parents = [i for i in range(V+1)]
visited = [False for _ in range(V+1)]
cnt = 0

edges = []
for _ in range(E):
    a, b, v = map(int, input().split())
    edges.append((v, a, b))

heapify(edges)

answer = 0
while(edges):
    v, a, b = heappop(edges)
    if(union(a, b)):
        continue
    
    answer += v

print(answer)