# MySudoku

A JavaFX Sudoku game built during my 2nd year of studies, now remastered with bug fixes and visual improvements.

![Java](https://img.shields.io/badge/Java-11+-orange?style=flat-square&logo=java)
![JavaFX](https://img.shields.io/badge/JavaFX-11-blue?style=flat-square)
![Maven](https://img.shields.io/badge/Maven-3.9+-red?style=flat-square&logo=apache-maven)
![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)

## Download

**[Download the latest version](https://alexyvanot.fr/mysudoku-archive/download)**

## Features

- **Multiple difficulty levels**: Beginner, Moderate, Hard
- **Timer**: Track your solving time
- **Visual feedback**: 
  - 3x3 block borders for better grid visibility
  - Color-coded cells (editable vs fixed)
- **Give Up mode** with color-coded solution:
  - 🟢 Green: Correct user input
  - 🔴 Red: Empty cells filled by solver
  - 🟠 Orange: Incorrect user input corrected
- **Multi-language support**: English & French

## Screenshots

<p align="center">
  <img src="src/main/resources/images/iconhd.png" alt="MySudoku Logo" width="200">
</p>

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.9+

### Build & Run

```bash
# Clone the repository
git clone https://github.com/alexyvanot/mysudoku-archive.git
cd mysudoku-archive

# Run the application
mvn javafx:run
```

### Build executable JAR

```bash
mvn clean package assembly:single
```

The JAR file will be created in `target/mysudoku-0.0.1-SNAPSHOT-jar-with-dependencies.jar`

## 🎮 How to Play

1. **Start a new game** from the main menu
2. **Select difficulty**: Beginner, Moderate, or Hard
3. **Fill in the grid**: Click on empty cells and enter numbers 1-9
4. **Win condition**: Complete the grid with valid Sudoku rules
5. **Give Up**: Click "Give Up" to see the solution with color feedback

### Sudoku Rules

- Each row must contain digits 1-9 without repetition
- Each column must contain digits 1-9 without repetition
- Each 3x3 box must contain digits 1-9 without repetition

## 🛠️ Project Structure

```
mysudoku-archive/
├── src/main/java/com/intech/mysudoku/
│   ├── application/     # Main application entry point
│   ├── controllers/     # JavaFX controllers
│   ├── model/           # Game models and JavaFX components
│   └── tools/           # Core Sudoku logic (Board, Cell, Solver)
├── src/main/resources/
│   ├── images/          # Icons and assets
│   └── views/           # FXML files and CSS stylesheets
└── pom.xml              # Maven configuration
```

## 🔧 Technical Details

- **Language**: Java 11
- **UI Framework**: JavaFX 11
- **Build Tool**: Maven
- **Algorithm**: Backtracking solver with randomization

## 📝 Changelog

### v2.0 (2026) - Remastered Edition
- ✅ Fixed solver not respecting initial grid values
- ✅ Fixed win screen appearing immediately after "Give Up"
- ✅ Added color-coded solution feedback (green/red/orange)
- ✅ Added 3x3 block borders for better visibility
- ✅ Improved visual distinction between editable and fixed cells
- ✅ Fixed timer display on win screen
- ✅ Differentiated "You Won" vs "Game Over" messages

### v1.0 (2022) - Original Release
- Initial release with basic Sudoku gameplay

## 👨‍💻 Author

**Alexy Vanot** - [GitHub](https://github.com/alexyvanot)

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

<p align="center">
  Made with ❤️ and ☕
</p>
