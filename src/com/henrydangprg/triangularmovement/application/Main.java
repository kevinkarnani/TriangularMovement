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
	private final double HEIGHT_SPEED = 3;
	private final double VECTOR_EPSILON = 3;
	
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
		
		//Sets the text displays to show data.
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
		
		//Creates a triangle with custom dimensions
		triangle = new Triangle(500, 400, 100);
		
		//Creates 3 motor objects of the skycam
		topMotor = new Motor();
		leftMotor = new Motor();
		rightMotor = new Motor();
		
		//Sets the 3 motor position to each vertex of the triangle
		topMotor.setPosition(triangle.getTopVertex());
		leftMotor.setPosition(triangle.getLeftVertex());
		rightMotor.setPosition(triangle.getRightVertex());

		//Creates a ghost object and resets its position to the right vertex
		ghost = new Ghost(leftMotor, topMotor, rightMotor);
		ghost.setDepthLimit(triangle.getDepth());
		ghost.resetToRight();

		//Creates new wires to attach to the ghost
		wireA = new Wire();
		wireB = new Wire();
		wireC = new Wire();

		//Attaches the wires to each of the 3 motors
		wireA.attachFrom(topMotor.getCoordinate());
		wireB.attachFrom(leftMotor.getCoordinate());
		wireC.attachFrom(rightMotor.getCoordinate());
		
		//Creates a vector variable used to calculate the ghost movement
		ghostVector = new Vector();

		//Creates a layout to show the simulation on screen
		Group layout = new Group(triangle.getTriangle(), ghost.getGhost(),
				wireA.getLine(), wireB.getLine(), wireC.getLine(),
				topMotor.getMotorShape(), leftMotor.getMotorShape(),
				rightMotor.getMotorShape(),
				distanceDisplay, encoderDisplay, ghostCoordDisplay,
				motorSpeedDisplay, controlDisplay);
		
		//Sets the starting encoder values of the ghost.
		topMotor.getEncoder().setValue(ghost.getDistanceFromTop());
		leftMotor.getEncoder().setValue(ghost.getDistanceFromLeft());
		rightMotor.getEncoder().setValue(ghost.getDistanceFromRight());

		//Moves the motor shape to the back to not overlap the triangle shape.
		topMotor.getMotorShape().toBack();
		leftMotor.getMotorShape().toBack();
		rightMotor.getMotorShape().toBack();

		//Creates a window to show the simulation.
		Scene scene = new Scene(layout, WIDTH, HEIGHT, Color.AQUAMARINE);

		//Sets a button press to true when a button is pressed
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				input.setKeyState(event.getCode().toString(), true);
			}
		});
		
		//Sets a button press to false when a button is released
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				input.setKeyState(event.getCode().toString(), false);
			}
		});
		
		//Runs the simulation at 60 fps
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				Platform.runLater(new Runnable() {
					public void run() {
						//Uses arrow keys as dummy joystick values
						if (input.isInputPressed()) {
							if (input.getKeyState("LEFT")) {
								ghostVector.findVectorComponents(VECTOR_EPSILON, 180);
							}
							if (input.getKeyState("RIGHT")) {
								ghostVector.findVectorComponents(VECTOR_EPSILON, 0);
							}
							if (input.getKeyState("UP")) {
								ghostVector.findVectorComponents(VECTOR_EPSILON, 270);
							}
							if (input.getKeyState("DOWN")) {
								ghostVector.findVectorComponents(VECTOR_EPSILON, 90);
							}
							if (input.isClashing("UP", "LEFT")) {
								ghostVector.findVectorComponents(VECTOR_EPSILON, 225);
							}
							if (input.isClashing("UP", "RIGHT")) {
								ghostVector.findVectorComponents(VECTOR_EPSILON, 315);
							}
							if (input.isClashing("DOWN", "LEFT")) {
								ghostVector.findVectorComponents(VECTOR_EPSILON, 135);
							}
							if (input.isClashing("DOWN", "RIGHT")) {
								ghostVector.findVectorComponents(VECTOR_EPSILON, 45);
							}
							if (input.isClashing("UP", "DOWN") || input.isClashing("LEFT", "RIGHT")) {
								ghostVector.setDeltaX(0);
								ghostVector.setDeltaY(0);
							}
			
							if (input.getKeyState("Z")) ghostVector.setDeltaZ(HEIGHT_SPEED);
							if (input.getKeyState("X")) ghostVector.setDeltaZ(-HEIGHT_SPEED);
						}
						
						//Calculates whether the next coordinate will move the ghost
						//out of bounds. If it does, it will stop the ghost.
						ghost.calcNextCoordinate(ghostVector);
						if (!triangle.isInBounds(ghost.getNextCoordinate())) {
							ghostVector.multiplyVector(0);
						}

						//Sets the motor speeds and updates ghost
						ghost.calcMotorSpeeds(ghostVector);
						ghost.calibrate();
						
						//Constantly rotates the motors
						topMotor.rotate();
						leftMotor.rotate();
						rightMotor.rotate();
						
						//Displays statistics for the simulation
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
						
						//Resets the vector back to 0
						ghostVector.resetVector();
						
						//Attaches the wire to the updated ghost position
						wireA.attachTo(ghost.getCoordinate());
						wireB.attachTo(ghost.getCoordinate());
						wireC.attachTo(ghost.getCoordinate());
					}
				});
			}
		}, delayInSimulation, pauseDuration);
		
		//Shows the simulation on screen
		stage.setTitle("Simulation");
		stage.setScene(scene);
		stage.show();
		
		//Closes the entire process to exit the application
		stage.setOnCloseRequest(event -> System.exit(0));
	}
}