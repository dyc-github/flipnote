package com.dyc.flipnote;

import com.dyc.flipnote.models.Brush;
import com.dyc.flipnote.models.Eraser;
import com.dyc.flipnote.models.Tool;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class AppController {
    @FXML
    private Book book;
    @FXML
    private ComboBox<String> colors, sizes, tools;
    @FXML
    private ToggleButton togglePlayButton;
    @FXML
    private Button addPageButton, deletePageButton, previousPageButton, nextPageButton, clearPageButton;

    public void initialize() {
        addPageButton.setOnAction(actionEvent -> {
            book.addPage();
            updateBookButtons();
        });
        deletePageButton.setOnAction(actionEvent -> {
            book.deletePage();
            updateBookButtons();
        });
        previousPageButton.setOnAction(actionEvent -> {
            book.previousPage();
            updateBookButtons();
        });
        nextPageButton.setOnAction(actionEvent -> {
            book.nextPage();
            updateBookButtons();
        });
        togglePlayButton.setOnAction(actionEvent ->
        {
            if (togglePlayButton.isSelected()) {
                book.play();
                previousPageButton.setDisable(true);
                nextPageButton.setDisable(true);
                return;
            }
            book.pause();
            updateBookButtons();
        });
        colors.setOnAction(actionEvent -> {
            book.getTool().setColor(getColorsValue());
        });
        sizes.setOnAction(actionEvent -> {
            book.getTool().setSize(getSizesValue());
        });
        tools.setOnAction(actionEvent -> {
            Tool tool = null;
            int size = getSizesValue();
            Color color = getColorsValue();
            switch (tools.getValue()) {
                case "Brush" -> tool = new Brush(size, color);
                case "Eraser" -> tool = new Eraser(size);
            }
            book.setTool(tool);
        });
        clearPageButton.setOnAction(actionEvent -> {
            book.clearPage();
        });
        updateBookButtons();
        book.setTool(new Brush(getSizesValue(), getColorsValue()));
    }

    private void updateBookButtons() {
        previousPageButton.setDisable(book.getCurrentPageNumber() <= 1);
        nextPageButton.setDisable(book.getTotalPageNumber() <= book.getCurrentPageNumber());
        deletePageButton.setDisable(book.getTotalPageNumber() <= 1);
    }

    private Color getColorsValue() {
        Color color = Color.TRANSPARENT;
        switch (colors.getValue()) {
            case "Black" -> color = Color.BLACK;
            case "Red" -> color = Color.RED;
            case "Blue" -> color = Color.BLUE;
        }
        return color;
    }

    private int getSizesValue() {
        int size = 0;
        switch (sizes.getValue()) {
            case "Small" -> size = 4;
            case "Medium" -> size = 8;
            case "Large" -> size = 16;
        }
        return size;
    }


}
