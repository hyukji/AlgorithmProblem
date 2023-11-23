
import sys
sys.stdin = open('input.txt', 'r')


T = int(input())
for tc in range(1, T + 1):
    n = int(input())
    items = list(map(int, input().split()))
    items.sort()

    items_size = len(items)
    answer = 0
    idx = 0

    while items:
        print(items)
        M = items.pop()
        items_size -= 1
        answer += M

        for plus_idx in range(items_size-1, -1, -1):
            plus_item = items[plus_idx]
            if plus_item < M:
                items.remove(plus_item)
                items_size -= 1
                break
        else:
            if items:
                answer += sum(items)
            break

    print(f"#{tc} {answer}")
