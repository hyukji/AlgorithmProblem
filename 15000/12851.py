from collections import deque

n, k = map(int, input().split())
arr = [-1 for _ in range(100001)]

# bfs를 이용
dq = deque([n])
t, cnt = 0, 0
while dq:
    for _ in range(len(dq)):
        i = dq.popleft()
        if i == k:
            cnt += 1
        
        arr[i] = t
        for ni in [i+1, i-1, i*2]:
            if 0 <= ni < 100001 and arr[ni] == -1:
                dq.append(ni)
                
    if cnt != 0:
        print(t)
        print(cnt)
        break
    t+=1