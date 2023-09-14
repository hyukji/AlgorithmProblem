from copy import deepcopy
from itertools import product
from collections import deque

m, s = map(int, input().split())
fish = [[[] for _ in range(4)] for _ in range(4)]
shark = [[0 for _ in range(4)] for _ in range(4)]
for _ in range(m):
    x, y, d = map(int, input().split())
    fish[x-1][y-1].append(d-1)
x, y = list(map(int, input().split()))
sharkLoc = (x-1, y-1)

directions = [(0, -1) , (-1, -1), (-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1)] # ←, ↖, ↑, ↗, →, ↘, ↓, ↙


def printFish():
    for row in fish:
        print(row)

def printShark():
    for row in shark:
        print(row)


def duplicateFish():
    for x in range(4):
        for y in range(4):
            fish[x][y] += oriFish[x][y]

def nextLocation(x, y, d):
    for nd in range(d+8, d, -1):
        nd %= 8
        dx, dy = directions[nd]
        nx, ny = x + dx, y + dy

        if 0 > nx or nx >= 4 or 0 > ny or ny >= 4:
            continue
        if shark[nx][ny] != 0:
            continue
        if (nx, ny) == sharkLoc:
            continue

        return nx, ny, nd
        
    return x, y, d

def moveFish():
    newFish = [[[] for _ in range(4)] for _ in range(4)]
    for x in range(4):
        for y in range(4):
            for d in fish[x][y]:
                nx, ny, nd = nextLocation(x, y, d)
                newFish[nx][ny].append(nd)

    return newFish

# def getSharkRoute(sharkLoc):
#     x, y = sharkLoc
#     directions = [(-1, 0), (0, -1), (1, 0), (0, 1)] # 상좌하우
#     paths = product(range(4), repeat=3)

#     M = 0
#     M_path = [0, 0, 0]
#     for path in paths:
#         nx, ny, value = x, y, 0
#         tempFish = deepcopy(fish)
#         for d in path:
#             dx, dy = directions[d]
#             nx, ny = nx + dx, ny + dy
#             if 0 > nx or nx >= 4 or 0 > ny or ny >= 4:
#                 break
#             value += len(tempFish[nx][ny])
#             tempFish[nx][ny] = []
#         else:
#             if value > M:
#                 M = value
#                 M_path = path

#     return M_path

def getSharkRouteByBfs(sharkLoc):
    x, y = sharkLoc
    directions = [(-1, 0), (0, -1), (1, 0), (0, 1)] # 상좌하우
    visited = [[0 for _ in range(4)] for _ in range(4)]
    dq = deque([(x, y, 0, [])])

    path = []
    for _ in range(3):
        for _ in range(len(dq)):
            x, y, v, path = dq.popleft()
            for d in range(4):
                dx, dy = directions[d]
                nx, ny, nv = x + dx, y + dy, v
                if 0 > nx or nx >= 4 or 0 > ny or ny >= 4:
                    continue
                
                if not (len(path) > 1 and (d + 2) % 4 == path[-1]): # 왔던 길을 되돌아 간다면 fish는 이미 없어진 상태.
                    nv += len(fish[nx][ny])

                if visited[nx][ny] <= nv:
                    visited[nx][ny] = nv
                    dq.append((nx, ny, nv, path + [d]))

    M = -1
    M_path = []
    for x, y, value, path in dq:
        if value > M:
            M = value
            M_path = path
        elif value == M and path < M_path:
            M_path = path

    return M_path


def moveShark(sharkLoc, route):
    x, y = sharkLoc
    directions = [(-1, 0), (0, -1), (1, 0), (0, 1)] # 상좌하우

    for d in route:
        dx, dy = directions[d]
        x += dx
        y += dy
    
        if fish[x][y] != []:
            fish[x][y] = []
            shark[x][y] = 3

    return (x, y)

def removeSmell():
    for x in range(4):
        for y in range(4):
            if shark[x][y] == 0:
                continue
            shark[x][y] -= 1

def countFish():
    answer = 0
    for x in range(4):
        for y in range(4):
            answer += len(fish[x][y])
    return answer

for i in range(s):
    oriFish = deepcopy(fish)

    fish = moveFish()

    sharkRoute = getSharkRouteByBfs(sharkLoc)
    sharkLoc = moveShark(sharkLoc, sharkRoute)
    removeSmell()

    duplicateFish()

print(countFish())