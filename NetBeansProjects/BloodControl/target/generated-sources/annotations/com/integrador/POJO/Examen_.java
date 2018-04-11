package com.integrador.POJO;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Examen.class)
public abstract class Examen_ {

	public static volatile CollectionAttribute<Examen, Cita> citaCollection;
	public static volatile SingularAttribute<Examen, Double> precio;
	public static volatile SingularAttribute<Examen, Integer> exaId;
	public static volatile CollectionAttribute<Examen, Estudios> estudiosCollection;
	public static volatile SingularAttribute<Examen, String> exaNom;

}

