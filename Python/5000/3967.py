from itertools import permutations 

graph = [[0, 2, 5, 7], [1, 2, 3, 4], [0, 3, 6, 10], [4, 6, 9, 11],[7, 8, 9, 10], [1, 5, 8, 11]]

map = []
loc_arr = [] # x인 위치
num_arr = [i for i in range(1, 13)] # 사용하지 않은 문자

def validate():
    for row in graph:
        s = 0
        for el in row:
            s += map[el]
        if s != 26:
            return False
    return True

# input을 int로 바꾸어 저장.
loc = 0
for _ in range(5):
    for alpha in input().replace('.', ''):
        o = ord(alpha) - 64
        if o == 56:
            o = 0
            loc_arr.append(loc)
        else:
            num_arr.remove(o)
        map.append(o)
        loc += 1

# 가능한 순열 조합을 찾아 해당 방법이 validate한지 확인
cnt = len(num_arr)
perms = permutations(num_arr, cnt)
for per in perms:
    for i in range(cnt):
        map[loc_arr[i]] = per[i]
        
    if validate() == True:
        break


# 문자로 바꾸어 출력
for i, num in enumerate(map):
    o = chr(num+ 64) 
    map[i] = o

print("...." + map[0] + "....")
print("." + map[1] + "." + map[2] + "." + map[3] + "." + map[4] + ".")
print(".." + map[5] + "..." + map[6] + "..")
print("." + map[7] + "." + map[8] + "." + map[9] + "." + map[10] + ".")
print("...." + map[11] + "....")