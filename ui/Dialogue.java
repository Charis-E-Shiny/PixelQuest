package ui;

public class Dialogue {

    String[] lines;
    int index = 0;

    public Dialogue(String[] lines) {
        this.lines = lines;
    }

    public String getLine() {
        return lines[index];
    }

    public boolean next() {
        index++;
        return index < lines.length;
    }
}