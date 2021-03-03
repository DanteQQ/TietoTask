package com.company;

import org.apache.commons.validator.routines.InetAddressValidator;

import java.io.IOException;

public class Main {

    private static String user;
    private static String password;
    private static String host;
    private static String command;

    public static void main(String[] args) {

        if(args.length != 4) {
            System.out.println("Proper parameters are: username, password, host address, command");
            System.exit(0);
        }

        InetAddressValidator validator = new InetAddressValidator();
        if(!validator.isValidInet4Address(args[2])){
            System.out.println("Wrong format of IP address");
            System.exit(0);
        }

        user = args[0];
        password = args[1];
        host = args[2];
        command = args[3];

        try {
            SSHConnect.sshConnect(user, password, host, command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
