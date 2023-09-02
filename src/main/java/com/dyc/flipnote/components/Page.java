package com.dyc.flipnote.components;

import com.dyc.flipnote.Point;
import com.dyc.flipnote.models.Tool;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Queue;

public class Page extends Canvas {
    private final GraphicsContext gc;
    private Tool tool;

    public Page(){
        this(600, 400);
    }
    public Page(int width, int height){
        super(width, height);
        gc = this.getGraphicsContext2D();

        Queue<Point> positions = new LinkedList<Point>();

        this.setOnMouseDragged(event -> {
            if(!positions.isEmpty()){
                Point oldPoint = positions.remove();
                positions.clear();
                tool.draw(gc, oldPoint.x, oldPoint.y, event.getX(), event.getY());
            }
            positions.add(new Point(event.getX(), event.getY()));
        });

        this.setOnMousePressed(event -> {
            positions.clear();
            positions.add(new Point(event.getX(), event.getY()));
            tool.draw(gc, event.getX(), event.getY(), event.getX(), event.getY());
        });
    }

    public void clear(){
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, getWidth(), getHeight());
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }
}
