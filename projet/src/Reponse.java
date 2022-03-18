public class Reponse {

    Type_Reponse type_reponse ;
    String entete ;
    String corp ;

    public Reponse(String entete, String corp, Type_Reponse type_reponse){
        this.type_reponse=  type_reponse;
        this.entete = entete;
        this.corp = corp ;
    }
    public String afficher(){
        return "entête :  " + type_reponse + '\n' +
                "corps " + corp + '\n';
    }
}




