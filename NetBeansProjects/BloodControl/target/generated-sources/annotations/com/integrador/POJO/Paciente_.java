package com.integrador.POJO;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Paciente.class)
public abstract class Paciente_ {

	public static volatile CollectionAttribute<Paciente, Cita> citaCollection;
	public static volatile SingularAttribute<Paciente, String> pacCe;
	public static volatile SingularAttribute<Paciente, String> pacContra;
	public static volatile SingularAttribute<Paciente, String> pacAp;
	public static volatile SingularAttribute<Paciente, String> pacAm;
	public static volatile SingularAttribute<Paciente, Integer> pacId;
	public static volatile SingularAttribute<Paciente, Integer> pacTel;
	public static volatile SingularAttribute<Paciente, Direccion> dirId;
	public static volatile SingularAttribute<Paciente, Integer> pacCel;
	public static volatile SingularAttribute<Paciente, String> pacFn;
	public static volatile SingularAttribute<Paciente, String> pacNombre;

}

