package org.example.dio.molniya.controller;

import org.example.dio.molniya.domain.Usuario;
import org.example.dio.molniya.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario dadosUsuario) {
        return repository.save(dadosUsuario);
    }
    @GetMapping
    public Iterable<Usuario> getUsuarios() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Usuario getUsuario(@PathVariable(name = "id") Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PutMapping(path = "/{id}")
    public Usuario updateUsuario(@PathVariable(name = "id") Long id, @RequestBody Usuario formUsuario) {
        Usuario usuario = repository.findById(id).orElseThrow();
        usuario.setUsuario(formUsuario.getUsuario());
        usuario.setSenha(formUsuario.getSenha());
        usuario.setNome(formUsuario.getNome());
        usuario.setQuota(formUsuario.getQuota());
        return repository.save(usuario);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUsuario(@PathVariable(name = "id") Long id) {
        Usuario usuarioParaRemover = repository.findById(id).orElseThrow();
        repository.delete(usuarioParaRemover);
    }
}
