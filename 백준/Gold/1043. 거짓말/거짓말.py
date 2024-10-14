import sys
from collections import deque

n, m = map(int, input().split())

arr = list(map(int, input().split()))
truth = set(arr[1:])

parties = []
for _ in range(m):
    arr = list(map(int, input().split()))
    parties.append(arr[1:])

dq = deque([i for i in range(m)])
while True:
    hasUpdate = False
    size = len(dq)
    for _ in range(size):
        idx = dq.popleft()
        arr = parties[idx]

        hasTruth = False
        for v in arr:
            if v in truth:
                hasTruth = True
                break
        
        if(hasTruth):  
            truth.update(arr)
            hasUpdate = True
            continue

        dq.append(idx)

    if not hasUpdate:
        break


print(len(dq))