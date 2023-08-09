n, k = map(int, input().split())
coins = []
for _ in range(n):
	coin = int(input())
	if coin <= k:
		coins.append(coin)
	
# coins.sort()

graph = [0 for _ in range(k+1)]
	
for coin in coins:
    graph[coin] += 1
    for price in range(coin, k+1):
        graph[price] += graph[price-coin]

print(graph[k])