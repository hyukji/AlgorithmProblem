from collections import defaultdict

n = int(input())
x, y = list(map(int, input().split()))

m = int(input())
graph = defaultdict(list)
for _ in range(m):
    a, b = list(map(int, input().split()))
    graph[a] += [b]
    graph[b] += [a]

stack = [x]
visited = [-1 for _ in range(n+1)]
visited[x] = 0
while stack:
    now = stack.pop()
    val = visited[now]

    if now == y:
        break
    
    for i in graph[now]:
        if visited[i] == -1:
            stack.append(i)
            visited[i] = (val + 1)
            if i == y:
                break

print(visited[y])
    
        
        