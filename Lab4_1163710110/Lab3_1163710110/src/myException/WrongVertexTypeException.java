package myException;

public class WrongVertexTypeException extends Exception{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public WrongVertexTypeException(String msg) {
        super(msg);
    }
}
