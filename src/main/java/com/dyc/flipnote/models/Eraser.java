package com.dyc.flipnote.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Eraser extends Tool{
    public Eraser(){
        super(4, Color.WHITE, true);
    }
    public Eraser(int size){
        super(size, Color.WHITE, true);
    }
    @Override
    public void draw(GraphicsContext gc, double x1, double y1, double x2, double y2) {
        gc.setStroke(getColor());
        gc.setLineWidth(getSize());
        gc.strokeLine(x1, y1, x2, y2);
    }
}
