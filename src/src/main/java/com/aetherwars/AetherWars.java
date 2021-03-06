package com.aetherwars;

import com.aetherwars.core.DisplayManager;
import com.aetherwars.core.GameManager;
import com.aetherwars.events.OnPhaseChange;
import javafx.application.Application;
import javafx.stage.Stage;

import com.aetherwars.models.*;

public class AetherWars extends Application {
    @Override
    public void start(Stage stage) {
        try{
            GameManager manager = GameManager.getInstance();

            DisplayManager dm = DisplayManager.getInstance();
            dm.init(stage);
            manager.addSubscriber(dm);
            manager.sendEvent(new OnPhaseChange(this, Phase.SELECT));
        } catch (Exception e) {
            System.out.println("ERROR: ");
            e.printStackTrace();
        } 

    }

    public static void main(String[] args) {
        launch();
    }
}