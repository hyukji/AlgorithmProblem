import copy

r,c,t = map(int, input().split())
room = []
air_cleaner = []
for i in range(r):
    row = list(map(int, input().split()))
    if -1 in row:
        air_cleaner.append((i, 0))
    room.append(row)

route = [(0,1), (1,0), (0,-1), (-1,0)]

def diffusion_clean(flag=0):
    dif_room = [[0] * c for _ in range(r)]

    for i in range(r):
        for j in range(c):
            if (i, j) not in air_cleaner:
                cnt = 0
                for move in route:
                    ni = i + move[0]
                    nj = j + move[1]
                    if 0 <= ni < r and 0 <= nj < c and (ni, nj) not in air_cleaner:
                        cnt += 1
                        dif_room[ni][nj] += (room[i][j] // 5)
                dif_room[i][j] += room[i][j] - (room[i][j] // 5) * cnt

    clean_room = copy.deepcopy(dif_room)
    for i in range(c-1):
        clean_room[air_cleaner[0][0]][i+1] = dif_room[air_cleaner[0][0]][i]
        clean_room[air_cleaner[1][0]][i+1] = dif_room[air_cleaner[1][0]][i]

        clean_room[0][i] = dif_room[0][i+1]
        clean_room[-1][i] = dif_room[-1][i+1]

    for j in range(r-1):
        if j < air_cleaner[0][0]: 
            clean_room[j][-1] = dif_room[j+1][-1]
            clean_room[j+1][0] = dif_room[j][0]
        
        if j > air_cleaner[0][0]: 
            clean_room[j+1][-1] = dif_room[j][-1]
            clean_room[j][0] = dif_room[j+1][0]

    clean_room[air_cleaner[0][0]][0] = 0
    clean_room[air_cleaner[1][0]][0] = 0

    if flag == 1:
        res = 0
        for cr in clean_room:
            res += sum(cr)

        print(res)
    else:
        return clean_room

for time in range(t-1):
    room = diffusion_clean()
else:
    diffusion_clean(1)
