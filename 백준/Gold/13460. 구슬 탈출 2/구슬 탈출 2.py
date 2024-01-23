from collections import deque

n, m = map(int, input().split())
board = []
rx, ry, bx, by = -1,-1,-1,-1
for cnt in range(n):
    row = input()
    if "R" in row:
        rx = row.find("R")
        ry = cnt
        row.replace("R", ".")
    if "B" in row:
        bx = row.find("B")
        by = cnt
        row.replace("B", ".")
    
    board.append(row)
    

visited = [[[[0] * n for _ in range(m)] for _ in range(n)] for _ in range(m)]

# right, up, left, down
dx = [1, 0, -1, 0]
dy = [0, -1, 0, 1]
        
def move(x, y, d):
    while True:
        nx, ny = x + dx[d], y + dy[d]
        if board[ny][nx] == "#":
            return x, y
        
        if board[ny][nx] == "O":
            return nx, ny
        
        x, y = nx, ny

    
def bfs(first):
    deq = deque()
    deq.append(first)
    
    while deq:
        depth, rx, ry, bx, by = deq.popleft()
        
        for d in range(4):
            nrx, nry = move(rx, ry, d)
            nbx, nby = move(bx, by, d)
        
            if board[nby][nbx] == "O":
                continue
            elif board[nry][nrx] == "O":
                return depth
            
            if (nby, nbx) == (nry, nrx):
                r_move = abs(nrx + nry - rx - ry)
                b_move = abs(nbx + nby - bx - by)
                if r_move > b_move:
                    nrx, nry = nrx - dx[d], nry - dy[d]
                else:
                    nbx, nby = nbx - dx[d], nby - dy[d]

            if visited[nrx][nry][nbx][nby] == 1:
                continue
            elif depth < 10:
                visited[nrx][nry][nbx][nby] = 1
                deq.append([depth+1, nrx, nry, nbx, nby])
                
    return -1
    

print(bfs([1, rx, ry, bx, by]))
