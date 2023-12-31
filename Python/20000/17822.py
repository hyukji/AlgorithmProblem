from collections import deque

def clockwise(i, k):
    for _ in range(k):
        graph[i].appendleft(graph[i].pop())

def counterClockWise(i, k):
    for _ in range(k):
        graph[i].append(graph[i].popleft())
    
def check():
    S, cnt = 0, 0
    isVisited = 0
    visited = [[0 for _ in range(m)] for _ in range(n)]
    
    directions = [(-1, 0), (1, 0), (0, -1), (0,1)]
    for c in range(n):
        for r in range(m):
            if graph[c][r] == 0: # 없는 숫자
                continue
            
            S += graph[c][r]
            cnt += 1
            
            for dc, dr in directions: # 인접 여부
                nc, nr = c + dc, r + dr
                
                if nc >= n or nc < 0:
                    continue
                
                if nr == m:
                    nr = 0
                
                if graph[c][r] == graph[nc][nr]:
                    visited[c][r] = 1
                    visited[nc][nr] = 1  
                    isVisited = 1
                    
    if isVisited == 1: # 인접한 경우가 있다면
        for c in range(n):
            for r in range(m):
                if visited[c][r] == 1:
                    S -= graph[c][r]
                    graph[c][r] = 0
    elif cnt != 0: # 인접한 경우가 없다면 -> 평균 계산
        avg = S / cnt
        for c in range(n):
            for r in range(m):
                if graph[c][r] == 0:
                    continue
                
                if avg > graph[c][r]:
                    graph[c][r] += 1
                    S+=1
                elif avg < graph[c][r]:
                    graph[c][r] -= 1
                    S-=1
                    
    return S
                    

n, m, t = map(int, input().split())
S = 0
graph = []
for _ in range(n):
    row = list(map(int, input().split()))
    graph.append(deque(row)) # rot를 위해 deque로 구현
    S += sum(row)
    
for _ in range(t):
    x, d, k = map(int, input().split())
    for i in range(1, n//x+1): # 돌려야 하는 원판
        if d == 0: # 방향 고려
            clockwise(x*i-1, k) 
        else:
            counterClockWise(x*i-1, k)
    
    S = check() # 회전 이후
            

print(S)


"""
4 4 1
1 1 2 3
5 2 4 2
3 1 3 5
2 1 3 2
2 0 1
"""