from itertools import combinations

def solution(friends, gifts):
    n = len(friends)
    
    nameMap = dict()
    for i, name in enumerate(friends):
        nameMap[name] = i
    
    give = [[0 for _ in range(n)] for _ in range(n)]
    tot_give = [0 for _ in range(n)]
    tot_receive = [0 for _ in range(n)]
    for gift in gifts:
        _from, _to = gift.split()
        f = nameMap[_from]
        t = nameMap[_to]
        
        give[f][t] += 1
        tot_give[f] += 1
        tot_receive[t] += 1
    
    result = [0 for _ in range(n)]
    for f, t in combinations([i for i in range(n)], 2):
        if(give[f][t] == give[t][f]):
            fv = tot_give[f] - tot_receive[f] 
            tv = tot_give[t] - tot_receive[t]
            if(fv < tv):
                result[t] +=1
            elif(fv > tv):
                result[f] +=1
        elif(give[f][t] > give[t][f]):
            result[f] += 1
        else:
            result[t] += 1
        
    return max(result)