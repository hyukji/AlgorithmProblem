from bisect import bisect_right

T = int(input())
for tc in range(1, T+1):
    n = int(input())
    arr = list(map(int, input().split()))

    len_sub = 0
    sub_value = []
    for v in arr:
        loc = bisect_right(sub_value, v) # 이진탐색을 이용한 풀이
        if(loc == len_sub):
            sub_value.append(v)
            len_sub += 1
        else:
            sub_value[loc] = v

    print(f"#{tc} {len(sub_value)}")
