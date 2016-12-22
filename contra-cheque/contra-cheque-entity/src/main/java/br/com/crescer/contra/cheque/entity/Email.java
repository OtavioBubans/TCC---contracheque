
package br.com.crescer.contra.cheque.entity;

/**
 *
 * @author Otávio
 */
public class Email {
    
    private String texto;
    private final String assunto;
    private String destinatario;
    private final String remetente;
    

    public Email( String destinatario, String ip, String usuario) {
        this.assunto = "EMAIL SEGURANÇA CONTRACHEQUE";
        this.destinatario = destinatario;
        this.remetente = "contracheque.cwi@gmail.com"; // email do admin;
        this.texto = "<html>\n" +
                            "<head>\n" +
                            "    \n" +
                            "</head>\n" +
                            "<body >\n" +
                            "    <div style=\"width:450px; margin:10px auto; text-align: justify;\">\n" +
                            "        <br/> \n" +
                            "        <br/>\n" +
                            "\n" +
                            "        <h4>Alerta</h4>\n" +
                            "\n" +
                            "        <br/>\n" +
                            "\n" +
                            "        <p>Houve uma incorfomidade suspeita no acesso do contracheque no sistema de visualização dos contracheques.</p>\n" +
                            "        <p>Alguém está acessando o contracheque com algum IP ou horário fora do comum do colaborador<b> " +usuario + " </b>.</p>\n" +
                            "\n" +
                            "        <br/>\n" +
                            "\n" +
                            "        <p><b>IP do maquina de acesso: </b> <label> " + ip +  " </label></p>\n" +
                            "        <br/> \n" +
                            "\n" +
                            "        <div style=\"width:250px;text-align:center;margin:0px auto;\">\n" +
                            "        <img src=\"http://i.imgur.com/92ei8XC.png\" width=\"250px\" alt=\"CWI\"/><h3> wwww.cwi.com.br</h3>\n" +
                            "        </div>          \n" +
                            "    </div>\n" +
                            "              \n" +
                            "    \n" +
                            "</body>\n" +
                            "</html>";
         
    }
    
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAssunto() {
        return assunto;
    }
    
    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getEmailAdmin() {
        return  remetente;
    }
 
}
