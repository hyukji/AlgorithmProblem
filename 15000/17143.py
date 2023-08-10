C, R, m = map(int, input().split())

graph = [[[0, 0, 0] for _ in range(R)] for _ in range(C)]
for _ in range(m):
    c, r, s, d, z = map(int, input().split())
    graph[c-1][r-1] = [z, s, d]
    
# 상어 이동    
def moveShark():
    global graph
    next_graph = [[[0, 0, 0] for _ in range(R)] for _ in range(C)]

    ccan, rcan = C - 1, R - 1 # 열 혹은 행의 이동 가능 거리
    for y in range(C):
        for x in range(R):
            z, s, d = graph[y][x]
            if z == 0: #해당 칸에 상어가 없다면 패스
                continue
            
            ny, nx = y, x # 초기값
            
            # 4가지 방향 별로 구성.
            # 상어가 방향을 돌려야하는 경우 방향을 돌려야하는 횟수에 맞춰 방향 및 위치 재구성
            if d == 1: # 위
                if s <= y:
                    ny = y - s
                else: # 방향 돌리는 경우
                    ns = s - y
                    if (ns // ccan) % 2 == 0: # 결과적으로 반대방향
                        ny = ns % ccan
                        d = 2
                    else: # 결과적으로 같은 방향
                        ny = C - 1 - ns % ccan 
            elif d == 2: # 아래
                if s < C-y:
                    ny = y + s
                else: # 방향 돌리는 경우
                    ns = s - ccan + y
                    if (ns // ccan) % 2 == 0: # 결과적으로 반대방향
                        ny = C - 1 - ns % ccan
                        d = 1
                    else: # 결과적으로 같은 방향
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
                

# 해당 열에 있는 상어 추출
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