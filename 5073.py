import sys
input=sys.stdin.readline


while True:
    lst = list(map(int, input().split()))
    if lst[0] == 0:
        break

    lst.sort()
    if lst[0] + lst[1] <= lst[2]:
        print("Invalid")
    elif lst[0] == lst[1] and lst[1] == lst[2]:
        print("Equilateral")
    elif (lst[0] == lst[1] and lst[1] != lst[2]) or (lst[0] != lst[1] and lst[1] == lst[2]):
        print("Isosceles")
    else:
        print("Scalene")
