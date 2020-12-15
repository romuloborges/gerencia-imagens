package br.ufes.gerenciaimagens.presenter.usuario.manterusuario.criptografasenhautil;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 *
 * @author rborges
 */
public class CriptografaSenhaUtil {
    
    private static CriptografaSenhaUtil instancia;
    
    private CriptografaSenhaUtil() {
    }
    
    public static CriptografaSenhaUtil getInstancia() {
        if (instancia == null) {
            instancia = new CriptografaSenhaUtil();
        }
        
        return instancia;
    }
    
    public String criptografar(String senha) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        
        digest.update(senha.getBytes(StandardCharsets.UTF_8));

        byte[] passwordDigest = digest.digest();

        return String.format("%064x", new BigInteger(1, passwordDigest));
    }
    
}
