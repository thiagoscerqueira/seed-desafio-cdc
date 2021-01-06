package br.com.jornadadev.casadocodigo.core;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExisteIdValidator implements ConstraintValidator<ExisteId, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private String domainAttribute;
    private Class<?> clazz;

    @Override
    public void initialize(ExisteId params) {
        this.domainAttribute = params.fieldName();
        this.clazz = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }

        Query query = entityManager.createQuery("select 1 from " + clazz.getName() + " where " + domainAttribute + " = :value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        return !list.isEmpty();
    }
}
