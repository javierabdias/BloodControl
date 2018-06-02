package com.integrador.POJO;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Estudios.class)
public abstract class Estudios_ {

	public static volatile SingularAttribute<Estudios, Integer> estId;
	public static volatile SingularAttribute<Estudios, EstadoRegistro> erId;
	public static volatile SingularAttribute<Estudios, String> estNombre;
	public static volatile SingularAttribute<Estudios, Double> estMax;
	public static volatile SingularAttribute<Estudios, Examen> exaId;
	public static volatile SingularAttribute<Estudios, Double> estMin;

}

