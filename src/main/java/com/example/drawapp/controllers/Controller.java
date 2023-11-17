package com.example.drawapp.controllers;

import com.example.drawapp.models.Circle;
import com.example.drawapp.models.Ellipse;
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

import java.util.*;

public class Controller {

    @FXML
    public Canvas canvas;
    @FXML
    public ColorPicker colorPicker;
    @FXML
    public Label areaValue, perimeterValue, widthOrRaduis, heightCaption;
    @FXML
    public TextField xValue, yValue, widthValue, heightValue;
    @FXML
    public Button rectShape, circleShape;
    private Color selectedColor;
    private GraphicsContext gc;
    private Shape selectedShape;
    private Rectangle rect, square;
    // private Circle cir;
    private Ellipse cir, ellipse;
    private Set<Shape> allShapes = new HashSet<>();
    double startX, startY, endX, endY;

    /**
     * initialise controlleurs, variables and set up event handlers
     */
    @FXML
    public void initialize() {
        initializeControls();
        setupEventHandlers();
    }

    /**
     * initialization of controllers
     */
    private void initializeControls() {

        selectedColor = colorPicker.getValue();
        gc = canvas.getGraphicsContext2D();

        xValue.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                xValue.setText(newValue);
                if (selectedShape != null) {
                    selectedShape.setX(Double.parseDouble(newValue));
                }
            }
        });
        yValue.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                yValue.setText(newValue);
                if (selectedShape != null) {
                    selectedShape.setY(Double.parseDouble(newValue));
                }
            }
        });

        // drawShapes();

    }

    /**
     * handles mouse events
     */
    private void setupEventHandlers() {
        canvas.setOnMousePressed(e -> {
            if (e.isShiftDown()) {
                gc.beginPath();
                startX = e.getX();
                startY = e.getY();
                gc.moveTo(startX, startY);
            } else {
                handleRightMousePressed(e);
            }
        });
        canvas.setOnMouseDragged(e -> {
            if (e.isShiftDown()) {
                endX = e.getX();
                endY = e.getY();
            } else {
                handleRightMouseDragged(e);
            }
        });
        canvas.setOnMouseReleased(e -> {
            if (e.isShiftDown()) {
                endX = e.getX();
                endY = e.getY();
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.rect(startX, startY, endX - startX, endY - startY);
                gc.stroke();
            }else{
                handleRightMouseReleased(e);
            }
        });

    }

    /**
     * if the right click of the mouse is realeased
     * @param e
     * @return boolean
     */
    private void handleRightMouseReleased(MouseEvent e) {
        drawShapes();
    }

    /**
     * move a shape is the right mouse dragged
     * @param e
     */
    public void handleRightMouseDragged(MouseEvent e) {
        endX = e.getX();
        endY = e.getY();

        // Calculate the translation values
        double deltaX = endX - startX;
        double deltaY = endY - startY;


        if (handleRightMousePressed(e) instanceof Rectangle) {
            Rectangle rect = (Rectangle) handleRightMousePressed(e);
            rect.move(deltaX, deltaY);
        } else if (handleRightMousePressed(e) instanceof Circle) {
            Ellipse cir = (Ellipse) handleRightMousePressed(e);
            cir.move(deltaX, deltaY);
        }

        // Update start coordinates for the next drag event
        startX = endX;
        startY = endY;
    }

    /**
     * return a shape if the mouse is in that shape
     * @param mouseEvent
     * @return
     */
    public Shape handleRightMousePressed(MouseEvent mouseEvent) {
        startX = mouseEvent.getX();
        startY = mouseEvent.getY();

        // Check if the mouse is inside any shape
        for (Shape shape : allShapes) {
            if (shape.isSelected()){
                return shape;
            }
        }

        return null;
    }

    /**
     * draw a shape
     */
    public void drawShapes() {
        //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Shape shape : allShapes) {
            if (shape.isSelected()) {
                if (shape instanceof Rectangle) {
                    drawRectangle((Rectangle) shape);
                } else if (shape instanceof Ellipse) {
                    drawEllipse((Ellipse) shape);
                }
            }
        }
    }

    /**
     * set all the properties of rectangle object
     */
    public void setRectProperties(Rectangle shape) {
        areaValue.setText(String.valueOf(shape.area()));
        perimeterValue.setText(String.valueOf(shape.perimeter()));

        xValue.setText(String.valueOf(shape.getX()));
        yValue.setText(String.valueOf(shape.getY()));

        widthOrRaduis.setText("Width : ");

        widthValue.setText(String.valueOf(shape.getWidth()));
        heightValue.setText(String.valueOf(shape.getHeight()));

        //if (shape.isSelected())
    }

    /**
     * set all the properties for a circle object
     */
    public void setCircleProperties(Ellipse shape) {
        areaValue.setText(String.valueOf(shape.area()));
        perimeterValue.setText(String.valueOf(shape.perimeter()));

        xValue.setText(String.valueOf(shape.getX()));
        yValue.setText(String.valueOf(shape.getY()));

        widthOrRaduis.setText("Radius : ");

        widthValue.setText(String.valueOf(shape.getWidth()));
        heightValue.setText(String.valueOf(shape.getHeight()));

        //if(shape.isSelected())
    }

    /**
     * handles the selection of a rectangle on the screen
     */
    public void selectRect() {
        double width = endX - startX;
        double height = endY - startY;

        rect = new Rectangle(startX, startY, width, height, selectedColor);
        allShapes.add(rect);
        selectedShape = rect;
        rect.setSelected(true);
        drawShapes();
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
     */
    public void selectCircle() {
        double diameter = Math.min(endX - startX, endY - startY);
        cir = Ellipse.Circle(startX, startY, diameter / 2, selectedColor);
        allShapes.add(cir);
        selectedShape = cir;
        cir.setSelected(true);

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
            drawSelectedShape(); // Redraw only the selected shape
        }
    }

    private void drawSelectedShape() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        if (selectedShape != null) {
            if (selectedShape instanceof Rectangle) {
                drawRectangle((Rectangle) selectedShape);
            } else if (selectedShape instanceof Ellipse) {
                drawEllipse((Ellipse) selectedShape);
            }
        }
    }

    private void drawEllipse(Ellipse shape) {
        shape.draw(gc);
        setCircleProperties(shape);
    }

    private void drawRectangle(Rectangle shape) {
        shape.draw(gc);
        setRectProperties(shape);
    }

    /**
     * selection of a square(rectangle with width = height)
     * @param actionEvent
     */
    public void selectSquare(ActionEvent actionEvent) {
        double width = endX - startX;
        double height = endY - startY;

        square = Rectangle.Square(startX, startY, width, selectedColor);
        allShapes.add(square);
        selectedShape = square;
        square.setSelected(true);

        drawShapes();
    }

    /**
     * selection of an ellipse
     * @param actionEvent
     */
    public void selectEllipse(ActionEvent actionEvent) {
        double width = endX - startX;
        double height = endY - startY;

        ellipse = new Ellipse(startX, startY, width / 2, height / 2, selectedColor);
        allShapes.add(ellipse);
        selectedShape = ellipse;
        ellipse.setSelected(true);

        drawShapes();
    }
}