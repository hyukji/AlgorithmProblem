import sys
input = sys.stdin.readline

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

def printGraph(graph):
    answer = ""
    for r in range(R):
        answer += "".join(graph[r]) +'\n'
    print(answer)

R, C, n = map(int, input().rstrip().split())
graph = [list(input().rstrip()) for _ in range(R)]
if(n == 1):
    printGraph(graph)
elif(n%2 == 0):
    print(('O' * C + '\n') * R)
else:
    nGraph = [['O' for _ in range(C)] for _ in range(R)]
    for r in range(R):
        for c in range(C):
            if(graph[r][c] == 'O'):
                nGraph[r][c] = '.'
                for d in range(4):
                    nr = r+dr[d]
                    nc = c+dc[d]
                    if(nr < 0 or nr >= R or nc < 0 or nc >= C):
                        continue
                    nGraph[nr][nc] = '.'
                    
    if(n%4 == 3):
        printGraph(nGraph)
    elif(n%4 == 1):
        graph = [['O' for _ in range(C)] for _ in range(R)]
        for r in range(R):
            for c in range(C):
                if(nGraph[r][c] == 'O'):
                    graph[r][c] = '.'
                    for d in range(4):
                        nr = r+dr[d]
                        nc = c+dc[d]
                        if(nr < 0 or nr >= R or nc < 0 or nc >= C):
                            continue
                        graph[nr][nc] = '.'
                
        printGraph(graph)