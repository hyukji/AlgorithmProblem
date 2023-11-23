n = int(input())
arr = [0]
for _ in range(n):
    arr.append(int(input()))

lcs = [[0 for _ in range(n+1)] for _  in range(n+1)]
for c in range(1, n+1):
    for r in range(1, n+1):
        el = arr[r]
        
        v = max(lcs[c][r-1], lcs[c-1][r])
        if el == c:
            v += 1
        lcs[c][r] = v
        
print(n - lcs[-1][-1])