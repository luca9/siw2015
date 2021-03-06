package it.uniroma3.validator;

import it.uniroma3.facade.ProductFacade;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by lorenzovalente on 23/05/15.
 */

@FacesValidator("IDValidator")
public class IDValidator implements Validator {

    private ProductFacade productFacade;

    @Override
    public void validate (FacesContext context, UIComponent component, Object value)
    throws ValidatorException {
        String l = (String)value;
        if (productFacade.getProduct(l) == null) {
            FacesMessage msg = new FacesMessage("ID not found");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
