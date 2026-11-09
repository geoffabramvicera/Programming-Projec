def hanoi_solver(n):

    rods = {'A': list(range(n, 0, -1)),
    'B': [],
    'C': []}

    logs = []
    logs.append(f"{rods['A']} {rods['B']} {rods['C']}")

    def move(n, source, target, auxiliary):
        if n > 0:
            move(n-1, source, auxiliary, target)

            disk = rods[source].pop()
            rods[target].append(disk)

            logs.append(f"{rods['A']} {rods['B']} {rods['C']}")

            move(n-1, auxiliary, target, source)

    move(n, 'A', 'C', 'B')

    return '\n'.join(logs)