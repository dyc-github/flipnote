package com.dyc.flipnote.models;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Book {
    private final AnimationTimer timer;
    private final BooleanProperty currentPageIsFirst;
    private final BooleanProperty currentPageIsLast;
    private final BooleanProperty isPlaying;
    private final IntegerProperty currentPage;
    private final IntegerProperty totalPages;

    public Book() {
        currentPage = new SimpleIntegerProperty();
        totalPages = new SimpleIntegerProperty();
        currentPageIsFirst = new SimpleBooleanProperty();
        currentPageIsLast = new SimpleBooleanProperty();
        isPlaying = new SimpleBooleanProperty();
        timer = new AnimationTimer() {
            private long lastUpdate = 0 ;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 67_000_000) {
                    lastUpdate = now ;
                    if(currentPageIsLast.get()){
                        setCurrentPage(1);
                    }
                    nextPage();
                    return;


                }
            }
        };
        isPlaying.set(false);
    }



    public void initialize() {

        addNewPage();
        updateIsFirstAndIsLast();
    }

    public void play(){
        timer.start();
        isPlaying.set(true);
    }
    public void pause(){
        timer.stop();
        isPlaying.set(false);
    }

    public int getCurrentPage() {
        return currentPage.get();
    }

    public void setCurrentPage(int pageNumber) {
        if (0 < pageNumber && pageNumber <= totalPages.get()) {
            currentPage.set(pageNumber);
            updateIsFirstAndIsLast();
        }
    }

    public int getTotalPages() {
        return totalPages.get();
    }

    public IntegerProperty currentPageProperty() {
        return currentPage;
    }

    public IntegerProperty totalPageProperty() {
        return totalPages;
    }

    public void addNewPage() {
        totalPages.set(getTotalPages() + 1);
        setCurrentPage(getCurrentPage() + 1);
        currentPage.get();
    }

    public void nextPage() {
        if (!currentPageIsLast.get()) {
            setCurrentPage(getCurrentPage() + 1);
        }
    }

    public void previousPage() {
        if (!currentPageIsFirst.get()) {
            setCurrentPage(getCurrentPage() - 1);
        }
    }

    private void updateIsFirstAndIsLast() {
        currentPageIsFirst.set(currentPage.get() == 1);
        currentPageIsLast.set(currentPage.get() == totalPages.get());
    }

    public BooleanProperty currentPageIsFirstProperty() {
        return currentPageIsFirst;
    }
    public BooleanProperty getCurrentPageIsLastProperty(){
        return currentPageIsLast;
    }
}
