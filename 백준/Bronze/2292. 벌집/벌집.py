N = int(input())
num = 1
for i in range(N):
    num += i*6 
    if N <= num:
        print(i+1) 
        break