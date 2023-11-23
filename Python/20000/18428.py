from itertools import combinations
from copy import deepcopy

n = int(input())
map = []
teachers = []
studentCount = 0
directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
        
def arr2loc(arr):
    locs = []
    for num in arr:
        r, c = num // n, num % n
        locs.append((r, c))
        if map[r][c] != 0:
            return []
        
    return locs
        
def setObstacles(locs):
    for r, c in locs:
        customMap[r][c] = 3

def findSFromT():
    cnt = 0

    return cnt

def findUsingDirection(nr, nc, d):
    cnt = 0
    while True:
        dr, dc = directions[d]
        nr, nc = nr + dr, nc + dc

        if nr < 0 or nr >= n or nc < 0 or nc >= n:
            break

        target = customMap[nr][nc]
        if target == 2 or target == 3: # annother teacher or obstacle
            break
        elif target == 1:
            customMap[nr][nc] = 0
            cnt += 1

    return cnt

for r in range(n):
    row = []
    for c, el in enumerate(input().split()):
        if el == "T":
            teachers.append((r, c))
            v = 2
        elif el == "S":
            studentCount += 1
            v = 1
        else:
            v = 0
        row.append(v)

    map.append(row)
    
answer = "NO"
for combi in list(combinations(range(n*n), 3)):
    locs = arr2loc(combi)
    if locs == []:
        continue

    customMap = deepcopy(map)

    setObstacles(locs)
    
    cnt = 0
    for r, c in teachers: # find students from teachers
        for d in range(4):
            cnt += findUsingDirection(r, c, d)

    if cnt == 0: # teachers cannot find any students
        answer = "YES"
        break

print(answer)