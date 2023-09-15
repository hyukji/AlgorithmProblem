from math import sqrt

n = int(input())
arr = [0 for _ in range(n+1)]

primes_sum = [0]
m = max(2, int(sqrt(n)) + 1)
for i in range(2, m):
    if arr[i] == 1: # 이전에 방문했다면
        continue

    for j in range(i*2, n+1, i): # i 배수를 방문 처리
        arr[j] = 1

    primes_sum.append(primes_sum[-1] + i) # 누적합

for i in range(m, n+1): # 남은 숫자에서도 소수 찾기.
    if arr[i] == 0:
        primes_sum.append(primes_sum[-1] + i)

left, right, answer = 0, 1, 0
while right != len(primes_sum):
    v = primes_sum[right] - primes_sum[left]
    if v == n:
        answer += 1

    if v > n:
        left += 1
    else:
        right += 1

print(answer)