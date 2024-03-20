import sys
input = sys.stdin.readline

from collections import defaultdict, deque

n = int(input())
ddict = defaultdict(list)
for _ in range(n-1):
    a, b = map(int, input().split())
    ddict[a].append(b)
    ddict[b].append(a)

answer = [0 for _ in range(n+1)]
answer[1] = 1

dq = deque()
dq.append(1)
while(dq):
    parent = dq.popleft()
    for child in ddict[parent]:
        if(answer[child] != 0):
            continue

        answer[child] = parent
        dq.append(child)

for i in range(2, n+1):
    print(answer[i])