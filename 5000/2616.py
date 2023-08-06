n = int(input())
arr = list(map(int, input().split()))
m = int(input())
s_arr = [0]
for el in arr:
    s_arr.append(s_arr[-1] + el)

dp = [[0 for _ in range(n+1)] for _ in range(3)]

for i in range(m, n+1):
    dp[0][i] = max(dp[0][i-1], s_arr[i] - s_arr[i-m])
    dp[1][i] = max(dp[1][i-1], dp[0][i-m] + s_arr[i] - s_arr[i-m])
    dp[2][i] = max(dp[2][i-1], dp[1][i-m] + s_arr[i] - s_arr[i-m])
    
print(dp[2][-1])