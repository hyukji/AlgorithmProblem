# 해당 알파벳이 i번째라면, i번째 알파벳 + [:i]까지 뒤집기 정렬 + i번째 이후
def orderOfDict(data):
    if len(data) == 0:
        return []
        
    m = 'Z'
    idx = 0
    for i, alpha in enumerate(data):
        if alpha <= m:
            m = alpha
            idx = i

    return [m] + orderOfDict(data[:idx]) + data[idx+1:]

data = list(input())
print("".join(orderOfDict(data)))