from collections import defaultdict

ddict = defaultdict(list)
_in = [0 for _ in range(1000001)]
_out = [0 for _ in range(1000001)]

def getShape(e):
    while(True):
        if(_out[e] > 1):
            return 3
        elif(_out[e] == 0):
            return 2
        elif(ddict[e] == []):
            return 1
        else:
            tmp = ddict[e][0]
            ddict[e] = []
            e = tmp

def solution(edges):
    
    n = len(edges)
    for s, e in edges:
        ddict[s].append(e)
        _out[s] += 1
        _in[e] += 1
        
    start = 0
    for i in range(1000001):
        if(_in[i] == 0 and _out[i] > 1):
            start = i
            break
    
    answer = [start, 0, 0, 0]
    for e in ddict[start]:
        shape = getShape(e)
        answer[shape] += 1
    
    return answer