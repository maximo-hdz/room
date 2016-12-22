package com.notmusa.room.forms;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionFormBean;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.SessionActionMapping;
import org.apache.struts.util.LabelValueBean;



import com.notmusa.room.db.*;
import com.notmusa.room.utils.Dia;
import com.notmusa.room.utils.MatrizHorasDias;




public class ReservacionFormBean2 extends ActionForm {


        private String operacion;
        private Vector salas;
        private int salaSelect;
        private String option;
        private ArrayList dias;
        private ArrayList horasDias;
        private String fecha;
        private String buscar;
        private String mail;
        private String departamento;
        private String comentario;
        private String total;


        public String getTotal() {
                return total;
        }

        public void setTotal(String total) {
                this.total = total;
        }

        public String getBuscar() {
                return buscar;
        }

        public void setBuscar(String buscar) {
                this.buscar = buscar;
        }

        public String getFecha() {
                return fecha;
        }

        public void setFecha(String fecha) {
                this.fecha = fecha;
        }

        //private boolean l
        public ReservacionFormBean2() throws SQLException {
                inicializaSalasDB();
                inicializa();
                if(!salas.isEmpty()){
                salaSelect=Integer.parseInt(((LabelValueBean)salas.get(0)).getValue());
                }else{
                        salaSelect = 0;
                }
                inicializaHeaderDias(Calendar.getInstance(new Locale("en","US")));
                horasDias=new MatrizHorasDias(dias,salaSelect);


        }




        public String getOperacion() {
                return operacion;
        }

        public Vector getSalas() {
                return salas;
        }

        public void setSalas(Vector salas) {
                this.salas = salas;
        }

        private Vector getSalasDB(){
                return salas;
        }

        public int getSalaSelect() {
                return salaSelect;
        }


        public void inicializaSalasDB() {

                ArrayList salasAll = new ArrayList();
                try {
                        SalaDB salaDB = SalaDB.getInstance();
                        salasAll = salaDB.selectAllByES(6);

                } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                Vector salas = new Vector();
                if (!salasAll.isEmpty()) {
                        Iterator iterator = salasAll.iterator();
                        while (iterator.hasNext()) {
                                Sala sala = (Sala) iterator.next();
                                salas.add(new LabelValueBean(sala.getNombre_sala(), String
                                                .valueOf(sala.getIdSala())));
                        }

                }
                this.salas = salas;
        }

        public void setSalaSelect(int salaSelect) {
                this.salaSelect = salaSelect;
        }
        public void setOperacion(String operacion) {
                this.operacion = operacion;
        }
        public void inicializaHeaderDias(Calendar calendar){
dias=new ArrayList();

                Dia dia;
                calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
                dia=new Dia("Domingo",calendar.getTime());
                dias.add(dia);


                calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
                dia=new Dia("Lunes",calendar.getTime());
                dias.add(dia);

                calendar.set(Calendar.DAY_OF_WEEK,Calendar.TUESDAY);
                dia=new Dia("Martes",calendar.getTime());
                dias.add(dia);
                calendar.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY);
                dia=new Dia("Miercoles",calendar.getTime());
                dias.add(dia);
                calendar.set(Calendar.DAY_OF_WEEK,Calendar.THURSDAY);
                dia=new Dia("Jueves",calendar.getTime());
                dias.add(dia);
                calendar.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);
                dia=new Dia("Viernes",calendar.getTime());
                dias.add(dia);

                calendar.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
                dia=new Dia("Sabado",calendar.getTime());
                dias.add(dia);

        }

        public ArrayList getDias() {
                return dias;
        }

        public void setDias(ArrayList dias) {
                this.dias = dias;
        }

        public ArrayList getHorasDias() {
                return horasDias;
        }

        public void setHorasDias(ArrayList horasDias) {
                this.horasDias = horasDias;
        }

        public String getOption() {
                return option;
        }

        public void setOption(String option) {
                this.option = option;
        }

        public String getComentario() {
                return comentario;
        }

        public void setComentario(String comentario) {
                this.comentario = comentario;
        }

        public String getDepartamento() {
                return departamento;
        }

        public void setDepartamento(String departamento) {
                this.departamento = departamento;
        }

        public String getMail() {
                return mail;
        }

        public void setMail(String mail) {
                this.mail = mail;
        }


        public void inicializa(){

                 buscar="";
                 mail="";
                 departamento="";
                 comentario="";
                 option="";
                 total="";

        }

     public ActionErrors validate(ActionMapping mapping,
                        HttpServletRequest request) {

                ActionErrors actionErrors = new ActionErrors();

                if (operacion != null && !operacion.equals("next")
                                && !operacion.equals("back") && !operacion.equals("mostrar")
                                && !operacion.equals("salir")
                                && !operacion.equals("updateByFecha")) {

                        if (mail == null || mail.trim().equals("")) {
                                actionErrors.add("empty", new ActionMessage(
                                                "errors.reservacion"));
                        } else if (!mail
                                        .matches("^[A-Za-z][A-Za-z0-9_.-]*@[A-Za-z0-9_-]+[.]+[A-Za-z0-9_.-]+[A-za-z]$")) {
                                actionErrors.add("empty",
                                                new ActionMessage("errors.formatMail"));
                        } else if (departamento == null || departamento.equals("")
                                        || comentario == null || comentario.trim().equals("")) {
                                actionErrors.add("empty", new ActionMessage(
                                                "errors.reservacion"));
                        } else if (option == null || option.trim().equals("")) {
                                actionErrors.add("empty", new ActionMessage("errors.hinicio"));
                        } else if (total == null || total.trim().equals("")) {
                                actionErrors.add("empty", new ActionMessage("errors.htotal"));
                        }
                }
                return actionErrors;
        }



}
