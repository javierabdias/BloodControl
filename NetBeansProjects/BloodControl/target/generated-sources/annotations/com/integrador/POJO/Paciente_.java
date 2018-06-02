package com.integrador.POJO;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Paciente.class)
public abstract class Paciente_ {

	public static volatile SingularAttribute<Paciente, Persona> perId;
	public static volatile SingularAttribute<Paciente, String> pacCe;
	public static volatile SingularAttribute<Paciente, String> pacContra;
	public static volatile SingularAttribute<Paciente, EstadoRegistro> erId;
	public static volatile SingularAttribute<Paciente, Integer> pacId;
	public static volatile SingularAttribute<Paciente, String> pacTel;
	public static volatile CollectionAttribute<Paciente, Citas> citasCollection;
	public static volatile SingularAttribute<Paciente, String> pacCel;

}

