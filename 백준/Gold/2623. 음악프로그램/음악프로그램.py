from collections import defaultdict
import sys

n, m = map(int, input().split())
parents = defaultdict(set)
children = defaultdict(int)
for _ in range(m):
    arr = list(map(int, input().split()))[1:]
    for i in range(len(arr)-1):
        if not arr[i] in parents[arr[i+1]]:
            parents[arr[i+1]].add(arr[i])   
            children[arr[i]] += 1

visited = [False for _ in range(n+1)]
canVisit = []
seq = []
for k in range(1, n+1):
    if children[k] == 0:
        canVisit.append(k)


while canVisit:
    k = canVisit.pop()

    seq.append(k)
    visited[k] = True

    for p in parents[k]:
        children[p] -= 1
        if children[p] == 0:
            canVisit.append(p)

for i in range(1, n+1):
    if visited[i] == True:
        continue
    elif children[i] != 0:
        print(0)
        break
    else:
        seq.append(i)
else:
    seq.reverse()
    print(*seq, sep= "\n")