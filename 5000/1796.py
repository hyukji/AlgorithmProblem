s = input()

locs = [[0, 0] for _ in range(26)] # m, M
visited = [0 for _ in range(26)]

for i, el in enumerate(s):
    converted = ord(el) - 97 # a -> 0, b -> 1
    if visited[converted] == 0:
        visited[converted] = 1
        locs[converted][0] = i    
    locs[converted][1] = i

existElements = [el for el in range(26) if visited[el] == 1]
dp = [[float('inf') for _ in range(2)] for _ in range(len(existElements))] # 0 : <- , 1 : ->

m, M = locs[existElements[0]]
dp[0] = [M+(M-m), M    ]
for i in range(1, len(existElements)):
    bef, now = existElements[i-1], existElements[i]
    bm, bM = locs[bef]
    m, M = locs[now]

    # dp[b][<-] = min(dp[a][<-] + 시작점을 위한 위치 이동, dp[a][->] + 시작점을 위한 위치 이동) + b 자체에서의 이동
    # dp[b][->] = min(dp[a][<-] + 시작점을 위한 위치 이동, dp[a][->] + 시작점을 위한 위치 이동) + b 자체에서의 이동
    dp[i][0] = min(abs(bm - M) + dp[i-1][0], abs(bM - M) + dp[i-1][1]) + M - m # <- 을 위한 (시작점 이동거리 및 이전까지의 버튼 입력 횟수)의 min 과 이동 거리.
    dp[i][1] = min(abs(bm - m) + dp[i-1][0], abs(bM - m) + dp[i-1][1]) + M - m

print(min(dp[-1]) + len(s))

# g4. dp. 1h