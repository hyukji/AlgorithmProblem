from collections import defaultdict
from heapq import heappop, heappush

n = int(input()) # 노드 개수
m = int(input()) # edge 개수

graph = defaultdict(list) # 출발점과 도착점 사이의 w 저장.
for _ in range(m):
    s, e, v = map(int, input().split())
    graph[s].append((v, e))
start, end = map(int, input().split()) # 출발지점, 도착지점

costs = [float('inf') for _ in range(n+1)]
costs[start] = 0

pq = []
heappush(pq, (0, start))
costs[start] = 0

while pq:
    cur_cost, cur_city = heappop(pq) # 아직 계산하지 않은 최단 거리

    if cur_cost > costs[cur_city]: # 해당 노드에 더 작은 최단 거리로 방문한 적이 있다면 continue
        continue

    for new_cost, new_city in graph[cur_city]: # 이어진 노드들
        cost = cur_cost + new_cost # 해당 노드를 이용한 거리 계산
        if cost < costs[new_city]: # 이전에 계산된 거리보다 작다면 최단 경로에 저장 및 pq에 추가
            costs[new_city] = cost
            heappush(pq, (cost, new_city))
        
print(costs[end])