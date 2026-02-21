package com.example.week3.homework.Exceptions;

public class DuplicateResourceException extends RuntimeException {

        public DuplicateResourceException(String message) {
            super(message);
        }

        public DuplicateResourceException() {
            super();
        }

}
