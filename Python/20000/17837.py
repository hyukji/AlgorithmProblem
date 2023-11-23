n, k = map(int, input().split())

chess = []
for _ in range(n):
    chess.append(list(map(int, input().split())))
    
graph = [[[] for _ in range(n)] for _ in range(n)] # 어떤 종류의 말이 있는 지. 순서대로.
pieces = [] # 말 별로 정보 저장. y, x, d
for i in range(k):
    y, x, d = map(int, input().split())
    graph[y-1][x-1].append(i)
    pieces.append([y-1, x-1, d-1])
    
directions = [(0, 1), (0, -1), (-1, 0), (1, 0)]
reverse_d = [1, 0, 3, 2]

answer = -1
turn = 1
while turn <= 1000 and answer == -1:
    for p in range(k):
        y, x, d = pieces[p]
        dy, dx = directions[d]
        ny, nx = y + dy, x + dx
        
        type = 2 # 이동할 체스판의 색깔
        if 0 <= ny < n and 0 <= nx < n:
            type = chess[ny][nx]
        
        if type == 2: # BLUE or OUTSIDE
            d = reverse_d[d]
            pieces[p][2] = d
            
            dy, dx = directions[d]
            ny, nx = y + dy, x + dx
            if 0 <= ny < n and 0 <= nx < n:
                type = chess[ny][nx]
        
        if type != 2:
            for i in range(len(graph[y][x])):
                if graph[y][x][i] == p:
                    dummy = graph[y][x][i:]
                    graph[y][x] = graph[y][x][:i]
                    break
            
            for i in dummy:
                pieces[i][0], pieces[i][1] = ny, nx # 말 정보 업데이트
            
            if type == 1: # RED
                dummy = reversed(dummy)
                
            graph[ny][nx] += dummy # 체스판 정보 업데이트
            
        
        # 새로 업데이트된 곳에 말 4개 이상인지 확인.
        if 0 <= ny < n and 0 <= nx < n:
            if len(graph[ny][nx]) >= 4:
                answer = turn
                break
    
    turn += 1
    
print(answer)

    
