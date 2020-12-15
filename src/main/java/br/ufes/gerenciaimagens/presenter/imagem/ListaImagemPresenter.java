package br.ufes.gerenciaimagens.presenter.imagem;

import br.ufes.gerenciaimagens.model.Imagem;
import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.model.interfaces.IImagem;
import br.ufes.gerenciaimagens.proxy.ImagemProxy;
import br.ufes.gerenciaimagens.presenter.base.BaseInternalFramePresenter;
import br.ufes.gerenciaimagens.presenter.imagem.renderer.RendererListaImagem;
import br.ufes.gerenciaimagens.presenter.imagem.state.ConcederPermissaoState;
import br.ufes.gerenciaimagens.presenter.imagem.state.ListaImagemState;
import br.ufes.gerenciaimagens.presenter.imagem.state.ListagemImagensState;
import br.ufes.gerenciaimagens.service.ImagemService;
import java.util.ArrayList;

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
    private ListaImagemState state;
    private Usuario usuarioParaConcederPermissao;
    private List<Imagem> imagens;

    public ListaImagemPresenter(JDesktopPane desktop, Long idUsuarioLogado) {
        super(desktop, new ListaImagemView(), idUsuarioLogado);

        imagemService = new ImagemService();

        iniciarListaImagens();
        
        setState(new ListagemImagensState(this));

        getView().setVisible(true);
    }
    
    public ListaImagemPresenter(JDesktopPane desktop, Long idUsuarioLogado, Usuario usuarioParaConcederPermissao) {
        super(desktop, new ListaImagemView(), idUsuarioLogado);

        imagemService = new ImagemService();
        this.usuarioParaConcederPermissao = usuarioParaConcederPermissao;

        iniciarListaImagens();
        
        setState(new ConcederPermissaoState(this));

        getView().setVisible(true);
    }

    private void iniciarListaImagens() {
        imagens = new ArrayList<>();
        buscarImagens();
    }
    
    public void buscarImagens() {
        try {
            imagens = imagemService.obterTodasNaoExcluidas();

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

    public Usuario getUsuarioParaConcederPermissao() {
        return usuarioParaConcederPermissao;
    }

    public void setState(ListaImagemState state) {
        this.state = state;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }
    
    public Long getIdImagemSelecionada() {
        int linhaSelecionada = getView().getListImagem().getSelectedIndex();
        
        if (linhaSelecionada >= 0) {
            Imagem imagem = imagens.get(linhaSelecionada);
            return imagem.getId();
        }
        
        return null;
    }
    
    public Imagem getImagemSelecionada() {
        int linhaSelecionada = getView().getListImagem().getSelectedIndex();
        
        if (linhaSelecionada >= 0) {
            Imagem imagem = imagens.get(linhaSelecionada);
            return imagem;
        }
        
        return null;
    }

}
