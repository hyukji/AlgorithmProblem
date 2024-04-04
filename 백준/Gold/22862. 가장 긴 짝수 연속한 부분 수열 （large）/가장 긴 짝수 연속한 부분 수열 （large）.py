import sys
input = sys.stdin.readline

n, m = map(int, input().split())
arr = list(map(int, input().split()))

cnt = 0
bef_is_even = False
for s in range(n):
    if arr[s] % 2 == 0:
        cnt = 1
        bef_is_even = True
        break
    
even = []
odd = []
for i in range(s+1, n):
    if(bef_is_even == (arr[i] % 2 == 0)):
        cnt+=1
        continue
    
    if(bef_is_even):
        even.append(cnt)
    else:
        odd.append(cnt)

    cnt = 1
    bef_is_even = not bef_is_even
        
if(bef_is_even):
    even.append(cnt)
    

sum_even = [0]
for e in even:
    sum_even.append(e + sum_even[-1])
    
sum_odd = [0]
for o in odd:
    sum_odd.append(o + sum_odd[-1])
    
answer = 0
if even:
    answer = max(even)
    
s = 0
e = 1
max_odd = len(sum_odd)
while(e < max_odd):
    odd_cnt = sum_odd[e] - sum_odd[s]
    if odd_cnt > m:
        s += 1
        continue
    
    e += 1
    answer = max(answer, sum_even[e] - sum_even[s])

print(answer)