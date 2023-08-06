arr = []
n, m = map(int, input().split())

for i in range(n):
    w, c = map(int, input().split())
    arr.append((w, c))

# c 오름차순, w 내림차순
arr.sort(key = lambda x: (x[1], -x[0]))

answer = float('inf')
c, cost, weight = 0, 0, 0
for nw, nc in arr:
    weight += nw
    
    # 새로운 c가 들어왔을 때
    if c != nc:
        c = nc
        cost = c
        
        # m 조건 만족하는 새로운 c면 로직 종료
        if m <= weight:
            answer = min(answer, cost)
            break
    else:
        cost += nc
        
        # 같은 c의 덩어리들을 샀을 경우 -> 새로운 c의 덩어리랑 비교필요
        if m <= weight:
            answer = min(answer, cost)

    print((nw, nc), weight, cost)
    

if answer == float('inf'):
    print(-1)
else:
    print(answer)

"""
10 14
2 3
2 4
2 5
3 1
1 3
7 9
7 3
8 4
10 3
3 10
"""