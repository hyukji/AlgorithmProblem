import sys
from collections import deque
input = sys.stdin.readline


n, k = map(int, input().split())
listN = []
hasSame = False
for i in str(n):
    if int(i) in listN:
        hasSame = True
    listN.append(int(i))
m = len(listN)

def bfs():
    if m == 1:
        return [[-1]]

    i = 0
    dq = deque([(listN, 0)])
    answer = []

    while dq:
        for _ in range(len(dq)):
            arr, cnt = dq.popleft()

            if cnt == k:
                answer.append(arr)
                continue

            if i >= m:
                if hasSame:
                    answer.append(arr)
                else:
                    now = arr[-1]
                    arr[-1] = arr[-2]
                    arr[-2] = now
                    if arr[0] != 0:
                        dq.append((arr, cnt+1))
                continue

            ListJ = []
            M = arr[i] + 1
            for jj in range(i+1, m):
                if arr[jj] > M:
                    M = arr[jj]
                    ListJ = [jj]
                elif arr[jj] == M:
                    ListJ.append(jj)

            if len(ListJ) == 0:
                dq.append((arr, cnt))

            for j in ListJ:
                newArr = arr[:]
                now = newArr[i]
                newArr[i] = newArr[j]
                newArr[j] = now
                dq.append((newArr, cnt+1))

        i += 1

    return answer

answer = -1
result = bfs()
for arr in result:
    s = "".join(map(str, arr))
    answer = max(answer, int(s))

print(answer)


