from collections import defaultdict
from heapq import heappush, heappop

def convertToCntList(arr, R):
    dic = defaultdict(int)
    for i in range(R):
        el = arr[i]
        if el != 0:
            dic[el] += 1
            
        arr[i] = 0
    
    heap = []
    for key in dic.keys():
        heappush(heap, (dic[key], key))
    
    cnt = min(50, len(heap))
    for i in range(cnt):
        value, key = heappop(heap)
        arr[i*2] = key
        arr[i*2+1] = value
    
    return cnt*2, arr


y, x, k = map(int, input().split())
y, x = y-1, x-1

graph = [[0 for _ in range(100)] for _ in range(100)]

C, R = 3, 0
for c in range(3):
    row = list(map(int, input().split()))
    rr = len(row)
    
    graph[c][:rr] = row
    R = max(R, rr)

    
answer = 0
while True:
    if graph[y][x] == k:
        break
    
    if answer > 100:
        answer = -1
        break
    
    if R <= C: # R연산
        nR = 0
        for c in range(C):
            rr, row = convertToCntList(graph[c], R)
            for r in range(max(rr, R)):
                graph[c][r] = row[r]
            nR = max(nR, rr)
        R = nR
    else: # C연산
        nC = 0
        for r in range(R):
            col = [row[r] for row in graph]
            cc, col = convertToCntList(col, C)
            for c in range(max(cc, C)):
                graph[c][r] = col[c]
            nC = max(nC, cc)
        C = nC
        
    answer += 1

print(answer)