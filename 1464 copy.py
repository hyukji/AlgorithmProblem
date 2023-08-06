import copy

#문자열을 뒤집어주는 함수
def reverse_string(left, right, iterable):
    global cnt
    cnt += 1
    l = left
    r = right-1
    while l < r:
        temp = iterable[r]
        iterable[r] = iterable[l]
        iterable[l] = temp
        l += 1
        r -= 1
    return iterable

cnt = 0
s = input()
s = list(s)
n = len(s)

s2 = copy.deepcopy(s)

for i in range(0, n-1):
   end = i+1
   # end와 end-1 크기 비교, end와 맨 처음 문자 크기 비교
   if s[i] > s[end] and s[end] <= s[0]:
       reverse_string(0, end, s)
       #뒤집고 난 뒤 end와 end-1을 다시 비교해서 end가 더 작거나 같다면 다시한번 뒤집어 주어야함.
       if s[i] >= s[end]:
           reverse_string(0, end+1, s)
           

# for el in s:
#     print(el, end='')

# print(cnt)

# cnt = 0
# s = s2
for i in range(0, n-1):
   end = i+1
   # end와 end-1 크기 비교, end와 맨 처음 문자 크기 비교
   if s[end] <= s[0]:
        reverse_string(0, end, s)
        reverse_string(0, end+1, s)
                   

for el in s:
    print(el, end='')

