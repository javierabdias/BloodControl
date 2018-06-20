/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.Paciente;

import com.integrador.POJOLista.Cita;
import com.integrador.POJOLista.CitaExamen;
import com.integrador.bloodcontrol.Alertas;
import com.integrador.bloodcontrol.Consultas.Fecha;
import com.integrador.bloodcontrol.Funciones.Funciones;
import com.integrador.bloodcontrol.MainSceneController;
import com.integrador.bloodcontrol.POJO.Citas;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author abdias
 */
public class ModificarCita1Controller extends Funciones implements Initializable {

    @FXML
    private JFXButton btn_aceptar;
    @FXML
    private JFXButton btn_cancelar;
    @FXML
    private JFXDatePicker iniRec_fecha;
    @FXML
    private JFXComboBox<String> iniRec_Hora;
    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    ObservableList<String> list = FXCollections.observableArrayList();
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
    SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
    ObservableList<CitaExamen> exam = FXCollections.observableArrayList();
    Double suma;
    Cita cita = MainSceneController.getCi();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accionBotones();

    }

    private Boolean verificarFecha(Date date) {

        LocalDate local = LocalDate.now();
        Instant instant = Instant.from(local.atStartOfDay(ZoneId.systemDefault()));
        Date antes = Date.from(instant);
        if (!date.before(antes)) {
            return true;
        }
        return false;
    }

    private void Fechas() throws ParseException {
        Date date = sdf.parse("07:00:00");
        list.add(sdf.format(date));

        for (int i = 0; i < 12; i++) {
            LocalDateTime local = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            local = local.plusMinutes(15);
            date = Date.from(local.atZone(ZoneId.systemDefault()).toInstant());
            list.add(sdf.format(date));
        }

    }

    private void accionBotones() {
       iniRec_fecha.setOnAction(e -> {
            if (verificarFecha(DatePickerParser(iniRec_fecha))) {
                list.clear();
                iniRec_Hora.setDisable(false);
                try {
                    Fechas();
                } catch (ParseException ex) {
                    Logger.getLogger(AgregarCitaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Fecha f = new Fecha(list, DatePickerParser(iniRec_fecha));
                new Thread(f).start();
                iniRec_Hora.setItems(list);
            } else {
                Alertas.warning("Fecha inválida", "Verifique la fecha seleccionada.", "Sólo se pueden añadir citas después del día actual.");
            }
        }); 
       
       btn_aceptar.setOnAction(e -> {
       if(iniRec_fecha.getValue().equals(null) || iniRec_Hora.getSelectionModel().isEmpty()){
           Alertas.warning("Datos no seleccionados", "No se seleccionaron datos.", "Seleccione fecha y hora para proceder.");
       } else {
           guardar.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
               anchor.getScene().getWindow().hide();
           });
           new Thread (guardar).start();
       }
       });
       
       btn_cancelar.setOnAction(e -> {
           anchor.getScene().getWindow().hide();
       });
       
    }

    Task <Void> guardar = new Task <Void> () {
        @Override
        protected Void call() throws Exception {
            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            
            Citas cit = em.find(Citas.class, cita.getId());
            cit.setCitFecha(DatePickerParser(iniRec_fecha));
            Date ho = sdf.parse(iniRec_Hora.getValue());
            cit.setCitHora(ho);
            em.persist(cit);
            
            em.getTransaction().commit();
            em.close();
            return null;
        }
    };
}
