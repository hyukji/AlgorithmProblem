from collections import defaultdict

n, m, k = map(int, input().split())
candies = list(map(int, input().split()))

rootArr = [i for i in range(n+1)]
def findRoot(i):
    while i != rootArr[i]:
        i = rootArr[i]
    return i

def unionRoot(i, j):
    ri, rj = findRoot(i), findRoot(j)
    
    if ri != rj:
        rootArr[ri] = rj    

for _ in range(m):
    i, j = map(int, input().split())
    unionRoot(i, j)

ddict = defaultdict(list)
for i in range(1, n+1):
    r = findRoot(i)
    rootArr[i] = r
    
    if len(ddict[r]) == 0:
        ddict[r] = [0, 0] # cost, weight
        
    ddict[r][0] += 1
    ddict[r][1] += candies[i-1]

keys = list(ddict.keys())
nkey = len(keys)
dp = [[0 for _ in range(k)] for _ in range(nkey)]

c, w = ddict[keys[0]]
for cost in range(k):
    if cost >= c:
        dp[0][cost] = w

for i in range(1, nkey):
    c, w = ddict[keys[i]]
    for cost in range(k):
        dp[i][cost] = dp[i-1][cost]
        if cost >= c:
            dp[i][cost] = max(dp[i-1][cost], dp[i-1][cost-c] + w)

print(dp[nkey-1][k-1])
    
    
    
