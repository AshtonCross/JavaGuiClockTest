/*
 * Ashton Cross
 * CSC 1061 680
 * October 9th, 2022
 * Exercise 14.27
 * 
 * This program creats a clock pane, and displays the time underneath is.
 * 
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class DisplayClock extends Application {
    @Override
    public void start(Stage primaryStage) {
        // create a clock & label
        ClockPane clock = new ClockPane();

        Label time = new Label(
            clock.getHour() + ":" + clock.getMinute() + ":" + clock.getSecond());

        time.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.REGULAR, 24));
        
        // place clock and time in border pane
        BorderPane clockAndTime = new BorderPane();
        clockAndTime.setCenter(clock);
        clockAndTime.setBottom(time);

        // padding (20% of height/width) and alignment
        double xInset = 0.2 * clockAndTime.getWidth();
        double yInset = 0.2 * clockAndTime.getHeight();
        clockAndTime.setPadding(new Insets(yInset, xInset, yInset, xInset));

        BorderPane.setAlignment(time, Pos.TOP_CENTER);
        
        // scene and stage
        Scene scene = new Scene(clockAndTime, 600, 600);

        primaryStage.setTitle("Clock");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(260);
        primaryStage.setMinWidth(220);
        primaryStage.show();
    }
    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }
}
