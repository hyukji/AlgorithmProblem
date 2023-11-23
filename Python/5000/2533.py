from collections import deque

n = int(input())
graph = [[] for _ in range(n+1)]
visited = [0 for _ in range(n+1)]
for _ in range(n-1):
    u, v = map(int, input().split())
    graph[u].append(v)
    graph[v].append(u)

depth = 0
answer = [0, 0]
dq = deque([1])
while dq:
    depth += 1
    answer[depth % 2] += len(dq)

    for _ in range(len(dq)):
        node = dq.popleft()
        children = graph[node]

        for next_node in children:
            if visited[next_node] == 1: # 부모일 수도 있어서 체크
                continue

            visited[next_node] = 1
            dq.append(next_node)

print((answer))