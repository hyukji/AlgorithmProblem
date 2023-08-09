R, C = map(int, input().split())
num = int(input())
graph = []

for _ in range(num):
    r, c = map(int, input().split())
    graph.append((r, c))
    
startIdx = 0
for i, (r, c) in enumerate(graph):
    if r == 0 or c == 0:
        startIdx = i
        
        nr, nc = graph[(i+1)%num]
        if nr == 0 or nc == 0:
            startIdx += 1
            
        break

cnt = 0
sr, sc, = -1, -1
Max_size = 0
for i in range(startIdx, startIdx + num):
    i %= num 
    r, c = graph[i]
    
    if r == 0 or c == 0:
        if sr == -1:
            sr, sc = r, c
            cnt += 1
        else:
            if sr != 0:
    