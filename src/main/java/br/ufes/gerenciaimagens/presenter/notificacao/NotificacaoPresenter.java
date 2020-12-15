package br.ufes.gerenciaimagens.presenter.notificacao;

import br.ufes.gerenciaimagens.model.Notificacao;
import br.ufes.gerenciaimagens.presenter.base.BaseInternalFramePresenter;
import br.ufes.gerenciaimagens.service.NotificacaoService;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rborges
 */
public class NotificacaoPresenter extends BaseInternalFramePresenter<NotificacaoView> {
    
    private NotificacaoService notificacaoService;
    private List<Notificacao> notificacoes;
    
    public NotificacaoPresenter(JDesktopPane container, Long idUsuarioLogado) {
        super(container, new NotificacaoView(), idUsuarioLogado);
        
        notificacaoService = new NotificacaoService();
        notificacoes = new ArrayList<>();
        
        configuraTabelaUsuarios();
        buscarUsuarios();
        marcarNotificacoesComoLida();
        
        getView().setVisible(true);
    }
    
    private void marcarNotificacoesComoLida() {
        try {
            notificacaoService.marcarNotificacoesComoLidas(getIdUsuarioLogado());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void configuraTabelaUsuarios() {
        JTable tabelaNotificacoes = getView().getTableNotificacoes();
        DefaultTableModel tmUsuarios = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tmUsuarios.setDataVector(new Object[][]{}, new String[]{"Remetente", "Mensagem", "Data envio"});
        
        tabelaNotificacoes.setModel(tmUsuarios);
        
        tabelaNotificacoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    private void buscarUsuarios() {
        try {
            DefaultTableModel tmNotificacoes = (DefaultTableModel) getView().getTableNotificacoes().getModel();

            tmNotificacoes.setNumRows(0);

            this.notificacoes = notificacaoService.obterTodasNotificacoes(getIdUsuarioLogado());

            for(Notificacao notificacao : notificacoes) {
                tmNotificacoes.addRow(new String[]{ notificacao.getRemetente().getNome(), notificacao.getMensagem(), notificacao.getDataEnviada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:MM")) });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
