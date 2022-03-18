public class Requetes {
    Type_Requetes type_requetes ;
    String author ;
    String corps;

    public Requetes(String author, String corp, Type_Requetes type_requetes){
        this.type_requetes =  type_requetes;
        this.author = author;
        this.corps = corp ;
    }
    public String afficher(){
        return type_requetes +" author:" + author + '\n' +
                "corps : " + corps + '\n';
    }
}