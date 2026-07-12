def fibonacci(n: int):
    sequence = [0, 1]

    if n < 0:
        raise ValueError("Value must be non-negative integer")

    if n < 2:
        return sequence[n]

    for i in range(2, n + 1):
        next_number = sequence[i-1] + sequence[i-2]
        sequence.append(next_number)

    return sequence[n]

fibonacci(10)