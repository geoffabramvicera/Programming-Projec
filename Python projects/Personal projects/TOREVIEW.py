test_settings = {
    'theme': 'light',
    'notifications': 'enabled',
    'volume': 'high'
}
def add_setting(settings, new_setting):
    key, value = new_setting[0].lower(), new_setting[1].lower()
    if key in settings:
        return f'Setting {key} already exists! Cannot add a new setting with this name.'
    elif key not in settings:
        settings[key] = value
        return f'Setting {key} added with value {value} successfully!'

def update_setting(settings, new_setting): 
    key, value = new_setting[0].lower(), new_setting[1].lower()
    if key in settings:
        settings[key] = value
        return f'Setting {key} updated to {value} successfully!'
    elif key not in settings: 
        return f'Setting {key} does not exist! Cannot update a non-existing setting.'

def delete_setting(settings, key):
    key = key.lower()
    if key in settings:
        del settings[key]
        return f'Setting {key} deleted successfully!'
    elif key not in settings:
        return 'Setting not found!'

def view_settings(settings):
    if not settings:
        return 'No settings available.'
    
    display = 'Current User Settings:\n'
    for key, value in settings.item():
        display += f' {key.capitalize()}: {value}\n'
    return display




test_settings = {
    'theme': 'light',
    'notifications': 'enabled',
    'volume': 'high'
}

def add_setting(settings, new_setting):
    key, value = new_setting[0].lower(), new_setting[1].lower()
    if key in settings:
        return f"Setting '{key}' already exists! Cannot add a new setting with this name."
    elif key not in settings:
        settings[key] = value
        return f"Setting '{key}' added with value '{value}' successfully!"

def update_setting(settings, new_setting): 
    key, value = new_setting[0].lower(), new_setting[1].lower()
    if key in settings:
        settings[key] = value
        return f"Setting '{key}' updated to '{value}' successfully!"
    elif key not in settings: 
        return f"Setting '{key}' does not exist! Cannot update a non-existing setting."

def delete_setting(settings, key):
    key = key.lower()
    if key in settings:
        del settings[key]
        return f"Setting '{key}' deleted successfully!"
    elif key not in settings:
        return "Setting not found!"

def view_settings(settings):
    if not settings:
        return "No settings available."
    
    display = "Current User Settings:\n"
    for key, value in settings.items():
        display += f"  {key.capitalize()}: {value}\n"
    return display.rstrip()