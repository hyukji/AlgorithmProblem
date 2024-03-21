import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

from heapq import heapify, heappop

def find(a):
    if(parents[a] == a):
        return a
    
    parents[a] = find(parents[a])
    return parents[a]

def union(a, b):
    pa = find(a)
    pb = find(b)
    if(pa == pb):
        return True

    if(pa < pb):
        parents[pb] = pa
    else:
        parents[pa] = pb

    return False


n, m = map(int, input().split())
edges = []
for _ in range(m):
    s, e, v = map(int, input().split())
    edges.append((v, s, e))

heapify(edges)

parents = [i for i in range(n+1)]

answer = 0
cnt = 0
while(cnt < n-2):
    v, s, e = heappop(edges)
    if(union(s, e)):
        continue

    answer += v
    cnt+=1

print(answer)