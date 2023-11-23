N = int(input())
A = []
for _ in range(N):
    A.append(list(map(int, input().split())))

settings = [] # x, y, d1, d2
for x in range(N):
    for y in range(N):
        for d1 in range(1, y+1): # d1 < y+1
            for d2 in range(1, N-y): # d2 < N-y
                if x+d1+d2 <= N-1:
                    settings.append([x, y, d1, d2])

answer = float('inf')
for x, y, d1, d2 in settings:
    graph = [[0 for _ in range(N)] for _ in range(N)]
    result = [0, 0, 0, 0, 0]
    s, e, ds, de = y, y, -1, 1

    # 5 채워주기
    for r in range(x, x+d1+d2+1):
        for c in range(s, e+1):
            graph[r][c] = 4

        if r == x + d1:
            ds = 1
        if r == x + d2:
            de = -1

        s += ds
        e += de


    for r in range(N):
        for c in range(N):
            if graph[r][c] == 4:
                result[4] += A[r][c]
                continue

            if 0 <= r < x+d1 and 0 <= c <= y:
                graph[r][c] = 0
                result[0] += A[r][c]
            elif 0 <= r <= x+d2 and y < c <= N-1:
                graph[r][c] = 1
                result[1] += A[r][c]
            elif x+d1 <= r <= N-1 and 0 <= c < y-d1+d2:
                graph[r][c] = 2
                result[2] += A[r][c]
            elif x+d2 < r <= N-1 and y-d1+d2 <= c <= N-1:
                graph[r][c] = 3
                result[3] += A[r][c]
                
    answer = min(answer, max(result) - min(result))

print(answer)