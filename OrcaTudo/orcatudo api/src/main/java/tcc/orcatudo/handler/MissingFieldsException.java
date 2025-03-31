package tcc.orcatudo.handler;

public class MissingFieldsException extends BusinessException {

    public MissingFieldsException() {
        super("há algum campo obrigatório ausente");
    }
    
}
