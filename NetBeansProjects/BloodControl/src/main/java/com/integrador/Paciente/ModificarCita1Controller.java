/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.Paciente;

import com.integrador.POJOLista.Cita;
import com.integrador.POJOLista.CitaExamen;
import com.integrador.bloodcontrol.Alertas;
import com.integrador.bloodcontrol.Consultas.ExaCita;
import com.integrador.bloodcontrol.Consultas.ExamenCitas;
import com.integrador.bloodcontrol.Consultas.Fecha;
import com.integrador.bloodcontrol.Funciones.Funciones;
import com.integrador.bloodcontrol.MainSceneController;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
    private JFXComboBox<String> iniRec_Examen;
    @FXML
    private Label iniRec_total;
    @FXML
    private JFXButton btnIniRec_agregar;
    @FXML
    private JFXTextArea iniRec_Estudios;
    @FXML
    private JFXComboBox<String> iniRec_Hora;
    @FXML
    private TableView<CitaExamen> tabla_examen;
    @FXML
    private TableColumn<CitaExamen, Integer> id;
    @FXML
    private TableColumn<CitaExamen, String> examen;
    @FXML
    private TableColumn<CitaExamen, Double> precio;
    @FXML
    private JFXButton btnIniRec_eliminar;
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
        examenes.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            iniRec_Examen.setItems(examenes.getValue());
        });
        new Thread(examenes).start();
        
        hilo.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            tablaExamen();
            iniRec_Hora.setItems(list);
            //Date in = (Date) cita.getFecha();
            //LocalDate date = in.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // iniRec_fecha.setValue(date);
            iniRec_Hora.setValue(sdf.format(cita.getHora()));
            
        });
        new Thread (hilo).start();
        
        accionBotones();

    }

    private Boolean verificarFecha(Date date) {

        Date hoy = new Date();
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
                Alertas.warning("Fecha inválida", "Verifique la fecha seleccionada.", "Sólo se pueden añadir citas a partir del día actual.");
            }
        }); 
    }

    Task<ObservableList<String>> examenes = new Task<ObservableList<String>>() {
        @Override
        protected ObservableList<String> call() throws Exception {
            ObservableList<String> list = FXCollections.observableArrayList();

            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT p.exaNom FROM Examen p WHERE p.erId='A'");
            list = FXCollections.observableArrayList(query.getResultList());
            em.getTransaction().commit();
            em.close();

            return list;
        }

    };
    
    public void Examen(){
        iniRec_Examen.setOnAction(e -> {

            ExamenCitas ex = new ExamenCitas(iniRec_Examen.getValue());

            ex.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                iniRec_Estudios.setText("");
                for (String st : ex.getValue()) {
                    iniRec_Estudios.appendText("*   " + st + "\n");
                }
            });
            new Thread(ex).start();
        });
        
        btnIniRec_agregar.setOnAction(e -> {
            ExaCita ec = new ExaCita(iniRec_Examen.getValue());
            ec.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {

                iniRec_Examen.getItems().remove(ec.getValue().getNombre());
                suma = 0.0;
                exam.add(ec.getValue());
                tablaExamen();

                for (CitaExamen exam1 : exam) {
                    suma = suma + exam1.getPrecio();
                }

                iniRec_total.setText("$ " + suma);
                iniRec_Examen.setValue(null);

            });
            new Thread(ec).start();
        });
        
        btnIniRec_eliminar.setOnAction(e -> {

            if (!tabla_examen.getSelectionModel().isEmpty()) {
                CitaExamen ce = tabla_examen.getSelectionModel().getSelectedItem();
                exam.remove(ce);
                tabla_examen.getItems().remove(ce);

                suma = 0.0;
                for (CitaExamen exam1 : exam) {
                    suma = suma + exam1.getPrecio();
                }

                iniRec_Examen.getItems().add(ce.getNombre());

                iniRec_total.setText("$ " + suma);

            } else {

                Alertas.warning("Datos no seleccionados", "No se seleccionaron datos.", "Seleccione un registro de la tabla de exámenes para proceder.");
            }
        });
    }
    
    private void tablaExamen() {
        tabla_examen.setItems(exam);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        examen.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
    }
    
    Task <Void> hilo = new Task<Void>(){
        @Override
        protected Void call() throws Exception {
            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            System.out.println(cita.getId());
            Query query = em.createQuery("SELECT NEW com.integrador.POJOLista.CitaExamen(A.exaId,A.exaNom,A.exaPrecio) FROM Examen A, Cita_Examen B "
                    + "WHERE B.primaryKey.examen.exaId=A.exaId AND B.primaryKey.cita.citId=:cita");
            query.setParameter("cita", cita.getId());
            exam = FXCollections.observableArrayList(query.getResultList());
            em.getTransaction().commit();
            em.close();
            return null;           
        }
    };

}
