class HashTable:
    def __init__(self):
        self.collection = {}

    def hash(self, key):
        total_sum = 0
        for character in key:
            total_sum += ord(character)
        return total_sum

    def add(self, key, value):
        hashed_value = self.hash(key)
        if hashed_value not in self.collection:
            self.collection[hashed_value] = {}
        self.collection[hashed_value][key] = value

    def remove(self, key):
        hashed_value = self.hash(key)
        if hashed_value in self.collection:
            if key in self.collection[hashed_value]:
                del self.collection[hashed_value][key]


    def lookup(self, key):
        hashed_value = self.hash(key)
        if hashed_value in self.collection:
            if key in self.collection[hashed_value]:
                return self.collection[hashed_value][key]