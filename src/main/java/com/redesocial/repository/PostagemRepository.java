package com.redesocial.repository;

import com.redesocial.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    List<Postagem> findByUsuarioId(Long usuarioId);
}
