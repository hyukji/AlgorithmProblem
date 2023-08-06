def solution(K, C, D):
    # Implement your solution here
    answer = 0

    clean = [0 for _ in range(51)]
    dirty = [0 for _ in range(51)]
    
    for sock in C:
        clean[sock] += 1
        if clean[sock] == 2:
            answer += 1
            clean[sock] = 0

    if K == 0:
        return answer
    

    for sock in D:
        if clean[sock] == 1:
            K -= 1
            answer += 1
            clean[sock] = 0
            if K == 0:
                return answer
        else:
            dirty[sock] += 1
        
    K = K // 2
    if K == 0:
        return answer

    for dsock in range(51):
        pair = dirty[dsock] // 2
        if pair > 0:
            laundry_pair = min(K, pair)
            K -= laundry_pair
            answer += laundry_pair
            if K == 0:
                return answer
            
    return answer
            
print(solution(5, [1, 1, 2], [2, 2, 3]) )