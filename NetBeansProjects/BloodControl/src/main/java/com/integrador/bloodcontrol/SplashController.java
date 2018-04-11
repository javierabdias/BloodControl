package com.integrador.bloodcontrol;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

//  **CLASE CONTROLLER DE SPLASH**

public class SplashController implements Initializable {
    
    @FXML
    private AnchorPane anchor;
    @FXML
    private Circle circle;
    @FXML
    private ImageView logo;
    @FXML
    private ProgressIndicator progress;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //AnchorPane transparente
        anchor.setBackground(Background.EMPTY);
        
        //Animación para más zoom
        ScaleTransition st2 = new ScaleTransition(Duration.seconds(3),circle);
        ScaleTransition st = new ScaleTransition(Duration.seconds(3),logo);
        ScaleTransition st3= new ScaleTransition(Duration.seconds(3),progress); 
        
        //Animación para mover ProgressIndicator
        TranslateTransition t= new TranslateTransition();
        
        
        st.setToX(2.8); st.setToY(2.8);
        st2.setToX(3); st2.setToY(3);
        st3.setToX(3); st3.setToY(3);
        
        //Configuarar duración y movimiento de TraslateTranstion
        t.setDuration(Duration.millis(3000));
        t.setNode(progress);
        t.setToY(80);
        
        //Método para correr animaciones al mismo tiempo
        ParallelTransition pt = new ParallelTransition(st,st2,st3,t);
        pt.play();
        
        
    }          
}
