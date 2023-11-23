import sys
sys.setrecursionlimit(10 ** 4)

nodes = []
while True:
    try:
        num = int(input())
        nodes.append(num)
    except:
        break


# s, e까지 tree 형태를 가지고 있음.
# s, e안에서 left subtree, right subtree를 구해서 재귀반복
def getSubTree(s, e):
    if s >= e:
        return 
    
    root = nodes[s]

    # left와 right 나누기.
    division = e
    for i in range(s+1, e):
        if root < nodes[i]:
            division = i
            break

    getSubTree(s+1, division) # left subtree
    getSubTree(division, e) # right subtree
    
    print(root)
    
    return

limit = 1000000
n = len(nodes)

getSubTree(0, n)