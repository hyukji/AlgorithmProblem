import sys
sys.setrecursionlimit(10 ** 4)

nodes = []
while True:
    try:
        num = int(input())
        nodes.append(num)
    except:
        break

def getSubTree(limit, now, s):
    if s >= n:
        print(now)
        return now, s
    
    new = nodes[s]
    if new < now: # left sub
        new, s = getSubTree(now, new, s+1)
    if new > now and new < limit: # right sub
        new, s = getSubTree(limit, new, s+1)

    print(now) 
    return new, s

limit = 1000000
now = nodes[0]
n = len(nodes)
getSubTree(limit, now, 1)