/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.exceptions;

/**
 *
 * @author Владислав
 */
public class ServerQueryException extends Exception {

    /**
     * Creates a new instance of <code>UserAlreadyExistExteption</code> without
     * detail message.
     */
    public ServerQueryException() {
    }

    /**
     * Constructs an instance of <code>UserAlreadyExistExteption</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ServerQueryException(String msg) {
        super(msg);
    }
}
