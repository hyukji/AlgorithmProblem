n = int(input())
a = list(map(int, input().split()))
opts = list(map(int, input().split()))

res = []

def dfs(l, ri, now_opts):
    if not sum(now_opts):
        res.append(l)
        return 

    r = a[ri]
    for opt in range(4):
        if now_opts[opt]:
            new_opts = now_opts[:]
            new_opts[opt] -= 1

            if opt == 0:
                dfs(l + r, ri+1, new_opts) 
            elif opt == 1:
                dfs(l - r, ri+1, new_opts) 
            elif opt == 2:
                dfs(l * r, ri+1, new_opts) 
            elif opt == 3: 
                dfs(int(l / r), ri+1, new_opts) 

dfs(a[0], 1, opts)

print(max(res), min(res))


