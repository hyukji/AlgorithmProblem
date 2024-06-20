from itertools import product

def purchase(user, emoticons, sales):
    money = 0
    
    for i, sale in enumerate(sales):
        if sale >= user[0]:
            money += emoticons[i] * (100 - sale) / 100
    
    if money < user[1]:
        return False, money
    
    return True, 0

def solution(users, emoticons):
    percent, price = zip(*users)
    n = len(users)
    m = len(emoticons)
    
    maxCount = 0
    maxMoney = 0
    for sale in product([10, 20,30, 40], repeat=m):
        count, money = 0, 0
        for i in range(n):
            plusService, userMoney = purchase(users[i], emoticons, sale)
            if plusService == True:
                count += 1
            else:
                money += userMoney
        
        if maxCount < count:
            maxCount = count
            maxMoney = money
        elif maxCount == count and maxMoney < money:
            maxMoney = money
        
    return [maxCount, maxMoney]