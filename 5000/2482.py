MAX = 1000000003

n = int(input())
k = int(input())

dp = [[0 for _ in range(k+1)] for _ in range(n+1)] # [i][j] i번째 색상까지 j개 선택할 경우의 수

for i in range(n+1):
    dp[i][0] = 1
    dp[i][1] = i

for i in range(2, n):
    for j in range(2, k+1):
        dp[i][j] = (dp[i-1][j] + dp[i-2][j-1]) % MAX

for j in range(2, k+1):
    dp[n][j] = (dp[n-2][j] + dp[n-3][j-1]) % MAX # n일 경우 맨 처음 색상을 선택하는 경우를 제외시켜야함.

print(dp[n][k])