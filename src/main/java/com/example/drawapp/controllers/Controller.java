package com.example.drawapp.controllers;

import com.example.drawapp.models.Circle;
import com.example.drawapp.models.Rectangle;
import com.example.drawapp.models.Shape;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.drawapp.models.Shape.BORDER_OFFSET;

public class Controller {

    @FXML
    public Canvas canvas;
    @FXML
    public ColorPicker colorPicker;
    @FXML
    public Label areaValue, perimeterValue, widthOrRaduis, heightCaption;
    public TextField xValue, yValue, widthValue, heightValue;
    private Color selectedColor;
    private GraphicsContext gc;
    private Shape selectedShape;
    private Rectangle rect;
    private Circle cir;
    private List<Shape> allShapes = new ArrayList<>();

    /**
     * initialise controlleurs, variables and set up event handlers
     */
    @FXML
    public void initialize() {
        initializeControls();
        setupEventHandlers();
        setupShapes();
    }

    /**
     * initialization of controllers
     */
    private void initializeControls() {
        selectedColor = colorPicker.getValue();
        heightCaption = new Label();
        xValue.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                xValue.setText(newValue);
                if(selectedShape != null){
                    selectedShape.setX(Double.parseDouble(xValue.getText()));
                }
            }
        });
        gc = canvas.getGraphicsContext2D();
    }

    /**
     * creation of objects
     */
    private void setupShapes() {
        rect = Rectangle.Square(50, 50, 200, selectedColor);
        cir = new Circle(500, 50, 50, selectedColor);
    }

    /**
     * handles mouse events
     */
    private void setupEventHandlers() {
        canvas.setOnMouseClicked(this::handleMouseClicked);
        canvas.setOnMousePressed(this::handleMousePressed);
        canvas.setOnMouseDragged(this::handleMouseDragged);
    }

    /**
     * Handles the mouse click event
     *
     * @param mouseEvent mouse click event
     */
    private void handleMouseClicked(MouseEvent mouseEvent) {
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY();
        boolean shapeClicked = false;

        for (Shape shape : allShapes) {
            if (shape.contains(mouseX, mouseY)) {
                shapeClicked = true;
                selectedShape = shape;
                break;
            }
        }

        if(shapeClicked){
            drawShapeBorder();
        } else{
            clearShapeBorder();
        }
    }

    /**
     * draw the border of the shape
     */
    public void drawShapeBorder(){
        try {
            gc.setStroke(Color.GRAY);
            gc.setLineWidth(2.0);
            selectedShape.drawBorder(gc);
            drawShapes();
        } catch (NullPointerException e) {
            System.out.println("the selected Shape is NULL");
        }
    }

    /**
     * clear the border of the shape
     */
    public void clearShapeBorder() {
        try {
            if (selectedShape instanceof Rectangle){
                gc.clearRect(selectedShape.getX() - BORDER_OFFSET, selectedShape.getY() - BORDER_OFFSET,
                        rect.getWidth() + 2 * BORDER_OFFSET, rect.getHeight() + 2 * BORDER_OFFSET);
            } else if (selectedShape instanceof Circle){
                gc.clearRect(selectedShape.getX() - BORDER_OFFSET, selectedShape.getY() - BORDER_OFFSET,
                        cir.getRadius() + 2 * BORDER_OFFSET, cir.getRadius() + 2 * BORDER_OFFSET);
            }
            drawShapes();
        } catch (NullPointerException e) {
            System.out.println("the selected Shape is NULL");
        }
    }

    /**
     * Handles the mouse drag event
     *
     * @param mouseEvent mouse drag event
     */
    void handleMouseDragged(MouseEvent mouseEvent) {
    }

    /**
     * Handles the mouse press event
     *
     * @param mouseEvent mouse press event
     */
    private void handleMousePressed(MouseEvent mouseEvent) {
    }

    /**
     * draw a shape
     */
    public void drawShapes() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Shape shape : allShapes) {
            shape.draw(gc);
        }

        if (selectedShape instanceof Rectangle) {
            setRectProperties();
        } else if (selectedShape instanceof Circle) {
            setCircleProperties();
        }
    }

    /**
     * set all the properties of rectangle object
     */
    public void setRectProperties() {
        areaValue.setText(String.valueOf(rect.area()));
        perimeterValue.setText(String.valueOf(rect.perimeter()));

        xValue.setText(String.valueOf(rect.getX()));
        yValue.setText(String.valueOf(rect.getY()));

        widthOrRaduis.setText("Width : ");
        if (heightCaption != null) {
            heightCaption.setVisible(true);
        }
        heightValue.setVisible(true);

        widthValue.setText(String.valueOf(rect.getWidth()));
        heightValue.setText(String.valueOf(rect.getHeight()));
    }

    /**
     * set all the properties for a circle object
     */
    public void setCircleProperties() {
        areaValue.setText(String.valueOf(cir.area()));
        perimeterValue.setText(String.valueOf(cir.perimeter()));

        xValue.setText(String.valueOf(cir.getX()));
        yValue.setText(String.valueOf(cir.getY()));

        widthOrRaduis.setText("Radius : ");
        heightCaption.setVisible(false);
        heightValue.setVisible(false);

        widthValue.setText(String.valueOf(cir.getRadius()));
    }

    /**
     * handles the selection of a rectangle on the screen
     *
     * @param actionEvent event on the button
     */
    public void selectRect(ActionEvent actionEvent) {
        selectedShape = rect;
        allShapes.add(selectedShape);

        if (selectedShape != null) {
            drawShapes();
        }
    }

    /**
     * handles the selection of a rectangle on the screen
     *
     * @param actionEvent event on the button
     */
    public void selectTriangle(ActionEvent actionEvent) {
    }

    /**
     * handles the selection of a circle on the screen
     *
     * @param actionEvent
     */
    public void selectCircle(ActionEvent actionEvent) {
        allShapes.add(cir);
        selectedShape = cir;

        drawShapes();
    }

    /**
     * handles the change of a color on the screen
     *
     * @param actionEvent
     */
    public void changeColor(ActionEvent actionEvent) {
        selectedColor = colorPicker.getValue();

        if (selectedShape != null) {
            selectedShape.setColor(selectedColor);
            drawShapes(); // Redraw to reflect the color change
        }
    }

}