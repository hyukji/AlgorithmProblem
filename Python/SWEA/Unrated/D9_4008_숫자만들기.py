import sys
import math
sys.stdin = open("input.txt", "r")
sys.setrecursionlimit(10**6)

opts = ["+", "-", "*", "/"]

def dfs(res, cnt, opt_count, nums, n):
    if(cnt == n):
        # print("res", cnt, res)
        global min_result, max_result
        min_result = min(min_result, res)
        max_result = max(max_result, res)
        return
    
    for i in range(4):
        if(opt_count[i] == 0):
            continue

        opt = opts[i]
        opt_count[i] -= 1
        dfs(math.trunc(eval(f"{res}{opt}{nums[cnt]}")), cnt+1, opt_count, nums, n)
        opt_count[i] += 1

    return

T = int(input())
for tc in range(1, T+1):
    n = int(input())
    opt_count = list(map(int, input().split()))
    nums = list(input().split())

    global min_result, max_result
    min_result = float("inf")
    max_result = -float("inf")

    dfs(nums[0], 1, opt_count, nums, n)
    print(f"#{tc} {max_result - min_result}")

