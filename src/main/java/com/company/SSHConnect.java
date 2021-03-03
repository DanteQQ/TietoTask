package com.company;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;

import java.io.IOException;

public class SSHConnect {

    public static void sshConnect(String username, String password, String address, String command) throws IOException {
            final SSHClient ssh = new SSHClient();
            ssh.addHostKeyVerifier(new PromiscuousVerifier());
            ssh.connect(address);
            Session session = null;
            try {
                ssh.authPassword(username, password);
                session = ssh.startSession();
                final Command cmd = session.exec(command);
                System.out.println(IOUtils.readFully(cmd.getInputStream()).toString());
                System.out.println("\nExit status: " + cmd.getExitStatus());
            } finally {
                try {
                    if (session != null) {
                        session.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ssh.disconnect();
            }
    }
}
