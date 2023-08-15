A = input()
B = input()

a, b = len(A), len(B)
dp = [[0 for _ in range(b+1)] for _ in range(a+1)]

for i in range(1, a+1):
    for j in range(1, b+1):
        if A[i-1] == B[j-1]:
            dp[i][j] = dp[i-1][j-1] + 1
        else:
            dp[i][j] = max(dp[i-1][j], dp[i][j-1])

size = dp[a][b]
print(size)


# dp[i][j] == dp[i-1][j-1] + 1 이라면, A[i] == B[j] but 그러기 위해서는
# dp[i-1][j] 랑 dp[i][j-1] 이 dp[i][j]와 같은 값을 가진다면 안됨. 같은 값을 가진다면 max를 이용해 값을 가져온 것 일수도!
LCS = []
while size != 0:
    if dp[a-1][b] == size:
        a = a-1
    elif dp[a][b-1] == size:
        b = b-1
    else:
        LCS.append(A[a-1])
        a, b = a-1, b-1
        size = dp[a][b]

print("".join(reversed(LCS)))