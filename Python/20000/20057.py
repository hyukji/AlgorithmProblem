n = int(input())
graph = []
for _ in range(n):
    graph.append(list(map(int, input().split())))

def getDirecttionInt(cnt):
    if cnt % 2 == 1:
        return [0, 3] # 좌 하
    else:
        return [1, 2] # 우 상

# 바람 방향에 따른 모래 퍼센트.
directions = [(0, -1), (0, 1), (-1, 0), (1, 0)]
arr = [[(-1, 0, 7), (1, 0, 7), (-1, 1, 1), (1, 1, 1), (-1, -1, 10), (1, -1, 10), (0, -2, 5), (-2, 0, 2), (2, 0, 2)], # 좌
       [(-1, 0, 7), (1, 0, 7), (-1, -1, 1), (1, -1, 1), (-1, 1, 10), (1, 1, 10), (0, 2, 5), (-2, 0, 2), (2, 0, 2)], # 우
       [(0, -1, 7), (0, 1, 7), (1, -1, 1), (1, 1, 1), (-1, -1, 10), (-1, 1, 10), (-2, 0, 5), (0, -2, 2), (0, 2, 2)], # 상
       [(0, -1, 7), (0, 1, 7), (-1, -1, 1), (-1, 1, 1), (1, -1, 10), (1, 1, 10), (2, 0, 5), (0, -2, 2), (0, 2, 2)]] # 하
       

def sand(y, x, d): # 좌표, 바람 바향 -> map이 어떻게 변하냐
    v = graph[y][x]
    graph[y][x] = 0
    
    remain, out = v, 0
    for dy, dx, p in arr[d]:
        ny, nx = y + dy, x + dx
        nv = int(v * p / 100)

        remain -= nv
        if 0 <= ny < n and 0 <= nx < n:
            graph[ny][nx] += nv
        else:
            out += nv
            
    dy, dx = directions[d]
    ny, nx = y + dy, x + dx
    if 0 <= ny < n and 0 <= nx < n:
        graph[ny][nx] += remain
    else:
        out += remain
        
    return out

answer = 0
y, x = n // 2, n // 2
for num in range(1, n):
    directionInt = getDirecttionInt(num)
    for d in directionInt:
        dy, dx = directions[d] # y, x 변화량 
        for _ in range(num):
            y, x = y + dy, x + dx
            answer += sand(y, x, d)
            
d = 0 # 좌
dy, dx = directions[d]
for _ in range(n-1):
    y, x = y + dy, x + dx
    answer += sand(y, x, 0)

print(answer)