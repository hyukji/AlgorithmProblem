import sys
input=sys.stdin.readline

T = int(input())
for _ in range(T):
    N = int(input())
    lst = list(map(int, input().split()))

    score = 0 # 주식 결과
    cnt = 0 # 구매한 주식의 수
    M = lst[N-1]
    for i in reversed(range(N-1)):
        v = lst[i]
        # 뒤의 주식 가격이 더 비싸다면 구매하고 뒤에 더 비싼 주식이 나오지 않으면 판매
        if v < M:
            # 구매
            cnt += 1
            score -= v
        elif v > M:
            # 판매
            score += (M*cnt)
            cnt = 0
            M = v
    else:
        # 판매
        score += (M*cnt)

    print(score)



