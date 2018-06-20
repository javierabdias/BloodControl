package com.integrador.bloodcontrol;


import com.integrador.EstudiosExamen.EstuSingle;
import com.integrador.POJOLista.Cita;
import com.integrador.bloodcontrol.Eliminaciones.EliminarPaciente;
import com.integrador.bloodcontrol.Consultas.Extraccion;
import com.integrador.bloodcontrol.Consultas.Informacion_Cita;
import com.integrador.bloodcontrol.Consultas.Paciente_Tabla;
import com.integrador.bloodcontrol.Consultas.Usuario;
import com.integrador.bloodcontrol.Modificaciones.ExtraccionStatus;
import com.integrador.POJOLista.Pacientes;
import com.integrador.Paciente.AgregarCitaController;
import com.integrador.POJOLista.CitaExamen;
import com.integrador.POJOLista.Estudio;
import com.integrador.POJOLista.Examenes;
import com.integrador.POJOLista.SigleCita;
import com.integrador.bloodcontrol.Consultas.CitaRes;
import com.integrador.bloodcontrol.Consultas.CitaStatus;
import com.integrador.bloodcontrol.Consultas.Cita_Tabla;
import com.integrador.bloodcontrol.Consultas.Consulta_Cita;
import com.integrador.bloodcontrol.Consultas.EstudioTabla;
import com.integrador.bloodcontrol.Consultas.ExaCita;
import com.integrador.bloodcontrol.Consultas.ExaTabla;
import com.integrador.bloodcontrol.Consultas.ExamenCitas;
import com.integrador.bloodcontrol.Consultas.Fecha;
import com.integrador.bloodcontrol.Consultas.Informacion_Examen;
import com.integrador.bloodcontrol.Eliminaciones.EliminarCita;
import com.integrador.bloodcontrol.Eliminaciones.EliminarEstudio;
import com.integrador.bloodcontrol.Eliminaciones.EliminarExamen;
import com.integrador.bloodcontrol.Funciones.CorreoTexto;
import com.integrador.bloodcontrol.Funciones.Funciones;
import com.integrador.bloodcontrol.Funciones.Reloj;
import com.integrador.bloodcontrol.Funciones.Usuarios;
import com.integrador.bloodcontrol.POJO.Cita_Examen;
import com.integrador.bloodcontrol.POJO.Citas;
import com.integrador.bloodcontrol.POJO.EstadoRegistro;
import com.integrador.bloodcontrol.POJO.Examen;
import com.integrador.bloodcontrol.POJO.Paciente;
import com.integrador.bloodcontrol.POJO.StatusExa;
import com.integrador.bloodcontrol.POJO.StatusPag;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;
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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
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

//  **CLASE DE MAIN CONTROLLER**

public class MainSceneController extends Funciones implements Initializable {

    
    // ID'S MAIN CONTROLLER 
    @FXML
    private Label reloj;
    @FXML
    private Label id;
    @FXML
    private Label correo;
    @FXML
    private Label fecha;
    @FXML
    private JFXButton logout;
    @FXML
    private AnchorPane anchor;

    
    //TABS PERMISOS
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private Tab inicio_lab;
    @FXML
    private Tab inicio_rec;
    @FXML
    private Tab pacientes;
    @FXML
    private Tab citas;
    @FXML
    private Tab estudios;
    @FXML
    private Tab examenes;
    @FXML
    private Tab usuarios;
    
    
    
    //  ID's INICIO LABORATORISTA
    
    int id_Cita;
    
    @FXML
    private TableColumn<Pacientes, String> nom_pac;
    @FXML
    private TableColumn<Pacientes, String> status;
    @FXML
    private TableColumn<Pacientes, Integer> id_pac;
    @FXML
    private TableView<Pacientes> tabla_pac;
    @FXML
    private TableColumn<Pacientes, String> ape_pat;
    @FXML
    private TableColumn<Pacientes, String> ape_mat;
    
    @FXML
    private JFXButton ini_actualizar;
    @FXML
    private Label hora_cita_ini;
    @FXML
    private JFXTextArea area_cita_ini;
    @FXML
    private JFXCheckBox cita_check_ini;
    @FXML
    private JFXButton ace_cita_ini;
    @FXML
    private JFXComboBox<String> combo_cita_ini;
    @FXML
    private Label cita_nom_ini;
    
    
    
    //  ID'S INICIO RECEPCIONISTA
    
    ObservableList<String> list = FXCollections.observableArrayList();
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
    SimpleDateFormat ttt = new SimpleDateFormat("yyyy-MM-dd");
    
    ObservableList<CitaExamen> exam = FXCollections.observableArrayList();
    
    String paci;
    Double suma;
    
    @FXML
    private Label iniRec_NomExam;
    @FXML
    private JFXTextArea iniRec_estudios;
    @FXML
    private Label iniRec_costo;
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
    private JFXButton btnIniRec_pagar;
    @FXML
    private JFXButton btnIniRec_agregar;
    @FXML
    private JFXButton btnIniRec_Aceptar;
    @FXML
    private JFXButton btnIniRec_eliminar;
    
    @FXML
    private TableView<CitaExamen> tabla_examen;
    @FXML
    private TableColumn<CitaExamen, Integer> id_examen;
    @FXML
    private TableColumn<CitaExamen, String> examen;
    @FXML
    private TableColumn<CitaExamen, Double> precio;
       
    
   
    
    //  CITAS
    
    private static Cita ci;

    public static Cita getCi() {
        return ci;
    }
    
    @FXML
    private JFXButton cit_agregar;
    @FXML
    private JFXButton cit_actualizar;
    @FXML
    private JFXButton cit_modificar;
    @FXML
    private JFXButton cit_eliminar;

    @FXML
    private TableView<Cita> cit_tabla;
    @FXML
    private TableColumn<Cita, String> cit_Nom;
    @FXML
    private TableColumn<Cita, String> cit_ApePat;
    @FXML
    private TableColumn<Cita, String> cit_ApeMat;
    @FXML
    private TableColumn<Cita, Date> cit_fecha;
    @FXML
    private TableColumn<Cita, Date> cit_hora;
    @FXML
    private TableColumn<Cita, String> cit_pago;
    @FXML
    private TableColumn<Cita, String> cit_extraccion;
    @FXML
    private TableColumn<Cita, Integer> cit_id;
    @FXML
    private JFXComboBox<String> tipo;
    
    ObservableList <String> cita_status = FXCollections.observableArrayList("MOSTRAR TODO","REALIZADO","NO REALIZADO","PAGADO","SIN PAGAR");

    Date d= new Date();
    @FXML
    private JFXTextField busque_cita;
    @FXML
    private JFXButton cit_pagar;
    
    
    //  PACIENTES
    
    private static Pacientes paciente;
    @FXML
    private JFXButton pac_anadir;
    @FXML
    private JFXButton pac_editar;
    @FXML
    private JFXButton pac_eliminar;
    @FXML
    private JFXButton pac_actualizar;
    @FXML
    private TableView<Pacientes> pac_tabla;
    @FXML
    private TableColumn<Pacientes, String> pac_nombre;
    @FXML
    private TableColumn<Pacientes, String> pac_ap;
    @FXML
    private TableColumn<Pacientes, String> pac_am;
    @FXML
    private TableColumn<Pacientes, String> pac_correo;
    @FXML
    private TableColumn<Pacientes, Date> pac_fn;
    @FXML
    private TableColumn<Pacientes, String> pac_tel;
    @FXML
    private TableColumn<Pacientes, String> pac_cel;
    @FXML
    private JFXTextField pac_buscar;
    
    public static Pacientes getPaciente() {
        return paciente;
    }
    
    //  RESULTADOS
    
    private static Cita Citas;
    public static Cita getCitas() {
        return Citas;
    }
    @FXML
    private JFXButton btn_resultados;
    
    
    // EXAMENES
    
    int idExamen;
    @FXML
    private JFXButton exa_agregar;
    @FXML
    private JFXButton exa_eliminar;
    @FXML
    private JFXButton exa_modificar;
    @FXML
    private JFXButton exa_actualizar;
    @FXML
    private Label nom_examen;
    @FXML
    private JFXTextArea examen_estudios;
    @FXML
    private JFXButton exa_est_ana;
     @FXML
    private TableView<Examenes> tabla_exame;
    @FXML
    private TableColumn<Examenes, String> exa_nom;
    @FXML
    private TableColumn<Examenes, Double> exa_prec;
    @FXML
    private TableColumn<Examenes, Integer> exa_id;
    
    
    //ESTUDIO
    
    @FXML
    private TableView<Estudio> tabla_estudio;
    @FXML
    private TableColumn<Estudio, String> nom_estudio;
    @FXML
    private TableColumn<Estudio, Double> min_estudio;
    @FXML
    private TableColumn<Estudio, Double> max_estudio;
    @FXML
    private TableColumn<Estudio, String> exa_estudio;
    @FXML
    private TableColumn<Estudio, Integer> estudio_id;
    @FXML
    private JFXComboBox<String> filExamen;
    @FXML
    private JFXButton est_añadir;
    @FXML
    private JFXButton est_modificar;
    @FXML
    private JFXButton est_eliminar;
    @FXML
    private JFXButton estu_actualizar;
    
    private static int estudio;

    public static int getEstudio() {
        return estudio;
    }

   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        Inicio();

        switch (Usuarios.getTipo()) {
            case "Administrador":
                Administrador();
                break;

            case "Recepcionista":
                Recepcionista();
                break;

            case "Laboratorista":
                Laboratorista();
                break;
        }
    }

    //// -- *** Métodos de tipo de usuario 
    private void Administrador() {

    }

    private void Laboratorista() {

        /// PERMISOS
        tabPane.getTabs().remove(usuarios);
        tabPane.getTabs().remove(inicio_rec);

        /// INICIO
        ObservableList<String> status = FXCollections.observableArrayList("REALIZADO", "NO REALIZADO");
        combo_cita_ini.setItems(status);
        iniTablaCitas();
        accionBotonesIni();

        /// PACIENTES
        pacienteTabla();
        accionBotonesPac();

        // CITAS
        cit_pagar.setVisible(false);
        citaTabla();
        accionBotonesCit();

        //RESULTADOS
        resultados();
        
        //EXAMEN
        examenTabla();
        accionesBotonesExa();
        
        //ESTUDIOS
        accionBotonesEst();
        estudioTabla();
        

    }

    private void Recepcionista() {

        /// PERMISOS
        tabPane.getTabs().remove(inicio_lab);
        tabPane.getTabs().remove(estudios);
        tabPane.getTabs().remove(examenes);
        tabPane.getTabs().remove(usuarios);
        btn_resultados.setVisible(false);

        /// PACIENTES
        pacienteTabla();
        accionBotonesPac();

        // CITAS
        citaTabla();
        accionBotonesCit();

        // INICIO
        InicioRecep();

    }
    

    //// -- *** Métodos de Inicio Laboratorista
    private void Inicio() {
        Thread thread = new Thread(new Reloj(reloj));
        thread.setDaemon(true);
        thread.start();
        fecha(fecha);
        usuario();

        tipo.setItems(cita_status);

        logout.setOnAction(e -> {
            anchor.getScene().getWindow().hide();
            AbrirVentana2 av = new AbrirVentana2("/LogIn/LogIn.fxml", "LogIn");
            new Thread(av).start();
        });

    }

    private void usuario() {
        Usuario usuario = new Usuario(Usuarios.getTipo(), Usuarios.getId());
        usuario.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            id.setText(usuario.getValue().get(0).getNombre() + " " + usuario.getValue().get(0).getApePat() + " " + usuario.getValue().get(0).getApeMat());
            correo.setText(usuario.getValue().get(0).getCorreo());
        });
        new Thread(usuario).start();
    }

    private void iniTablaCitas() {
        Extraccion e = new Extraccion();
        e.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            tabla_pac.setItems(e.getValue());
            id_pac.setCellValueFactory(new PropertyValueFactory<>("id"));
            nom_pac.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            ape_pat.setCellValueFactory(new PropertyValueFactory<>("apePat"));
            ape_mat.setCellValueFactory(new PropertyValueFactory<>("apeMat"));
            status.setCellValueFactory(new PropertyValueFactory<>("status"));
        });
        new Thread(e).start();
    }

    private void accionBotonesIni() {
        ini_actualizar.setOnAction(e -> {
            iniTablaCitas();
            backtoBeginning();
        });

        tabla_pac.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (!tabla_pac.getSelectionModel().isEmpty()) {
                area_cita_ini.setText("");
                id_Cita = tabla_pac.getSelectionModel().getSelectedItem().getId();
                citaInformacion();
            }
        });

        cita_check_ini.setOnAction(e -> {
            if (cita_check_ini.isSelected()) {
                ace_cita_ini.setDisable(false);
                combo_cita_ini.setDisable(false);
            } else {
                ace_cita_ini.setDisable(true);
                combo_cita_ini.setDisable(true);
            }
        });

        ace_cita_ini.setOnAction(e -> {
            Extraccion ex = new Extraccion();
            ex.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                tabla_pac.setItems(ex.getValue());
                id_pac.setCellValueFactory(new PropertyValueFactory<>("id"));
                nom_pac.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                ape_pat.setCellValueFactory(new PropertyValueFactory<>("apePat"));
                ape_mat.setCellValueFactory(new PropertyValueFactory<>("apeMat"));
                status.setCellValueFactory(new PropertyValueFactory<>("status"));
            });
            ExtraccionStatus ES = new ExtraccionStatus(id_Cita, Status(combo_cita_ini.getValue()), new Thread(ex));
            new Thread(ES).start();
            backtoBeginning();
        });
    }

    private void citaInformacion() {
        Informacion_Cita ic = new Informacion_Cita(id_Cita);

        ic.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            List<Object> lista = (List) ic.getValue().get(0);
            Pacientes pac = (Pacientes) ic.getValue().get(1);

            for (int i = 0; i < lista.size(); i++) {
                area_cita_ini.appendText("*     " + lista.get(i) + "\n");
            }

            hora_cita_ini.setText(pac.getHora().toString());
            cita_nom_ini.setText(pac.getNombre() + " " + pac.getApePat() + " " + pac.getApeMat());
            combo_cita_ini.setValue(pac.getStatus());
        });
        new Thread(ic).start();
    }

    private void backtoBeginning() {

        ace_cita_ini.setDisable(true);
        combo_cita_ini.setDisable(true);
        cita_check_ini.setSelected(false);
        hora_cita_ini.setText("00:00:00");
        cita_nom_ini.setText("Nombre del paciente");
        area_cita_ini.setText("");
    }

    private void fecha(Label l1) {
        Date myDate = new Date();
        l1.setText(new SimpleDateFormat("dd-MM-yyyy").format(myDate));
    }

    private String Status(String status) {
        if (status.equals("REALIZADO")) {
            return "R";
        }
        return "N";
    }
    

    //// -- *** Métodos de Inicio Recepcionista
    private void InicioRecep() {

        Enable(true);
        accionBotonesIR();
        exame.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            iniRec_Examen.setItems(exame.getValue());
        });
        new Thread(exame).start();

    }

    private void Enable(Boolean status) {
        btnIniRec_Aceptar.setDisable(status);
        iniRec_fecha.setDisable(status);
        iniRec_Examen.setDisable(status);
        btnIniRec_agregar.setDisable(status);
        btnIniRec_eliminar.setDisable(status);
        btnIniRec_pagar.setDisable(status);
        iniRec_Hora.setDisable(status);
    }

    private Boolean verificarFecha(Date date) {

        LocalDate local = LocalDate.now();
        Instant instant = Instant.from(local.atStartOfDay(ZoneId.systemDefault()));
        Date antes = Date.from(instant);
        if (!date.equals(antes)) {
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

    Task<ObservableList<String>> exame = new Task<ObservableList<String>>() {
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

    private void accionBotonesIR() {

        iniRec_fecha.setOnAction(e -> {
            if (verificarFecha(DatePickerParser(iniRec_fecha))) {
                list.clear();
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

        iniRec_Buscar.setOnAction(e -> {

            if (validarEmailFuerte(iniRec_correo.getText())) {
                Consulta_Cita persona = new Consulta_Cita(iniRec_correo.getText());
                persona.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                    paci = persona.getValue();
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

        btnIniRec_Aceptar.setOnAction(e -> {
            if (!exam.isEmpty()) {
                confirmacion();
            } else {
                Alertas.warning("Sin selección.", "Exámenes no seleccionados.", "Seleccionar exaámenes para proceder.");
            }
        });

        Examen();
        pagar();
    }

    private void Examen() {

        iniRec_Examen.setOnAction(e -> {
            if(iniRec_Examen.getValue()!=null){
                ExaCita ec = new ExaCita(iniRec_Examen.getValue());
                ec.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                    iniRec_NomExam.setText(ec.getValue().getNombre());
                    iniRec_costo.setText(String.valueOf("$ " + ec.getValue().getPrecio()) + "0");
                });
                new Thread(ec).start();

            } 
            
            ExamenCitas ex = new ExamenCitas(iniRec_Examen.getValue());

            ex.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                iniRec_estudios.setText("");
                for (String st : ex.getValue()) {
                    iniRec_estudios.appendText("*   " + st + "\n");
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

                iniRec_total.setText("$ " + suma + "0");
                iniRec_Examen.setValue(null);
                
            });
            new Thread(ec).start();
            iniRec_NomExam.setText("Nombre Examen");
            iniRec_costo.setText("$ 0.00");

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

                iniRec_total.setText("$ " + suma + "0");
                iniRec_Examen.setValue(null);

            } else {

                Alertas.warning("Datos no seleccionados", "No se seleccionaron datos.", "Seleccione un registro de la tabla de exámenes para proceder.");
            }
        });

    }

    private void tablaExamen() {
        tabla_examen.setItems(exam);
        id_examen.setCellValueFactory(new PropertyValueFactory<>("id"));
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
            correo.setNombre("Buen día, " + pac.getPerId().getPerNombre() + " " + pac.getPerId().getPerAp() + " " + pac.getPerId().getPerAm());
            correo.setMensaje(" \n\n Confirmación de cita no. " + cita.getCitId() + ":"
                    + "\n*  FECHA: " + ttt.format(cita.getCitFecha()) + "\n" + "*  HORA: " + sdf.format(cita.getCitHora()) + ""
                    + "\n\n\n Se le invita a acudir con una anticipación de cinco minutos. Gracias."
                    + "\n\n\nEste es un correo automático; en caso de desconocer la procedencia, hacer caso omiso.");

            correo.setCorreo(iniRec_correo.getText());
            new Thread(correo).start();
            return null;
        }

    };
    
    Task<Void> guardarPago = new Task<Void>() {
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
            StatusPag sp = em.find(StatusPag.class, 1);
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

            
            SigleCita.setCita(cita);
            SigleCita.setTotal(suma);
            SigleCita.setCorreo(iniRec_correo.getText());
            SigleCita.setNombre(pac.getPerId().getPerNombre() + " " + pac.getPerId().getPerAp() + " " + pac.getPerId().getPerAm());
            SigleCita.setFecha(cita.getCitFecha());
            SigleCita.setHora(cita.getCitHora());
            
            new Thread (new AbrirVentana("/Pagos/Pago.fxml","Pagos")).start();
            return null;
        }

    };

    private void confirmacion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getDialogPane().setPrefSize(400, 250);
        alert.setTitle("Confirmación de cita.");
        alert.setHeaderText("CITA PRELIMINAR:");
        String impresion = "Exámenes a realizar:";

        for (CitaExamen e : exam) {
            impresion = impresion + "\n     *   " + e.getNombre();
        }
        alert.setContentText(impresion);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            guardar.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                backToBeginning();
            });
            new Thread(guardar).start();

        } else {
            alert.close();
        }
    }
    
    private void confirmacion2() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getDialogPane().setPrefSize(400, 250);
        alert.setTitle("Confirmación de cita a pagar.");
        alert.setHeaderText("CITA PRELIMINAR:");
        String impresion = "Exámenes a realizar:";

        for (CitaExamen e : exam) {
            impresion = impresion + "\n     *   " + e.getNombre();
        }
        alert.setContentText(impresion);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            guardarPago.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                backToBeginning();
            });
            new Thread(guardarPago).start();

        } else {
            alert.close();
        }
    }

    private void backToBeginning() {
        iniRec_NomExam.setText("Nombre Examen");
        iniRec_estudios.setText("");
        iniRec_correo.setText("");
        iniRec_Paciente.setText("");
        iniRec_fecha.setValue(LocalDate.now());
        iniRec_Hora.setValue(null);
        iniRec_Examen.setValue(null);
        iniRec_total.setText("$ 0.00");
        iniRec_costo.setText("$ 0.00");
        Enable(true);
        exam.clear();
        tabla_examen.getItems().clear();
    }

    public void confirmacionPago() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Pago");
        alert.setHeaderText("Transacción de Pago.");
        alert.setContentText("¿Desea proceder al pago de la cita?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            AbrirVentana av = new AbrirVentana("/Pagos/Pago.fxml", "Pago");
            av.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                backToBeginning();
            });

            new Thread(av).start();
        } else {
            alert.close();
        }
    }

    private void pagar() {

        btnIniRec_pagar.setOnAction(e -> {
            if (!exam.isEmpty()) {
                confirmacion2();
            }
        });
    }
    

    //// -- *** Métodos de Paciente
    private void pacienteTabla() {
        Paciente_Tabla pt = new Paciente_Tabla();
        pt.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            pac_tabla.setItems(pt.getValue());
            pac_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            pac_ap.setCellValueFactory(new PropertyValueFactory<>("apePat"));
            pac_am.setCellValueFactory(new PropertyValueFactory<>("apeMat"));
            pac_correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
            pac_fn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            pac_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            pac_cel.setCellValueFactory(new PropertyValueFactory<>("cel"));

            FilteredList<Pacientes> datos = new FilteredList<>(pt.getValue(), a -> true);

            pac_buscar.setOnKeyReleased(e -> {
                pac_buscar.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    datos.setPredicate((Predicate<? super Pacientes>) paciente -> {

                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();

                        if (paciente.getApePat().toLowerCase().contains(lower)) {
                            return true;
                        } else if (paciente.getCorreo().toLowerCase().contains(lower)) {
                            return true;
                        }
                        return false;
                    });
                });
            });
            SortedList<Pacientes> datosCambio = new SortedList<>(datos);
            datosCambio.comparatorProperty().bind(pac_tabla.comparatorProperty());
            pac_tabla.setItems(datosCambio);
        });
        new Thread(pt).start();
    }

    private void accionBotonesPac() {
        pac_anadir.setOnAction(e -> {
            new Thread(new AbrirVentana("/Pacientes/Switch.fxml", "Añadir Paciente")).start();
        });

        pac_actualizar.setOnAction(e -> {
            pacienteTabla();
        });

        pac_eliminar.setOnAction(e -> {
            Paciente_Tabla pt = new Paciente_Tabla();
            pt.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                pac_tabla.setItems(pt.getValue());
                pac_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                pac_ap.setCellValueFactory(new PropertyValueFactory<>("apePat"));
                pac_am.setCellValueFactory(new PropertyValueFactory<>("apeMat"));
                pac_correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
                pac_fn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
                pac_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
                pac_cel.setCellValueFactory(new PropertyValueFactory<>("cel"));

                FilteredList<Pacientes> datos = new FilteredList<>(pt.getValue(), a -> true);

                pac_buscar.setOnKeyReleased(d -> {
                    pac_buscar.textProperty().addListener((observableValue, oldValue, newValue) -> {
                        datos.setPredicate((Predicate<? super Pacientes>) paciente -> {

                            if (newValue == null || newValue.isEmpty()) {
                                return true;
                            }
                            String lower = newValue.toLowerCase();

                            if (paciente.getApePat().toLowerCase().contains(lower)) {
                                return true;
                            } else if (paciente.getCorreo().toLowerCase().contains(lower)) {
                                return true;
                            }
                            return false;
                        });
                    });
                });
                SortedList<Pacientes> datosCambio = new SortedList<>(datos);
                datosCambio.comparatorProperty().bind(pac_tabla.comparatorProperty());
                pac_tabla.setItems(datosCambio);
            });
            if (!pac_tabla.getSelectionModel().isEmpty()) {
                String correo = pac_tabla.getSelectionModel().getSelectedItem().getCorreo();
                new Thread(new EliminarPaciente(correo, new Thread(pt))).start();
            } else {
                Alertas.warning("Sin selección.", "Datos no seleccionados.", "Seleccionar datos de la tabla para proceder.");
            }
        });

        pac_editar.setOnAction(e -> {
            if (!pac_tabla.getSelectionModel().isEmpty()) {
                paciente = pac_tabla.getSelectionModel().getSelectedItem();
                new Thread(new AbrirVentana("/Pacientes/ModificarPaciente.fxml", "Modificar Paciente")).start();
            } else {
                Alertas.warning("Sin selección.", "Datos no seleccionados.", "Seleccionar datos de la tabla para proceder.");
            }
        });
    }
    

    //// -- *** Métodos de Citas
    private void citaTabla() {
        Cita_Tabla pt = new Cita_Tabla();
        pt.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            cit_tabla.setItems(pt.getValue());

            cit_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            cit_Nom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            cit_ApePat.setCellValueFactory(new PropertyValueFactory<>("apePat"));
            cit_ApeMat.setCellValueFactory(new PropertyValueFactory<>("apeMat"));
            cit_fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            cit_hora.setCellValueFactory(new PropertyValueFactory<>("hora"));
            cit_pago.setCellValueFactory(new PropertyValueFactory<>("pago"));
            cit_extraccion.setCellValueFactory(new PropertyValueFactory<>("extraccion"));

            FilteredList<Cita> datos = new FilteredList<>(pt.getValue(), a -> true);
            
            busque_cita.setOnKeyReleased(e -> {
                
                busque_cita.textProperty().addListener((observableValue, oldValue, newValue) -> {
                
                    datos.setPredicate((Predicate<? super Cita>) citas -> {
                        
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        
                        if(citas.getId().toString().contains(newValue)){
                            return true;
                        }
                        
                        return false;
                    
                    });
                
                });
            
            });
            
            tipo.setOnAction(e -> {
                datos.setPredicate((Predicate<? super Cita>) citas -> {

                    if (tipo.getSelectionModel().isEmpty()) {
                        return true;
                    }

                    if (tipo.getValue().equals("MOSTRAR TODO")) {
                        return true;
                    }

                    if (tipo.getValue().equals(citas.getExtraccion())) {
                        return true;
                    }

                    if (tipo.getValue().equals(citas.getPago())) {
                        return true;
                    }

                    return false;
                });
            });
            SortedList<Cita> datosCambio = new SortedList<>(datos);
            datosCambio.comparatorProperty().bind(cit_tabla.comparatorProperty());
            cit_tabla.setItems(datosCambio);
        });
        new Thread(pt).start();
    }

    private void accionBotonesCit() {

        cit_actualizar.setOnAction(e -> {
            citaTabla();
        });

        cit_agregar.setOnAction(e -> {
            AbrirVentana av = new AbrirVentana("/Cita/AgregarCita.fxml", "Crear nueva cita");
            av.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                citaTabla();
            });
            new Thread(av).start();
        });

        cit_eliminar.setOnAction(e -> {

            tipo.setValue(null);

            if (cit_tabla.getSelectionModel().isEmpty()) {
                Alertas.warning("Sin selección.", "Datos no seleccionados.", "Seleccionar datos de la tabla para proceder.");
            } else if (cit_tabla.getSelectionModel().getSelectedItem().getPago().equals("PAGADO")){
                Alertas.warning("Cita pagada", "Cita no puede ser eliminada.", "La cita seleccionada ha sido pagada previamente, \nno puede ser eliminada,");
            } else {
                Integer id = cit_tabla.getSelectionModel().getSelectedItem().getId();
                EliminarCita ec = new EliminarCita(id);
                ec.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                    citaTabla();
                });
                new Thread(ec).start();
            }
        });
        
        cit_modificar.setOnAction(e -> {
            
            if(cit_tabla.getSelectionModel().isEmpty()){
                Alertas.warning("Sin selección.", "Datos no seleccionados.", "Seleccionar datos de la tabla para proceder.");
            } else {
                ci = cit_tabla.getSelectionModel().getSelectedItem();
                new Thread(new AbrirVentana("/Cita/ModificarCita1.fxml", "Modificar citas")).start();
            }
        
        });
        
        cit_pagar.setOnAction(e -> {

            if (cit_tabla.getSelectionModel().isEmpty()) {
                Alertas.warning("Sin selección.", "Datos no seleccionados.", "Seleccionar datos de la tabla para proceder.");
            } else if (cit_tabla.getSelectionModel().getSelectedItem().getPago().equals("PAGADO")) {
                Alertas.informacion("Extracción pagada.", "El monto de la cita de extracción fue \ncubierta previamente.");

            } else {
                CitaRes cit_res = new CitaRes(cit_tabla.getSelectionModel().getSelectedItem().getId());

                cit_res.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {

                    Citas cit = (Citas) cit_res.getValue().get(0);
                    String corr = (String) cit_res.getValue().get(1);
                    Cita ci = cit_tabla.getSelectionModel().getSelectedItem();

                    SigleCita.setCita(cit);
                    SigleCita.setHora(cit.getCitHora());
                    SigleCita.setFecha(cit.getCitFecha());
                    SigleCita.setTotal(cit.getCitTotal());
                    SigleCita.setCorreo(corr);
                    SigleCita.setNombre(ci.getNombre() + " " + ci.getApePat() + " " + ci.getApeMat());

                    new Thread(new AbrirVentana("/Pagos/Pago.fxml", "Pagos")).start();
                });
                new Thread(cit_res).start();
            }

        });

    }
        
    /// -- *** Resultados
    private void resultados() {
        btn_resultados.setOnAction(e -> {          

            if (cit_tabla.getSelectionModel().isEmpty()) {

                Alertas.warning("Sin selección.", "Datos no seleccionados.", "Seleccionar datos de la tabla para proceder.");

            } else {

                CitaStatus c = new CitaStatus(cit_tabla.getSelectionModel().getSelectedItems().get(0).getId());

                c.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                    if (c.getValue()) {
                        if (cit_tabla.getSelectionModel().getSelectedItems().get(0).getPago().equals("SIN PAGAR")) {
                            Alertas.warning("Pago", "Pago pendiente.", "El pago debe de ser cubierto en su totalidad \npara proceder.");
                        } else if ((cit_tabla.getSelectionModel().getSelectedItems().get(0).getExtraccion().equals("NO REALIZADO"))) {
                            Alertas.warning("Extracción", "No se ha realizado extracción de muestras.", "Las muestras deben de ser extraídas para poder proceder.");
                        } else {
                            Citas = cit_tabla.getSelectionModel().getSelectedItem();
                            new Thread(new AbrirVentana("/Resultados/Resultados.fxml", "Resultados")).start();

                        }
                    } else {
                        Alertas.warning("Resultados existentes.", "Resultados registrados previamente.", "Los resultados han sido registrados previamente.");
                    }
                });

                new Thread(c).start();

            }
        });

    }
    
    /// -- *** Métodos de Exámenes
    
    private void examenTabla(){
        ExaTabla et = new ExaTabla();
        et.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            tabla_exame.setItems(et.getValue());
            exa_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            exa_nom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            exa_prec.setCellValueFactory(new PropertyValueFactory<>("precio"));
            nom_examen.setText("Nombre de Examen");
            examen_estudios.setText("");
        });
        new Thread(et).start();
    }
    
    private void accionesBotonesExa(){
        tabla_exame.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
        
            if(!tabla_exame.getSelectionModel().isEmpty()){
                examen_estudios.setText("");
                nom_examen.setText(tabla_exame.getSelectionModel().getSelectedItem().getNombre());
                idExamen=tabla_exame.getSelectionModel().getSelectedItem().getId();
                InformacionExamen();
            }
        });
        
        exa_est_ana.setOnAction(e -> {
            if(tabla_exame.getSelectionModel().isEmpty()){
                Alertas.warning("Sin selección.", "Datos no seleccionados.", "Seleccionar datos de la tabla para proceder.");
            } else {
                EstuSingle.setNombre(tabla_exame.getSelectionModel().getSelectedItem().getNombre());
                new Thread (new AbrirVentana("/Estudios/Estudios.fxml","Añadir estudio")).start();
            }
        });
        
        exa_agregar.setOnAction(e -> {
            new Thread (new AbrirVentana("/Examen/AñadirExamen.fxml","Añadir examen")).start();
        });
        
        exa_actualizar.setOnAction(e -> {
            examenTabla();
        });
        
        exa_eliminar.setOnAction(e -> {
            if (tabla_exame.getSelectionModel().isEmpty()){
                Alertas.warning("Sin selección.", "Datos no seleccionados.", "Seleccionar datos de la tabla para proceder.");
            } else {
                int i=tabla_exame.getSelectionModel().getSelectedItem().getId();
                EliminarExamen ee = new EliminarExamen(i);
                ee.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                    examenTabla();
                });
                new Thread (ee).start();
            }
        });
        
        exa_modificar.setOnAction(e -> {
        
            if (tabla_exame.getSelectionModel().isEmpty()){
                Alertas.warning("Sin selección.", "Datos no seleccionados.", "Seleccionar datos de la tabla para proceder.");
            } else {
                estudio = tabla_exame.getSelectionModel().getSelectedItem().getId();
                new Thread ( new AbrirVentana("/Examen/ModificarExamen.fxml","Modificar examen")).start();
            }
        });
        
    }
    
    private void InformacionExamen (){
        Informacion_Examen ie = new Informacion_Examen(idExamen);
        ie.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            for (Object value : ie.getValue()) {
                examen_estudios.appendText("*     " + value + "\n");
            }
        });
        new Thread (ie).start();
    }
    
    /// -- *** Métodos de Estudios
    
    private void estudioTabla(){
        EstudioTabla et = new EstudioTabla();

        et.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            tabla_estudio.setItems(et.getValue());
            nom_estudio.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            min_estudio.setCellValueFactory(new PropertyValueFactory<>("min"));
            max_estudio.setCellValueFactory(new PropertyValueFactory<>("max"));
            exa_estudio.setCellValueFactory(new PropertyValueFactory<>("examen"));
            estudio_id.setCellValueFactory(new PropertyValueFactory<>("id"));

            FilteredList<Estudio> estu = new FilteredList<>(et.getValue(), a -> true);

            filExamen.setOnAction(e -> {

                estu.setPredicate((Predicate<? super Estudio>) est -> {

                    if (filExamen.getSelectionModel().isEmpty()) {
                        return true;
                    }

                    if (filExamen.getValue().equals(est.getExamen())) {
                        return true;
                    }
                    
                    if (filExamen.getValue().equals("MOSTRAR TODO")) {
                        return true;
                    }

                    return false;
                });

            });
            
            SortedList<Estudio> datosCambio = new SortedList<>(estu);
            datosCambio.comparatorProperty().bind(tabla_estudio.comparatorProperty());
            tabla_estudio.setItems(datosCambio);
        });
        new Thread(et).start();
        
        exame.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            filExamen.getItems().clear();
            filExamen.setItems(exame.getValue());
            filExamen.getItems().add("MOSTRAR TODO");
        });
        new Thread(exame).start();
    }
    
    private void accionBotonesEst() {
               
        estu_actualizar.setOnAction(e -> {
            estudioTabla();
        });
        
        est_añadir.setOnAction(e -> {
            new Thread (new AbrirVentana ("/Estudios/Estudios2.fxml","Añadir estudio")).start();
        });
        
        est_eliminar.setOnAction(e -> {
            if(tabla_estudio.getSelectionModel().isEmpty()) {
                Alertas.warning("Sin selección.", "Datos no seleccionados.", "Seleccionar datos de la tabla para proceder.");
            } else {
                EliminarEstudio ee = new EliminarEstudio (tabla_estudio.getSelectionModel().getSelectedItem().getId());
                ee.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                    estudioTabla();
                });
                new Thread(ee).start();
            }});
        
        est_modificar.setOnAction(e -> {
            if(tabla_estudio.getSelectionModel().isEmpty()){
                Alertas.warning("Sin selección.", "Datos no seleccionados.", "Seleccionar datos de la tabla para proceder.");
            } else {
                estudio = tabla_estudio.getSelectionModel().getSelectedItem().getId();
                new Thread (new AbrirVentana("/Estudios/Modificar.fxml","Modificar estudio")).start();
            }
        });
        
        
    }
    
    
    
}
