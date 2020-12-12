package br.ufes.gerenciaimagens.model;

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
public class Imagem implements IImagem {
    
    private Long id;
    private String caminho;
    private boolean excluida;

    public Imagem(Long id, String caminho, boolean excluida) {
        this.id = id;
        this.caminho = caminho;
        this.excluida = excluida;
    }
    
    @Override
    public ImageIcon getImageIcon() throws Exception {
        BufferedImage img = ImageIO.read(new File(getCaminho()));

        return new ImageIcon(img);
    }

    public Long getId() {
        return id;
    }

    public String getCaminho() {
        return caminho;
    }

    public boolean isExcluida() {
        return excluida;
    }

    public void setExcluida(boolean excluida) {
        this.excluida = excluida;
    }
    
}
