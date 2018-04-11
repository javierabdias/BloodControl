package com.integrador.POJO;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Direccion.class)
public abstract class Direccion_ {

	public static volatile SingularAttribute<Direccion, String> dirCalle;
	public static volatile SingularAttribute<Direccion, String> dirColonia;
	public static volatile SingularAttribute<Direccion, String> dirMza;
	public static volatile SingularAttribute<Direccion, String> dirLote;
	public static volatile CollectionAttribute<Direccion, Usuarios> usuariosCollection;
	public static volatile SingularAttribute<Direccion, Integer> dirId;
	public static volatile CollectionAttribute<Direccion, Paciente> pacienteCollection;

}

