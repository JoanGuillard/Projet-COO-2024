package modele;

public class ElementCarte {
    private String apparence;
    private int abscisse;
    private int ordonnee;



    public ElementCarte(String apparence){
        this.apparence = apparence;
    }

    public ElementCarte(){
        this.apparence="";
    }
    public String getApparence(){
        return this.apparence;
    }

    public void setApparence(String apparence){
        this.apparence = apparence;
    }

    public int getAbscisse() {
        return abscisse;
    }

    public int getOrdonnee() {
        return ordonnee;
    }

    public void nouvellePosition(int abscisse,int ordonnee){
        this.abscisse=abscisse;
        this.ordonnee=ordonnee;
    }
}
