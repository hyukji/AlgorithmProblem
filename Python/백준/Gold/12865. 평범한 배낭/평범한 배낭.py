import sys
input = sys.stdin.readline

n, k = map(int, input().split())
data = [list(map(int, input().split())) for _ in range(n)] # w, v를 모아둔다.
data.sort(reverse=True)

d = {0:0}
for dw, dv in data:
    tmp = dict()
    for v, w in d.items():
        nv = v + dv
        nw = w + dw
        if(d.get(nv, k+1) > nw):
            tmp[nv] = nw
    d.update(tmp)

print(max(d.keys()))