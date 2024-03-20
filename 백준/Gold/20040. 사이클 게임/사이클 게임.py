import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

def find(e):
    if(parents[e] == e):
        return e
    
    parents[e] = find(parents[e])
    return parents[e]

def union(a, b):
    pa = find(a)
    pb = find(b)
    if(pa == pb):
        return True
    
    parents[pa] = pb
    return False
    

n, m = map(int, input().split())
parents = [i for i in range(n)]
for i in range(m):
    a, b = map(int, input().split())
    if(union(a, b)):
        print(i+1)
        break
else:
    print(0)