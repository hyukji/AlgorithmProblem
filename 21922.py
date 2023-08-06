n, m = map(int, input().split())

check = [[0 for _ in range(m)] for _ in range(n)] 
graph = []
aircon = []
for c in range(n):
    row = list(map(int, input().split()))
    graph.append(row)
    for r in range(m):
        if row[r] == 9:
            aircon.append((c, r))
            check[c][r] = 15

directions = [(-1, 0), (0, 1), (1, 0), (0, -1)]
aircon_direction = [[], [0, 3, 2, 1], [2, 1, 0, 3], [1, 0, 3, 2], [3, 2, 1, 0]]
stack = [(c, r, d) for c, r in aircon for d in range(4)]

while len(stack) > 0:
    y, x, d = stack.pop()
    dy, dx = directions[d]
    ny, nx = y + dy, x + dx

    if 0 <= ny < n and 0 <= nx < m:
        if check[ny][nx] & (1 << d) > 0:
            continue

        check[ny][nx] |= (1 << d)
        num_aircon = graph[ny][nx]
        if num_aircon == 0 or num_aircon == 9:
            stack.append((ny, nx, d))
        else:
            nd = aircon_direction[num_aircon][d]
            stack.append((ny, nx, nd))

answer = 0
for c in range(n):
    for r in range(m):
        if check[c][r] > 0:
            answer += 1

print(answer)