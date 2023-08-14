R, C, Q = map(int, input().split())
graph = []
for _ in range(R):
    graph.append(list(map(int, input().split())))

sum_graph = [[0 for _ in range(C+1)] for _ in range(R+1)]
for r in range(R):
    S = 0
    for c in range(C):
        S += graph[r][c]
        sum_graph[r+1][c+1] = S + sum_graph[r][c+1]

for _ in range(Q):
    answer = 0
    r1, c1, r2, c2 = map(int, input().split())

    answer += sum_graph[r2][c2]
    answer += sum_graph[r1-1][c1-1]
    answer -= sum_graph[r2][c1-1]
    answer -= sum_graph[r1-1][c2]

    answer //= (r2-r1+1) * (c2-c1+1)

    print(answer)
            