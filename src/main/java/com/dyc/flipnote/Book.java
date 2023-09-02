package com.dyc.flipnote;

import com.dyc.flipnote.components.Page;
import com.dyc.flipnote.models.Brush;
import com.dyc.flipnote.models.Tool;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;


public class Book extends Group {
    ArrayList<Page> pages;
    private final AnimationTimer timer;
    private int currentPageNumber;
    private Tool tool;

    @FXML
    private HBox pageContainer;
    @FXML
    private Label currentPageNumberLabel;
    @FXML
    private Label totalPageNumberLabel;

    public Book() {
        pages = new ArrayList<Page>();
        currentPageNumber = 0;
        timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 67_000_000) {
                    lastUpdate = now;
                    if (currentPageNumber >= getTotalPageNumber()) {
                        setCurrentPage(1);
                        return;
                    }
                    nextPage();
                }
            }
        };

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "Book.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void initialize() {
        addPage();
    }

    public void setTool(Tool tool) {
        this.tool = tool;
        getCurrentPage().setTool(tool);
    }
    public Tool getTool(){
        return tool;
    }

    public void play() {
        timer.start();
    }

    public void pause() {
        timer.stop();
    }


    private Page getCurrentPage(){
        return pages.get(currentPageNumber - 1);
    }
    private void setCurrentPage(int pageNumber) {
        if (0 >= pageNumber || pageNumber > getTotalPageNumber()) {
            throw new IndexOutOfBoundsException("Cannot set page");
        }
        currentPageNumber = pageNumber;
        getCurrentPage().setTool(tool);
        updateUI();
    }

    public void addPage() {
        pages.add(currentPageNumber, new Page());
        setCurrentPage(currentPageNumber + 1);
    }

    public void deletePage() {
        if (getTotalPageNumber() <= 1) {
            throw new IndexOutOfBoundsException("Cannot delete page");
        }

        pages.remove(currentPageNumber - 1);
        if (getTotalPageNumber() < currentPageNumber) {
            setCurrentPage(currentPageNumber - 1);
            return;
        }
        setCurrentPage(currentPageNumber);
    }

    public void clearPage(){
        getCurrentPage().clear();
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public int getTotalPageNumber() {
        return pages.size();
    }

    public void previousPage() {
        if (1 >= currentPageNumber) {
            throw new IndexOutOfBoundsException("Cannot go to previous page");
        }
        setCurrentPage(currentPageNumber - 1);
    }

    public void nextPage() {
        if (currentPageNumber >= getTotalPageNumber()) {
            throw new IndexOutOfBoundsException("Cannot go to next page");
        }
        setCurrentPage(currentPageNumber + 1);
    }

    private void updateUI() {
        pageContainer.getChildren().clear();
        pageContainer.getChildren().add(getCurrentPage());
        currentPageNumberLabel.setText(Integer.toString(currentPageNumber));
        totalPageNumberLabel.setText(Integer.toString(getTotalPageNumber()));
    }
}
