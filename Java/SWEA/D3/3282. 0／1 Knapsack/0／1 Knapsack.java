T = int(input())
for tc in range(1, T+1):
    n, k = map(int, input().split())
    graph = [list(map(int, input().split())) for _ in range(n)]

    dp = [[0 for _ in range(k+1)] for _ in range(n)]
    v, c = graph[0]
    for j in range(v, k+1):
        dp[0][j] = c

    for i in range(1, n):
        v, c = graph[i]
        for j in range(v):
            dp[i][j] = dp[i-1][j]
        for j in range(v, k+1):
            dp[i][j] = max(dp[i-1][j-v] + c, dp[i-1][j])

    print(f'#{tc} {dp[-1][-1]}')