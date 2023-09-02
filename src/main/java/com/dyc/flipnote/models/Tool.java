package com.dyc.flipnote.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Tool {
    Color color;
    int size;
    boolean isEraser;

    public Tool(){
        this(4, Color.BLACK, false);
    }
    public Tool(int size, Color color, boolean isEraser){
        this.size = size;
        this.color = color;
        this.isEraser = isEraser;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public boolean isEraser(){
        return isEraser;
    }

    public void setIsEraser(boolean isEraser) {
        this.isEraser = isEraser;
    }

    public abstract void draw(GraphicsContext gc, double x1, double y1, double x2, double y2);
}
