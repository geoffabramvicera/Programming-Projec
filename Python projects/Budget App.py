import math

class Category:
    def __init__(self, name):
        self.name = name
        self.ledger = []

    def deposit(self, amount, description=""):
        self.ledger.append({'amount': amount, 'description': description})

    def withdraw(self, amount, description=""):
        total = sum(entry['amount'] for entry in self.ledger)
        if total >= amount:
            self.ledger.append({'amount': -amount, 'description': description})
            return True
        return False

    def get_balance(self):
        return sum(entry['amount'] for entry in self.ledger)

    def transfer(self, amount, category):
        if self.withdraw(amount, f"Transfer to {category.name}"):
            category.deposit(amount, f"Transfer from {self.name}")
            return True
        return False

    def check_funds(self, amount):
        if amount > self.get_balance():
            return False
        return True

    def __str__(self):
        title = self.name.center(30, "*")
        output = ""
        for entry in self.ledger:
            description = entry['description'][:23].ljust(23)
            amount = f"{entry['amount']:.2f}"
            amount = amount.rjust(7)
            output += description + amount + "\n"
        total = f"Total: {self.get_balance():.2f}"
        return title + "\n" + output + total


def create_spend_chart(categories):
    total_spent = []
    for category in categories:
        spent = 0
        for entry in category.ledger:
            if entry['amount'] < 0:
                spent += entry['amount']
        total_spent.append(spent)

    total = sum(total_spent)
    percentages = []
    for spent in total_spent:
        percentage = (spent / total) * 100
        percentage = math.floor(percentage / 10) * 10
        percentages.append(percentage)

    chart = 'Percentage spent by category\n'
    for y in range(100, -1, -10):
        row = str(y).rjust(3) + '| '
        for percentage in percentages:
            if percentage >= y:
                row += 'o  '
            else:
                row += '   '
        chart += row + '\n'

    line = '    ' + '---'* len(categories) + '-'
    chart += line + '\n'
    max_length = max(len(category.name) for category in categories)
    for i in range(max_length):
        row = '     '
        for category in categories:
            if i < len(category.name):
                row += category.name[i] + '  '
            else:
                row += '   '

        chart += row + '\n'
    return chart.rstrip('\n')