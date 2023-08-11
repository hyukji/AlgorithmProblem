import sys
from itertools import permutations

input = sys.stdin.readline
n = int(input())
graph = []
for _ in range(n):
    graph.append(list(map(int, sys.stdin.readline().split())))
    
    
answer = 0
ps= permutations(range(1, 9), 8) # 선수들 배치 가능한 경우의 수 -> 순열
for p in ps:
    seq = list(p)
    seq.insert(3, 0) # 4번째 타자는 0번.
    
    p_answer, i, inning = 0, 0, 0
    while inning < n:
        f, s, t = 0, 0, 0 # 1루, 2루, 3루
        out = 0 # out 경우의 수
        while out < 3:
            hit = graph[inning][seq[i]] # hit 결과.
            
            i = (i + 1) % 9 # 선수들 싸이클이 다 돌면 다시 처음부터.
            
            if hit == 0:
                out += 1
            else:
                if hit == 1:
                    p_answer += t
                    f, s, t = 1, f, s
                elif hit == 2:
                    p_answer += (s+t)
                    f, s, t = 0, 1, f
                elif hit == 3:
                    p_answer += (f+s+t)
                    f, s, t = 0, 0, 1
                elif hit == 4:
                    p_answer += (f+s+t+1)
                    f, s, t = 0, 0, 0
        out = 0
        inning += 1
    answer = max(answer, p_answer)

print(answer)