public class Error extends Reponse{

    public Error(String entete, String corp, Type_Reponse type_reponse) {

        super(null, corp, Type_Reponse.ERROR);
    }
}
