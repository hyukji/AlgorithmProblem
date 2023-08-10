n, m = map(int, input().split())

dp = [[0 for _ in range(m+1)] for _ in range(n+1)]

answer = 0
cols = [0] * (m+1)
for c in range(1, n+1):
    row = [0] + list(input())
    cnt = 0
    for r in range(1, m+1):
        v = row[r]
        if v == '0':
            cnt = 0
            cols[r] = 0
            continue
        
        
        # 대각선과 COL확인 및 cnt 를 통해 row 확인
        dp[c][r] = min(dp[c-1][r-1], cols[r], cnt) + 1
        answer = max(answer, dp[c][r])
    
        cols[r] += 1
        cnt += 1
        
print(answer**2)