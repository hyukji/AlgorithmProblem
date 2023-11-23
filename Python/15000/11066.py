from math import inf

for t in range(int(input())):
    n = int(input())
    arr = tuple(map(int, input().split()))

    prefix = [0]
    for i in range(n):
        prefix.append(prefix[-1]+arr[i])

    dp = [[inf] * (n+1) for _ in range(n+1)]
    dpk = [[0] * n for _ in range(n)]
    for i in range(n):
        dp[i][i] = 0
        dpk[i][i] = i

    for d in range(1, n):
        for i in range(n-d):
            j = i+d
            for k in range(dpk[i][j-1], dpk[i+1][j]+1):
                if dp[i][j] > dp[i][k]+dp[k+1][j]:
                    dpk[i][j] = k
                    dp[i][j] = dp[i][k]+dp[k+1][j]
            dp[i][j] += prefix[j+1]-prefix[i]

    print(dp[0][-2])