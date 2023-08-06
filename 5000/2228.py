import sys
input=sys.stdin.readline

n, m = map(int, input().split())
arr = [int(input()) for _ in range(n)]

# 다음 숫자를 구간에 포함시킨 경우와 제외시킨 경우, [i][j] = 0~i까지 j 구간을 설정했을 때 최대값 저장
contain = [[-float("inf") for _ in range(m+1)] for _ in range(n)]
notContain = [[-float("inf") for _ in range(m+1)] for _ in range(n)]

# 0번째 하나에 구간 1개 이므로 해당 값이 최대값
contain[0][1] = arr[0]

# 점화식에서 notContain[i-1][j-1] + arr[i] 값이 필요하기 때문에 notContain, j=0일 때 -float("inf")가 아닌 0 대입
notContain[0][0] = 0
for i in range(1, n):
    notContain[i][0] = 0
    for j in range(1, min(m, (i+2)//2) + 1):
        contain[i][j] = max(contain[i-1][j], notContain[i-1][j-1]) + arr[i]
        notContain[i][j] = max(contain[i-1][j], notContain[i-1][j])

print(max(contain[n-1][m], notContain[n-1][m]))