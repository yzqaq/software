package myException;
/*
 * 文件中存在不符合语法规则的语句 在使用正则表
*达式进行文本解析的过程中,程序一旦发现这些非法语句,应捕获异常,
*进行异常处理(提示错误)
*,结束此文件的读取,允许用户选择其他文本
*文件。
 */

public class IllegalException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public IllegalException(String s) {
        super(s);
    }

}
