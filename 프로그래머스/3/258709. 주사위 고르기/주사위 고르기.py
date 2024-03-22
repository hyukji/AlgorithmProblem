from itertools import combinations
from collections import Counter
from bisect import bisect_left, bisect_right


def dfs(dice, comb, i, v, isLeft, arr):
    if(len(comb) == i):
        arr.append(v)
        return
    
    for dv in dice[comb[i]]:
        dfs(dice, comb, i+1, v + dv, isLeft, arr)

def solution(dice):
    n = len(dice)
    arr_size = 6 ** (n//2)
    
    max_value = 0
    answer = []
    for comb in combinations(range(n), n//2):
        left = []
        right = []
        comb2 = [i for i in range(n) if i not in comb]
        dfs(dice, comb, 0, 0, True, left)
        dfs(dice, comb2, 0, 0, False, right)

        win = 0
        lose = 0
        left.sort()
        for r in right:
            lose += bisect_left(left, r) 
            win += arr_size - bisect_right(left, r)

        if(max_value < win):
            max_value = win
            answer = comb
        if(max_value < lose):
            max_value = lose
            answer = comb2
    
    
    return [v+1 for v in answer]