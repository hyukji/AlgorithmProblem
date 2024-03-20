import sys
input = sys.stdin.readline

n, m = map(int, input().split())
memories = list(map(int, input().split()))
values = list(map(int, input().split()))

dp = [[0 for _ in range(10001)] for _ in range(n)]

nm = memories[0]
nv = values[0]
for i in range(nv, 10001):
    dp[0][i] = nm

for i in range(1, n):
    nm = memories[i]
    nv = values[i]
    for j in range(nv):
        dp[i][j] = dp[i-1][j]

    for j in range(nv, 10001):
        dp[i][j] = max(dp[i-1][j], dp[i-1][j-nv] + nm)

for j in range(10001):
    if(dp[n-1][j] >= m):
        print(j)
        break