import pdb
def add_setting(settings, new_setting):
    pdb.set_trace()  # Set a breakpoint to debug the function
    key = new_setting.lower()  # CRASH HERE
    
settings = {'theme': 'light'}
add_setting(settings, ('THEME', 'dark'))