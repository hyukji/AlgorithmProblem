from heapq import heappush, heappop

heap = []
n, m = map(int, input().split())

for i in range(n):
    w, c = map(int, input().split())
    heappush(heap, (c, (-1) * w))

answer = float('inf')
c = 0
w = 0
weight = 0
cost = 0
for _ in range(n):
    nc, nw = heappop(heap)
    nw *= (-1) 
    
    weight += nw
    
    if c != nc:
        c = nc
        cost = c
        
        if m <= weight:
            answer = min(answer, cost)
            break
    else:
        cost += nc
        
        if m <= weight:
            answer = min(answer, cost)
    

if answer == float('inf'):
    print(-1)
else:
    print(answer)