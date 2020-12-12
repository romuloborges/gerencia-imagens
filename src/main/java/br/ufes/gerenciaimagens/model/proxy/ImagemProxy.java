package br.ufes.gerenciaimagens.model.proxy;

import br.ufes.gerenciaimagens.model.Imagem;
import br.ufes.gerenciaimagens.model.interfaces.IImagem;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author rborges
 */
public class ImagemProxy implements IImagem {
    
    private Imagem imagem;
    
    public ImagemProxy(Imagem imagem) {
        if (imagem == null) {
            throw new RuntimeException("Imagem fornecida é inválida");
        }
        
        this.imagem = imagem;
    }

    @Override
    public ImageIcon getImageIcon() throws Exception {
        try {
            BufferedImage img = ImageIO.read(new File(imagem.getCaminho()));
            Image scaled = img.getScaledInstance(64, 64, Image.SCALE_SMOOTH);

            return new ImageIcon(scaled);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar imagem", "", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }
    
}
