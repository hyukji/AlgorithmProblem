from collections import deque

n = int(input())
area = [list(map(int, input().split())) for _ in range(n)] 
size = 2

dy = [-1,0,0,1]
dx = [0,-1,1,0]

for i in range(n):
    for j in range(n):
        if area[i][j] == 9:
            y,x = i,j
            
            
# 물고기 한마리 먹는 과정
def dfs(y, x, size, cnt):
    dq = deque()
    dq.append([y,x])
    checked = [[0 for _ in range(n)] for _ in range(n)]
    period = 0
    while dq:
        period += 1
        res = []
        for _ in range(len(dq)):
            y, x = dq.popleft()
            for d in range(4):
                ny, nx = y + dy[d], x + dx[d]
                if 0 <= ny < n and 0 <= nx < n and checked[ny][nx] == 0:
                    if area[ny][nx] == size or area[ny][nx] == 0:
                        checked[ny][nx] = 1
                        dq.append([ny, nx])
                    elif area[ny][nx] > size:
                        continue
                    else:
                        res.append([ny, nx])
                    
        if len(res) > 0:
            y, x = n, n
            for ny, nx in res:
                if ny < y or (ny == y and nx < x):
                    y, x = ny, nx
                    
            return period, y, x

    return 0, -1, -1
    
time = 0
size = 2
cnt = 0

while True:
    area[y][x] = 0
    period, y, x = dfs(y, x, size, cnt)
    
    time += period
    if period == 0:
        print(time)
        break
    
    area[y][x] = 0
    
    cnt += 1
    if cnt == size:
        size += 1
        cnt = 0