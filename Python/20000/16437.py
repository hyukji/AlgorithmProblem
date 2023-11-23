import sys
from collections import defaultdict
sys.setrecursionlimit(10 ** 4)

n = int(input())
tree = [[] for _ in range(n+1)]
graph = [[0, 0], [0, 0]]

for i in range(n-1):
    island = input().split()

    v = int(island[1])
    parent = int(island[2])
    isWolf = True if island[0] == "W" else False

    graph.append([v, isWolf])
    tree[parent].append((i+2))


def dfs(i, w):
    sheep,  wolf = 0, 0
   
    v, isWolf = graph[i]

    if isWolf:
        wolf = w + v
    elif v > w:
        sheep = v - w
    else:
        wolf = w - v

    for c in tree[i]:
        cs, wolf = dfs(c, wolf)
        
        sheep += cs

    return sheep, min(wolf, w)

answer = 0
w = 0
for c in tree[1]:
    s, w = dfs(c, w)
    answer += s

print(answer)