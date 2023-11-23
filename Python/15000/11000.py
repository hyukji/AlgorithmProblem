from heapq import heappop, heappush

n = int(input())
arr = []
for _ in range(n):
    arr.append(list(map(int, input().split())))

arr.sort() # start, end 순으로 sort

answer = 1
notEnding = [] # end 지점 모아두기 e
for s, e in arr:
    while notEnding and notEnding[0] <= s:
        heappop(notEnding)
    
    heappush(notEnding, e) # 새로운 수업 업데이트 
    answer = max(len(notEnding), answer) # 동시에 수업 진행 되는 최대.

print(answer)