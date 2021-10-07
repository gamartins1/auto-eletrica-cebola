package com.aec.autoeletricacebola.mock;

import static com.aec.autoeletricacebola.utils.CebolaAutoEletricaConstants.DEFAULT_DATE_PATTERN;

import javax.annotation.PostConstruct;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.aec.autoeletricacebola.model.Cliente;
import com.aec.autoeletricacebola.model.DescricaoServico;
import com.aec.autoeletricacebola.model.Mecanico;
import com.aec.autoeletricacebola.model.Servico;
import com.aec.autoeletricacebola.model.Usuario;
import com.aec.autoeletricacebola.model.Veiculo;
import com.aec.autoeletricacebola.repository.ClienteRepository;
import com.aec.autoeletricacebola.repository.DescricaoServicoRepository;
import com.aec.autoeletricacebola.repository.MecanicoRepository;
import com.aec.autoeletricacebola.repository.ServicoRepository;
import com.aec.autoeletricacebola.repository.UsuarioRepository;
import com.aec.autoeletricacebola.repository.VeiculoRepository;
import org.hibernate.validator.internal.util.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.ListUtils;

@Component
public class CebolaAutoEletricaDummyData {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private MecanicoRepository mecanicoRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private DescricaoServicoRepository descricaoServicoRepository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN);

    //    @PostConstruct
    public void initializeDatabase() {
        insertUsuario();
        insertCliente();
        insertVeiculo();
        insertMecanico();
        insertServico();

    }

    private void insertUsuario() {
        List<Usuario> usuarios = new ArrayList <>();

        Usuario usuario1 = new Usuario();
        usuario1.setAtivo(true);
        usuario1.setNome("Gabriel Martins");
        usuario1.setUser("gamartins1");
        usuario1.setSenha("123");
        usuarios.add(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setAtivo(true);
        usuario2.setNome("Administrador do Sistema");
        usuario2.setUser("admin");
        usuario2.setSenha("admin");
        usuarios.add(usuario2);

        List<Usuario> usuariosSalvos = usuarioRepository.saveAll(usuarios);
        System.out.println("Foram salvos " + usuariosSalvos.size() + " usuarios");
    }

    private void insertCliente() {
        List <Cliente> clientes = new ArrayList <>();

        Cliente cliente1 = new Cliente();
        cliente1.setDataCadastro(LocalDateTime.now().format(formatter));
        cliente1.setNome("Gabriel Martins");
        clientes.add(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setDataCadastro(LocalDateTime.now().format(formatter));
        cliente2.setNome("Gabriel Pacheco");
        clientes.add(cliente2);

        Cliente cliente3 = new Cliente();
        cliente3.setDataCadastro(LocalDateTime.now().format(formatter));
        cliente3.setNome("Amaury Alves");
        clientes.add(cliente3);

        List<Cliente> clientesSalvos = clienteRepository.saveAll(clientes);
        System.out.println("Foram salvos " + clientesSalvos.size() + " clientes");
    }

    private void insertVeiculo() {
        List<Veiculo> veiculos = new ArrayList <>();

        Veiculo veiculo1 = new Veiculo();
        veiculo1.setAtivo(true);
        veiculo1.setModelo("GM Corsa Maxx 2011");
        veiculo1.setPlaca("EUH4I76");
        veiculo1.setObservacoes("Rebaixado; Não possui alarme");
        Cliente cliente1 = new Cliente();
        cliente1.setId(4L);
        veiculo1.setCliente(cliente1);
        veiculos.add(veiculo1);

        Veiculo veiculo2 = new Veiculo();
        veiculo2.setAtivo(true);
        veiculo2.setModelo("GM Corsa Maxx 2010");
        veiculo2.setPlaca("EKQ-1849");
        veiculo2.setObservacoes("Não possui alarme");
        Cliente cliente2 = new Cliente();
        cliente2.setId(3L);
        veiculo2.setCliente(cliente2);
        veiculos.add(veiculo2);

        List<Veiculo> veiculosInseridos = veiculoRepository.saveAll(veiculos);
        System.out.println("Foram inseridos " + veiculosInseridos.size() + " veículos");
    }

    private void insertMecanico() {
        List<Mecanico> mecanicos = new ArrayList <>();

        Mecanico mecanico1 = new Mecanico();
        mecanico1.setAtivo(true);
        mecanico1.setNome("Ricardo Pacheco");
        mecanicos.add(mecanico1);

        Mecanico mecanico2 = new Mecanico();
        mecanico2.setAtivo(true);
        mecanico2.setNome("Gabriel Pacheco");
        mecanicos.add(mecanico2);

        List<Mecanico> mecanicosInseridos = mecanicoRepository.saveAll(mecanicos);
        System.out.println("Foram inseridos " + mecanicosInseridos.size() + " mecânicos");
    }

    private void insertServico() {
        Servico servico1 = new Servico();
        servico1.setAbertura(LocalDateTime.now().format(formatter));
        servico1.setStatus("Aberto");
        Veiculo veiculo2 = new Veiculo();
        veiculo2.setAtivo(true);
        veiculo2.setId(6L);
        Cliente cliente2 = new Cliente();
        cliente2.setId(3L);
        veiculo2.setCliente(cliente2);
        servico1.setVeiculo(veiculo2);
        servico1.setCliente(cliente2);
        Servico servicoInserido = servicoRepository.save(servico1);

        List<DescricaoServico> descricoes = new ArrayList <>();

        DescricaoServico descricaoServico = new DescricaoServico();
        descricaoServico.setDescricao("Troca de bateria");
        descricaoServico.setServico(servico1);
        descricoes.add(descricaoServico);

        DescricaoServico descricaoServico1 = new DescricaoServico();
        descricaoServico1.setDescricao("Falha na trava elétrica");
        descricaoServico1.setServico(servico1);
        descricoes.add(descricaoServico1);

        List<DescricaoServico> descricoesServicoSalvos = this.descricaoServicoRepository.saveAll(descricoes);

        servico1.setDescricaoServico(descricoesServicoSalvos);
        Servico servicoAtualizado = servicoRepository.save(servico1);
        System.out.println("Servico salvo: " + servicoAtualizado.getStatus());
    }
}