from collections import defaultdict

n = int(input()) # 노드 개수
m = int(input()) # edge 개수

graph = defaultdict(list) # 출발점과 도착점 사이의 w 저장.
for _ in range(m):
    s, e, v = map(int, input().split())
    graph[s].append((e, v)) 
start, end = map(int, input().split()) # 출발지점, 도착지점

notVisited = [i for i in range(1, n+1)] # 아직 방문하지 않은 노드들.
costs = [float('inf') for _ in range(n+1)]

costs[start] = 0
cost, city = 0, start
while True:
    notVisited.remove(city) # 방문 -> notVisited에서 제거
    for e, v in graph[city]: # 방문한 노드들과 이어진 노드를
        if cost + v < costs[e]:
            costs[e] = cost + v # 최단 거리 갱신

    if len(notVisited) == 0: # 더 이상 방문할 도시가 없다면 종료
        break

    # 이어진 노드들 중 최소 거리를 가지는 노드 방문
    cost, city = min([(costs[city], city) for city in notVisited])

    if cost == float('inf'): # 출발점과 이어진 노드들이 더이상 없다면
        break
    

print(costs[end])