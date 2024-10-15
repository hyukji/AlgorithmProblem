import sys

dp = [1 for _ in range(10001)]
for i in range(2, 10001):
    dp[i] += dp[i-2]
    
for i in range(3, 10001):
    dp[i] += dp[i-3]

answer = []
n = int(input())
for _ in range(n):
    v = int(input())
    answer.append(str(dp[v]))

print("\n".join(answer))