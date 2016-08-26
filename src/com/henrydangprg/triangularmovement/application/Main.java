package com.henrydangprg.triangularmovement.application;

import java.util.Timer;
import java.util.TimerTask;

import com.henrydangprg.triangularmovement.component.Ghost;
import com.henrydangprg.triangularmovement.component.Motor;
import com.henrydangprg.triangularmovement.component.Triangle;
import com.henrydangprg.triangularmovement.component.Wire;
import com.henrydangprg.triangularmovement.utilities.Input;
import com.henrydangprg.triangularmovement.utilities.Vector;

import javafx.application.Application;
import javafx.application.Platform;
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
	private final double Y_COORDINATE_FOR_DISTANCE_LABEL = 80;
	private final double Y_COORDINATE_FOR_ENCODER_LABEL = 180;
	private final double Y_COORDINATE_FOR_GHOST_LABEL = 280;
	private final double Y_COORDINATE_FOR_MOTOR_SPEED_LABEL = 360;
	private final double Y_COORDINATE_FOR_CONTROL_LABEL = 440;
	
	private final double WIDTH = 800.0;
	private final double HEIGHT = 600.0;
	private final double HEIGHT_SPEED = 1.5;
	private double moveSpeed = 1;
	
	private long delayInSimulation = 0;
	private long pauseDuration = 1000/60;
	
	private Input input = new Input();

	private Motor topMotor, leftMotor, rightMotor;
	private Triangle triangle;
	private Ghost ghost;
	private Wire wireA, wireB, wireC;
	private Vector ghostVector;
	
	private Text distanceDisplay;
	private Text encoderDisplay;
	private Text ghostCoordDisplay;
	private Text motorSpeedDisplay;
	private Text controlDisplay;
	
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
		
		distanceDisplay = new Text(X_COORDINATE_FOR_TEXT, Y_COORDINATE_FOR_DISTANCE_LABEL, "Actual Distance from Motors"
				+ "\nDistance from Top: x"
				+ "\nDistance from Left: x"
				+ "\nDistance from Right: x");
		
		encoderDisplay = new Text(X_COORDINATE_FOR_TEXT, Y_COORDINATE_FOR_ENCODER_LABEL, "Encoder Values" 
				+ "\nTop Encoder: x"
				+ "\nLeft Encoder: x"
				+ "\nRight Encoder: x");

		ghostCoordDisplay = new Text(X_COORDINATE_FOR_TEXT, Y_COORDINATE_FOR_GHOST_LABEL, "Ghost Coord: x, x, x");
		
		motorSpeedDisplay = new Text(X_COORDINATE_FOR_TEXT, Y_COORDINATE_FOR_MOTOR_SPEED_LABEL, "Top Motor Speed: x"
				+ "\nLeft Motor Speed: x"
				+ "\nRight Motor Speed: x");
		
		controlDisplay = new Text(X_COORDINATE_FOR_TEXT, Y_COORDINATE_FOR_CONTROL_LABEL, "Up: x"
				+ "\nDown: x"
				+ "\nLeft: x"
				+ "\nRight: x"
				+ "\nZ: x"
				+ "\nX: x");
		
		triangle = new Triangle(300, 200, 100);
		
		topMotor = new Motor();
		leftMotor = new Motor();
		rightMotor = new Motor();
		
		topMotor.setPosition(triangle.getTopVertex());
		leftMotor.setPosition(triangle.getLeftVertex());
		rightMotor.setPosition(triangle.getRightVertex());

		ghost = new Ghost(leftMotor, topMotor, rightMotor);
		ghost.setDepthLimit(triangle.getDepth());
		ghost.resetToRight();

		wireA = new Wire();
		wireB = new Wire();
		wireC = new Wire();

		wireA.attachFrom(topMotor.getCoordinate());
		wireB.attachFrom(leftMotor.getCoordinate());
		wireC.attachFrom(rightMotor.getCoordinate());
		
		ghostVector = new Vector();
		
		moveSpeed = topMotor.getMotorSpeed();

		Group layout = new Group(triangle.getTriangle(), ghost.getGhost(),
				wireA.getLine(), wireB.getLine(), wireC.getLine(),
				topMotor.getMotorShape(), leftMotor.getMotorShape(),
				rightMotor.getMotorShape(),
				distanceDisplay, encoderDisplay, ghostCoordDisplay,
				motorSpeedDisplay, controlDisplay);
		
		topMotor.getEncoder().setValue(ghost.getDistanceFromTop());
		leftMotor.getEncoder().setValue(ghost.getDistanceFromLeft());
		rightMotor.getEncoder().setValue(ghost.getDistanceFromRight());

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
			public void run() {
				Platform.runLater(new Runnable() {
					public void run() {
						if (input.isInputPressed()) {
							if (input.getKeyState("LEFT")) ghostVector.setDeltaX(-moveSpeed);
							if (input.getKeyState("RIGHT")) ghostVector.setDeltaX(moveSpeed);
							if (input.getKeyState("UP")) ghostVector.setDeltaY(-moveSpeed);
							if (input.getKeyState("DOWN")) ghostVector.setDeltaY(moveSpeed);
							if (input.isClashing("UP", "LEFT")) {
								ghostVector.setDeltaX(-moveSpeed * Math.cos(Math.toRadians(45)));
								ghostVector.setDeltaY(-moveSpeed * Math.sin(Math.toRadians(45)));
							}
							if (input.isClashing("UP", "RIGHT")) {
								ghostVector.setDeltaX(moveSpeed * Math.cos(Math.toRadians(45)));
								ghostVector.setDeltaY(-moveSpeed * Math.sin(Math.toRadians(45)));
							}
							if (input.isClashing("DOWN", "LEFT")) {
								ghostVector.setDeltaX(-moveSpeed * Math.cos(Math.toRadians(45)));
								ghostVector.setDeltaY(moveSpeed * Math.sin(Math.toRadians(45)));
							}
							if (input.isClashing("DOWN", "RIGHT")) {
								ghostVector.setDeltaX(moveSpeed * Math.cos(Math.toRadians(45)));
								ghostVector.setDeltaY(moveSpeed * Math.sin(Math.toRadians(45)));
							}
							if (input.isClashing("UP", "DOWN") || input.isClashing("LEFT", "RIGHT")) {
								ghostVector.setDeltaX(0);
								ghostVector.setDeltaY(0);
							}
			
							if (input.getKeyState("Z")) ghostVector.setDeltaZ(HEIGHT_SPEED);
							if (input.getKeyState("X")) ghostVector.setDeltaZ(-HEIGHT_SPEED);
						}
						
						ghostVector.multiplyVector(2);
						
						if (triangle.isInBounds(ghost.getNextCoordinate(ghostVector))) {
							ghostVector.multiplyVector(0.5);
							ghost.calcMotorSpeeds(ghostVector);
							ghost.setPosition(ghost.getPosition());
						} else {
							ghostVector.multiplyVector(0);
							ghost.calcMotorSpeeds(ghostVector);
							ghost.setPosition(ghost.getPosition());
						}
						
						topMotor.rotate();
						leftMotor.rotate();
						rightMotor.rotate();
						
						distanceDisplay.setText("Actual Distance from Motors"
								+ "\nDistance from Top: " + ghost.getDistanceFromTop()
								+ "\nDistance from Left: " + ghost.getDistanceFromLeft()
								+ "\nDistance from Right: " + ghost.getDistanceFromRight());
						
						encoderDisplay.setText("Encoder Values" 
								+ "\nTop Encoder: " + topMotor.getEncoderValue()
								+ "\nLeft Encoder: " + leftMotor.getEncoderValue()
								+ "\nRight Encoder: " + rightMotor.getEncoderValue());
						
						ghostCoordDisplay.setText("Ghost Coord: " + ghost.getCoordinate().getX() + "\n "
								                           + ghost.getCoordinate().getY() + "\n "
								                           + ghost.getCoordinate().getZ());
						
						motorSpeedDisplay.setText("Top Motor Speed: " + topMotor.getSpeed()
								+ "\nLeft Motor Speed: " + leftMotor.getSpeed()
								+ "\nRight Motor Speed: " + rightMotor.getSpeed());
						
						controlDisplay.setText("Up: " + input.getKeyState("UP")
								+ "\nDown: " + input.getKeyState("DOWN")
								+ "\nLeft: " + input.getKeyState("LEFT")
								+ "\nRight: " + input.getKeyState("RIGHT")
								+ "\nZ: " + input.getKeyState("Z")
								+ "\nX: " + input.getKeyState("X"));
						
						ghostVector.resetVector();
						
						wireA.attachTo(ghost.getCoordinate());
						wireB.attachTo(ghost.getCoordinate());
						wireC.attachTo(ghost.getCoordinate());
					}
				});
			}
		}, delayInSimulation, pauseDuration);
		
		stage.setTitle("Simulation");
		stage.setScene(scene);
		stage.show();
		
		stage.setOnCloseRequest(event -> System.exit(0));
	}
}