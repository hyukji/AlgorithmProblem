import sys
from collections import deque
sys.setrecursionlimit(10 ** 6)

input = sys.stdin.readline

def input2Int(v):
    return int(v) - 1

def hasCycle(v):
    _set = set()
    dq = deque()
    
    while v not in _set:
        if visited[v]:
            return 0
        
        visited[v] = True
        _set.add(v)
        dq.append(v)

        v = arr[v]
        
    while dq[0] != v:
        dq.popleft()
        
    return len(dq)

answer = []
tc = int(input())
for _ in range(tc):
    n = int(input())
    arr = list(map(input2Int, input().split()))
    visited = [False for _ in range(n)]


    result = n
    for i in range(n):
        if visited[i]:
            continue

        result -= hasCycle(i)

    answer.append(str(result))

print('\n'.join(answer))