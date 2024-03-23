from collections import deque
R, C = map(int, input().split())

lake = []
for _ in range(R):
    lake.append(list(input()))

def findRoot(r, c):
    while graph[r][c] != r * C + c:
        r, c = graph[r][c] // C, graph[r][c] % C
        
    return r, c

graph = [[ r * C + c for c in range(C)] for r in range(R)]
water = []
swan = []
for r in range(R):
    for c in range(C):
        v = lake[r][c]
        
        if v == "X":
            graph[r][c] = float('inf')
            continue
        
        if v == ".":
            water.append((r, c))
        elif v == "L":
            swan.append((r, c))
            water.append((r, c))
            
        # update root
        root = graph[r][c]
        if r > 0 and lake[r-1][c] != "X": 
            rr, rc = findRoot(r-1, c)
            root = min(rr * C + rc, root)
            
            graph[rr][rc], graph[r][c] = root, root
            
        if c > 0 and lake[r][c-1] != "X": 
            r1, c1 = findRoot(r, c)
            r2, c2 = findRoot(r, c-1)
            root = min(r1 * C + c1, r2 * C + c2)
            
            graph[r1][c1], graph[r2][c2] = root, root
            

# bfs
directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
answer = 0
dq = deque(water)
while dq:
    if findRoot(swan[0][0], swan[0][1]) == findRoot(swan[1][0], swan[1][1]):
        break
    
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
        root = r * C + c
        
        for d in range(4):
            dy, dx = directions[d]
            ny, nx = y + dy, x + dx
            if ny < 0 or ny >= R or nx < 0 or nx >= C:
                continue
            
            if lake[ny][nx] == "X":
                continue
            
            nr, nc = findRoot(ny, nx)
            nroot = nr * C + nc
            if root < nroot:
                graph[nr][nc] = root
            elif root > nroot:
                graph[r][c] = nroot
                root = nroot
                
    # print(answer)
    # for row in graph:
    #     print(row)
    # print()
    
print(answer)