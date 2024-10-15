import sys

n = int(input())
arr = list(map(int, input().split()))
arr.sort()

answer = 0
for i in range(n):
    v = arr[i]
    s, e = 0, n-1
    
    while s < e:

        nv = arr[s] + arr[e]
        if nv == v:
            if s == i:
                s += 1
            elif e == i:
                e -= 1
            else:
                answer += 1
                break
        elif nv < v:
            s += 1
        else:
            e -= 1

print(answer)