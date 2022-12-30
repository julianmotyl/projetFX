/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.projetfx;


import java.io.File;
import java.net.URL;
import java.util.Arrays;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart. NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 *
 * @author Mattéo
 */
public class ProjetFX extends Application {

    static Sortie sortie;
    private URL file = getClass().getClassLoader().getResource("01_07_2_TL.csv");
    
    
    @Override
    public void start(Stage primaryStage) {
        Button boutonLireFichier = new Button("Lire fichier");
        Button btnPrintSortie = new Button("Print sortie");


        VBox vbx = new VBox(5);
        HBox hboxFileSelector = new HBox (5);
        HBox hboxMessage = new HBox(5);

        HBox hboxButtonsGraphics = statsButtonsBuilder();
        hboxFileSelector.getChildren().addAll(boutonLireFichier, btnPrintSortie);

        vbx.getChildren().addAll(hboxFileSelector, hboxButtonsGraphics,hboxMessage);


        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        xAxis.setLabel("seconde");
        
        final AreaChart<Number,Number> chart = new AreaChart<>( xAxis,yAxis);
        
        chart.setCreateSymbols(false);
        
        Scene scene = new Scene (vbx, 800, 600);
        
        primaryStage.setTitle("Analyse GPX");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //-------------------------------------------------------------------



        boutonLireFichier.setOnAction((ActionEvent event) -> {
            FileChooser selectCSV = new FileChooser();
            selectCSV.setTitle("Open Resource File");
//            selectCSV.selectedExtensionFilterProperty().setValue();
            File file = selectCSV.showOpenDialog(primaryStage);
            try {
                sortie = new Sortie(file.getPath());

            } catch (NullPointerException e) {
                hboxMessage.getChildren().removeAll();
                hboxMessage.getChildren().add(new Text("Le fichier n'existe pas !!"));
                vbx.getChildren().add(hboxMessage);
            }
        });
        
        btnHR.setOnAction((event) ->  {

            XYChart.Series seriesHR = new XYChart.Series();
            
            yAxis.setLabel("bpm");
            seriesHR.setName("Profil HR");
            try {
                sortie.fillHRseries(seriesHR);
                chart.getData().add(seriesHR);
                //vbx.getChildren().add(chart);
            } catch (NullPointerException nulle) {

            }

        });
                
        btnPrintSortie.setOnAction((event) -> {
            if (sortie != null) {
                System.out.println(sortie);
            }});
                
                
                
        btnP.setOnAction((event) -> {
            XYChart.Series seriesPuiss = new XYChart.Series();
            
            yAxis.setLabel("puissance moyenne");
            seriesPuiss.setName("profil puissance moyenne");
            
            sortie.fillPuisseries(seriesPuiss);
            chart.getData().add(seriesPuiss);
            vbx.getChildren().add(chart);
        });
    }

    public HBox statsButtonsBuilder(VBox vbx, NumberAxis yAxis, XYChart chart, XYChart.Series series) {
        VBox vBoxDataSelector = new VBox(5);
        Button btnHR = new Button("Profil HR");
        Button btnP = new Button("Puissance moyenne");

        Arrays.stream(Enregistrement.class.getDeclaredFields()).toList().forEach( field -> {
            Button button = new Button(field.getName()).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                boolean clicked = false;
                @Override
                public void handle(MouseEvent mouseEvent) {
                    button.setOnAction((actionEvent -> {
                        if (clicked) {

                        } else {
                            yAxis.(field.getName());
                            series.setName(field.getName());

                            sortie.fillPuisseries(series);
                            chart.getData().add(series);
                            vbx.getChildren().add(chart);
                        }
                    }));
                }
            vBoxDataSelector.getChildren().add(
            });)

        });

    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
