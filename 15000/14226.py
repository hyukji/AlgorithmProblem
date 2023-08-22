from collections import deque

s = int(input())

MAX_DP = s*2
dp = [[float('inf') for _ in range(MAX_DP)] for _ in range(MAX_DP)] # 해당 경우까지의 최소 횟수

t = 0
screen = 1
clip = 0

dp[screen][clip] = t

# bfs
dq = deque([(screen, clip)])
while dq:
    t += 1
    for _ in range(len(dq)):
        screen, clip = dq.popleft()

        next_step = [(screen, screen), (screen+clip, clip), (screen-1, clip)]
        for nscreen, nclip in next_step:
            if nscreen == s:
                print(t)
                exit()

            if nscreen < 0 or nscreen >= MAX_DP or nclip < 0 or nclip >= MAX_DP:
                continue

            if dp[nscreen][nclip] == float('inf'):
                dq.append((nscreen, nclip))
                dp[nscreen][nclip] = t
    
    
            
        


            


        
        
        
