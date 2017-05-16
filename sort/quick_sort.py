import random, time

def quick_sort(ll):
    if(len(ll) <= 1):
        return ll
    lo = 0
    hi = len(ll) - 1
    pivot_index = hi / 2
    pivot = ll[pivot_index]
    rest = ll[:pivot_index] + ll[pivot_index + 1:]
    return quick_sort([x for x in rest if x < pivot]) + [pivot] + quick_sort([x for x in rest if x >= pivot])


n = 100000
unsorted = [random.randint(-99,100) for x in range(0, n)]
start = time.time()
res = quick_sort(unsorted)
end = time.time()
elapsed = round(end - start, 2)
print("Done in {0} seconds.".format(elapsed))


