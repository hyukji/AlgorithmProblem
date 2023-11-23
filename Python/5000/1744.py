N = int(input())
answer = 0

p_arr = []
m_arr = []
for _ in range(N):
    v = int(input())
    if v < 1:
        m_arr.append(v)
    elif v > 1:
        p_arr.append(v)
    else: # 1
        answer += 1

p_arr.sort()
m_arr.sort()

m = len(m_arr)
for i in range(m//2):
    answer += (m_arr[2*i] * m_arr[2*i+1])
if m % 2 == 1:
    answer += m_arr[-1]
    
p = len(p_arr)
s = p % 2
if s == 1:
    answer += p_arr[0]
for i in range(p//2):
    answer += (p_arr[s+2*i] * p_arr[s+2*i+1])
    
print(answer)
    