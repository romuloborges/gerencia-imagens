package br.ufes.gerenciaimagens.service;

import java.util.List;

import br.ufes.gerenciaimagens.model.Imagem;
import br.ufes.gerenciaimagens.repository.ImagemRepository;

/**
 *
 * @author rborges
 */
public class ImagemService {
    
    private ImagemRepository imagemRepository;
    
    public ImagemService() {
        this.imagemRepository = new ImagemRepository();
    }

    public List<Imagem> obterTodasNaoExcluidas() throws Exception {
        return imagemRepository.obterTodasNaoExcluidas();
    }
    
    public void excluir(Long id) throws Exception {
        imagemRepository.excluir(id);
    }
    
    public void restaurar(Long id) throws Exception {
        imagemRepository.restaurar(id);
    }
    
}
