import sys
input = sys.stdin.readline

n = int(input())
A, B, C, D = [], [], [], []
for _ in range(n):
    a, b, c, d = map(int, input().split())
    A.append(a)
    B.append(b)
    C.append(c)
    D.append(d)

left = dict()
for i in range(n):
    for j in range(n):
        v = A[i] + B[j]
        left[v] = left.get(v, 0) + 1

answer = 0
right = dict()
for i in range(n):
    for j in range(n):
        v = 0 - (C[i] + D[j])
        if v in left:
            answer += left[v]

print(answer)