package keksdose.fwkib.modules;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

import org.pircbotx.hooks.events.MessageEvent;

public class RsaGenPub implements Command {

    @Override
	public String apply(MessageEvent event) {
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");

        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) kp.getPublic();
        ByteArrayOutputStream byteOs = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(byteOs);
        dos.writeInt("ssh-rsa".getBytes().length);
        dos.write("ssh-rsa".getBytes());
        dos.writeInt(rsaPublicKey.getPublicExponent().toByteArray().length);
        dos.write(rsaPublicKey.getPublicExponent().toByteArray());
        dos.writeInt(rsaPublicKey.getModulus().toByteArray().length);
        dos.write(rsaPublicKey.getModulus().toByteArray());
        String enc = Base64.getEncoder().encodeToString(byteOs.toByteArray());
        return "ssh-rsa " + enc + " " + "made by fwkib";
    } catch (NoSuchAlgorithmException | IOException e) {
        e.printStackTrace();
    }
    return "wohl kein key für dich";
    }
}