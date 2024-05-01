# VisuAlly
_Needs reviewing_

**Prerequisites:**
    Ensure you have Python and a virtual environment (recommended) installed on your system.
    Ensure you have Node.js and npm (Node Package Manager) installed on your system.

**NOTE:** Make sure you're in the visually_app directory

**Development Environment Setup:**
    1. Open two terminal windows.
    2. Django Backend (in one terminal):
        - cd backend
        - python manage.py runserver
    2. React Native Frontend (in the other terminal):
        - cd app
        - npm install
        - npx react-native start

**Development:**
    With both development servers running (optional), you can start working on your project.
    Changes to the Django backend typically require a server restart (Ctrl+C to stop, then run python manage.py runserver again).
    Changes to the React Native frontend usually don't require a server restart (changes are reflected automatically).

**Additional Notes:**
    You can close the development server terminals (Ctrl+C or Command+C) when you're not actively testing or making changes.
    Remember to activate your virtual environment whenever working on the Django backend.
