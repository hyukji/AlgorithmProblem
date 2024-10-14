import sys
from collections import defaultdict

n, m, d = map(int, input().split())

boxes = []
start, end = -1, 0
for _ in range(m):
    s, e, t = map(int, input().split())
    cnt = (e - s) // t + 1
    boxes.append([s, e, t, cnt])
    end = max(end, e)

boxes.sort()


while start + 1 < end:
    mid = (end + start) // 2
    cnt = 0
    for s, e, t, v in boxes:
        if mid < s:
            break
        if e <= mid:            
            cnt += v
            continue

        cnt += (mid - s) // t + 1
    
    if d <= cnt:
        end = mid
    else:
        start = mid

print(end)