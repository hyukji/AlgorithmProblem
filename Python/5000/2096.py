import copy

directions = [[0, 1], [0, 1, 2], [1, 2]]

n = int(input())
first = list(map(int, input().split()))
M = copy.deepcopy(first) 
m = copy.deepcopy(first) 

for _ in range(n-1):
    nrow = list(map(int, input().split()))
    nM = []
    nm = []
    for i in range(3):
        nM.append(max([M[j] for j in directions[i]]) + nrow[i])
        nm.append(min([m[j] for j in directions[i]]) + nrow[i])
        
    M = nM
    m = nm

print(max(M), min(m))