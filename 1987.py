C, R = map(int, input().split())
graph = []
for _ in range(C):
    graph.append(input())

directions = [(1, 0), (0, 1), (-1, 0), (0, -1)]
check = [[[0] for _ in range(R)] for _ in range(C)] # 백트래킹을 위한 arr

# A로 부터 해당 알파벳까지의 차이만큼 bit이동 ex) B의 경우 10(2)
def getAlphaIndex(alpha):
    return 1 << (ord(alpha) - 65)

# DFS
answer = 1
alpha = graph[0][0]
stack = [(0, 0, getAlphaIndex(alpha), 1)] # (c, r, 현재 경로에서 발견된 문자열, 모든 경로에서 발견된 문자열 개수)
while stack:
    c, r, visited, cnt = stack.pop()
        
    for dc, dr in directions:
        nc = c + dc
        nr = r + dr
        if nc < 0 or nc >= C or nr < 0 or nr >= R:
            continue
        
        alpha = graph[nc][nr]
        alphaIndex = getAlphaIndex(alpha)
        
        if visited & alphaIndex == 0: # 현재 경로에 발견된 적이 없다면.
            new_visited = visited | alphaIndex
            if new_visited not in check[nc][nr]: # 이전에 같은 경우로 접근했는 가
                check[nc][nr].append(new_visited)
                stack.append((nc, nr, new_visited, cnt+1))
                answer = max(answer, cnt+1)

print(answer)