package com.henrydangprg.triangularmovement.application;

import java.util.Timer;
import java.util.TimerTask;

import com.henrydangprg.triangularmovement.component.Ghost;
import com.henrydangprg.triangularmovement.component.Input;
import com.henrydangprg.triangularmovement.component.Motor;
import com.henrydangprg.triangularmovement.component.Triangle;
import com.henrydangprg.triangularmovement.component.Wire;
import com.henrydangprg.triangularmovement.utilities.Vector;
import com.henrydangprg.triangularmovement.utilities.MathUtil;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

// Java 8 code
public class Main extends Application {

	private final double X_COORDINATE_FOR_TEXT = 520;
	private final double Y_COORDINATE_FOR_DISTANCE_LABEL = 160;
	private final double Y_COORDINATE_FOR_TOP_DISTANCE_LABEL = 180;
	private final double Y_COORDINATE_FOR_LEFT_DISTANCE_LABEL = 200;
	private final double Y_COORDINATE_FOR_RIGHT_DISTANCE_LABEL = 220;
	private final double Y_COORDINATE_FOR_ENCODER_LABEL = 260;
	private final double Y_COORDINATE_FOR_TOP_ENCODER_LABEL = 280;
	private final double Y_COORDINATE_FOR_LEFT_ENCODER_LABEL = 300;
	private final double Y_COORDINATE_FOR_RIGHT_ENCODER_LABEL = 320;
	
	public final double WIDTH = 800.0;
	public final double HEIGHT = 600.0;
	public final double MOVE_SPEED = 1.0;
	public final double HEIGHT_SPEED = 0.25;
	
	private long delayInSimulation = 0;
	private long pauseDuration = 20;
	
	Input input = new Input();

	private Motor topMotor, leftMotor, rightMotor;
	private Triangle triangle;
	private Ghost ghost;
	private Wire wireA, wireB, wireC;
	private Vector ghostVector;
	
	private Text distanceLabel, encoderLabel;
	private Text topDistance, leftDistance, rightDistance;
	private Text topEncoderDisplay, leftEncoderDisplay, rightEncoderDisplay;
	
	Timer timer = new Timer();

	/**
	 * Launches the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		
		distanceLabel = new Text(X_COORDINATE_FOR_TEXT, Y_COORDINATE_FOR_DISTANCE_LABEL, "Actual Distance from Motors");
		topDistance = new Text(X_COORDINATE_FOR_TEXT, Y_COORDINATE_FOR_TOP_DISTANCE_LABEL, "Distance from Top: x");
		leftDistance = new Text(X_COORDINATE_FOR_TEXT, Y_COORDINATE_FOR_LEFT_DISTANCE_LABEL, "Distance from Left: x");
		rightDistance = new Text(X_COORDINATE_FOR_TEXT, Y_COORDINATE_FOR_RIGHT_DISTANCE_LABEL, "Distance from Right: x");
		
		encoderLabel = new Text(X_COORDINATE_FOR_TEXT, Y_COORDINATE_FOR_ENCODER_LABEL, "Encoder Values");
		topEncoderDisplay = new Text(X_COORDINATE_FOR_TEXT, Y_COORDINATE_FOR_TOP_ENCODER_LABEL, "Top Encoder: x");
		leftEncoderDisplay = new Text(X_COORDINATE_FOR_TEXT, Y_COORDINATE_FOR_LEFT_ENCODER_LABEL, "Left Encoder: x");
		rightEncoderDisplay = new Text(X_COORDINATE_FOR_TEXT, Y_COORDINATE_FOR_RIGHT_ENCODER_LABEL, "Right Encoder: x");

		triangle = new Triangle();
		
		topMotor = new Motor();
		leftMotor = new Motor();
		rightMotor = new Motor();
		
		topMotor.setPosition(triangle.getTopVertex());
		leftMotor.setPosition(triangle.getLeftVertex());
		rightMotor.setPosition(triangle.getRightVertex());

		ghost = new Ghost(topMotor, leftMotor, rightMotor);
		ghost.resetToRight();

		wireA = new Wire(ghost.getCoordinate());
		wireB = new Wire(ghost.getCoordinate());
		wireC = new Wire(ghost.getCoordinate());

		wireA.attachFrom(topMotor.getCoordinate());
		wireB.attachFrom(leftMotor.getCoordinate());
		wireC.attachFrom(rightMotor.getCoordinate());
		
		ghostVector = new Vector();

		Group layout = new Group(triangle.getTriangle(), ghost.getGhost(),
				wireA.getLine(), wireB.getLine(), wireC.getLine(),
				topMotor.getMotorShape(), leftMotor.getMotorShape(),
				rightMotor.getMotorShape(),
				distanceLabel, encoderLabel,
				topDistance, leftDistance, rightDistance,
				topEncoderDisplay, leftEncoderDisplay, rightEncoderDisplay);
		
		topMotor.getEncoder().setValue(MathUtil.distFromPoints(topMotor.getCoordinate(), ghost.getCoordinate()));
		leftMotor.getEncoder().setValue(MathUtil.distFromPoints(leftMotor.getCoordinate(), ghost.getCoordinate()));
		rightMotor.getEncoder().setValue(MathUtil.distFromPoints(rightMotor.getCoordinate(), ghost.getCoordinate()));

		topMotor.getMotorShape().toBack();
		leftMotor.getMotorShape().toBack();
		rightMotor.getMotorShape().toBack();

		Scene scene = new Scene(layout, WIDTH, HEIGHT, Color.AQUAMARINE);

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				input.setKeyState(event.getCode().toString(), true);
			}
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				input.setKeyState(event.getCode().toString(), false);
			}
		});

		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (input.getKeyState("UP")) {
					ghostVector.setDeltaY(MOVE_SPEED);
				}
				if (input.getKeyState("DOWN")) {
					ghostVector.setDeltaY(-MOVE_SPEED);
				}
				if (input.getKeyState("RIGHT")) {
					ghostVector.setDeltaX(MOVE_SPEED);
				}
				if (input.getKeyState("LEFT")) {
					ghostVector.setDeltaX(-MOVE_SPEED);
				}
				if (input.getKeyState("Z")) {
					ghostVector.setDeltaZ(HEIGHT_SPEED);
				}
				if (input.getKeyState("X")) {
					ghostVector.setDeltaZ(-HEIGHT_SPEED);
				}
				if (input.isClashing("LEFT", "RIGHT")){
					ghostVector.setDeltaX(0);
				}
				if (input.isClashing("UP", "DOWN")){
					ghostVector.setDeltaY(0);
				}
				
				if (triangle.isInBounds(ghost.getNextCoordinate(ghostVector))) {
					ghost.moveGhost(ghostVector);
				}
				
				ghostVector.resetVector();
				  
				wireA.attachTo(ghost.getCoordinate());
				wireB.attachTo(ghost.getCoordinate());
				wireC.attachTo(ghost.getCoordinate());
				  
				if (input.isKeyPressed()) {
					topDistance.setText("Distance from Top: " + 
							MathUtil.distFromPoints(topMotor.getCoordinate(), ghost.getCoordinate()));
					leftDistance.setText("Distance from Left: " + 
							MathUtil.distFromPoints(leftMotor.getCoordinate(), ghost.getCoordinate()));
					rightDistance.setText("Distance from Right: " + 
							MathUtil.distFromPoints(rightMotor.getCoordinate(), ghost.getCoordinate()));
					
					topEncoderDisplay.setText("Top Encoder: " + topMotor.getEncoderValue());
					leftEncoderDisplay.setText("Left Encoder: " + leftMotor.getEncoderValue());
					rightEncoderDisplay.setText("Right Encoder: " + rightMotor.getEncoderValue());
				}
			}
		}, delayInSimulation, pauseDuration);
		
		stage.setTitle("Simulation");
		stage.setScene(scene);
		stage.show();

	}
}