package app.cleancode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Entrypoint {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger();
        logger.error("Hello, World!");
    }
}
