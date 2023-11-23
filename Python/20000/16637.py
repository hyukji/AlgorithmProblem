n = int(input())
text = list(input())

oprs, opts = [], []
for i in range(n):
    if i % 2 == 0:
        oprs.append(int(text[i]))
    else:
        opts.append(text[i])

def calculate(opt, left, right):
    if opt == "+": # M, M 
        return left + right
    elif opt == "-":
        return left - right
    elif opt == "*":
        return left * right


def getMm(i, right):
    opt = opts[i]
    if opt == "+": # M, M 
        return Mdp[i] + right, mdp[i] + right
    elif opt == "-":
        return Mdp[i] - right, mdp[i] - right
    elif opt == "*" and right < 0:
        return mdp[i] * right, Mdp[i] * right
    elif opt == "*" and right > 0:
        return Mdp[i] * right, mdp[i] * right
    else:
        return 0, 0

mdp = [0 for _ in range(10)]
Mdp = [0 for _ in range(10)]
mdp[0], Mdp[0] = oprs[0], oprs[0]
if n == 1:
    print(Mdp[0])
    exit()

Mdp[1], mdp[1] = getMm(0, oprs[1])
for i in range(2, n//2+1):
    bracket = calculate(opts[i-1], oprs[i-1], oprs[i])
    
    M, m = getMm(i-2, bracket) # i번째 괄호
    nM, nm = getMm(i-1, oprs[i]) # i-1에 괄호

    Mdp[i], mdp[i] = max(M, nM), min(m, nm)

print(Mdp[n//2])