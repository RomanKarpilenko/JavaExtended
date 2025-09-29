package ru.itmo.jpa.util;

public enum State {
    WAIT_CODE,
    WAIT_NAME_TR_KEY,
    WAIT_RU_TR,
    WAIT_EN_TR,
    WAIT_POPULATION,
    DONE,
    CONTINUE,
    EXIT
}
