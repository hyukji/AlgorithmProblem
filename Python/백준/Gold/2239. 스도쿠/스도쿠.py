import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**5)

def check(r, c, v):
    return v in row[r] or v in col[c] or v in sqr[r//3][c//3]

def dfs(r, c):
    if(c == 9):
        r+=1
        c = 0
    if(r == 9):
        return True

    if(graph[r][c] != 0):
        return dfs(r, c+1)

    for v in range(1, 10):
        if not (check(r, c, v)):
            row[r].add(v)
            col[c].add(v)
            sqr[r//3][c//3].add(v)
            graph[r][c] = v
            if(dfs(r, c+1)):
                return True
            row[r].remove(v)
            col[c].remove(v)
            sqr[r//3][c//3].remove(v)
            graph[r][c] = 0

    return False
    
graph = [list(map(int, input().rstrip())) for _ in range(9)]
row = [set() for _ in range(9)]
col = [set() for _ in range(9)]
sqr = [[set() for _ in range(3)] for _ in range(3)]

for r in range(9):
    for c, v in enumerate(graph[r]):
        if(v == 0):
            continue
        row[r].add(v)
        col[c].add(v)
        sqr[r//3][c//3].add(v)

dfs(0, 0)

for row in graph:
    print("".join(map(str, row)))