import sys
input = sys.stdin.readline

from itertools import combinations
from bisect import bisect_left, bisect_right

def getArr(values):
    nArr = []
    for i in range(1, len(values)+1):
        for comb in combinations(values, i):
            nArr.append(sum(comb))
    return sorted(nArr)

n, s = map(int, input().split())
arr = list(map(int, input().split()))

mid = n//2
left = getArr(arr[:mid])
right = getArr(arr[mid:])

answer = 0
for l in left:
    t = s - l
    answer += bisect_right(right, t) - bisect_left(right, t)

answer += bisect_right(left, s) - bisect_left(left, s)
answer += bisect_right(right, s) - bisect_left(right, s)

print(answer)