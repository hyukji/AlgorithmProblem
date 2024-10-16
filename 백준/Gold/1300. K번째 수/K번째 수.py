import sys

n = int(input())
k = int(input())

e = n*n
s = 0
while s + 1 < e:
    m = (s+e)//2

    cnt = 0
    for r in range(1, n+1):
        cnt += min(n, m // r)

    if cnt < k:
        s = m
    else:
        e = m

print(e)