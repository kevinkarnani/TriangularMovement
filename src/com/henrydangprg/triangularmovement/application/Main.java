package com.henrydangprg.triangularmovement.application;

import java.util.Timer;
import java.util.TimerTask;

import com.henrydangprg.triangularmovement.component.Encoder;
import com.henrydangprg.triangularmovement.component.Ghost;
import com.henrydangprg.triangularmovement.component.Motor;
import com.henrydangprg.triangularmovement.component.Triangle;
import com.henrydangprg.triangularmovement.component.Vector;
import com.henrydangprg.triangularmovement.component.Wire;
import com.henrydangprg.triangularmovement.utilities.MathUtil;

import javafx.animation.AnimationTimer;
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

	boolean goNorth, goSouth, goEast, goWest, goUp, goDown;

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

		wireA = new Wire(ghost);
		wireB = new Wire(ghost);
		wireC = new Wire(ghost);

		wireA.attachFrom(topMotor.getMotorCoordinate());
		wireB.attachFrom(leftMotor.getMotorCoordinate());
		wireC.attachFrom(rightMotor.getMotorCoordinate());
		
		ghostVector = new Vector();

		Group layout = new Group(triangle.getTriangle(), ghost.getGhost(),
				wireA.getLine(), wireB.getLine(), wireC.getLine(),
				topMotor.getMotorShape(), leftMotor.getMotorShape(),
				rightMotor.getMotorShape(),
				distanceLabel, encoderLabel,
				topDistance, leftDistance, rightDistance,
				topEncoderDisplay, leftEncoderDisplay, rightEncoderDisplay);
		
		topMotor.getEncoder().setValue(MathUtil.distFromPoints(topMotor.getMotorCoordinate(), ghost.getCoordinate()));
		leftMotor.getEncoder().setValue(MathUtil.distFromPoints(leftMotor.getMotorCoordinate(), ghost.getCoordinate()));
		rightMotor.getEncoder().setValue(MathUtil.distFromPoints(rightMotor.getMotorCoordinate(), ghost.getCoordinate()));

		topMotor.getMotorShape().toBack();
		leftMotor.getMotorShape().toBack();
		rightMotor.getMotorShape().toBack();

		Scene scene = new Scene(layout, WIDTH, HEIGHT, Color.AQUAMARINE);

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
					goNorth = true;
					break;
				case DOWN:
					goSouth = true;
					break;
				case LEFT:
					goWest = true;
					break;
				case RIGHT:
					goEast = true;
					break;
				case Z:
					goUp = true;
					break;
				case X:
					goDown = true;
					break;
				}
			}
		});
		

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
					goNorth = false;
					break;
				case DOWN:
					goSouth = false;
					break;
				case LEFT:
					goWest = false;
					break;
				case RIGHT:
					goEast = false;
					break;
				case Z:
					goUp = false;
					break;
				case X:
					goDown = false;
					break;
				}
			}
		});

		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				  if (goNorth) {
						ghostVector.setDeltaY(MOVE_SPEED);
					}
					if (goSouth) {
						ghostVector.setDeltaY(-MOVE_SPEED);
					}
					if (goEast) {
						ghostVector.setDeltaX(MOVE_SPEED);
					}
					if (goWest) {
						ghostVector.setDeltaX(-MOVE_SPEED);
					}
					if (goUp) {
						ghostVector.setDeltaZ(HEIGHT_SPEED);
					}
					if (goDown) {
						ghostVector.setDeltaZ(-HEIGHT_SPEED);
					}
					if(goEast && goWest){
						ghostVector.setDeltaX(0);
					}
					if(goNorth && goSouth){
						ghostVector.setDeltaY(0);
					}
					if(goEast && goWest){
						ghostVector.setDeltaX(0);
					}
					
					if (triangle.isInBounds(ghost.getNextCoordinate(ghostVector))) {
						ghost.moveGhost(ghostVector);
					}
					
					wireA.attachTo(ghost.getCoordinate());
					wireB.attachTo(ghost.getCoordinate());
					wireC.attachTo(ghost.getCoordinate());
					
					ghostVector.resetVector();
					
					topDistance.setText("Distance from Top: " + 
							MathUtil.distFromPoints(topMotor.getMotorCoordinate(), ghost.getCoordinate()));
					leftDistance.setText("Distance from Left: " + 
							MathUtil.distFromPoints(leftMotor.getMotorCoordinate(), ghost.getCoordinate()));
					rightDistance.setText("Distance from Right: " + 
							MathUtil.distFromPoints(rightMotor.getMotorCoordinate(), ghost.getCoordinate()));
				
					topEncoderDisplay.setText("Top Encoder: " + topMotor.getEncoderValue());
					leftEncoderDisplay.setText("Left Encoder: " + leftMotor.getEncoderValue());
					rightEncoderDisplay.setText("Right Encoder: " + rightMotor.getEncoderValue());
			  }
		}, delayInSimulation, pauseDuration);
		
		stage.setTitle("Simulation");
		stage.setScene(scene);
		stage.show();

	}

	private void movementHandler() {
		
	}
}