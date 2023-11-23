import sys
sys.setrecursionlimit(10**5)

def printAnswer():
    print()
    for row in graph:
        print(*row)

def dfs(num):
    if num == 81:
        printAnswer()
        exit()

    c, r = num // 9, num % 9

    if graph[c][r] != 0:
        dfs(num+1)

    for el in range(1, 10): # c, r 에 들어갈 선정
        exist = False

        for i in range(9):
            if graph[i][r] == el or graph[c][i] == el:
                exist = True
                break

        cs, rs = (c//3)*3, (r//3)*3
        for cc in range(cs, cs+3):
            for rr in range(rs, rs+3):
                if graph[cc][rr] == el:
                    exist = True
        
        if exist:
            continue
        
        graph[c][r] = el 
        dfs(num+1) # 다음 위치로 이동
        graph[c][r] = 0 # 다시 숫자 탐색 전 해당 위치를 0으로 재설정



graph = []
for _ in range(9):
    row = list(map(int, input().split()))
    graph.append(row)

dfs(0)

printAnswer()
exit()



