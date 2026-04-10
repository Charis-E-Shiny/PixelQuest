# 🎮 PixelQuest

PixelQuest is a **Java Swing-based 2D game** with a storyline-driven experience.  
Explore maps, navigate challenges, and progress through levels in a retro-style pixel world.

---

## 🚀 Features

- 🕹️ Built using **Java Swing**
- 🗺️ Custom map system
- 📖 Storyline-based gameplay
- 🎨 Resource-based rendering (sprites, tiles, etc.)
- ⚡ Lightweight and easy to run locally

---

## 📁 Project Structure

```
PixelQuest/
│── src/
│   ├── main/        # Main application entry point
│   ├── res/         # Game assets (images, sprites, etc.)
│   ├── maps/        # Map files
│
│── sources.txt      # List of Java source files
│── README.md
```

---

## 🛠️ Requirements

- Java JDK 8 or higher
- Terminal / Command Prompt

---

## ▶️ Run Locally

Follow these steps to compile and run the game:

```bash
rm -rf out
mkdir out
javac --release 8 -d out @sources.txt
cp -r src/res out/
cp -r src/maps out/
java -cp out main.App
```

---

## 🎯 How It Works

1. The project compiles all Java files listed in `sources.txt`
2. Output `.class` files are stored in the `out/` directory
3. Resource folders (`res`, `maps`) are copied into the build directory
4. The game starts from:
   ```
   main.App
   ```

---

## 🧩 Future Improvements

- Add more levels and maps
- Enhance storyline depth
- Add sound effects and background music
- Improve UI/UX with animations
- Player progression system

---

## 🤝 Contributing

Feel free to fork the project and submit pull requests for improvements!

---

## 📜 License

This project is open-source and available for educational and personal use.

---

## 💡 Author

Developed as part of a learning project using Java and game design concepts.
