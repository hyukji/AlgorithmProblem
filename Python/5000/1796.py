s = input()

locs = [[0, 0] for _ in range(26)] # 알파벳 순서별로 가장 앞에 있는 index와 가장 끝에 있는 index를 저장. m, M
visited = [0 for _ in range(26)]

for i, el in enumerate(s):
    converted = ord(el) - 97 # 알파벳을 int로 변환. a -> 0, b -> 1
    if visited[converted] == 0:
        visited[converted] = 1
        locs[converted][0] = i    
    locs[converted][1] = i

existElements = [el for el in range(26) if visited[el] == 1] # 26개의 알파벳 중 존재하는 알파벳만 추출.


# 알파벳을 찾는 과정에서 가장 앞에서부터 뒤쪽으로 찾거나 (-> 방향) 반대 방향으로 (<- 방향) 찾는 것이 가장 효율적임.
# 따라서 dp 안에는 방향에 맞추어 해당 알파벳을 <-으로 검색할때의 최소값, ->으로 검색할 때의 최소값을 저장함.
dp = [[float('inf') for _ in range(2)] for _ in range(len(existElements))] # 0 : <- , 1 : ->

m, M = locs[existElements[0]]
dp[0] = [M+(M-m), M] # 첫번째 알파벳이므로 <- 으로 찾기위해서는 M까지 갔다가 (M-m)칸을 이용해야함. 반대로 -> 은 0번째부터 M까지 이동해야함.
for i in range(1, len(existElements)): # 존재하는 모든 알파벳을 검색하면서.
    bef, now = existElements[i-1], existElements[i] # 이전 알파벳, 현재 알파벳
    bm, bM = locs[bef] # 이전 알파벳의 가장 앞의 위치와 마지막 위치.
    m, M = locs[now] # 현재 알파벳의 가장 앞의 위치와 마지막 위치.

    # dp[현재 알파벳][방향] = (이전 알파벳까지의 이동 값 + 해당 방향의 시작점을 위한 이동)의 최솟값 + 현재 알파벳 탐색하는 이동거리
    # dp[b][<-] = min(dp[a][<-] + 시작점을 위한 위치 이동, dp[a][->] + 시작점을 위한 위치 이동) + b 탐색을 위한 이동
    # dp[b][->] = min(dp[a][<-] + 시작점을 위한 위치 이동, dp[a][->] + 시작점을 위한 위치 이동) + b 탐색을 위한 이동
    dp[i][0] = min(abs(bm - M) + dp[i-1][0], abs(bM - M) + dp[i-1][1]) + M - m
    dp[i][1] = min(abs(bm - m) + dp[i-1][0], abs(bM - m) + dp[i-1][1]) + M - m

print(min(dp[-1]) + len(s)) # 엔터 개수는 문자열의 길이

# g4. dp. 1h