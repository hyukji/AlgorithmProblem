
# 단순 플로이드워샬로 푸니 4분 가까이 나와서... 다른 방법으로 접근했습니다.

from collections import deque

def bfs(s, n, graph):
    visited = [False for _ in range(n)]
    visited[s] = True
    visited_cnt = 1
    _sum = 0
    
    dq = deque([s])
    dist = 0
    while(dq):
        size = len(dq)
        for _ in range(size):
            e = dq.popleft()
            _sum += dist

            if(visited_cnt == n):
                continue

            for ne in graph[e]:
                if(visited[ne]):
                    continue

                dq.append((ne))
                visited[ne] = True 
                visited_cnt += 1
                
        dist += 1
    
    return _sum

T = int(input())
for tc in range(1, T+1):
    arr = list(map(int, input().rstrip().split()))
    n = arr[0]
    
    graph = []
    for r in range(n):
        row = []
        for c in range(n):
            if arr[r*n+c+1] != 0:
                row.append(c)
        graph.append(row)

    answer = float('inf')
    for r in range(n):
        answer = min(bfs(r, n, graph), answer)

    print(f"#{tc} {answer}")