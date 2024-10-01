from collections import defaultdict
import sys

input = sys.stdin.readline

n, m = map(int, input().split())
arr = list(map(int, input().split()))
answer = 0
ddict = defaultdict(int)

sum_arr = [0]
for i in range(n):
    r = (sum_arr[-1] + arr[i]) % m
    sum_arr.append(r)
    ddict[r] += 1

for k in ddict.keys():
    v = ddict[k]
    answer += (v * (v-1) // 2)

answer += ddict[0]

print(answer)