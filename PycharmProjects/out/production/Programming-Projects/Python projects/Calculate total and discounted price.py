# Function to calculate total and discounted price
def calculate_prices(quantity, price):
    total_price = quantity * price
    discount = total_price * 0.20
    net_price = total_price - discount
    return total_price, net_price

# Input product details
product = input("Enter the product name: ")
quantity = int(input("Enter the quantity: "))
price = float(input("Enter the price per unit: "))

# Calculate prices
total_price, net_price = calculate_prices(quantity, price)

# Display results
print(f"Product: {product}")
print(f"Total Price: {total_price:.2f}")
print(f"Discounted Price (20% off): {net_price:.2f}")
print(f"Quantity:", quantity)