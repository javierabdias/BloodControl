/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.Paciente;

import com.integrador.POJOLista.CitaExamen;
import com.integrador.bloodcontrol.Alertas;
import com.integrador.bloodcontrol.Consultas.Consulta_Cita;
import com.integrador.bloodcontrol.Consultas.ExaCita;
import com.integrador.bloodcontrol.Consultas.ExamenCitas;
import com.integrador.bloodcontrol.Consultas.Fecha;
import com.integrador.bloodcontrol.Funciones.CorreoTexto;
import com.integrador.bloodcontrol.Funciones.Funciones;
import com.integrador.bloodcontrol.POJO.Citas;
import com.integrador.bloodcontrol.POJO.Cita_Examen;
import com.integrador.bloodcontrol.POJO.EstadoRegistro;
import com.integrador.bloodcontrol.POJO.Examen;
import com.integrador.bloodcontrol.POJO.Paciente;
import com.integrador.bloodcontrol.POJO.StatusExa;
import com.integrador.bloodcontrol.POJO.StatusPag;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
import javafx.scene.control.ProgressIndicator;
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
public class AgregarCitaController extends Funciones implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXButton btn_aceptar;
    @FXML
    private JFXButton btn_cancelar;
    @FXML
    private ProgressIndicator progress;
    @FXML
    private JFXTextField iniRec_correo;
    @FXML
    private JFXButton iniRec_Buscar;
    @FXML
    private Label iniRec_Paciente;
    @FXML
    private JFXDatePicker iniRec_fecha;
    @FXML
    private JFXComboBox<String> iniRec_Hora;
    @FXML
    private JFXComboBox<String> iniRec_Examen;
    @FXML
    private Label iniRec_total;
    @FXML
    private JFXButton btnIniRec_agregar;

    /**
     * Initializes the controller class.
     */
    String paciente;
    Paciente pac;

    @FXML
    private JFXTextArea iniRec_Estudios;
    @FXML
    private TableView<CitaExamen> tabla_examen;
    @FXML
    private TableColumn<CitaExamen, String> examen;
    @FXML
    private TableColumn<CitaExamen, Double> precio;
    @FXML
    private TableColumn<CitaExamen, Integer> id;
    @FXML
    private JFXButton btnIniRec_eliminar;

    ObservableList<String> list = FXCollections.observableArrayList();
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
    SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
    ObservableList<CitaExamen> exam = FXCollections.observableArrayList();
    Double suma;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Enable(true);

        iniRec_Hora.setDisable(true);
        accionBotones();
        examenes.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            iniRec_Examen.setItems(examenes.getValue());
        });
        new Thread(examenes).start();

    }

    private void accionBotones() {

        btn_cancelar.setOnAction(e -> {
            anchor.getScene().getWindow().hide();
        });

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

        iniRec_Buscar.setOnAction(e -> {

            if (validarEmailFuerte(iniRec_correo.getText())) {
                Consulta_Cita persona = new Consulta_Cita(iniRec_correo.getText());
                persona.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                    paciente = persona.getValue();
                    iniRec_Paciente.setText(persona.getValue());
                    if (!persona.getValue().equals("No se encuentra paciente.")) {
                        Enable(false);
                    }
                });
                new Thread(persona).start();
            } else {
                Alertas.error("Error", "Correo Inválido", "Verificar correo electrónico de paciente");
            }
        });

        Examen();

        btn_aceptar.setOnAction(e -> {
            
            guardar.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                anchor.getScene().getWindow().hide();
            });
            new Thread(guardar).start();
            
        });
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

    private void Enable(Boolean status) {
        btn_aceptar.setDisable(status);
        iniRec_fecha.setDisable(status);
        iniRec_Examen.setDisable(status);
        btnIniRec_agregar.setDisable(status);
        btnIniRec_eliminar.setDisable(status);
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

    private void Examen() {

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

    private void tablaExamen() {
        tabla_examen.setItems(exam);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        examen.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
    }

    Task<Void> guardar = new Task<Void>() {
        @Override
        protected Void call() throws Exception {

            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT p FROM Paciente p WHERE p.pacCe = :pacCe");
            query.setParameter("pacCe", iniRec_correo.getText());
            Paciente pac = (Paciente) query.getSingleResult();
            Citas cita = new Citas();
            cita.setPacId(pac);
            cita.setCitFecha(DatePickerParser(iniRec_fecha));
            cita.setCitTotal(Double.valueOf(suma));
            Date ho = sdf.parse(iniRec_Hora.getValue());
            cita.setCitHora(ho);
            EstadoRegistro er = em.find(EstadoRegistro.class, "A");
            cita.setErId(er);
            StatusExa se = em.find(StatusExa.class, "N");
            cita.setStaeId(se);
            StatusPag sp = em.find(StatusPag.class, 0);
            cita.setStapId(sp);
            em.persist(cita);
            em.getTransaction().commit();

            em.getTransaction().begin();
            for (int i = 0; i < exam.size(); i++) {
                Cita_Examen ce = new Cita_Examen();
                Examen e = em.find(Examen.class, exam.get(i).getId());
                ce.setExamen(e);
                ce.setCitas(cita);
                em.persist(ce);
            }
            em.getTransaction().commit();
            em.close();

            CorreoTexto correo = new CorreoTexto();
            correo.setBienvenida("Bienvenido a BloodControl.\nLa salud es siempre lo más importante.");
            correo.setNombre("Buen día, " + paciente);
            correo.setMensaje(" \n\n Confirmación de cita: "+ cita.getCitId() +" \n*   FECHA: " + sd.format(cita.getCitFecha()) + "\n" + "*   HORA: " + sdf.format(cita.getCitHora()) + ""
                    + "\n\n\n Se le invita a acudir con una antinipación de cinco minutos. Gracias."
                    + "\n\n\nEste es un correo de verificación de cuenta; en caso de desconocer la procedencia, hacer caso omiso.");

            correo.setCorreo(iniRec_correo.getText());
            new Thread(correo).start();
            return null;
        }

    };

}
