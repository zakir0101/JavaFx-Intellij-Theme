# JavaFX IntelliJ Theme IDE

A modern Python IDE built with JavaFX and Kotlin, inspired by JetBrains IntelliJ IDEA. This project features a professional IntelliJ-style theme with both light and dark modes, a resizable multi-panel interface, and integrated code editing capabilities.

## Features

### 🎨 IntelliJ-Inspired Theme
- **Dual Theme Support**: Seamless switching between light and dark modes (Alt+L/Alt+D)
- **Professional Styling**: Over 1200 lines of comprehensive CSS theming
- **Authentic IntelliJ Look**: Color schemes and component designs matching JetBrains products

### 🖥️ IDE Interface
- **Project Explorer**: Tree-view file browser with file type icons
- **Code Editor**: Integrated Ace editor with syntax highlighting for Python, Kotlin, and Java
- **Tab Management**: Multi-document interface with tab-based navigation
- **Resizable Panels**: Three-panel drawer system (left, right, bottom) with drag-to-resize functionality
- **Custom Window Chrome**: Undecorated window with custom title bar and controls

### 📝 Code Editor Features
- Syntax highlighting for multiple languages (Python, Kotlin, Java, JavaScript)
- Dark/Light mode synchronization
- Real-time file content loading
- Language auto-detection based on file extension

### 🎯 Additional Features
- JavaFX component showcase gallery
- Keyboard shortcuts for quick navigation
- Dynamic layout management with responsive resizing
- Material Design icons integration

## Technologies

- **Language**: Kotlin 1.6.20
- **UI Framework**: JavaFX 16
- **DSL**: TornadoFX 2.0.0-SNAPSHOT
- **Build Tool**: Gradle 7.4.2
- **JVM Target**: Java 11
- **Icons**: Ikonli 12.3.1 with Material Design 2 icons
- **Code Editor**: Ace Editor (web-based)

## Prerequisites

- **Java Development Kit (JDK)**: Version 11 or higher
- **Gradle**: 7.4.2 (or use the included Gradle wrapper)

## Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/JavaFx-Intellij-Theme.git
   cd JavaFx-Intellij-Theme
   ```

2. **Build the project**
   ```bash
   ./gradlew build
   ```

3. **Run the application**
   ```bash
   ./gradlew run
   ```

## Usage

### Running the Application

```bash
# Using Gradle wrapper (recommended)
./gradlew run

# On Windows
gradlew.bat run
```

### Keyboard Shortcuts

- **Alt+L**: Switch to Light mode
- **Alt+D**: Switch to Dark mode
- **Alt+2**: Switch to IntelliJ IDE view
- **Alt+3**: Switch to JavaFX Components showcase
- **Enter**: Open selected file in project explorer

### Switching Between Views

The application offers two main views:

1. **IntelliJ IDE Mode**: Full IDE interface with project explorer, code editor, and side panels
2. **Component Showcase**: Gallery of JavaFX UI components with various controls and layouts

Use the "View" menu or keyboard shortcuts to toggle between modes.

### Opening Files

- Navigate the project explorer in the left panel
- Double-click or press Enter on any file to open it in the editor
- Files open in new tabs and support multiple simultaneous documents

## Project Structure

```
JavaFx-Intellij-Theme/
├── build.gradle                    # Build configuration
├── settings.gradle                 # Gradle settings
├── gradlew / gradlew.bat          # Gradle wrapper scripts
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── module-info.java   # Java module declaration
│   │   ├── kotlin/
│   │   │   └── com/javafx/intellijtheme/
│   │   │       ├── MyApp.kt                        # Application entry point
│   │   │       ├── IntellijIDE.kt                  # Main IDE layout
│   │   │       ├── IntellijController.kt           # State management
│   │   │       ├── intellij/                       # IntelliJ theme components
│   │   │       │   ├── IntellijStyles.kt          # Comprehensive styling
│   │   │       │   ├── IntellijTabPane.kt         # Tab management
│   │   │       │   ├── IntellijProjectExplorer.kt # File tree explorer
│   │   │       │   ├── IntellijDrawer.kt          # Resizable panels
│   │   │       │   └── intellijStage.kt           # Custom window
│   │   │       ├── components/                     # UI showcase components
│   │   │       └── editors/
│   │   │           └── AceEditor.kt               # Code editor integration
│   │   └── resources/
│   │       └── ace/                               # Ace editor library
│   └── test/
└── README.md
```

## Building from Source

### Build executable JAR
```bash
./gradlew jar
```

### Create distribution
```bash
./gradlew jlink
```

This creates a custom runtime image with the application and its dependencies.

## Theme Customization

The theme system is defined in `IntellijStyles.kt`. You can customize:

- **Colors**: Modify the color scheme for light and dark modes
- **Component Styles**: Adjust individual component appearances
- **Icons**: Change file type icons and UI icons

Example color scheme variables:
```kotlin
val primaryColor = if (darkMode.value) "#3C3F41" else "#F2F2F2"
val secondaryColor = if (darkMode.value) "#2B2B2B" else "#FFFFFF"
```

## Development

### Adding New Components

1. Create your component in `src/main/kotlin/com/javafx/intellijtheme/components/`
2. Add styling in `IntellijStyles.kt`
3. Register in the appropriate view or controller

### Extending File Type Support

To add support for new file types:

1. Update `IntellijProjectExplorer.kt` to add file icon mapping
2. Add the corresponding language mode in `AceEditor.kt`
3. Ensure the Ace editor mode is available in `src/main/resources/ace/`

## Current Status

✅ **Implemented:**
- Application window with custom chrome
- Navigation menu bar
- Resizable drawer system (left, right, bottom)
- Project explorer with file icons
- Code editor with syntax highlighting
- Tab management for multiple files
- Light/Dark theme switching

🚧 **In Progress:**
- File system operations (create, delete, rename)
- Enhanced editor features (search, replace)
- Build system integration

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is open source. Please check the LICENSE file for more information.

## Acknowledgments

- Inspired by [JetBrains IntelliJ IDEA](https://www.jetbrains.com/idea/)
- Built with [TornadoFX](https://tornadofx.io/)
- Code editing powered by [Ace Editor](https://ace.c9.io/)
- Icons from [Material Design](https://materialdesignicons.com/)

## Screenshots

<!-- Add screenshots here to showcase your IDE -->
<!-- Recommended: Light mode view, Dark mode view, Code editor in action -->

---

**Note**: This project is a learning/demonstration project and is not affiliated with JetBrains.
