def toBool(v): # 스위치 on/off에 대해 boolean
    return True if v == "1" else False

n = int(input())
initial = list(map(toBool, list(input()))) 
goal = list(map(toBool, list(input())))

first = True 
answer = float('inf')
for _ in range(2):
    cnt = 0 # 스위치 on/off
    cur = initial.copy() 

    first = not first # 처음 전구 스위치 여부
    if first:
        cnt += 1
        cur[0] = not cur[0]
        cur[1] = not cur[1]
    
    for i in range(1, n-1): # i번째 스위치 이후 i-1 전구는 goal과 같아야함.
        if cur[i-1] != goal[i-1]:
            cnt += 1
            cur[i-1] = not cur[i-1]
            cur[i] = not cur[i]
            cur[i+1] = not cur[i+1]

    if cur[-2:] != goal[-2:]: # 마지막 스위치 on/off 판단
        cnt += 1
        cur[-2] = not cur[-2]
        cur[-1] = not cur[-1]

    if cur == goal:
        answer = min(answer, cnt)

answer = answer if answer != float('inf') else -1
print(answer)