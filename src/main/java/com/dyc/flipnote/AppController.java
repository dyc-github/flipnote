package com.dyc.flipnote;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AppController {
    @FXML
    private Book book;
    @FXML
    private ComboBox<String> colors, sizes, tools;
    @FXML
    private ToggleButton togglePlayButton;
    @FXML
    private Button addPageButton, deletePageButton, previousPageButton, nextPageButton;

    public void initialize() {
        //Controller to Model
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
            if(togglePlayButton.isSelected()){
             book.play();
                previousPageButton.setDisable(true);
                nextPageButton.setDisable(true);
             return;
            }
            book.pause();
            updateBookButtons();
        });
        colors.setOnAction(actionEvent -> {
            System.out.println(actionEvent);
            System.out.println(colors.valueProperty().getValue());
        });
    }

    private void updateBookButtons(){
        previousPageButton.setDisable(book.getCurrentPageNumber() <= 1);
        nextPageButton.setDisable(book.getTotalPageNumber() <= book.getCurrentPageNumber());
        deletePageButton.setDisable(book.getTotalPageNumber() <= 1);
    }


}
