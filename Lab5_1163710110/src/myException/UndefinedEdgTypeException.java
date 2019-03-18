package myException;

public class UndefinedEdgTypeException extends Exception {

    /**
     * 边中使用的节点在节点部分未定义:进入自定义异常处理,提示错误, 结束此文件的读取,允许用户选择其他文本文件。
     */
    private static final long serialVersionUID = 1L;

    public UndefinedEdgTypeException(String msg) {
        super(msg);
    }
}
