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
            if graph[c][r] == 0:
                continue
            
            S += graph[c][r]
            cnt += 1
            
            for dc, dr in directions:
                nc, nr = c + dc, r + dr
                
                if nc >= n or nc < 0:
                    continue
                
                if nr == m:
                    nr = 0
                
                if graph[c][r] == graph[nc][nr]:
                    visited[c][r] = 1
                    visited[nc][nr] = 1  
                    isVisited = 1
                    
    if isVisited == 1:
        for c in range(n):
            for r in range(m):
                if visited[c][r] == 1:
                    S -= graph[c][r]
                    graph[c][r] = 0
    elif cnt != 0:
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
    graph.append(deque(row))
    S += sum(row)
    
for _ in range(t):
    x, d, k = map(int, input().split())
    for i in range(1, n//x+1):
        if d == 0:
            clockwise(x*i-1, k)
        else:
            counterClockWise(x*i-1, k)
    
    S = check()
            

print(S)


"""
4 4 1
1 1 2 3
5 2 4 2
3 1 3 5
2 1 3 2
2 0 1
"""