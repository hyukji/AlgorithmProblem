from heapq import heapify, heappush, heappop, heappushpop

n = int(input())
pq = []
for _ in range(n):
    pq.append(int(input()))

answer = 0

heapify(pq)
m = heappop(pq)
while n > 1:
    v = m + heappop(pq)
    m = heappushpop(pq, v)

    answer += v
    n-=1

print(answer)