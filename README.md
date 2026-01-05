📘 StudyMate – Student Companion App
Overview

StudyMate is a semester project Android application built in Java using Android Studio.
It combines multiple learning modules: login system, notes management, quiz integration with API + offline storage, theme switching, and web resources.
The app demonstrates key Android concepts such as Activities, Intents, RecyclerView, SQLite, SharedPreferences, Retrofit, WebView, and lifecycle management.


 ✨ Features Implemented
- **Login & Authentication**
  - Hard-coded credentials and registration with SharedPreferences.
  - Forgot Password and Reset Password flows.
- **Notes Module**
  - Create, edit, delete notes.
  - Stored locally in SQLite.
  - RecyclerView with custom adapter.
- **Quiz Module**
  - Fetch questions from JSONPlaceholder API using Retrofit.
  - Store questions offline in SQLite.
  - Display via RecyclerView.
- **Theme Switching**
  - Light/Dark themes.
  - Persisted using SharedPreferences.
- **WebView Integration**
  - In-app browsing of study resources.
- **Lifecycle & State Management**
  - Preserves login state and theme across app restarts.
  - Handles rotation and configuration changes.
----------------------------------------

- Login Screen with icons  
- Registration / Forgot Password / Reset Password  
- Notes list with RecyclerView  
- Quiz questions fetched from API  
- WebView showing external resources  
- Settings with theme toggle  

---

  📂 Project Structure
```
app/src/main/java/com/example/studymate/
    ui/                → Activities (Login, Home, Notes, Quiz, WebView, Settings, Registration, Forgot, Reset)
    adapter/           → RecyclerView adapters (NoteAdapter, QuizAdapter)
    db/                → SQLite helpers (NoteDbHelper, QuizDbHelper)
    model/             → Data models (Note)
    network/           → Retrofit API interface + models (QuizApi, QuizQuestion)

app/src/main/res/
    layout/            → XML layouts for each screen
    drawable/          → Icons and images
    values/            → colors.xml, styles.xml, strings.xml
    menu/              → Options menu (theme, logout)
```

---


---

🛠 Challenges Faced
- Gradle sync issues due to missing SDK → fixed by installing API 33.
- Emulator performance → improved with hardware acceleration.
- Handling Retrofit + SQLite integration → required debugging JSON parsing.
- Preserving theme and login state across lifecycle events.

---

 📑 Submission Checklist
- [x] Source code pushed to GitHub  
- [x] README file with features and navigation flow  
- [x] Screenshots of project code, emulator output, and GitHub repo  

Do you want me to also prepare a **short feasibility study table** (mapping features to Android APIs/libraries) so you can include it for Activity 4 submission?e across app restarts.

        Handles rotation and configuration changes.
