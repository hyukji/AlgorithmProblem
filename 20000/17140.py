from collections import defaultdict

def convertToCntList(arr, R):
    dic = defaultdict(int)
    for i in range(R):
        el = arr[i]
        if el != 0:
            dic[el] += 1
            
        arr[i] = 0
    
    cnt_arr = []
    for key in dic.keys():
        cnt_arr.append((key, dic[key]))
    
    cnt_arr.sort(key=lambda x : (x[1], x[0]))
    
    n = min(50, len(cnt_arr))
    for i in range(n):
        key, value = cnt_arr[i]
        arr[i*2] = key
        arr[i*2+1] = value
    
    return n*2, arr


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

