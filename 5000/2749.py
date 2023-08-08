from collections import defaultdict

n = int(input())
dp = defaultdict(int)

dp[0] = 0
dp[1] = 1
dp[2] = 1
def getValue(i):
    if dp[i] != 0 or i < 3:
        return dp[i]
    
    if i%2 == 0: 
        M, m = getValue(i//2+1), getValue(i//2-1) 
        dp[i] = (M**2 - m**2) % 1000000
    else:
        M, m = getValue(i//2+1), getValue(i//2)
        dp[i] = (M**2 + m**2) % 1000000
    
    return dp[i]

print(getValue(n))