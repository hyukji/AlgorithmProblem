N = int(input())

graph = [[] for _ in range(N)]
for _ in range(N-2):
    s, e = map(int, input().split())
    s, e = min(s, e), max(s, e)
    
    graph[s-1].append(e-1)
    graph[e-1].append(s-1)
    
bridge = [0 for _ in range(N)]
stack = [0]
bridge[0] = 1
while stack:
    v = stack.pop()
    for nv in graph[v]:
        if bridge[nv] == 0:
            bridge[nv] = 1
            stack.append(nv)

for i in range(N):
    if bridge[i] == 0:
        print(1, i+1)
        break
