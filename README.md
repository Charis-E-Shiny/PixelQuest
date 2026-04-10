# PixelQuest
Java Swing with storyline
# Local instructions -
rm -rf out
mkdir out
javac --release 8 -d out @sources.txt
cp -r src/res out/
cp -r src/maps out/
java -cp out main.App
