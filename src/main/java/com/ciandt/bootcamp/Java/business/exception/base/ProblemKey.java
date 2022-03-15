package com.ciandt.bootcamp.Java.business.exception.base;

/**
 *
 * Enum contendo os tipos de exceções. As mensagens encontram-se em "resource/messages.properties".
 *
 **/

public enum ProblemKey {

    //BREWERY API
    BREWERY_NOT_FOUND,

    //E-mail
    INVALID_EMAIL,
    USED_EMAIL,

    //Avaliação
    BREWERY_EVALUETED,
    EVALUATION_NOT_POSSIBLE,
    OUT_RANGE_EVALUETION
}
