from collections import defaultdict
from itertools import combinations

n, m = map(int, input().split())
arr = list(map(int, input().split()))

_dict = defaultdict(int)
_dict[0] += 1

arr[0] %= m 
_dict[arr[0]] += 1
for i in range(1, n):
    arr[i] = (arr[i] + arr[i-1]) % m
    _dict[arr[i]] += 1
    
cnt = 0
for key in _dict.keys():
    v = _dict[key]
    cnt += (v * (v-1) // 2) 

print(cnt)