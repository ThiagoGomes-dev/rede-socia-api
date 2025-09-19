package com.redesocial.controller;

import com.redesocial.dto.PostagemRequest;
import com.redesocial.model.Postagem;
import com.redesocial.model.Usuario;
import com.redesocial.repository.PostagemRepository;
import com.redesocial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/postagens")
public class PostagemController {
    @Autowired
    private PostagemRepository postagemRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Postagem> criar(@RequestBody PostagemRequest req) {
        Usuario usuario = usuarioRepository.findById(req.getUsuarioId()).orElse(null);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Postagem postagem = new Postagem();
        postagem.setTitulo(req.getTitulo());
        postagem.setConteudo(req.getConteudo());
        postagem.setUsuario(usuario);
        Postagem nova = postagemRepository.save(postagem);
        return ResponseEntity.status(HttpStatus.CREATED).body(nova);
    }

    @PutMapping("/usuario/{usuarioId}/{postagemId}")
    public ResponseEntity<?> editarPostagem(
        @PathVariable Long usuarioId,
        @PathVariable Long postagemId,
        @RequestBody PostagemRequest request
    ) {
        Optional<Postagem> postagemOpt = postagemRepository.findById(postagemId);
        if (postagemOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Postagem postagem = postagemOpt.get();
        if (!postagem.getUsuario().getId().equals(usuarioId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário não autorizado para editar esta postagem.");
        }
        postagem.setTitulo(request.getTitulo());
        postagem.setConteudo(request.getConteudo());
        postagemRepository.save(postagem);
        return ResponseEntity.ok(postagem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!postagemRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        postagemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Postagem>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<Postagem> postagens = postagemRepository.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(postagens);
    }

    @GetMapping
    public ResponseEntity<List<Postagem>> listarTodas() {
        return ResponseEntity.ok(postagemRepository.findAll());
    }
}
