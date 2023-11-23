from collections import deque, defaultdict

# BFS를 활용해 풀었습니다. 단 중복된 경우를 탐색하는 경우를 제외하고자 visited에 탐색 여부를 저장했습니다.
T = int(input())
for tc in range(1, T + 1):
    n, a, b = map(int, input().split())
    scores = list(map(int, input().split()))
    scores.sort()

    visited = defaultdict(bool)

    answer = 0
    mid = deque(scores)

    M = mid.pop()
    while M == mid[-1]:
        mid.pop()
    m = mid.popleft()
    while m == mid[0]:
        mid.popleft()

    dq = deque([mid])
    visited[str(set(mid))] = True

    while dq:
        print(dq)
        mid = dq.popleft()
        mid_size = len(mid)
        if mid_size <= b:
            answer = max(answer, mid_size)
            continue

        new_mid = mid.copy()
        M = new_mid.pop()
        while new_mid:
            if M != new_mid[-1]:
                break
            new_mid.pop()

        if a <= len(new_mid) and not visited[str(set(new_mid))]:
            visited[str(set(new_mid))] = True
            dq.append(new_mid)

        new_mid = mid.copy()
        m = new_mid.popleft()
        while new_mid:
            if m != new_mid[0]:
                break
            new_mid.popleft()

        if a <= len(new_mid) and not visited[str(set(new_mid))]:
            visited[str(set(new_mid))] = True
            dq.append(new_mid)

    print(f"#{tc} {answer}")
