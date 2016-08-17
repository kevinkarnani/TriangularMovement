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

	public final double WIDTH = 800;
	public final double HEIGHT = 600;
	public final double MOVE_SPEED = 1;
	public final double HEIGHT_SPEED = 0.25;

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
		
		distanceLabel = new Text(520, 160, "Actual Distance from Motors");
		topDistance = new Text(520, 180, "Distance from Top: x");
		leftDistance = new Text(520, 200, "Distance from Left: x");
		rightDistance = new Text(520, 220, "Distance from Right: x");
		
		encoderLabel = new Text(520, 260, "Encoder Values");
		topEncoderDisplay = new Text(520, 280, "Top Encoder: x");
		leftEncoderDisplay = new Text(520, 300, "Left Encoder: x");
		rightEncoderDisplay = new Text(520, 320, "Right Encoder: x");

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
		}, 0, 20);
		
		stage.setTitle("Simulation");
		stage.setScene(scene);
		stage.show();

	}

	private void movementHandler() {
		
	}
}