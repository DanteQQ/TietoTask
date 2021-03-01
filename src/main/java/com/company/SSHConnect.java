package com.company;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;

import java.io.Console;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SSHConnect {
    private static final Console myConsole = System.console();

    public static void sshConnect(String username, String password, String address) throws IOException {
            final SSHClient ssh = new SSHClient();
            ssh.addHostKeyVerifier(new PromiscuousVerifier());
            ssh.connect(address);
            Session session = null;
            try {
                ssh.authPassword(username, password);
                session = ssh.startSession();
                final Command cmd = session.exec("ping -c 1 google.com");
                myConsole.writer().print(IOUtils.readFully(cmd.getInputStream()).toString());
                cmd.join(5, TimeUnit.SECONDS);
                myConsole.writer().print("\n** exit status: " + cmd.getExitStatus());
            } finally {
                try {
                    if (session != null) {
                        session.close();
                    }
                } catch (IOException e) {
                    // Do Nothing
                }

                ssh.disconnect();
            }
    }
}
