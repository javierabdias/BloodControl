/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol;

import com.integrador.POJOLista.ResultadoFull;
import com.integrador.bloodcontrol.Funciones.Path;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import org.jboss.jandex.Main;



/**
 *
 * @author abdias
 */
public class Resultado extends Task<Void> {

    public ObservableList<ResultadoFull> resultado;
    public String laboratorista, cedula, persona, fe;
    public int cita;

    public void setResultado(ObservableList<ResultadoFull> resultado) {
        this.resultado = resultado;
    }

    public void setLaboratorista(String laboratorista) {
        this.laboratorista = laboratorista;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public void setFe(String fe) {
        this.fe = fe;
    }

    public void setCita(int cita) {
        this.cita = cita;
    }

    @Override
    protected Void call() throws Exception {
        
    Document doc = new Document();
    doc.setMargins(20, 20, 1, 7);
    
    Font fonts[] = {
            new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, new BaseColor(255, 255, 255)),
            new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, new BaseColor(255, 255, 255)),
            new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL, new BaseColor(255, 255, 255)),
            new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, new BaseColor(0, 0, 0))
        };
    
        PdfWriter.getInstance(doc, new FileOutputStream(Path.getPath() + "/resultado.pdf"));
            
           doc.open();
            
           Rectangle rec = new Rectangle(600, 120);
            BaseColor b = new BaseColor(80, 83, 84);
            rec.setBackgroundColor(b);
            rec.setBottom(842);
            rec.setTop(710);
            doc.add(rec);
            
            Image logo= Image.getInstance(Main.class.getResource("/MainScene/logo128x128_blancopng.png"));
            logo.setAbsolutePosition(25, 740);
            logo.scaleAbsolute(100, 92);
            doc.add(logo);

            Paragraph pa = new Paragraph("Resultados de Estudio", fonts[0]);
            pa.setIndentationLeft(150);
            doc.add(pa);
            
            Paragraph paciente = new Paragraph("Paciente: " + persona, fonts[1]);
            paciente.setIndentationLeft(150);
            doc.add(paciente);
            
            Paragraph user = new Paragraph("Laboratorista: "+ laboratorista, fonts[1]);
            user.setIndentationLeft(150);
            doc.add(user);
            
            Paragraph cedulap = new Paragraph("Cédula profesional: " + cedula, fonts[1]);
            cedulap.setIndentationLeft(150);
            doc.add(cedulap);
           
            Paragraph fecha = new Paragraph("Número de cita: "+  cita + "             Fecha: " + fe, fonts[1]);
            fecha.setIndentationLeft(150);
            doc.add(fecha);
            
            PdfPTable pt = new PdfPTable(6);
            
            PdfPCell tit1 = new PdfPCell(new Paragraph("Examen", fonts[2]));
            tit1.setColspan(1);
            tit1.setHorizontalAlignment(Element.ALIGN_CENTER);
            tit1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pt.addCell(tit1);

            PdfPCell tit2 = new PdfPCell(new Paragraph("Estudio", fonts[2]));
            tit2.setColspan(1);
            tit2.setHorizontalAlignment(Element.ALIGN_CENTER);
            tit2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pt.addCell(tit2);

            PdfPCell tit3 = new PdfPCell(new Paragraph("Valor Mínimo", fonts[2]));
            tit3.setColspan(1);
            tit3.setHorizontalAlignment(Element.ALIGN_CENTER);
            tit3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pt.addCell(tit3);
            
            PdfPCell tit4 = new PdfPCell(new Paragraph("Valor Máximo", fonts[2]));
            tit4.setColspan(1);
            tit4.setHorizontalAlignment(Element.ALIGN_CENTER);
            tit4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pt.addCell(tit4);
            
            PdfPCell tit5 = new PdfPCell(new Paragraph("Resultado", fonts[2]));
            tit5.setColspan(1);
            tit5.setHorizontalAlignment(Element.ALIGN_CENTER);
            tit5.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pt.addCell(tit5);
            
            PdfPCell tit6 = new PdfPCell(new Paragraph("Referencia", fonts[2]));
            tit6.setColspan(1);
            tit6.setHorizontalAlignment(Element.ALIGN_CENTER);
            tit6.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pt.addCell(tit6);
            
            pt.setSpacingBefore(50);
            
            for (ResultadoFull full : resultado){
                pt.addCell(full.getExamen());
                pt.addCell(full.getEstudio());
                pt.addCell(String.valueOf(full.getMin()));
                pt.addCell(String.valueOf(full.getMax()));
                pt.addCell(String.valueOf(full.getResultado()));
                pt.addCell(full.getReferencia());
            }
            
            doc.add(pt);
            
            doc.close();
            
        return null;
    }
    
}
