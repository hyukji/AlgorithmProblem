from collections import deque
R, C = map(int, input().split())

lake = []
for _ in range(R):
    lake.append(list(input()))

# root -> y * C + x
def findRoot(r, c):
    while graph[r][c] != r * C + c:
        r, c = graph[r][c] // C, graph[r][c] % C
        
    return r, c

def updateRoot(r, c, nr, nc):
    root = graph[r][c]
    nroot = graph[nr][nc]
    
    if root < nroot:
        graph[nr][nc] = root
    elif root > nroot:
        graph[r][c] = nroot
        r, c = nr, nc
    
    return r, c

graph = [[ r * C + c for c in range(C)] for r in range(R)] # graph for root
water = []
swan = []
for r in range(R):
    for c in range(C):
        v = lake[r][c]
        
        if v == "X":
            graph[r][c] = float('inf')
            continue
        
        water.append((r, c))
        if v == "L":
            swan.append((r, c))
            
        # update root
        if r > 0 and lake[r-1][c] != "X": 
            nr, nc = findRoot(r-1, c)
            r, c = updateRoot(r, c, nr, nc)
            
        if c > 0 and lake[r][c-1] != "X": 
            nr, nc = findRoot(r, c-1)
            r, c = updateRoot(r, c, nr, nc)
            

directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
answer = 0
dq = deque(water)
while dq:
    if findRoot(swan[0][0], swan[0][1]) == findRoot(swan[1][0], swan[1][1]):
        break
    
    # bfs로 얼음탐지
    answer += 1
    for _ in range(len(dq)):
        y, x = dq.popleft()
        
        r, c = findRoot(y, x)
        root = r * C + c
        
        for d in range(4):
            dy, dx = directions[d]
            ny, nx = y + dy, x + dx
            if ny < 0 or ny >= R or nx < 0 or nx >= C:
                continue
            
            if lake[ny][nx] == "X":
                lake[ny][nx] = "."
                dq.append((ny, nx))
                
                graph[ny][nx] = root
                continue
            
    # root 업데이트
    for y, x in dq:
        r, c = findRoot(y, x)
        
        for d in range(4):
            dy, dx = directions[d]
            ny, nx = y + dy, x + dx
            if ny < 0 or ny >= R or nx < 0 or nx >= C:
                continue
            
            if lake[ny][nx] == "X":
                continue
            
            nr, nc = findRoot(ny, nx)
            r, c = updateRoot(r, c, nr, nc)
                
    # print(answer)
    # for row in graph:
    #     print(row)
    # print()
    
print(answer)