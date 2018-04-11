package com.integrador.bloodcontrol;

import com.integrador.POJO.Usuarios;
import com.integrador.bloodcontrol.Funciones.Reloj;
import com.integrador.persistence.EManagerFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author abdias
 */

//  **CLASE DE MAIN CONTROLLER**

public class MainSceneController implements Initializable {

    @FXML
    private Label reloj;
    @FXML
    private Label id;
    @FXML
    private Label correo;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        general();
    }    
    
    public void general(){
        new Thread (new QueryUsuario()).start();
        Thread thread= new Thread(new Reloj(reloj));
        thread.setDaemon(true);
        thread.start();
    }
    
    private class QueryUsuario extends Task<Void>{
        @Override
        protected Void call() throws Exception {
            System.out.println(LogInController.id_usuario);
            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Usuarios u = em.find(Usuarios.class, LogInController.id_usuario);
            em.getTransaction().commit();
            em.close();
            Platform.runLater(()-> {
                id.setText(u.getUsuNombre()+" "+u.getUsuAp()+" "+u.getUsuAm());
                correo.setText(u.getUsuCe());
                System.gc();
            });
            return null;
        }
    }
    
    
}
