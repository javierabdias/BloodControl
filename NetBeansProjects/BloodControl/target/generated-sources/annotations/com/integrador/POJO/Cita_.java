package com.integrador.POJO;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cita.class)
public abstract class Cita_ {

	public static volatile SingularAttribute<Cita, Date> citFecha;
	public static volatile SingularAttribute<Cita, Double> total;
	public static volatile SingularAttribute<Cita, Usuarios> usuId;
	public static volatile CollectionAttribute<Cita, Examen> examenCollection;
	public static volatile SingularAttribute<Cita, Paciente> pacId;
	public static volatile SingularAttribute<Cita, Integer> citId;
	public static volatile SingularAttribute<Cita, Date> citHora;
	public static volatile SingularAttribute<Cita, String> status;

}

