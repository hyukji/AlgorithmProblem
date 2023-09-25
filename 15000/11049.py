n = int(input())
graph = []
for _ in range(n):
    graph.append(list(map(int, input().split())))

dp = [[(0, 0, 0) for _ in range(n)] for _ in range(n)] # v1, v2, cnt

def countMultiOpt(l, r): # return Count
    if l == r:
        return 0
    
    left = graph[l]
    right = graph[r]

    cnt = float('inf')
    for p in range(l+1, r+1):
        cnt = min(cnt, dp[l][p-1][2] + dp[p][r][2] + (left[0] * graph[p][0] * right[1]))

    return cnt

for r in range(1, n):
    right = graph[r]
    for l in range(r, 0, -1):
        left = graph[l]
        dp[l][r] = (left[0], right[1], countMultiOpt(l, r))

    dp[0][r] = (graph[0][0], right[1], countMultiOpt(0, r))

print(dp[0][r][2])