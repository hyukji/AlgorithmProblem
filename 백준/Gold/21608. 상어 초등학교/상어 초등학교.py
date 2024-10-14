import sys
from collections import deque

directions = [[-1, 0], [1, 0], [0, -1], [0, 1]]

n = int(input())
likes = dict()

seats = [[-1 for _ in range(n)] for _ in range(n)]
for _ in range(n*n):
    s, a, b, c, d = map(int, input().split())
    likes[s-1] = [a-1, b-1, c-1, d-1]

    tr, rc, tlike, tempty = 0, 0, -1, -1
    for r in range(n):
        for c in range(n):
            if seats[r][c] != -1:
                continue

            like, empty = 0, 0
            for d in range(4):
                dr, dc = directions[d]
                nr, nc = r + dr, c + dc
                if nr < 0 or nr >= n or nc < 0 or nc >= n:
                    continue

                nv = seats[nr][nc]
                if nv in likes[s-1]:
                    like+=1
                elif nv == -1:
                    empty += 1
            
            if tlike < like:
                tr, tc, tlike, tempty = r, c, like, empty
            elif tlike == like and tempty < empty:
                tr, tc, tlike, tempty = r, c, like, empty

    
    seats[tr][tc] = s-1

answer = 0
for r in range(n):
    for c in range(n):
        like = 0
        v = seats[r][c]
        for d in range(4):
            dr, dc = directions[d]
            nr, nc = r + dr, c + dc
            if nr < 0 or nr >= n or nc < 0 or nc >= n:
                continue

            nv = seats[nr][nc]
            if nv in likes[v]:
                like+=1
        
        if like > 0:
            answer += 10 ** (like-1)

print(answer)