package ru.alwertus.zerospring.delme;

public class NoAccessException extends RuntimeException{
    public NoAccessException(String accessNeed) {
        super("No access. Need = " + accessNeed);
    }
}
