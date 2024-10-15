import sys
from heapq import heappush, heappop

jewels = []
n, k = map(int, input().split())
for _ in range(n):
    m, v = map(int, input().split())
    jewels.append([m, v])

bags = []
for _ in range(k):
    bags.append(int(input()))

jewels.sort()
bags.sort()

heap = []
answer = 0

j = 0
for b in bags:
    while j < n:
        m, v = jewels[j]
        if m > b:
            break

        heappush(heap, -v)
        j+=1
    
    if heap:
        answer -= heappop(heap)

print(answer)