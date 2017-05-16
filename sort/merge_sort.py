from random import randint
import time

def merge_sort(m):
    n = len(m)

    if n <= 1:
        return m

    left = m[:n/2]
    right = m[n/2:]

    left = merge_sort(left)
    right = merge_sort(right)

    return merge(left, right)

def merge(left, right):
    result = []

    while left and right:
        if left[0] <= right[0]:
            result.append(left[0])
            left = left[1:]
        else:
            result.append(right[0])
            right = right[1:]

    while left:
        result.append(left[0])
        left = left[1:]
    while right:
        result.append(right[0])
        right = right[1:]
    return result

unsorted = [randint(-100,99) for x in range(0, 10000)]

start = time.time()
s = merge_sort(unsorted)
end = time.time()
print("Done in {0} seconds".format(round(end-start, 2)))
        

