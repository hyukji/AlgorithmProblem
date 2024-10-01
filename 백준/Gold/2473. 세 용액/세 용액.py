import sys

n = int(input())
arr = list(map(int, input().split()))
arr.sort()

answer = 4000000000
ra, rb, rc = 0, 0, 0

# print(arr)

for i in range(0, n-2):
    v = arr[i]
    s = i + 1
    e = n - 1
    while(s < e):
        nv = v + arr[s] + arr[e]
        if(abs(nv) < answer):
            answer = abs(nv)
            ra, rb, rc = v, arr[s], arr[e]
        
        if(nv < 0):
            s += 1
        elif nv > 0:
            e -= 1
        else:
            break

    # print(v, arr[s], arr[e])
    
    if(answer == 0):
        break
        

print(ra, rb, rc)