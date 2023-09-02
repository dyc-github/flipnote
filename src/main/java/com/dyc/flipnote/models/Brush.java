package com.dyc.flipnote.models;

import com.dyc.flipnote.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Queue;

public class Brush extends Tool {

    public Brush(){
        super(4, Color.BLACK, false);
    }
    public Brush(int size, Color color){
        super(size, color, false);
    }

    @Override
    public void draw(GraphicsContext gc, double x1, double y1, double x2, double y2) {
        gc.setStroke(getColor());
        gc.setLineWidth(getSize());
        gc.strokeLine(x1, y1, x2, y2);
    }
}
