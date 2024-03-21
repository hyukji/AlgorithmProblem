import sys
input = sys.stdin.readline

from itertools import combinations
from collections import Counter

def getArr(values):
    nArr = []
    for i in range(1, len(values)+1):
        for comb in combinations(values, i):
            nArr.append(sum(comb))
    return nArr

n, s = map(int, input().split())
arr = list(map(int, input().split()))

mid = n//2
left = Counter(getArr(arr[:mid]))
right = Counter(getArr(arr[mid:]))

answer = 0
for l in left.keys():
    r = s - l
    answer += left[l] * right[r]

answer += left[s] + right[s]

print(answer)