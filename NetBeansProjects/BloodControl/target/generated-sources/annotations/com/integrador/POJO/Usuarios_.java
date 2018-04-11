package com.integrador.POJO;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuarios.class)
public abstract class Usuarios_ {

	public static volatile SingularAttribute<Usuarios, String> usuNombre;
	public static volatile SingularAttribute<Usuarios, String> usuAp;
	public static volatile CollectionAttribute<Usuarios, Cita> citaCollection;
	public static volatile SingularAttribute<Usuarios, String> usuCel;
	public static volatile SingularAttribute<Usuarios, String> usuContra;
	public static volatile SingularAttribute<Usuarios, Integer> usuId;
	public static volatile SingularAttribute<Usuarios, String> usuAm;
	public static volatile SingularAttribute<Usuarios, String> usuTipo;
	public static volatile SingularAttribute<Usuarios, String> usuTel;
	public static volatile SingularAttribute<Usuarios, Date> usuFn;
	public static volatile SingularAttribute<Usuarios, Direccion> dirId;
	public static volatile SingularAttribute<Usuarios, String> usuCe;

}

