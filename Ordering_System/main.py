MENU = {
    1: {"name": "Burger", "price": 120, "prep_time": 8},
    2: {"name": "Pizza", "price": 250, "prep_time": 15},
    3: {"name": "Pasta", "price": 180, "prep_time": 12},
    4: {"name": "Fried Chicken", "price": 150, "prep_time": 10},
    5: {"name": "Fries", "price": 60, "prep_time": 5},
    6: {"name": "Iced Tea", "price": 40, "prep_time": 2},
}

REGULAR_PRIORITY = 2
PRIORITY_LANE_PRIORITY = 1

def get_integer(prompt, minimum, maximum=None):
    while True:
        try:
            number = int(input(prompt))

            if number < minimum:
                print("Please enter a number that is at least", minimum)
            elif maximum is not None and number > maximum:
                print("Please enter a number no greater than", maximum)
            else:
                return number
        except ValueError:
            print("Invalid input. Please enter a whole number.")


def display_menu():
    print("\n================ RESTAURANT MENU ================")
    print(f"{'No.':<5}{'Food / Drink':<20}{'Price':>12}{'Prep Time':>14}")
    print("-" * 51)

    for menu_number, food in MENU.items():
        print(
            f"{menu_number:<5}"
            f"{food['name']:<20}"
            f"PHP {food['price']:>7}"
            f"{food['prep_time']:>10} min"
        )

    print("0    Finish ordering")
    print("=================================================")


def choose_lane():
    print("\n=============== CHOOSE A LANE ===============")
    print("1. Regular Lane")
    print("2. Priority Lane (PWD, Senior Citizen, Pregnant)")

    lane_choice = get_integer("Choose your lane: ", 1, 2)

    if lane_choice == 1:
        return "Regular", REGULAR_PRIORITY

    return "Priority", PRIORITY_LANE_PRIORITY


def take_food_order():
    ordered_items = []
    total_price = 0
    total_prep_time = 0

    while True:
        display_menu()
        menu_choice = get_integer("Choose a food number: ", 0, len(MENU))

        if menu_choice == 0:
            if len(ordered_items) == 0:
                print("Please order at least one item before finishing.")
                continue
            break

        selected_food = MENU[menu_choice]
        quantity = get_integer("Enter quantity: ", 1)
        item_total = selected_food["price"] * quantity
        item_prep_time = selected_food["prep_time"] * quantity

        ordered_items.append({
            "name": selected_food["name"],
            "quantity": quantity,
            "subtotal": item_total,
        })

        total_price += item_total
        total_prep_time += item_prep_time
        print(quantity, selected_food["name"], "added. Subtotal: PHP", item_total)

    return ordered_items, total_price, total_prep_time


def add_customer_order(customers, next_order_id, next_arrival_time):
    print("\n=============== NEW CUSTOMER ===============")

    lane, priority = choose_lane()
    arrival_time = next_arrival_time
    next_arrival_time += 1
    print("Arrival Time recorded:", arrival_time)

    ordered_items, total_price, burst_time = take_food_order()

    order_id = next_order_id
    next_order_id += 1

    customer_order = {
        "order_id": order_id,
        "lane": lane,
        "priority": priority,
        "items": ordered_items,
        "total_price": total_price,
        "arrival_time": arrival_time,
        "burst_time": burst_time,
    }

    customers.append(customer_order)

    print("\nOrder placed successfully!")
    print("Order ID:", order_id)
    print("Lane:", lane)
    print("Arrival Time:", arrival_time)
    print("Food preparation time (Burst Time):", burst_time, "minutes")
    print("Total bill: PHP", total_price)

    return next_order_id, next_arrival_time


def get_order_description(customer_order):
    item_descriptions = []

    for item in customer_order["items"]:
        item_descriptions.append(item["name"] + " x" + str(item["quantity"]))

    return ", ".join(item_descriptions)


def display_pending_orders(customers):
    if len(customers) == 0:
        print("\nThere are no pending orders.")
        return

    print("\n================ PENDING ORDERS ================")
    print(f"{'ID':<5}{'Lane':<12}{'Food Order':<35}{'AT':>5}{'BT':>5}")
    print("-" * 62)

    for customer in customers:
        print(
            f"{customer['order_id']:<5}"
            f"{customer['lane']:<12}"
            f"{get_order_description(customer):<35}"
            f"{customer['arrival_time']:>5}"
            f"{customer['burst_time']:>5}"
        )


def select_next_customer(ready_customers):
    selected_customer = ready_customers[0]

    for customer in ready_customers[1:]:
        if customer["priority"] < selected_customer["priority"]:
            selected_customer = customer
        elif customer["priority"] == selected_customer["priority"]:
            if customer["arrival_time"] < selected_customer["arrival_time"]:
                selected_customer = customer
            elif customer["arrival_time"] == selected_customer["arrival_time"]:
                if customer["order_id"] < selected_customer["order_id"]:
                    selected_customer = customer

    return selected_customer


def process_orders(customers):
    waiting_customers = customers.copy()
    completed_customers = []
    current_time = 0

    while len(waiting_customers) > 0:
        ready_customers = []

        for customer in waiting_customers:
            if customer["arrival_time"] <= current_time:
                ready_customers.append(customer)

        if len(ready_customers) == 0:
            next_arrival_time = waiting_customers[0]["arrival_time"]

            for customer in waiting_customers:
                if customer["arrival_time"] < next_arrival_time:
                    next_arrival_time = customer["arrival_time"]

            current_time = next_arrival_time

            for customer in waiting_customers:
                if customer["arrival_time"] <= current_time:
                    ready_customers.append(customer)

        selected_customer = select_next_customer(ready_customers)

        start_time = current_time
        completion_time = start_time + selected_customer["burst_time"]
        turnaround_time = completion_time - selected_customer["arrival_time"]
        waiting_time = start_time - selected_customer["arrival_time"]

        selected_customer["start_time"] = start_time
        selected_customer["completion_time"] = completion_time
        selected_customer["turnaround_time"] = turnaround_time
        selected_customer["waiting_time"] = waiting_time

        completed_customers.append(selected_customer)
        waiting_customers.remove(selected_customer)
        current_time = completion_time

    display_results(completed_customers)

def display_results(completed_customers):
    print("\n================ SERVICE ORDER ================")

    for position, customer in enumerate(completed_customers, start=1):
        print(
            f"{position}. Order #{customer['order_id']}"
            f" ({customer['lane']} Lane), "
            f"{get_order_description(customer)}"
        )

    print("\n================ FINAL RESULTS ================")
    print(f"{'ID':<5}{'Lane':<12}{'AT':>5}{'BT':>5}{'ST':>5}{'CT':>5}{'TAT':>6}{'WT':>5}")
    print("-" * 48)

    total_turnaround_time = 0
    total_waiting_time = 0

    for customer in completed_customers:
        print(
            f"{customer['order_id']:<5}"
            f"{customer['lane']:<12}"
            f"{customer['arrival_time']:>5}"
            f"{customer['burst_time']:>5}"
            f"{customer['start_time']:>5}"
            f"{customer['completion_time']:>5}"
            f"{customer['turnaround_time']:>6}"
            f"{customer['waiting_time']:>5}"
        )

        total_turnaround_time += customer["turnaround_time"]
        total_waiting_time += customer["waiting_time"]

    customer_count = len(completed_customers)
    average_turnaround_time = total_turnaround_time / customer_count
    average_waiting_time = total_waiting_time / customer_count

    print("-" * 48)
    print(f"Average Turnaround Time: {average_turnaround_time:.2f} minutes")
    print(f"Average Waiting Time: {average_waiting_time:.2f} minutes")

    print("\n================ CUSTOMER BILLS ================")

    for customer in completed_customers:
        print(f"Order #{customer['order_id']}: {get_order_description(customer)}")
        print(f"Total: PHP {customer['total_price']}")

def main():
    customers = []
    next_order_id = 1
    next_arrival_time = 0

    while True:
        print("\n========== RESTAURANT ORDERING SYSTEM ==========")
        print("1. Add Customer Order")
        print("2. View Pending Orders")
        print("3. Process Orders")
        print("4. Exit")
        print("================================================")

        choice = input("Enter your choice: ").strip()

        if choice == "1":
            next_order_id, next_arrival_time = add_customer_order(
                customers, next_order_id, next_arrival_time
            )
        elif choice == "2":
            display_pending_orders(customers)
        elif choice == "3":
            if len(customers) == 0:
                print("\nThere are no orders to process.")
            else:
                process_orders(customers)
                print("\nAll customer orders have been processed.")
                break
        elif choice == "4":
            print("\nThank you for using the Restaurant Ordering System!")
            break
        else:
            print("Invalid choice. Please select from 1 to 4.")


if __name__ == "__main__":
    main()