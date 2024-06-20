from itertools import product

def solution(users, emoticons):
    m = len(emoticons)
    cases = product([10, 20, 30, 40], repeat=m)
    
    answer = [0, 0]
    for case in cases:
        result = [0, 0]
        for percent, money in users:
            cost = 0
            for i, sale in enumerate(case):
                if percent <= sale:
                    cost += (emoticons[i] * (100 - sale) * 0.01)
            
            if cost >= money:
                result[0] += 1
            else:
                result[1] += cost
                
        answer = max(result, answer)
    
    return answer