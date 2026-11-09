JOB_COUNT = 5

arrival = []
burst = []

for i in range(JOB_COUNT):
    print("\nJob", i + 1)
    arrival_time = int(input("Arrival time: "))
    burst_time = int(input("Burst time: "))

    if arrival_time < 0 or burst_time <= 0:
        raise SystemExit("Arrival must be >= 0 and burst must be > 0.")

    arrival.append(arrival_time)
    burst.append(burst_time)


def run(algorithm):
    completed = [False] * JOB_COUNT
    completion = [0] * JOB_COUNT
    turnaround = [0] * JOB_COUNT
    waiting = [0] * JOB_COUNT

    time = 0
    finished = 0
    timeline = []

    print("\n" + algorithm)
    print("Timeline: Start | Job | End")

    while finished < JOB_COUNT:
        selected = -1
        for i in range(JOB_COUNT):
            if completed[i] or arrival[i] > time:
                continue

            if selected == -1:
                selected = i
                continue

            if algorithm == "FCFS":
                if arrival[i] < arrival[selected]:
                    selected = i
            else:
                if burst[i] < burst[selected]:
                    selected = i
                elif burst[i] == burst[selected]:
                    if arrival[i] < arrival[selected]:
                        selected = i

        if selected == -1:
            time += 1
            continue

        start = time
        time += burst[selected]

        completion[selected] = time
        turnaround[selected] = completion[selected] - arrival[selected]
        waiting[selected] = turnaround[selected] - burst[selected]

        completed[selected] = True
        finished += 1

        print(start, "| J" + str(selected + 1), "|", time)
        timeline.append((start, "J" + str(selected + 1), time))

    print("\nGantt Chart:")
    for start_time, job_name, end_time in timeline:
        print(f"| {job_name} ", end="")
    print("|")

    for start_time, job_name, end_time in timeline:
        print(f"{start_time:<5}", end="")
    print(timeline[-1][2])

    print("\nJob\tAT\tBT\tCT\tTAT\tWT")
    for i in range(JOB_COUNT):
        print(
            f"J{i + 1}\t{arrival[i]}\t{burst[i]}\t"
            f"{completion[i]}\t{turnaround[i]}\t{waiting[i]}"
        )

    print(f"Average TAT: {sum(turnaround) / JOB_COUNT:.2f}")
    print(f"Average WT: {sum(waiting) / JOB_COUNT:.2f}")


run("FCFS")
run("SJF")