package br.com.vittalis.sistema.controller;

import br.com.vittalis.sistema.model.ImagemPacote;
import br.com.vittalis.sistema.model.Pacote;
import br.com.vittalis.sistema.repository.ImagemPacoteRepository;
import br.com.vittalis.sistema.repository.NavioRepository;
import br.com.vittalis.sistema.repository.PacoteRepository;
import br.com.vittalis.sistema.util.FileUploadUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pacote")
public class PacoteController {

    @Autowired
    private PacoteRepository pacoteRepository;

    @Autowired
    private NavioRepository navioRepository;

    @Autowired
    private ImagemPacoteRepository imagemPacoteRepository;

    @GetMapping
    public String listagem(Model model) {

        List<Pacote> listaPacotes = pacoteRepository.findAll();

        model.addAttribute("pacotes", listaPacotes);

        return "pages/pacote/listagem";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid Pacote pacote,
            BindingResult result,
            RedirectAttributes attributes,
            @RequestParam(value = "imagensPacote", required = false) MultipartFile[] imagensPacote,
            @RequestParam(value = "imagemDestaque", required = false) String[] imagemDestaque,
            @RequestParam(value = "imagensParaRemover", required = false) Long[] imagensParaRemover,
            @RequestParam(value = "imagemExistenteDestaque", required = false) Long imagemExistenteDestaque
    ) throws IOException {
        
        if (result.hasErrors()) {
            return "redirect:/pacote/cadastro";
        }

        // Verifica os dados enviados pelo formulário



        // Salvar o pacote primeiro para obter o ID
        pacoteRepository.save(pacote);


        // Processar remoção de imagens existentes
        if (imagensParaRemover != null && imagensParaRemover.length > 0) {
            for (Long imagemId : imagensParaRemover) {
                Optional<ImagemPacote> imagemOptional = imagemPacoteRepository.findById(imagemId);
                if (imagemOptional.isPresent()) {
                    ImagemPacote imagem = imagemOptional.get();
                    // Remover arquivo físico se existir
                    try {
                        String caminhoArquivo = "src/main/resources/static" + imagem.getURL();
                        File arquivo = new File(caminhoArquivo);
                        if (arquivo.exists()) {
                            arquivo.delete();
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao remover arquivo físico: " + e.getMessage());
                    }
                    // Remover do banco
                    imagemPacoteRepository.delete(imagem);
                    System.out.println("Imagem removida: " + imagemId);
                }
            }
        }

        // Atualizar destaque das imagens existentes
        if (imagemExistenteDestaque != null) {
            // Primeiro, remover destaque de todas as imagens existentes do pacote
            List<ImagemPacote> todasImagensExistentes = imagemPacoteRepository.findByPacote(pacote);
            for (ImagemPacote img : todasImagensExistentes) {
                img.setDestaque(false);
                imagemPacoteRepository.save(img);
            }
            
            // Marcar a imagem selecionada como destaque
            Optional<ImagemPacote> imagemDestaqueOptional = imagemPacoteRepository.findById(imagemExistenteDestaque);
            if (imagemDestaqueOptional.isPresent()) {
                ImagemPacote imagemSelecionada = imagemDestaqueOptional.get();
                imagemSelecionada.setDestaque(true);
                imagemPacoteRepository.save(imagemSelecionada);
                
                // Definir como imagem principal do pacote
                pacote.setImage(imagemSelecionada.getURL());
                pacoteRepository.save(pacote);
                System.out.println("Imagem existente marcada como destaque: " + imagemExistenteDestaque);
            }
        }

        // Processar novas imagens
        if (imagensPacote != null && imagensPacote.length > 0) {
            // Criar diretório específico para o pacote
            String pacoteId = String.valueOf(pacote.getId());
            String uploadPasta = "src/main/resources/static/img/pacote/" + pacoteId;

            // Se não há imagem existente marcada como destaque, remover destaque de todas as existentes
            if (imagemExistenteDestaque == null) {
                List<ImagemPacote> imagensExistentes = imagemPacoteRepository.findByPacote(pacote);
                for (ImagemPacote img : imagensExistentes) {
                    img.setDestaque(false);
                    imagemPacoteRepository.save(img);
                }
            }

            for (int i = 0; i < imagensPacote.length; i++) {
                MultipartFile file = imagensPacote[i];

                if (!file.isEmpty()) {
                    // Gerar nome único para o arquivo
                    String extensao = StringUtils.getFilenameExtension(file.getOriginalFilename());
                    String fileName = pacote.getId() + "_" + System.currentTimeMillis() + "_" + i + "." + extensao;

                    // Salvar arquivo físico
                    FileUploadUtil.saveFile(uploadPasta, fileName, file);

                    // Criar registro ImagemPacote
                    ImagemPacote imagemPacoteEntity = new ImagemPacote();
                    imagemPacoteEntity.setURL("/img/pacote/" + pacoteId + "/" + fileName);
                    imagemPacoteEntity.setPacote(pacote);

                    // Verificar se esta imagem é destaque
                    boolean isDestaque = false;
                    if (imagemDestaque != null && i < imagemDestaque.length) {
                        isDestaque = Boolean.TRUE.toString().equalsIgnoreCase(imagemDestaque[i]);
                        System.out.println("Nova imagem " + i + " - Valor recebido: '" + imagemDestaque[i] + "' - É destaque: " + isDestaque);
                    }
                    imagemPacoteEntity.setDestaque(isDestaque);

                    // Salvar no banco
                    imagemPacoteRepository.save(imagemPacoteEntity);

                    // Se for a primeira imagem ou a imagem destaque, definir como imagem principal do pacote
                    if ((i == 0 && imagemExistenteDestaque == null) || isDestaque) {
                        pacote.setImage("/img/pacote/" + pacoteId + "/" + fileName);
                        pacoteRepository.save(pacote);
                    }
                }
            }
        }

        return "redirect:/pacote";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        Pacote pacote = new Pacote();
        model.addAttribute("pacote", pacote);
        model.addAttribute("navios", navioRepository.findAll());
        return "pages/pacote/cadastro";
    }

    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, Model model) {
        Pacote pacote = pacoteRepository.findById(id).get();
        List<ImagemPacote> imagens = imagemPacoteRepository.findByPacote(pacote);
        model.addAttribute("pacote", pacote);
        model.addAttribute("imagens", imagens);
        model.addAttribute("navios", navioRepository.findAll());
        return "pages/pacote/alterar";
    }

    @PostMapping("/atualizar")
    public String atualizar(
            @Valid Pacote pacote,
            BindingResult result,
            RedirectAttributes attributes,
            @RequestParam(value = "imagensPacote", required = false) MultipartFile[] imagensPacote,
            @RequestParam(value = "imagemDestaque", required = false) String[] imagemDestaque,
            @RequestParam(value = "imagensParaRemover", required = false) Long[] imagensParaRemover,
            @RequestParam(value = "imagemExistenteDestaque", required = false) Long imagemExistenteDestaque
    ) throws IOException {
        
        System.out.println("=== MÉTODO ATUALIZAR CHAMADO ===");
        if (result.hasErrors()) {
            return "redirect:/pacote/alterar/" + pacote.getId();
        }

        // Atualizar o pacote
        pacoteRepository.save(pacote);

        // Debug: verificar valores recebidos
        System.out.println("ID do pacote: " + pacote.getId());
        System.out.println("Número de novas imagens: " + (imagensPacote != null ? imagensPacote.length : 0));
        System.out.println("Array imagemDestaque: " + (imagemDestaque != null ? Arrays.toString(imagemDestaque) : "null"));
        System.out.println("Imagens para remover: " + (imagensParaRemover != null ? Arrays.toString(imagensParaRemover) : "null"));
        System.out.println("Imagem existente destaque: " + imagemExistenteDestaque);

        // Processar remoção de imagens existentes
        if (imagensParaRemover != null && imagensParaRemover.length > 0) {
            for (Long imagemId : imagensParaRemover) {
                Optional<ImagemPacote> imagemOptional = imagemPacoteRepository.findById(imagemId);
                if (imagemOptional.isPresent()) {
                    ImagemPacote imagem = imagemOptional.get();
                    // Remover arquivo físico se existir
                    try {
                        String caminhoArquivo = "src/main/resources/static" + imagem.getURL();
                        File arquivo = new File(caminhoArquivo);
                        if (arquivo.exists()) {
                            arquivo.delete();
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao remover arquivo físico: " + e.getMessage());
                    }
                    // Remover do banco
                    imagemPacoteRepository.delete(imagem);
                    System.out.println("Imagem removida: " + imagemId);
                }
            }
        }

        // Atualizar destaque das imagens existentes
        if (imagemExistenteDestaque != null) {
            // Primeiro, remover destaque de todas as imagens existentes do pacote
            List<ImagemPacote> todasImagensExistentes = imagemPacoteRepository.findByPacote(pacote);
            for (ImagemPacote img : todasImagensExistentes) {
                img.setDestaque(false);
                imagemPacoteRepository.save(img);
            }
            
            // Marcar a imagem selecionada como destaque
            Optional<ImagemPacote> imagemDestaqueOptional = imagemPacoteRepository.findById(imagemExistenteDestaque);
            if (imagemDestaqueOptional.isPresent()) {
                ImagemPacote imagemSelecionada = imagemDestaqueOptional.get();
                imagemSelecionada.setDestaque(true);
                imagemPacoteRepository.save(imagemSelecionada);
                
                // Definir como imagem principal do pacote
                pacote.setImage(imagemSelecionada.getURL());
                pacoteRepository.save(pacote);
                System.out.println("Imagem existente marcada como destaque: " + imagemExistenteDestaque);
            }
        }

        // Processar novas imagens
        if (imagensPacote != null && imagensPacote.length > 0) {
            // Criar diretório específico para o pacote
            String pacoteId = String.valueOf(pacote.getId());
            String uploadPasta = "src/main/resources/static/img/pacote/" + pacoteId;

            // Se não há imagem existente marcada como destaque, remover destaque de todas as existentes
            if (imagemExistenteDestaque == null) {
                List<ImagemPacote> imagensExistentes = imagemPacoteRepository.findByPacote(pacote);
                for (ImagemPacote img : imagensExistentes) {
                    img.setDestaque(false);
                    imagemPacoteRepository.save(img);
                }
            }

            for (int i = 0; i < imagensPacote.length; i++) {
                MultipartFile file = imagensPacote[i];

                if (!file.isEmpty()) {
                    // Gerar nome único para o arquivo
                    String extensao = StringUtils.getFilenameExtension(file.getOriginalFilename());
                    String fileName = pacote.getId() + "_" + System.currentTimeMillis() + "_" + i + "." + extensao;

                    // Salvar arquivo físico
                    FileUploadUtil.saveFile(uploadPasta, fileName, file);

                    // Criar registro ImagemPacote
                    ImagemPacote imagemPacoteEntity = new ImagemPacote();
                    imagemPacoteEntity.setURL("/img/pacote/" + pacoteId + "/" + fileName);
                    imagemPacoteEntity.setPacote(pacote);

                    // Verificar se esta imagem é destaque
                    boolean isDestaque = false;
                    if (imagemDestaque != null && i < imagemDestaque.length) {
                        isDestaque = Boolean.TRUE.toString().equalsIgnoreCase(imagemDestaque[i]);
                        System.out.println("Nova imagem " + i + " - Valor recebido: '" + imagemDestaque[i] + "' - É destaque: " + isDestaque);
                    }
                    imagemPacoteEntity.setDestaque(isDestaque);

                    // Salvar no banco
                    imagemPacoteRepository.save(imagemPacoteEntity);

                    // Se for a primeira imagem ou a imagem destaque, definir como imagem principal do pacote
                    if ((i == 0 && imagemExistenteDestaque == null) || isDestaque) {
                        pacote.setImage("/img/pacote/" + pacoteId + "/" + fileName);
                        pacoteRepository.save(pacote);
                    }
                }
            }
        }

        // Se não há imagens restantes, definir imagem principal como null
        List<ImagemPacote> imagensRestantes = imagemPacoteRepository.findByPacote(pacote);
        if (imagensRestantes.isEmpty()) {
            pacote.setImage(null);
            pacoteRepository.save(pacote);
        } else {
            // Se não há imagem destaque, definir a primeira como principal
            boolean temDestaque = imagensRestantes.stream().anyMatch(img -> Boolean.TRUE.equals(img.getDestaque()));
            if (!temDestaque && !imagensRestantes.isEmpty()) {
                pacote.setImage(imagensRestantes.get(0).getURL());
                pacoteRepository.save(pacote);
            }
        }

        return "redirect:/pacote";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {
        pacoteRepository.deleteById(id);
        return "redirect:/pacote";
    }

    @PostMapping("/buscar")
    public String buscar(Model model, @Param("nome") String nome) {
        if (nome == null) {
            return "pages/pacote/listagem";
        }

        List<Pacote> pacotesBuscados = PacoteRepository.findPacoteByNomeDestinoContaining(nome);
        model.addAttribute("pacotes", pacotesBuscados);
        return "pages/pacote/listagem";
    }



}
