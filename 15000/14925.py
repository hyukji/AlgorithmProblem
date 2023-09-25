M, N = map(int, input().split())
graph = [[1 for _ in range(N+1)]]
for _ in range(M):
    graph.append([1] + list(map(int, input().split())))

continuity_sqr = [[0 for _ in range(N+1)] for _ in range(M+1)]
continuity_col = [0 for _ in range(N+1)]

answer = 0
for c in range(1, M+1):
    continuity_row = 0
    for r in range(1, N+1):
        if graph[c][r] != 0:
            continuity_row = 0
            continuity_col[r] = 0
            continue

        sqr_size = min(continuity_sqr[c-1][r-1], continuity_row, continuity_col[r]) + 1
        continuity_row += 1
        continuity_col[r] += 1
        continuity_sqr[c][r] = sqr_size

        answer = max(sqr_size, answer)

print(answer)


# g4. dp. 20m