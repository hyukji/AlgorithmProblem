from collections import defaultdict

def solution(n, results):
    winners = defaultdict(set)
    losers = defaultdict(set)
    
    for a, b in results:
        losers[a].add(b)
        winners[b].add(a)

    answer = 0
    for a in range(1, n+1):
        for w in winners[a]:
            losers[w].update(losers[a])
        for l in losers[a]:
            winners[l].update(winners[a])
            
    for a in range(1, n+1):
        result = len(winners[a]) + len(losers[a]) +1
        if result == n:
            answer+=1
            
        
    return answer