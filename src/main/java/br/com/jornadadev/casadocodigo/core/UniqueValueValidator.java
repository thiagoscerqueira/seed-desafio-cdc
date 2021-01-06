package br.com.jornadadev.casadocodigo.core;

import org.modelmapper.internal.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private String domainAttribute;
    private Class<?> clazz;

    @Override
    public void initialize(UniqueValue params) {
        this.domainAttribute = params.fieldName();
        this.clazz = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Query query = entityManager.createQuery("select 1 from " + clazz.getName() + " where " + domainAttribute + " = :value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.state(list.size() <=1, "Foi encontrado mais de um" + clazz + " com o atributo "+ domainAttribute);

        return list.isEmpty();
    }
}
