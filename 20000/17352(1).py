N = int(input())
graph = [i for i in range(N)]

def findRoot(i):
    while graph[i] != i:
        i = graph[i]
        
    return i

for _ in range(N-2):
    s, e = map(int, input().split())
    s, e = s-1, e-1
    
    r1, r2 = findRoot(s), findRoot(e)
    root = min(r1, r2)
    
    graph[r1] = root
    graph[r2] = root
    
for i in range(N):
    if 0 != findRoot(i):
        print(1, i+1)
        break


"""
8
1 2
3 4
3 5
7 8
6 7
5 2 
"""  
 