C, R, m = map(int, input().split())

graph = [[[0, 0, 0] for _ in range(R)] for _ in range(C)]
for _ in range(m):
    c, r, s, d, z = map(int, input().split())
    graph[c-1][r-1] = [z, s, d]
    
def moveShark():
    global graph
    next_graph = [[[0, 0, 0] for _ in range(R)] for _ in range(C)]

    ccan, rcan = C - 1, R - 1
    for y in range(C):
        for x in range(R):
            z, s, d = graph[y][x]
            if z == 0:
                continue
            
            ny, nx = y, x
            
            if d == 1: # 위
                if s <= y:
                    ny = y - s
                else:
                    ns = s - y
                    if (ns // ccan) % 2 == 0:
                        ny = ns % ccan
                        d = 2
                    else:
                        ny = C - 1 - ns % ccan 
            elif d == 2:
                if s < C-y:
                    ny = y + s
                else:
                    ns = s - ccan + y
                    if (ns // ccan) % 2 == 0:
                        ny = C - 1 - ns % ccan
                        d = 1
                    else:
                        ny = ns % ccan
            elif d == 3:
                if s < R-x:
                    nx = x + s
                else:
                    ns = s - rcan + x
                    if (ns // rcan) % 2 == 0:
                        nx = R - 1 - (ns % rcan)
                        d = 4
                    else:
                        nx = ns % rcan            
            elif d == 4: 
                if s <= x:
                    nx = x - s
                else:
                    ns = s - x
                    if (ns // rcan) % 2 == 0:
                        nx = ns % rcan
                        d = 3
                    else:
                        nx = R - 1 - ns % rcan 
            
            if next_graph[ny][nx][0] < z:
                next_graph[ny][nx] = [z, s, d]
            
    graph = next_graph
                

def getShark(r):
    for c in range(C):
        z, _, _ = graph[c][r]
        if z != 0:
            graph[c][r] = [0, 0, 0]
            return z
        
    return 0

answer = 0
for r in range(R):
    answer += getShark(r)
    moveShark()
    
print(answer)