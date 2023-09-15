from heapq import heappop, heappush

n = int(input())
arr = []
for _ in range(n):
    arr.append(list(map(int, input().split())))

arr.sort()

answer = 1
notEnding = []
for s, e in arr:
    while notEnding and notEnding[0] <= s:
        heappop(notEnding)
    
    heappush(notEnding, e)
    answer = max(len(notEnding), answer)

print(answer)