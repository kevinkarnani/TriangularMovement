package com.henrydangprg.triangularmovement.application;

import com.henrydangprg.triangularmovement.component.Encoder;
import com.henrydangprg.triangularmovement.component.Ghost;
import com.henrydangprg.triangularmovement.component.Motor;
import com.henrydangprg.triangularmovement.component.Triangle;
import com.henrydangprg.triangularmovement.component.Wire;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

// Java 8 code
public class Main extends Application {

	public final double WIDTH = 800;
	public final double HEIGHT = 600;
	
	boolean goNorth, goSouth, goEast, goWest;
	
	private Encoder aMotorEncoder, bMotorEncoder, cMotorEncoder;
	private Motor aMotor, bMotor, cMotor;
	private Triangle triangle;
	private Ghost ghost;
	private Wire wireA, wireB, wireC;

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

		aMotor = new Motor(triangle.getTopVertex(), aMotorEncoder);
		bMotor = new Motor(triangle.getLeftVertex(), bMotorEncoder);
		cMotor = new Motor(triangle.getRightVertex(), cMotorEncoder);
		
		ghost = new Ghost(aMotor, bMotor, cMotor);
		ghost.resetToCenter();
		
		wireA = new Wire(ghost);
		wireB = new Wire(ghost);
		wireC = new Wire(ghost);
		
		wireA.attachFrom(aMotor.getMotorPosition());
		wireB.attachFrom(bMotor.getMotorPosition());
		wireC.attachFrom(cMotor.getMotorPosition());
		
		ghost.resetToCenter();

		Group layout = new Group(triangle.getTriangle(), ghost.getGhost(),
				 				 wireA.getLine(), wireB.getLine(), wireC.getLine(),
								 aMotor.getMotorShape(), bMotor.getMotorShape(), cMotor.getMotorShape());

		aMotor.getMotorShape().toBack();
		bMotor.getMotorShape().toBack();
		cMotor.getMotorShape().toBack();

		
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
				}
			}
		});

		AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
        		
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