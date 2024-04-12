import sys
sys.setrecursionlimit(10**9)
input = sys.stdin.readline

def preorder(inorder_s, inorder_e, postorder_s, postorder_e):
    # 재귀함수 종료 조건
    if (inorder_s > inorder_e) or (postorder_s > postorder_e):
        return
    
    # 후위 순회의 마지막 노드 출력
    p = postorder[postorder_e]
    print(p, end =' ')
    
    # 후위 순회의 마지막 값이 중위 순회에 어디에 위치 하는지 구했던 리스트가 여기서 사용
    left = inorder_pos[p] - inorder_s
    right = inorder_e - inorder_pos[p]
    
    # 왼쪽 서브 트리
    preorder(inorder_s, inorder_s+left-1, postorder_s, postorder_s+left-1)
    # 오른쪽 서브 트리
    preorder(inorder_e - right + 1, inorder_e, postorder_e - right, postorder_e - 1)

n = int(input())
inorder = list(map(int, input().split()))
postorder = list(map(int, input().split()))
inorder_pos = [0] * (n+1)

# 후위순회의 마지막값이 중위순회 몇번째에 존재하는지 알기 위해 사용. 범위를 나누는 기준!
for i in range(n):
    inorder_pos[inorder[i]] = i
    
preorder(0, n-1, 0, n-1)