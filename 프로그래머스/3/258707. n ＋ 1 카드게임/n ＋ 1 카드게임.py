def solution(coin, cards):
    n = len(cards)
    target = n+1
    initial = n//3
    
    isFirst = [False for _ in range(n+1)]
    visited = [False for _ in range(n+1)]
    canBuy = 0
    canPlay = 1
    for i in range(initial):
        card = cards[i]
        pair = target - card
        isFirst[card] = True
        
        if(isFirst[pair]):
            canPlay += 1 
    
    round = 0
    while(canPlay > 0):
        canPlay -= 1
        
        idx = initial + (round * 2)
        if(idx >= n):
            break
            
        # 첫번째 카드
        card1 = cards[idx]
        card2 = cards[idx+1]
        visited[card1] = True
        visited[card2] = True
        
        pair1 = target - card1
        pair2 = target - card2
        if(coin > 0 and isFirst[pair1]):
            canPlay += 1
            coin -= 1
        if(coin > 0 and isFirst[pair2]):
            canPlay += 1
            coin -= 1
        
        if(pair1 == card2):
            canBuy += 1
        else:
            if(visited[pair1]):
                canBuy+=1
            if(visited[pair2]):
                canBuy+=1
        
        if(canPlay == 0):
            if(canBuy > 0 and coin > 1):
                canBuy -= 1
                canPlay += 1
                coin -= 2
            else:
                break
        
        round += 1
        
    return round + 1