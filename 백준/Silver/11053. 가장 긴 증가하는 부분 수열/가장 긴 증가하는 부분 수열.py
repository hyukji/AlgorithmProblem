import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))
dp = [0 for _ in range(1001)]

for el in arr:
    M = 0
    for i in range(el):
        M = max(dp[i], M)

    dp[el] = max(M+1, dp[el])

print(max(dp))