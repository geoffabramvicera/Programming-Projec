full_dot = '●'
empty_dot = '○'

def create_character(character_name: str, strength: int, intelligence: int, charisma: int):

    if not isinstance(character_name, str):
        return 'The character name should be a string'
    elif character_name == '':
        return 'The character should have a name'
    elif len(character_name) > 10:
        return 'The character name is too long'
    elif ' ' in character_name:
        return 'The character name should not contain spaces'

    stats = [strength, intelligence, charisma]

    if not all (isinstance(s, int) for s in [strength, intelligence, charisma]):
        return 'All stats should be integers'
    elif any(s < 1 for s in stats):
        return 'All stats should be no less than 1'
    elif any(s > 4 for s in stats):
         return 'All stats should be no more than 4'
    elif sum(stats) != 7:
        return 'The character should start with 7 points'

    else:
        line1 = (character_name)
        line2 = (f'STR {full_dot * strength}{empty_dot * (10 - strength)}')
        line3 = (f'INT {full_dot * intelligence}{empty_dot * (10 - intelligence)}')
        line4 = (f'CHA {full_dot * charisma}{empty_dot * (10 - charisma)}')

        return f'{line1}\n{line2}\n{line3}\n{line4}'

create_character('ren', 4, 2, 1)