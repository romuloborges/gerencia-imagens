package br.ufes.gerenciaimagens.presenter.listaimagem;

import br.ufes.gerenciaimagens.model.Imagem;
import br.ufes.gerenciaimagens.model.interfaces.IImagem;
import br.ufes.gerenciaimagens.model.proxy.ImagemProxy;
import br.ufes.gerenciaimagens.presenter.base.BaseInternalFramePresenter;
import br.ufes.gerenciaimagens.presenter.listaimagem.renderer.RendererListaImagem;
import br.ufes.gerenciaimagens.service.ImagemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDesktopPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author rborges
 */
public class ListaImagemPresenter extends BaseInternalFramePresenter<ListaImagemView> {

    private ImagemService imagemService;
    private Map<String, IImagem> mapPathImagem;

    public ListaImagemPresenter(JDesktopPane desktop, Long idUsuarioLogado) {
        super(desktop, new ListaImagemView(), idUsuarioLogado);

        imagemService = new ImagemService();

        iniciarListaImagens();

        getView().setVisible(true);
    }

    private void iniciarListaImagens() {
        try {
            List<Imagem> imagens = imagemService.obterTodasNaoExcluidas();

            if (imagens == null) {
                throw new Exception("Erro ao carregar imagens");
            }

            if (imagens.size() > 0) {
                mapPathImagem = new HashMap<>();

                String[] listData = new String[imagens.size()];

                for (int i = 0; i < imagens.size(); i++) {
                    Imagem imagem = imagens.get(i);

                    String caminho = imagem.getCaminho();
                    IImagem proxy = new ImagemProxy(imagem);

                    mapPathImagem.put(caminho, proxy);
                    listData[i] = caminho;
                }

                JList list = getView().getListImagem();

                list.setListData(listData);
                list.setCellRenderer(new RendererListaImagem(mapPathImagem));
            } else {
                JList list = getView().getListImagem();

                list.setListData(new String[]{"Nenhuma imagem foi encontrada"});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

}
