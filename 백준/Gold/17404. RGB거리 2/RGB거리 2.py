import sys
input = sys.stdin.readline

from copy import deepcopy

n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]

# dp[n][3][3] => 시작, n번째, 지금

answer = float('inf')
for s in range(3):
    dp = deepcopy(graph)
    dp[0][(s+1)%3] = float('inf')
    dp[0][(s+2)%3] = float('inf')

    for i in range(1, n):
        dp[i][0] += min(dp[i-1][1], dp[i-1][2])
        dp[i][1] += min(dp[i-1][0], dp[i-1][2])
        dp[i][2] += min(dp[i-1][0], dp[i-1][1])
    
    answer = min(answer, dp[n-1][(s+1)%3], dp[n-1][(s+2)%3])

print(answer)