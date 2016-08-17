package com.henrydangprg.triangularmovement.application;

import com.henrydangprg.triangularmovement.component.Encoder;
import com.henrydangprg.triangularmovement.component.Ghost;
import com.henrydangprg.triangularmovement.component.Motor;
import com.henrydangprg.triangularmovement.component.Triangle;
import com.henrydangprg.triangularmovement.component.Vector;
import com.henrydangprg.triangularmovement.component.Wire;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// Java 8 code
public class Main extends Application {

	public final double WIDTH = 800;
	public final double HEIGHT = 600;
	public final double MOVE_SPEED = 1;
	public final double HEIGHT_SPEED = 0.25;

	boolean goNorth, goSouth, goEast, goWest, goUp, goDown;

	private Encoder topMotorEncoder, leftMotorEncoder, rightMotorEncoder;
	private Motor topMotor, leftMotor, rightMotor;
	private Triangle triangle;
	private Ghost ghost;
	private Wire wireA, wireB, wireC;
	private Vector ghostVector;

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

		triangle = new Triangle();

		topMotor = new Motor(topMotorEncoder);
		leftMotor = new Motor(leftMotorEncoder);
		rightMotor = new Motor(rightMotorEncoder);
		
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
				rightMotor.getMotorShape());

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

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {

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
			}
		};
		
		timer.start();
		stage.setTitle("Simulation");
		stage.setScene(scene);
		stage.show();

	}

	private void movementHandler(KeyEvent event) {
		switch (event.getCode()) {

		}
	}
}