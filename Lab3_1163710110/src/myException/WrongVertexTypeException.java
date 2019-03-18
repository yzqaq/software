package myException;

public class WrongVertexTypeException extends Exception{

    /**
     * 在某种类型的图应用中引入了不应出现的节点类型 进入自定义异常处理,提示错误,
结束此文件的读取,允许用户选择其他文本文件
     */
    private static final long serialVersionUID = 1L;

    public WrongVertexTypeException(String msg) {
        super(msg);
    }
}
