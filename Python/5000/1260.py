import sys
from collections import deque
input=sys.stdin.readline

n, m ,v = map(int, input().split())

dict = {}
for i in range(n+1):
    dict[i] = []

for _ in range(m):
    s, e = map(int, input().split())
    dict[s].append(e)
    dict[e].append(s)




def dfs(v):
    result.append(v)
    lst = sorted(dict[v])
    for el in lst:
        if check[el] != 1:
            check[el] = 1
            dfs(el)

   
# dfs  
check = [0 for _ in range(n+1)]
check[0] = 1
check[v] = 1

result = []

dfs(v)   
print(*result)



# bfs
check = [0 for _ in range(n+1)]
check[0] = 1
check[v] = 1

result = [v]

dq = deque([v])
while len(dq) > 0:
    nv = dq.popleft()
    lst = sorted(dict[nv])

    for el in lst:
        if check[el] == 0:
            dq.append(el)
            check[el] = 1
            result.append(el)

print(*result)
