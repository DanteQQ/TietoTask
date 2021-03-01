package com.company;

import java.io.IOException;

public class Main {

    private static String user;
    private static String password;
    private static String host;

    public static void main(String[] args) {
        System.out.println("Arg 0 = " + args[0]);
        System.out.println("Arg 1 = " + args[1]);
        System.out.println("Arg 2 = " + args[2]);
        user = args[0];
        password = args[1];
        host = args[2];

        try {
            SSHConnect.sshConnect(user, password, host);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
