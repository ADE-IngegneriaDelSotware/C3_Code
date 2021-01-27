package it.unicam.ids.c3.view;

import org.springframework.stereotype.Component;

@Component
public class InteractManager {

    private IAddettoNegozio ia;

    private ICorriere ic;

    private ICliente icl;

    private IAmministratore iam;

    public InteractManager() {
        this.ia = new IAddettoNegozio();
        this.ic = new ICorriere();
        this.icl = new ICliente();
        this.iam = new IAmministratore();
    }

    public IAddettoNegozio getIa(){
        return ia;
    }

    public void setIa(IAddettoNegozio ia){
        this.ia = ia;
    }

    public ICorriere getIc() {
        return ic;
    }

    public void setIc(ICorriere ic) {
        this.ic = ic;
    }

    public ICliente getIcl() {
        return icl;
    }

    public void setIcl(ICliente icl) {
        this.icl = icl;
    }

    public IAmministratore getIam(){
        return iam;
    }

    public void setIam(IAmministratore iam){
        this.iam = iam;
    }

}
