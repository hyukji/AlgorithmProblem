from collections import deque

def move(loc, short, step):
    if short == 0:
        loc += step*2
        if loc > 40:
            loc = 0
        return loc
    else:
        for _ in range(step):
            if loc == 0:
                return 0
            loc = shortcut[loc]
        return loc


steps = list(map(int, input().split()))
shortcut = {
    10: 13, 13: 16, 16: 19, 19: 25, 
    20: 22, 22: 24, 24: 25,
    30: 28, 28: 27, 27: 26, 26:25,
    25: 30, 30: 35, 35: 40,
    40: 0}

# pieces = [[0, 0, 0]] # loc, score, shortcut
dq = deque([[0, 4, []]])

for step in steps:
    for _ in range(len(dq)):
        tot, start, pieces = dq.popleft()

        if start > 0: 
            loc = move(0, 0, step)
            score = loc
            short = 1 if loc % 10 == 0 else 0

            for nloc, nscore, nshort in pieces: # 기존 말들과 겹치지 않는 지
                if nloc == loc and nshort == short:
                    break
            else:
                if loc == 0:
                    dq.append([tot+score, start-1, pieces])
                else:
                    dq.append([tot, start-1, pieces + [[loc, score, short]]])

        for i in range(len(pieces)):
            loc, score, short = pieces[i]

            loc = move(loc, short, step)
            
            score += loc
            if loc == 0:
                dq.append([tot + score, start, pieces[:i] + pieces[i+1:]])
                continue

            if short == 0 and loc % 10 == 0:
                short = 1

            for nloc, nscore, nshort in pieces: # 기존 말들과 겹치지 않는 지
                if nloc == loc and nshort == short:
                    break
            else:
                dq.append([tot, start, pieces[:i] + pieces[i+1:] + [[loc, score, short]]])

    print(dq)

answer = 0
for tot, _, _ in dq:
    print(tot)
    answer = max(answer, tot)
print(answer)
            

    

