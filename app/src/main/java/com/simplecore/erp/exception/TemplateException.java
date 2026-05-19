package com.simplecore.erp.exception;

public class TemplateException extends Exception{

    public static final long serialVersionUID = 100L;

    public TemplateException() {
        super("Template length does Not match with entried value");
    }
}
