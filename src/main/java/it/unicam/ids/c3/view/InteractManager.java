package it.unicam.ids.c3.view;

import org.springframework.stereotype.Component;

@Component
public class InteractManager {

    private ICliente icl;

    public InteractManager() {
        this.icl = new ICliente();
    }

    public ICliente getIcl() {
        return icl;
    }

    public void setIcl(ICliente icl) {
        this.icl = icl;
    }

}
