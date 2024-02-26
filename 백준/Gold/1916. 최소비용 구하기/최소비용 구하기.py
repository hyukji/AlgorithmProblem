from collections import defaultdict

n = int(input())
m = int(input())

graph = defaultdict(list)
for _ in range(m):
    s, e, v = map(int, input().split())
    graph[s].append((e, v))
start, end = map(int, input().split())

notVisited = [i for i in range(1, n+1)]
costs = [float('inf') for _ in range(n+1)]

costs[start] = 0
cost, city = 0, start
while True:
    notVisited.remove(city)
    for e, v in graph[city]:
        if cost + v < costs[e]:
            costs[e] = cost + v

    if len(notVisited) == 0:
        break

    cost, city = min([(costs[city], city) for city in notVisited])

    if cost == float('inf'):
        break
    

print(costs[end])