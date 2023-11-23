from collections import deque

n, k = map(int, input().split())
arr = [-1 for _ in range(100001)]

# bfs를 이용 -> 최소 시간에 찾는 방법을 모두 구하기 위해서.
dq = deque([n])
t, cnt = 0, 0 
while dq:
    for _ in range(len(dq)):
        i = dq.popleft()
        if i == k:
            cnt += 1
        
        arr[i] = t # 해당 위치에 들어갈 수 있는 최소 시간

        for ni in [i+1, i-1, i*2]: # 이동 가능한 경우의 수
            if 0 <= ni < 100001 and arr[ni] == -1:
                dq.append(ni) # 이동.
                
    if cnt != 0: # 하나라도 방법을 찾았다면!
        print(t)
        print(cnt)
        break

    t+=1