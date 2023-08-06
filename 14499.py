N, M, x, y, K = map(int, input().split())
graph = []
for _ in range(N):
    graph.append(list(map(int, input().split())))

comms = list(map(int, input().split()))
dice = [0, 0, 0, 0, 0, 0, 0]
directions = [(0, 0), (0, 1), (0, -1), (-1,0), (1, 0)]

for comm in comms:
    dx, dy = directions[comm]
    nx = x + dx
    ny = y + dy
    if nx < 0 or nx >= N or ny < 0 or ny >= M:
        continue

    x, y = nx, ny

    if comm == 1:
        dice[4], dice[1], dice[3], dice[6] = dice[6], dice[4], dice[1], dice[3]
    elif comm == 2:
        dice[4], dice[1], dice[3], dice[6] = dice[1], dice[3], dice[6], dice[4]
    elif comm == 3:
        dice[2], dice[1], dice[5], dice[6] = dice[1], dice[5], dice[6], dice[2]
    elif comm == 4:
        dice[2], dice[1], dice[5], dice[6] = dice[6], dice[2], dice[1], dice[5]

    if graph[x][y] == 0:
        graph[x][y] = dice[6]
    else:
        dice[6] = graph[x][y]
        graph[x][y] = 0
    
    print(dice[1])


