package com.aec.autoeletricacebola.mock;

import static com.aec.autoeletricacebola.utils.CebolaAutoEletricaConstants.APPLICATION_DATE_TIME_FORMAT;
import static com.aec.autoeletricacebola.utils.StatusServicoConstants.ABERTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.aec.autoeletricacebola.model.Cliente;
import com.aec.autoeletricacebola.model.DescricaoServico;
import com.aec.autoeletricacebola.model.Mecanico;
import com.aec.autoeletricacebola.model.Servico;
import com.aec.autoeletricacebola.model.TelefoneCliente;
import com.aec.autoeletricacebola.model.Usuario;
import com.aec.autoeletricacebola.model.Veiculo;
import com.aec.autoeletricacebola.repository.ClienteRepository;
import com.aec.autoeletricacebola.repository.DescricaoServicoRepository;
import com.aec.autoeletricacebola.repository.MecanicoRepository;
import com.aec.autoeletricacebola.repository.ServicoRepository;
import com.aec.autoeletricacebola.repository.TelefoneClienteRepository;
import com.aec.autoeletricacebola.repository.UsuarioRepository;
import com.aec.autoeletricacebola.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Autowired
    private TelefoneClienteRepository telefoneClienteRepository;


    //    @PostConstruct
    public void initializeDatabase() {
        insertUsuario();
        insertCliente();
        insertVeiculo();
        insertMecanico();
        insertServico();
        insertTelefoneCliente();
    }

    private void insertUsuario() {
        List<Usuario> usuarios = new ArrayList <>();

        Usuario usuario1 = new Usuario();
        usuario1.setAtivo(true);
        usuario1.setNomeUsuario("Gabriel Martins");
        usuario1.setUserUsuario("gamartins1");
        usuario1.setSenhaUsuario("123");
        usuarios.add(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setAtivo(true);
        usuario2.setNomeUsuario("Administrador do Sistema");
        usuario2.setUserUsuario("admin");
        usuario2.setSenhaUsuario("admin");
        usuarios.add(usuario2);

        List<Usuario> usuariosSalvos = usuarioRepository.saveAll(usuarios);
        System.out.println("Foram salvos " + usuariosSalvos.size() + " usuarios");
    }

    private void insertCliente() {
        List <Cliente> clientes = new ArrayList <>();

        Cliente cliente1 = new Cliente();
        cliente1.setDataCadastroCliente(LocalDateTime.now().format(APPLICATION_DATE_TIME_FORMAT));
        cliente1.setNomeCliente("Gabriel Martins");
        clientes.add(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setDataCadastroCliente(LocalDateTime.now().format(APPLICATION_DATE_TIME_FORMAT));
        cliente2.setNomeCliente("Gabriel Pacheco");
        clientes.add(cliente2);

        Cliente cliente3 = new Cliente();
        cliente3.setDataCadastroCliente(LocalDateTime.now().format(APPLICATION_DATE_TIME_FORMAT));
        cliente3.setNomeCliente("Amaury Alves");
        clientes.add(cliente3);

        List<Cliente> clientesSalvos = clienteRepository.saveAll(clientes);
        System.out.println("Foram salvos " + clientesSalvos.size() + " clientes");
    }

    private void insertVeiculo() {
        List<Veiculo> veiculos = new ArrayList <>();

        Veiculo veiculo1 = new Veiculo();
        veiculo1.setAtivo(true);
        veiculo1.setModeloVeiculo("Yamaha Fazer 2014/2015 Preta");
        veiculo1.setPlacaVeiculo("FPB8169");
        veiculo1.setObservacoesVeiculo("Moto com alarme");

        Veiculo veiculo3 = new Veiculo();
        veiculo3.setAtivo(true);
        veiculo3.setModeloVeiculo("GM Corsa Maxx 2011");
        veiculo3.setPlacaVeiculo("EUH4I76");
        veiculo3.setObservacoesVeiculo("Rebaixado; Não possui alarme");

        Cliente cliente1 = new Cliente();
        cliente1.setIdCliente(4L);
        cliente1.setDataCadastroCliente(LocalDateTime.now().format(APPLICATION_DATE_TIME_FORMAT));
        cliente1.setNomeCliente("Gabriel Martins");
        veiculo1.setCliente(cliente1);
        veiculo3.setCliente(cliente1);

        veiculos.add(veiculo1);
        veiculos.add(veiculo3);

        Veiculo veiculo2 = new Veiculo();
        veiculo2.setAtivo(true);
        veiculo2.setModeloVeiculo("GM Corsa Maxx 2010");
        veiculo2.setPlacaVeiculo("EKQ-1849");
        veiculo2.setObservacoesVeiculo("Não possui alarme");
        Cliente cliente2 = new Cliente();
        cliente2.setIdCliente(3L);
        cliente2.setDataCadastroCliente(LocalDateTime.now().format(APPLICATION_DATE_TIME_FORMAT));
        cliente2.setNomeCliente("Gabriel Pacheco");
        veiculo2.setCliente(cliente2);
        veiculos.add(veiculo2);

        List<Veiculo> veiculosInseridos = veiculoRepository.saveAll(veiculos);
        cliente1.addVeiculoCliente(veiculosInseridos.get(0));
        cliente1.addVeiculoCliente(veiculosInseridos.get(2));
        cliente2.addVeiculoCliente(veiculosInseridos.get(1));
        cliente1 = this.clienteRepository.save(cliente1);
        cliente2 = this.clienteRepository.save(cliente2);
        System.out.println(cliente2.getNomeCliente());
        System.out.println(cliente1.getNomeCliente());

        System.out.println("Foram inseridos " + veiculosInseridos.size() + " veículos");
    }

    private void insertMecanico() {
        List<Mecanico> mecanicos = new ArrayList <>();

        Mecanico mecanico1 = new Mecanico();
        mecanico1.setAtivo(true);
        mecanico1.setNomeMecanico("Ricardo Pacheco");
        mecanicos.add(mecanico1);

        Mecanico mecanico2 = new Mecanico();
        mecanico2.setAtivo(true);
        mecanico2.setNomeMecanico("Gabriel Pacheco");
        mecanicos.add(mecanico2);

        List<Mecanico> mecanicosInseridos = mecanicoRepository.saveAll(mecanicos);
        System.out.println("Foram inseridos " + mecanicosInseridos.size() + " mecânicos");
    }

    private void insertServico() {
        this.insertFirstService();
        this.insertSecondService();
    }

    private void insertFirstService() {
        Servico servico1 = new Servico();
        servico1.setAberturaServico(LocalDateTime.now().format(APPLICATION_DATE_TIME_FORMAT));
        servico1.setStatusAtualServico(ABERTO);
        Veiculo veiculo2 = new Veiculo();
        veiculo2.setAtivo(true);
        veiculo2.setIdVeiculo(6L);
        Cliente cliente2 = new Cliente();
        cliente2.setIdCliente(3L);
        veiculo2.setCliente(cliente2);
        servico1.setVeiculo(veiculo2);
        servico1.setCliente(cliente2);
        Servico servicoInserido = servicoRepository.save(servico1);

        List<DescricaoServico> descricoes = new ArrayList <>();

        DescricaoServico descricaoServico = new DescricaoServico();
        descricaoServico.setDescricaoDoServico("Troca de bateria");
        descricaoServico.setServico(servico1);
        descricoes.add(descricaoServico);

        DescricaoServico descricaoServico1 = new DescricaoServico();
        descricaoServico1.setDescricaoDoServico("Instalação de farol de milha");
        descricaoServico1.setServico(servico1);
        descricoes.add(descricaoServico1);

        List<DescricaoServico> descricoesServicoSalvos = this.descricaoServicoRepository.saveAll(descricoes);

        servico1.setDescricaoServico(descricoesServicoSalvos);
        Servico servicoAtualizado = servicoRepository.save(servico1);
        System.out.println("Servico salvo: " + servicoAtualizado.getStatusAtualServico());
    }

    private void insertSecondService() {
        Servico servico1 = new Servico();
        servico1.setAberturaServico(LocalDateTime.now().format(APPLICATION_DATE_TIME_FORMAT));
        servico1.setStatusAtualServico(ABERTO);
        Veiculo veiculo2 = new Veiculo();
        veiculo2.setAtivo(true);
        veiculo2.setIdVeiculo(7L);
        Cliente cliente2 = new Cliente();
        cliente2.setIdCliente(4L);
        veiculo2.setCliente(cliente2);
        servico1.setVeiculo(veiculo2);
        servico1.setCliente(cliente2);
        Servico servicoInserido = servicoRepository.save(servico1);

        List<DescricaoServico> descricoes = new ArrayList <>();

        DescricaoServico descricaoServico = new DescricaoServico();
        descricaoServico.setDescricaoDoServico("Instalação de alarme");
        descricaoServico.setServico(servico1);
        descricoes.add(descricaoServico);

        DescricaoServico descricaoServico1 = new DescricaoServico();
        descricaoServico1.setDescricaoDoServico("Troca de lâmpada do farol esquerdo");
        descricaoServico1.setServico(servico1);
        descricoes.add(descricaoServico1);

        List<DescricaoServico> descricoesServicoSalvos = this.descricaoServicoRepository.saveAll(descricoes);

        servico1.setDescricaoServico(descricoesServicoSalvos);
        Servico servicoAtualizado = servicoRepository.save(servico1);
        System.out.println("Servico salvo: " + servicoAtualizado.getStatusAtualServico());
    }

    public void insertTelefoneCliente() {
        List<TelefoneCliente> telefonesCliente = new ArrayList <>();
        Cliente cliente1 = new Cliente();
        cliente1.setDataCadastroCliente(LocalDateTime.now().format(APPLICATION_DATE_TIME_FORMAT));
        cliente1.setNomeCliente("Gabriel Martins");
        cliente1.setIdCliente(3L);

        TelefoneCliente telefoneCliente = new TelefoneCliente();
        telefoneCliente.setAtivo(true);
        telefoneCliente.setNumeroTelefoneCliente("(11)99237-3162");
        telefoneCliente.setCliente(cliente1);
        telefonesCliente.add(telefoneCliente);
        cliente1.addTelefoneCliente(telefoneCliente);

        TelefoneCliente telefoneCliente1 = new TelefoneCliente();
        telefoneCliente1.setAtivo(true);
        telefoneCliente1.setNumeroTelefoneCliente("(11)97037-6278");
        telefoneCliente1.setCliente(cliente1);
        telefonesCliente.add(telefoneCliente1);
        cliente1.addTelefoneCliente(telefoneCliente1);


        Cliente cliente2 = new Cliente();
        cliente2.setDataCadastroCliente(LocalDateTime.now().format(APPLICATION_DATE_TIME_FORMAT));
        cliente2.setNomeCliente("Gabriel Pacheco");
        cliente2.setIdCliente(4L);

        TelefoneCliente telefoneCliente2 = new TelefoneCliente();
        telefoneCliente2.setAtivo(true);
        telefoneCliente2.setNumeroTelefoneCliente("(11)94031-2020");
        telefoneCliente2.setCliente(cliente2);
        telefonesCliente.add(telefoneCliente2);
        cliente2.addTelefoneCliente(telefoneCliente2);

        List<TelefoneCliente> telefonesClientesSalvos = this.telefoneClienteRepository.saveAll(telefonesCliente);

        cliente1 = this.clienteRepository.save(cliente1);
        cliente2 = clienteRepository.save(cliente2);
        System.out.println("Foram salvos " + telefonesClientesSalvos.size() + " telefones de clientes");
    }
}