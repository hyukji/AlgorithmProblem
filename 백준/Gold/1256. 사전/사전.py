import sys
input = sys.stdin.readline

n, m, k = map(int, input().split()) #1 ≤ N, M ≤ 100

factorial = [1]
value = 1
for i in range(1, n+m+1):
    value *= i
    factorial.append(value)

if k > factorial[n+m] // (factorial[n] * factorial[m]):
    # aaazzz 의 경우의 수 => k가 더 크다면 -1
    print(-1) 
else:
    # a aazzz 의 경우의 수  => k가 더 크다면 앞자리 z, k -= 경우의 수
    # za aazz의 경우의 수 => k가 더 작다면 2번째 자리 a 
    # zaa azz의 경우의 수 => ...
    pre = ""
    while(n != 0 or m != 0):
        cnt = factorial[n-1+m] // (factorial[n-1] * factorial[m])
        if cnt < k:
            pre += "z"
            m -= 1
            k -= cnt
        else:
            pre += "a"
            n -= 1
            
    print(pre)