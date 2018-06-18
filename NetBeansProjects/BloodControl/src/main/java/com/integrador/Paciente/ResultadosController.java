/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.Paciente;

import com.integrador.POJOLista.Resul;
import com.integrador.bloodcontrol.Alertas;
import com.integrador.bloodcontrol.Consultas.ResulCitas;
import com.integrador.bloodcontrol.Funciones.Funciones;
import com.integrador.bloodcontrol.Funciones.Usuarios;
import com.integrador.bloodcontrol.MainSceneController;
import com.integrador.bloodcontrol.POJO.Citas;
import com.integrador.bloodcontrol.POJO.EstadoRegistro;
import com.integrador.bloodcontrol.POJO.Estudios;
import com.integrador.bloodcontrol.POJO.Laboratorista;
import com.integrador.bloodcontrol.POJO.Resultados;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author abdias
 */
public class ResultadosController extends Funciones implements Initializable {

    @FXML
    private TableView<Resul> tabla;
    @FXML
    private TableColumn<Resul, Integer> id;
    @FXML
    private TableColumn<Resul, String> estudio;
    @FXML
    private JFXButton btn_aceptar;
    @FXML
    private JFXButton btn_cancelar;
    @FXML
    private TableView<Resul> tabla1;
    @FXML
    private TableColumn<Resul, Integer> id1;
    @FXML
    private TableColumn<Resul, String> estudio1;
    @FXML
    private JFXTextField resultado;
    @FXML
    private JFXButton pasar;
    @FXML
    private JFXButton regresar;
    @FXML
    private TableColumn<Resul, Double> resultados;
    @FXML
    private AnchorPane anchor;

    ObservableList <Resul> list1 = FXCollections.observableArrayList();
    ObservableList <Resul> list2 = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFieldDouble(resultado);
        setTextFieldLimit(resultado, 5);
        consulta();
        accionBotones();
    }
    
    private void consulta() {

        ResulCitas rc = new ResulCitas(MainSceneController.getCitas().getId());

        rc.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            list1 = rc.getValue();
            tabla();
        });
        new Thread(rc).start();
    }

    private void tabla() {

        tabla.setItems(list1);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        estudio.setCellValueFactory(new PropertyValueFactory<>("estudio"));

    }
    
    private void tabla2(){
        tabla1.setItems(list2);
        id1.setCellValueFactory(new PropertyValueFactory<>("id"));
        estudio1.setCellValueFactory(new PropertyValueFactory<>("estudio"));
        resultados.setCellValueFactory(new PropertyValueFactory<>("resultado"));
    }
    
    private void accionBotones(){
 
        btn_cancelar.setOnAction(e -> {
            anchor.getScene().getWindow().hide();
        });
        
        pasar.setOnAction(e -> {

            if (resultado.getText().equals("")) {
                Alertas.error("Error", "No se ingresó resultado.", "Ingrese resultado en la casilla para continuar.");
            } else if (!tabla.getSelectionModel().isEmpty()) {
                Resul r = tabla.getSelectionModel().getSelectedItem();
                list1.remove(r);
                list2.add(r);
                r.setResultado(Double.valueOf(resultado.getText()));
                tabla();
                tabla2();
                resultado.setText("");
            }
        });
        
        regresar.setOnAction(e -> {

            if (!tabla1.getSelectionModel().isEmpty()) {

                Resul r = tabla1.getSelectionModel().getSelectedItem();
                list2.remove(r);
                list1.add(r);
                tabla();
                tabla2();
            }
        });

        btn_aceptar.setOnAction(e -> {
            
            if(!list1.isEmpty()){
            
                Alertas.error("Error", "Estudios pendientes de resultado.", "Complete el llenado de resultados para la cita.");
            } else {
                enviar.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                    anchor.getScene().getWindow().hide();
                });
                new Thread (enviar).start();
            }
            
        });

    }

    Task<Void> enviar = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();

            Citas cita = em.find(Citas.class, MainSceneController.getCitas().getId());
            Laboratorista lab = em.find(Laboratorista.class, Usuarios.getId());
            EstadoRegistro er = em.find(EstadoRegistro.class, "A");
            Date date = new Date();

            for (Resul resul : list2) {

                Resultados res = new Resultados();
                res.setCitas(cita);
                res.setLaboratorista(lab);
                res.setER(er);
                res.setTimestamp(date);
                Estudios est = em.find(Estudios.class, resul.getId());
                res.setEstudios(est);
                res.setResultado(resul.getResultado());
                em.persist(res);
            }

            em.getTransaction().commit();
            em.close();
            
            return null;
        }

    };
        
}
