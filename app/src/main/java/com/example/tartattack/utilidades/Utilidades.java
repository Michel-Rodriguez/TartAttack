package com.example.tartattack.utilidades;

public class Utilidades {

    //   //Constantes campos tabla TartaPedido
    public static final String TABLA_TARTAPEDIDO="tartapedido";

    public static final String CAMPO_ID="id";
    public static final String CAMPO_SABOR="sabor";
    public static final String CAMPO_PRECIO="precio";
    public static final String CAMPO_IMAGEN="imagen";

    public static final String CREAR_TABLA_TARTAPEDIDO="CREATE TABLE "+""+TABLA_TARTAPEDIDO+
            " ("+CAMPO_ID+" INT, "+CAMPO_SABOR+" TEXT,"+CAMPO_PRECIO+" TEXT, "+CAMPO_IMAGEN+" INT)";
}
