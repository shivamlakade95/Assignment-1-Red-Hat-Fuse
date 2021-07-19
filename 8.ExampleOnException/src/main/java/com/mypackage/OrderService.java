package com.mypackage;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;

public class OrderService {

    Logger logger = Logger.getLogger(this.getClass());
    public void validate() throws IOException {
        logger.info("Within validate method....");
        throw new IOException();
    }

    public void validate2() throws FileNotFoundException {
        logger.info("Within validate2 method....");
        throw new FileNotFoundException();
    }


}
