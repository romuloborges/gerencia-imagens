package br.ufes.gerenciaimagens.model.proxy;

import br.ufes.gerenciaimagens.model.Imagem;
import br.ufes.gerenciaimagens.model.interfaces.IImagem;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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
        BufferedImage img = ImageIO.read(new File(imagem.getCaminho()));
        Image scaled = img.getScaledInstance(64, 64, Image.SCALE_SMOOTH);

        return new ImageIcon(scaled);
    }
    
}
