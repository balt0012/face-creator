package com.example.facecreator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class HelloApplication extends Application {
    static int width = 600;
    static int height = 600;
    static Canvas canvas = new Canvas(width, height);
    static GraphicsContext gc = canvas.getGraphicsContext2D();


    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Group root = new Group();
        Scene scene = new Scene(root, width, height);
        stage.setTitle("Face");
        stage.setScene(scene);


        gc.clearRect(0, 0, width, height);
        root.getChildren().add(canvas);

        drawRandomFace();
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            gc.clearRect(0, 0, width, height);
            try {
                drawRandomFace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        timeline.setCycleCount(500);
        timeline.play();

    }

    public static void drawRandomFace(){
        Random random = new Random();
        int headWidth = getRandomInt(320, 280);
        int headHeight = getRandomInt(320, 280);
        int mouthWidth = getRandomInt(100, 0);
        int mouthHeight = getRandomInt(20, -20);
        int eyeWidth = getRandomInt(30, 10);
        int eyeNarrowness = getRandomInt(30, 10);
        int eyeSeparation = getRandomInt(10, -10);
        int eyeHeight = getRandomInt(250, 230);
        int noseLength = getRandomInt(10, -10);
        int noseHeight = getRandomInt(10, -10);
        int noseWidth = getRandomInt(10, -10);
        int hairRed = getRandomInt(255, 0);
        int hairGreen = getRandomInt(255, 0);
        int hairBlue = getRandomInt(255, 0);
        int eyeRed = getRandomInt(255, 0);
        int eyeGreen = getRandomInt(255, 0);
        int eyeBlue = getRandomInt(255, 0);
        drawRandomHead(headHeight, headWidth);
        drawRandomMouth(mouthWidth, mouthHeight);
        drawRandomEyes(eyeWidth, eyeNarrowness, eyeSeparation, eyeHeight, eyeRed, eyeGreen, eyeBlue);
        drawRandomNose(noseLength, noseHeight, noseWidth);
        drawRandomHair(hairRed, hairGreen, hairBlue, headWidth);
    }

    public static int getRandomInt (int maxValue, int minValue){
        Random random = new Random();
        return random.nextInt(maxValue-minValue) + minValue;
    }

    public static void drawRandomHead(int headHeight, int headWidth){
        gc.setFill(Color.BLACK);
        gc.strokeOval(150, 150, headWidth, headHeight);
    }

    public static void drawRandomMouth(int mouthWidth, int mouthHeight){
        gc.setFill(Color.BLACK);
        gc.fillOval(280-(mouthWidth/2),350+mouthHeight,50+mouthWidth,50);
    }

    public static void drawRandomEyes(int eyeWidth, int eyeNarrowness, int eyeSeparation, int eyeHeight, int eyeRed, int eyeGreen, int eyeBlue){
        gc.setFill(Color.rgb(eyeRed,eyeGreen,eyeBlue));
        gc.fillOval(220 + eyeSeparation,eyeHeight,eyeWidth,eyeNarrowness);
        if(eyeNarrowness >=25){
            eyeNarrowness = 5;
        }
        gc.fillOval(360 - eyeSeparation,eyeHeight,eyeWidth,eyeNarrowness);
    }

    public static void drawRandomNose(int noseLength, int noseHeight, int noseWidth){
        gc.setFill(Color.BLACK);
        gc.fillPolygon(new double[]{300, 300, 330+noseWidth}, new double[]{320+noseHeight+noseLength, 270+noseHeight, 320+noseHeight+noseLength}, 3);
    }

    public static void drawRandomHair(int hairRed, int hairGreen, int hairBlue, int headWidth){
        gc.setFill(Color.rgb(hairRed,hairGreen,hairBlue));
        gc.fillArc(170, 130, headWidth-35, 150, 350, 200, ArcType.ROUND);
    }

    public static void main(String[] args) {
        launch();
    }
}