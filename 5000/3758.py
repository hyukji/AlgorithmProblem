from heapq import heappush, heappop

import sys
input=sys.stdin.readline


T = int(input())
for _ in range(T):
    n, k, t, m = map(int, input().split())
    
    score = [[0 for _ in range(k+1)] for _ in range(n+1)] # id 별로 점수 합
    n_log = [0 for _ in range(n+1)] # id별로 log 개수
    logs = [] # 로그들 id만 따로 저장

    for _ in range(m):
        i, j, s = map(int, input().split())
        score[i][j] = max(score[i][j], s)
        n_log[i] += 1
        logs.append(i)
    

    result = []
    for i in range(n+1):
        # 가장 큰 값을 pop 시키기 위해서 음수로 저장 [score, id]
        heappush(result, [(-1) * sum(score[i]), i])


    # t보다 순위 높은 팀 나올때마다 rank += 1
    rank = 1
    last_score = 0

    # t팀보다 점수 작은 팀 나올 때까지 same_result_team와 rank변수 관리
    find_t = False
    same_result_team = []
    for _ in range(n):
        _s, _i = heappop(result)
        _s *= (-1)

        if _s != last_score:
            if find_t == True:
                break
            rank += len(same_result_team)
            same_result_team = [_i]
        else:
            same_result_team.append(_i)

        last_score = _s
        if _i == t:
            find_t = True


    # log 나중에 나온 팀 계산
    same_result_team.remove(t)
    logs.reverse()
    for _i in same_result_team:
        if n_log[t] > n_log[_i]:
            rank += 1
        elif n_log[t] == n_log[_i]:
            # 로그 순서 뒤집고 index 함수 사용시 먼저 나오면 더 나중에 불린것
            lt = logs.index(t)
            li = logs.index(_i)
            if lt < li:
                rank+=1

    print(rank)
            
    
    
    

        