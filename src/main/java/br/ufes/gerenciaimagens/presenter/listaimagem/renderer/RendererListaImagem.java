package br.ufes.gerenciaimagens.presenter.listaimagem.renderer;

import br.ufes.gerenciaimagens.model.interfaces.IImagem;
import java.awt.Component;
import java.util.Map;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;

/**
 *
 * @author rborges
 */
public class RendererListaImagem extends DefaultListCellRenderer {
    
    private Map<String, IImagem> mapPathImagem;
    
    public RendererListaImagem(Map<String, IImagem> mapPathImagem) {
        if (mapPathImagem == null) {
            throw new RuntimeException("Lista passada é inválida");
        }
        
        this.mapPathImagem = mapPathImagem;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        label.setText("");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        
        try {
            label.setIcon(mapPathImagem.get((String) value).getImageIcon());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return label;
    }

}
