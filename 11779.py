from collections import defaultdict

n = int(input())
m = int(input())

graph = defaultdict(list)
for _ in range(m):
    s, e, v = map(int, input().split())
    graph[s].append((e, v))
start, end = map(int, input().split())

notVisited = [i for i in range(1, n+1)] # 아직 방문하지 않은 도시
costs = [float('inf') for _ in range(n+1)]
path = [0 for _ in range(n+1)] # 해당 도시에 도착하기 이전에 방문한 도시를 저장 

costs[start] = 0
cost, city = 0, start
while True:
    notVisited.remove(city) # 새로운 도시 방문했으므로 제거
    for e, v in graph[city]:
        if cost + v < costs[e]:
            costs[e] = cost + v
            path[e] = city # 이전 방문 도시 업데이트

    if len(notVisited) == 0:
        break

    cost, city = min([(costs[city], city) for city in notVisited]) # 다음 도시 선정

    if cost == float('inf'):
        break
    

print(costs[end])
ans_path = []
for i in range(1, n+1):
    ans_path.append(end)
    if end == start:
        print(i)
        break
    end = path[end]

ans_path.reverse()
print(" ".join(map(str, ans_path)))