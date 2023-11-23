from itertools import combinations
from collections import deque
import copy

N, M, D = map(int, input().split())
graph = []
for _ in range(N):
    graph.append(list(map(int, input().split())))

# 사전에 D에 맞추어 가능한 좌표 탐색 저장 -> 앞에서부터 거리, 왼쪽 순으로 저장. 궁수들이 탐지 방법 순으로 저장
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
            
answer = 0
combies = combinations(range(M), 3)
# 시작 위치들을 설정하고 탐색
for combi in combies:
    combi = list(combi)
    ggraph = copy.deepcopy(graph)
    y, d, sub_answer = N, 2, 0 # y:궁수 y위치. d 현재 진행되는 턴을 의미. d=3일때 화살을 맞으면 3이 저장.
    while y != -1:
        for x in combi:
            for dy, dx in path: # 탐지 순서대로
                ny, nx = y + dy, x + dx
                if 0 > ny or 0 > nx or nx >= M:
                    continue
                
                if ggraph[ny][nx] == 1:
                    # 해당 적을 처음 공격한 경우
                    ggraph[ny][nx] = d
                    sub_answer += 1
                    break
                
                if ggraph[ny][nx] == d:
                    # 해당 적을 이전에 공격했던 경우 -> 한 명의 적에게 여러 화살 가능.
                    break
                
        d+=1            
        y-=1
        
    answer = max(answer, sub_answer)

print(answer)

