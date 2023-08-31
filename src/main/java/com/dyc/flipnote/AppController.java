package com.dyc.flipnote;

import com.dyc.flipnote.components.Page;
import com.dyc.flipnote.models.Book;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class AppController {
    private final Book book;
    private ArrayList<Page> pages;

    @FXML
    private HBox pageContainer;
    @FXML
    private ComboBox<String> colors, sizes, tools;
    @FXML
    private ToggleButton togglePlayButton;
    @FXML
    private Button newPageButton, previousPageButton, nextPageButton;
    @FXML
    private Label currentPageLabel, totalPagesLabel;

    public AppController() {
        book = new Book();
        pages = new ArrayList<Page>();
        pages.add(new Page());
    }

    public void initialize() {
        //Controller to View

        //Model to View
        book.totalPageProperty().addListener((observableValue, oldNumber, newNumber) -> {
            pages.add(new Page());
            totalPagesLabel.setText(newNumber.toString());
        });
        book.currentPageProperty().addListener((observableValue, oldNumber, newNumber) -> {
            Page page = pages.get(newNumber.intValue() - 1);
            ObservableList<Node> children = pageContainer.getChildren();
            children.clear();
            children.add(page);
            currentPageLabel.setText(newNumber.toString());
        });
        book.currentPageIsFirstProperty().addListener((observableValue, oldBoolean, newBoolean) -> {
            previousPageButton.setDisable(newBoolean);
        });
        book.getCurrentPageIsLastProperty().addListener((observableValue, oldBoolean, newBoolean) -> {
            nextPageButton.setDisable(newBoolean);
        });
        book.initialize();

        //Controller to Model
        newPageButton.setOnAction(actionEvent -> {
            book.addNewPage();
        });
        previousPageButton.setOnAction(actionEvent -> {
            book.previousPage();
        });
        nextPageButton.setOnAction(actionEvent -> {
            book.nextPage();
        });
        togglePlayButton.setOnAction(actionEvent ->
        {
            if(togglePlayButton.isSelected()){
             book.play();
             return;
            }
            book.pause();
        });
        colors.setOnAction(actionEvent -> {
            System.out.println(actionEvent);
            System.out.println(colors.valueProperty().getValue());

        });
    }

    private void updatePageContainer() {
        int pageIndex = book.getCurrentPage() - 1;
    }


}
