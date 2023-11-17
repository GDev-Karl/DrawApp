package com.example.drawapp.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * this class define a shape and its method that some shapes must implement
 */
public abstract class Shape {
    protected double x;
    protected double y;
    protected Color color;
    protected boolean isSelected = false;
    public static final double BORDER_OFFSET = 2;

    /**
     * Constructor of the shape
     *
     * @param x     x-coordonate
     * @param y     y-coordonate
     * @param color color of the shape
     */
    public Shape(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * is implemented by the shape types we can build
     *
     * @param context : where we are going to draw
     */
    public abstract void draw(GraphicsContext context);

    /**
     * Draw the border of the shape
     *
     * @param context
     */
    public abstract void drawBorder(GraphicsContext context);

    /**
     * clear the border of the shape
     *
     * @param context
     */
    public abstract void clearBorder(GraphicsContext context);

    /**
     * @return: the area of the Shape
     */
    public abstract double area();

    /**
     * @return: the perimeter of the shape
     */
    public abstract double perimeter();

    /**
     * Check if the mouse is in the shape
     *
     * @param mouseX
     * @param mouseY
     * @return true if the mouse is in the shape false otherwise
     */
    public abstract boolean contains(double mouseX, double mouseY);


    /**
     * Move the shape by the specified delta values.
     *
     * @param deltaX the change in X coordinate
     * @param deltaY the change in Y coordinate
     */
    public void move(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    /**
     * set the value of x
     *
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * set the value of y
     *
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Set the color of the rectangle
     *
     * @param color Color of the rectangle
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the coordonate on the x axis
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the coordonate on the y axis
     */
    public double getY() {
        return this.y;
    }

    /**
     * @return: get the color of the Shape
     */
    public Color getColor() {
        return this.color;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

