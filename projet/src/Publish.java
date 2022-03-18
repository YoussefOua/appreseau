
public class Publish  extends Requetes{
    public Publish(String entete, String corp) {
        super(entete, corp, Type_Requetes.PUBLISH);
    }

    @Override
    public String afficher(){

    return "entete : " + type_requetes +  author + '\n' +
             corps;
}
}
