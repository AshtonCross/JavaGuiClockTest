/*
 * Ashton Cross
 * CSC 1061 680
 * October 9th, 2022
 * Exercise 14.27
 * 
 * This file defines the "Clock Pane" class, a custom pane that adds
 * a clock into itself displaying the current time.
 * 
 */

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClockPane extends Pane {
    private int hour;
    private int minute;
    private int second;
    
    public ClockPane() {
        setCurrentTime();        
    }

    public ClockPane(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
        paintClock();
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
        paintClock();
    }

    public int getSecond() { 
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
        paintClock();
    }

    public void setCurrentTime() {
        // calendar for current time and date
        Calendar calendar = new GregorianCalendar();

        //set current hour minute and second
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);

        paintClock();
    }

    // update clock    
    private void paintClock() {
        setMinSize(200, 200);

        double clockRadius =
            Math.min(getWidth(), getHeight()) * 0.8 * 0.5;
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;

        // draw circle
        Circle circle = new Circle(centerX, centerY, clockRadius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        // draw second hand
        double sLength = clockRadius * 0.8;
        double secondX = centerX + sLength *
            Math.sin(second * (2 * Math.PI / 60));
        double secondY = centerY - sLength * 
            Math.cos(second * (2 * Math.PI / 60));
        Line sLine = new Line(centerX, centerY, secondX, secondY);
        sLine.setStroke(Color.RED);

        // minute hand        
        double mLength = clockRadius * 0.65;
        double minuteX = centerX + mLength *
            Math.sin(minute * (2 * Math.PI / 60));
        double minuteY = centerY - mLength * 
            Math.cos(minute * (2 * Math.PI / 60));
        Line mLine = new Line(centerX, centerY, minuteX, minuteY);
        mLine.setStroke(Color.BLUE);

        // hour hand
        double hLength = clockRadius * 0.65;
        double hourX = centerX + hLength *
            Math.sin(hour * (2 * Math.PI / 60));
        double hourY = centerY - hLength * 
            Math.cos(hour * (2 * Math.PI / 60));
        Line hLine = new Line(centerX, centerY, hourX, hourY);
        hLine.setStroke(Color.GREEN);

        getChildren().clear();
        getChildren().addAll(circle, sLine, mLine, hLine);

        // indicate seconds on side of clock
        for (int i = 0; i < 60; i++) {
            // determine whether on minute or second
            double lineLength = clockRadius / 10; 

            if (i % 5 == 0)
                lineLength *= 2;

            // find outer point
            double rotation = (360/60) * i;

            double outerPointX = centerX + (Math.cos(Math.toRadians(rotation)) * clockRadius);
            double outerPointY = centerY + (Math.sin(Math.toRadians(rotation)) * clockRadius);

            // find inner point 
            double innerPointX = outerPointX - (Math.cos(Math.toRadians(rotation)) * lineLength);
            double innerPointY = outerPointY - (Math.sin(Math.toRadians(rotation)) * lineLength);

            Line test = new Line(innerPointX, innerPointY, outerPointX, outerPointY);

            getChildren().add(test);

            // add text if on minute hand
            if (i % 5 == 0) {
                // determine what hour it is
                String[] hourNumbers = {"3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "1", "2"};
                String numberString = hourNumbers[i / 5];

                // create the number 
                final int lineDistance = 15; /* distance between line and number */

                double numberX = innerPointX - (Math.cos(Math.toRadians(rotation)) * lineDistance) - 5 ;
                double numberY = innerPointY - (Math.sin(Math.toRadians(rotation)) * lineDistance) + 5 ;

                Text numberText = new Text(numberX, numberY, numberString);
                numberText.setFont(Font.font("Times New Roman", FontWeight.LIGHT, FontPosture.ITALIC, 20));

                getChildren().add(numberText);
            }

        }

        
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paintClock();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paintClock();
    }
}
