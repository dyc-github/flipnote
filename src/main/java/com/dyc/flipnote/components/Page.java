package com.dyc.flipnote.components;

import com.dyc.flipnote.Point;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.LinkedList;
import java.util.Queue;

public class Page extends Canvas {
    //TODO DISABLE DRAWING WHEN PLAYED
    private GraphicsContext gc = this.getGraphicsContext2D();

    public Page(){
        this(600, 400);
    }
    public Page(int width, int height){
        super(width, height);
        gc.setFill(Color.BLACK);
        gc.setLineWidth(4);

        Queue<Point> positions = new LinkedList<Point>();

        this.setOnMouseDragged(event -> {
            if(!positions.isEmpty()){
                Point oldPoint = positions.remove();
                positions.clear();
                gc.strokeLine(oldPoint.x, oldPoint.y, event.getX(), event.getY());
            }
            positions.add(new Point(event.getX(), event.getY()));
        });

        this.setOnMousePressed(event -> {
            positions.clear();
            positions.add(new Point(event.getX(), event.getY()));
            gc.strokeLine(event.getX(), event.getY(), event.getX(), event.getY());
        });
    }

    public void setStrokeSize(int size){
        gc.setLineWidth(size);
    }
    public void setColor(Paint paint){
        gc.setStroke(paint);
    }

}
