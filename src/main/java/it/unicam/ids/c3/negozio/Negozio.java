package it.unicam.ids.c3.negozio;

import it.unicam.ids.c3.merce.Categoria;
import it.unicam.ids.c3.merce.MerceInventarioNegozio;
import it.unicam.ids.c3.personale.AddettoNegozio;
import it.unicam.ids.c3.personale.Corriere;
import it.unicam.ids.c3.vendita.Vendita;
import it.unicam.ids.c3.vendita.VenditaSpedita;

import java.util.ArrayList;
import java.util.List;

public class Negozio {

    private String nome;
    private String indirizzo;
    private String p_iva;
    private List<Categoria> settori;
    private int ID;
    private List<AddettoNegozio> addettiNegozio;
    private List<Corriere> corrieri;
    private List<Negozio> negoziDisponibili;
    private List<Carta> cartaList;
    private List<VenditaSpedita> venditePuntiDiRitiro;
    private List<MerceInventarioNegozio> merceInventarioNegozioList;
    private List<Vendita> vendite;
    private boolean disponibilitaRitiro;
    private List<MerceInventarioNegozio> merciDaOrdinare;

    public Negozio(String nome,String indirizzo, String p_iva, List<Categoria> categorie){
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.p_iva = p_iva;
        this.settori = categorie;
        this.addettiNegozio = new ArrayList<>();
        this.corrieri = new ArrayList<>();
        this.negoziDisponibili = new ArrayList<>();
        this.cartaList = new ArrayList<>();
        this.merceInventarioNegozioList = new ArrayList<>();
        this.vendite = new ArrayList<>();
        this.venditePuntiDiRitiro = new ArrayList<>();
        this.merciDaOrdinare = new ArrayList<>();
    }

    public String getIndirizzo() {
        return this.indirizzo;
    }

    public String getP_Iva() {
        return p_iva;
    }

    public List<Categoria> getCategorie() {
        return this.settori;
    }

    public List<Carta> getCarte() {
        return cartaList;
    }

    public void addCarta(Carta carta) {
        this.cartaList.add(carta);
    }

    public List<Negozio> getNegoziDisponibili() {
        return negoziDisponibili;
    }

    public List<Corriere> getCorrieri() {
        return corrieri;
    }

    public void addCorriere(Corriere corriere2) {
        corrieri.add(corriere2);
    }

    public void addNegozioDisponibile(Negozio pdr) {
        negoziDisponibili.add(pdr);
    }

    public List<MerceInventarioNegozio> getMerceInventarioNegozio() {
        return merceInventarioNegozioList;
    }

    public void addMerceInventarioNegozio(MerceInventarioNegozio merceInventarioNegozio) {
        this.merceInventarioNegozioList.add(merceInventarioNegozio);
    }

    public void removeMerciInventarioNegozio(List<MerceInventarioNegozio> merci) {
        this.merceInventarioNegozioList.removeAll(merci);
    }

    public List<Vendita> getVendite() {
        return this.vendite;
    }

    public void addVendita(Vendita v) {
        this.vendite.add(v);
    }

    public List<VenditaSpedita> getVenditeNegozioRitiro() {
        return venditePuntiDiRitiro;
    }

    public void addVenditaInNegozioRitiro(VenditaSpedita vs) {
        this.venditePuntiDiRitiro.add(vs);
    }

    public void removeVenditeInNegozioRitiro(List<VenditaSpedita> list) {
        this.venditePuntiDiRitiro.removeAll(list);
    }

    public boolean getDisponibilitaRitiro() {
        return disponibilitaRitiro;
    }

    public void setDisponibilitaRitiro(boolean disponibilita) {
        this.disponibilitaRitiro = disponibilita;
    }

    public List<MerceInventarioNegozio> getMerciDaOrdinare() {
        return merciDaOrdinare;
    }

    public void addMerciDaOrdinare(MerceInventarioNegozio min) {
        merciDaOrdinare.add(min);
    }

    public void removeMerciDaOrdinare(List<MerceInventarioNegozio> lmdo) {
        merciDaOrdinare.removeAll(lmdo);
    }

    public List<AddettoNegozio> getAddetti() {
        return addettiNegozio;
    }

    public void addAddettoNegozio(AddettoNegozio an) {
        this.addettiNegozio.add(an);
    }

    public void removeAddettoNegozio(AddettoNegozio an) {
        this.addettiNegozio.remove(an);
    }

    @Override
    public String toString() {
        return nome + ", " + indirizzo;
    }

}
