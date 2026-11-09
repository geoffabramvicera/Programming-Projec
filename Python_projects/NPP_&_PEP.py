JOB_COUNT = 5

arrival = []
burst = []
priority = []

for i in range(JOB_COUNT):
    print("\nJob", i + 1)
    arrival_time = int(input("Arrival time: "))
    burst_time = int(input("Burst time: "))
    priority_number = int(input("Priority (1 is highest): "))

    if arrival_time < 0 or burst_time <= 0 or priority_number < 1:
        raise SystemExit("Arrival must be >= 0; burst and priority > 0.")

    arrival.append(arrival_time)
    burst.append(burst_time)
    priority.append(priority_number)


def run(algorithm):
    remaining = burst.copy()
    completion = [0] * JOB_COUNT
    turnaround = [0] * JOB_COUNT
    waiting = [0] * JOB_COUNT

    time = 0
    finished = 0
    previous_job = -1
    segment_start = 0
    timeline = []

    print("\n" + algorithm)
    print("Timeline: Start | Job | End")

    while finished < JOB_COUNT:
        selected = -1

        for i in range(JOB_COUNT):
            if remaining[i] == 0 or arrival[i] > time:
                continue

            if selected == -1:
                selected = i
                continue

            if priority[i] < priority[selected]:
                selected = i
            elif priority[i] == priority[selected]:
                if arrival[i] < arrival[selected]:
                    selected = i

        if selected != previous_job:
            if previous_job != -1:
                print(segment_start, "| J" + str(previous_job + 1), "|", time)
                timeline.append((segment_start, "J" + str(previous_job + 1), time))

            segment_start = time
            previous_job = selected

        if selected == -1:
            time += 1
            continue

        if algorithm == "NPP":
            duration = remaining[selected]
        else:
            duration = 1

        time += duration
        remaining[selected] -= duration

        if remaining[selected] == 0:
            completion[selected] = time
            turnaround[selected] = completion[selected] - arrival[selected]
            waiting[selected] = turnaround[selected] - burst[selected]
            finished += 1

    print(segment_start, "| J" + str(previous_job + 1), "|", time)
    timeline.append((segment_start, "J" + str(previous_job + 1), time))

    print("\nGantt Chart:")
    for start_time, job_name, end_time in timeline:
        print(f"| {job_name} ", end="")
    print("|")

    for start_time, job_name, end_time in timeline:
        print(f"{start_time:<5}", end="")
    print(timeline[-1][2])

    print("\nJob\tAT\tBT\tPR\tCT\tTAT\tWT")
    for i in range(JOB_COUNT):
        print(
            f"J{i + 1}\t{arrival[i]}\t{burst[i]}\t{priority[i]}\t"
            f"{completion[i]}\t{turnaround[i]}\t{waiting[i]}"
        )

    print(f"Average TAT: {sum(turnaround) / JOB_COUNT:.2f}")
    print(f"Average WT: {sum(waiting) / JOB_COUNT:.2f}")


run("NPP")
run("PEP")