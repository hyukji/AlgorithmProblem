from itertools import combinations
from collections import deque
import copy

N, M, D = map(int, input().split())
graph = []
for _ in range(N):
    graph.append(list(map(int, input().split())))

# D에 맞추어 가능한 좌표 탐색.
path = [(-1, 0)]
directions = [(0, -1),(-1, 0),(0, 1)]
dq = deque([(-1, 0)])
for _ in range(D-1):
    for _ in range(len(dq)):
        y, x = dq.popleft()
        for dy, dx in directions:
            ny, nx = y + dy, x + dx
            if (ny, nx) in path:
                continue
            path.append((ny, nx))
            dq.append((ny, nx))
            
answer =0
combies = combinations(range(M), 3)
# 시작 위치들을 설정하고 탐색
for combi in combies:
    combi = list(combi)
    ggraph = copy.deepcopy(graph)
    y, d, sub_answer = N, 2, 0
    while y != -1:
        for x in combi:
            for dy, dx in path:
                ny, nx = y + dy, x + dx
                if 0 > ny or 0 > nx or nx >= M:
                    continue
                
                if ggraph[ny][nx] == 1:
                    # 해당 적을 처음 공격한 경우
                    ggraph[ny][nx] = d
                    sub_answer += 1
                    break
                
                if ggraph[ny][nx] == d:
                    # 해당 적을 이전에 공격했던 경우
                    break
                
        d+=1            
        y-=1
        
    answer = max(answer, sub_answer)

print(answer)

