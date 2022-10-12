$(document).ready(function() {
    $("#placaVeiculo").mask("AAA-0A00");
    $("#telefoneCliente").mask("(00)00000-0000");
    $("#content-lista-servicos").load("newConsultaServicosPadrao");
    $("#content-lista-servicos-pagamento-pendente").load("newConsultaServicosPagamentoPendentePadrao");

    $("#inputDescPeca").on('input', function () {
        var nomePeca = this.value;
        if($('#data-list-pecas-nomes option').filter(function(){
            return this.value.toUpperCase() === nomePeca.toUpperCase();
        }).length) {
            $("#inputDescPeca").val(nomePeca.split(" - ")[0]);
            $("#inputValorPeca").val(nomePeca.split(" - ")[1]);
            $('#inputGarantiaPeca').val(nomePeca.split(" - ")[2]);
        }
    });

    $("input[type='radio']").click(function(){
        var sim = $("input[type='radio']:checked").val();
        if (sim < 3) {
            $('.myratings').css('color','red'); $(".myratings").text(sim);
        }
        else {
            $('.myratings').css('color','green'); $(".myratings").text(sim);
        }
    });

    $("#pickerCarro").change(function (){
        var idCarro = $('#data-list-veiculos option[value="' + $('#pickerCarro').val() + '"]').attr("id");

    });

    $("#pickerCliente").change(function (){
        var idCliente = $('#data-list-clientes option[value="' + $('#pickerCliente').val() + '"]').attr("id");

        $("#optsVeiculos").load("cliente/getVeiculosCliente", {idCliente:idCliente});
    });

    $("#adicionarNovaDescricaoServico").click(function () {
        var idNewLI = "descServico" + (parseInt($("#list-descricoes-servicos").children().length) + 1);

        var descricao = $('#inputDescServico').val();

        if(descricao === "") {
            alert('Preencha a descrição para adicioná-la ao serviço.');
            return;
        }

        var _li = "<li class='list-group-item' id='" + idNewLI + "'>" +
            "<div class='d-flex justify-content-between'>" +
            "<div class='col-md-8 flex-grow-1'>" +
            "<h6 class='descricoes-servicos-values'>" + descricao + "</h6>" +
            "</div>" +
            "<div>" +
            "<h6 onclick=removerLI('" + idNewLI +"')><span class='badge bg-primary rounded-pill'><i class='bi bi-x-circle'></i></span></h6>" +
            "</div>" +
            "</div>" +
            "</li>";
        $("#list-descricoes-servicos").append(_li);

        $('#inputDescServico').val("");
    });

    $("#adicionarNovaMaoDeObra").click(function () {
        var newId = (parseInt($("#list-descricoes-mao-de-obra").children().length) + 1);
        var idNewLI = "maoDeObra" + newId;
        var textoId = "textoMaoObra" + newId;

        var descricao = $('#inputDescMaoDeObra').val();
        var mecanico = $('#inputMecanicoMaoDeObra').val();
        var garantia = $('#inputGarantiaMaoDeObra').val();
        var valor = $('#inputValorMaoDeObra').val();

        if(descricao === "" || mecanico === "" || valor === "") {
            alert('Preencha todos os campos referentes a mão de obra para adicioná-la ao serviço.');
            return;
        }

        var textoLI = descricao + " - R$" + valor + " (único). Garantia de " + garantia + ". Realizado por: " + mecanico

        var _li = `<li class='list-group-item' id='${idNewLI}'><div class='d-flex justify-content-between'><div class='col-md-8 flex-grow-1'><h6 class='maosdeobra-servicos-values' id='${textoId}'>${textoLI}</h6></div><div><h6 onclick="removerLIDecrementarValorFinal('${idNewLI}', '${textoId}')"><span class='badge bg-primary rounded-pill'><i class='bi bi-x-circle'></i></span></h6></div></div></li>`;

        $("#list-descricoes-mao-de-obra").append(_li);

        incrementarValorFinal(valor, 1);

        $('#inputDescMaoDeObra').val("");
        $('#inputMecanicoMaoDeObra').val("");
        $('#inputGarantiaMaoDeObra').val("");
        $('#inputValorMaoDeObra').val("");
    });

    $("#adicionarNovaPeca").click(function () {
        var newId = (parseInt($("#list-descricoes-pecas").children().length) + 1);
        var idNewLI = "peca" + newId;
        var idTextoLI = "textoPeca" + newId;

        var descricao = $('#inputDescPeca').val();
        var quantidade = $('#inputQtdPeca').val();
        var garantia = $('#inputGarantiaPeca').val();
        var valor = $('#inputValorPeca').val();

        if(descricao === "" || quantidade === "" || valor === "") {
            alert('Preencha todos os campos da peça utilizada para adicionar ao serviço.');
            return;
        }

        var idPecaEstoque = ""
        var itemPecaEstoque = descricao + " - " + valor + " - " + garantia;
        var idPecaSelecionada = document.querySelector("#data-list-pecas-nomes option[value='" + itemPecaEstoque + "']");

        if(idPecaSelecionada != null) {
            idPecaEstoque = idPecaSelecionada.id;
        }

        var textoLI = descricao + " - R$" + valor + " (único)."

        if(isPecaNova(quantidade, textoLI, garantia)) {
            textoLI += " Garantia de " + garantia + ". Quantidade " + quantidade

            var _li = `<li class='list-group-item' id='${idNewLI}'><div class='d-flex justify-content-between'><div class='col-md-8 flex-grow-1'><h6 class='pecas-servicos-values'><span id='${idTextoLI}' id-peca-servico='${idPecaEstoque}'>${textoLI}</span></h6></div><div><h6 onclick="removerLIDecrementarValorFinal('${idNewLI}', '${idTextoLI}')"><span class='badge bg-primary rounded-pill'><i class='bi bi-x-circle'></i></span></h6></div></div></li>`;
            $("#list-descricoes-pecas").append(_li);

            incrementarValorFinal(valor, quantidade);
        }

        $('#inputDescPeca').val("");
        $('#inputQtdPeca').val("");
        $('#inputGarantiaPeca').val("");
        $('#inputValorPeca').val("");
    });

    $("#form-cadastrar-servico").submit(function (event) {

        //stop submit the form event. Do this manually using ajax post function
        event.preventDefault();

        var idCliente = $('#data-list-clientes option[value="' + $('#pickerCliente').val() + '"]').attr("id");
        var idVeiculo = $('input[name="rbVeiculos"]:checked').attr("id");

        var descricaoServicos = []

        var descricoes = document.getElementsByClassName("descricoes-servicos-values");
        for (var i = 0; i < descricoes.length; i++) {
            descricaoServicos[i] = descricoes[i].innerText;
        }

        const objRequest = {idCliente: idCliente, idVeiculo: idVeiculo, descricaoServicos: descricaoServicos};

        $("#btnCadastrarNovoServico").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "servico/cadastrarNovoServico",
            data: JSON.stringify(objRequest),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            error: function (e) {


                if(e.responseText == "Servico salvo com sucesso") {
                    $('#toastSucesso').toast('show');

                    setTimeout(function() {
                        window.location.href = "menu";
                    }, 3500);
                }
                else {
                    $('#toastErro').toast('show');
                    console.log("ERROR : ", e.responseText);
                }

                $("#btnCadastrarNovoServico").prop("disabled", false);

            }
        });

    });

    $("#form-finalizar-servico").submit(function (event) {
        //stop submit the form event. Do this manually using ajax post function
        event.preventDefault();

        var idServico = document.forms['form-finalizar-servico'].name;
        var valorFinalServico = document.getElementById('lbl-valor-final-servico').innerText;
        var valorRecebidoServico = $('#inputValorRecebidoServico').val();
        var notaServico = $("input[type='radio']:checked").val();

        if(notaServico == null) {
            notaServico = "0.0";
        }

        var descricaoServicos = [];
        var descricoes = document.getElementsByClassName("descricoes-servicos-values");
        for (var i = 0; i < descricoes.length; i++) {
            descricaoServicos[i] = descricoes[i].innerText;
        }

        var maosDeObraServico = [];
        var maosDeObra = document.getElementsByClassName("maosdeobra-servicos-values");
        for (var i = 0; i < maosDeObra.length; i++) {
            maosDeObraServico[i] = maosDeObra[i].innerText;
        }

        var pecasServico = [];
        var pecas = document.getElementsByClassName("pecas-servicos-values");
        for (var i = 0; i < pecas.length; i++) {
            pecasServico[i] = pecas[i].innerText;
        }

        const objRequest = {idServico : idServico, descricaoServicos : descricaoServicos, maosDeObraServico : maosDeObraServico, pecasServico : pecasServico, valorFinalServico : valorFinalServico, notaServico : notaServico, valorRecebidoServico : valorRecebidoServico};

        $("#btnFinalizarServico").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "servico/" + idServico +"/finalizarUmServico",
            data: JSON.stringify(objRequest),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            error: function (e) {

                if(e.responseText === "Servico finalizado com sucesso") {
                    $('#toastSucesso').toast('show');

                    setTimeout(function() {
                        window.location.href = "/menu";
                    }, 3500);
                }
                else {
                    $('#toastErro').toast('show');
                    setTimeout(function() {
                        console.log("ERROR : ", e.responseText);
                    }, 5500);

                }

                $("#btnFinalizarServico").prop("disabled", false);

            }
        });
    });

    $("#form-editar-servico").submit(function (event) {
        //stop submit the form event. Do this manually using ajax post function
        event.preventDefault();

        var idServico = document.forms['form-editar-servico'].name;
        var valorFinalServico = document.getElementById('lbl-valor-final-servico').innerText;

        var descricaoServicos = [];
        var descricoes = document.getElementsByClassName("descricoes-servicos-values");
        for (var i = 0; i < descricoes.length; i++) {
            descricaoServicos[i] = descricoes[i].innerText;
        }

        var maosDeObraServico = [];
        var maosDeObra = document.getElementsByClassName("maosdeobra-servicos-values");
        for (var i = 0; i < maosDeObra.length; i++) {
            maosDeObraServico[i] = maosDeObra[i].innerText;
        }

        var pecasServico = [];
        var pecas = document.getElementsByClassName("pecas-servicos-values");
        for (var i = 0; i < pecas.length; i++) {
            pecasServico[i] = pecas[i].innerText;
        }

        const objRequest = {idServico : idServico, descricaoServicos : descricaoServicos, maosDeObraServico : maosDeObraServico, pecasServico : pecasServico, valorFinalServico : valorFinalServico};

        $("#btnFinalizarServico").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "servico/" + idServico +"/editarUmServico",
            data: JSON.stringify(objRequest),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            error: function (e) {

                if(e.responseText === "Servico editado com sucesso") {
                    $('#toastSucesso').toast('show');

                    setTimeout(function() {
                        window.location.href = "/menu";
                    }, 3500);
                }
                else {
                    $('#toastErro').toast('show');
                    setTimeout(function() {
                        console.log("ERROR : ", e.responseText);
                    }, 5500);

                }

                $("#btnFinalizarServico").prop("disabled", false);

            }
        });
    });

    $("#form-receber-pagamento").submit(function (event) {
        event.preventDefault();
        var idServico = document.forms['form-receber-pagamento'].name;
        var valorRecebidoServico = $('#valorRecebido').val();

        const objRequest = {idServico: idServico, valorRecebidoServico: valorRecebidoServico};

        $("#btnReceberPagamento").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "servico/" + idServico + "/receberPagamentoServico",
            data: JSON.stringify(objRequest),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            error: function (e) {
                if(e.responseText == "Pagamento recebido com sucesso") {
                    $('#toastSucesso').toast('show');

                    setTimeout(function() {
                        window.location.href = "/menu";
                    }, 3500);
                }
                else {
                    $('#toastErro').toast('show');
                    console.log("ERROR : ", e.responseText);
                }

                $("#btnReceberPagamento").prop("disabled", false);
            }
        });

        });

    $("#btn-search-servicos").on("click", function () {
        var telefoneCliente = $('#telefoneCliente').val();
        var nomeCliente = $('#nomeCliente').val();
        var placaVeiculo = $('#placaVeiculo').val();
        var dataAte = $('#dataAte').val();
        var dataDe = $('#dataDe').val();

        $("#content-lista-servicos").load("servico/reSearchServicesUsingParams", {telefoneCliente:telefoneCliente, nomeCliente:nomeCliente, placaVeiculo:placaVeiculo, dataAte:dataAte, dataDe:dataDe});
    });
});

function removerLI(id) {
    $("#" + id).remove();
}

function removerLIDecrementarValorFinal(id, idTextoPeca) {
    var objValorTotalAtual = document.getElementById('lbl-valor-final-servico');
    var valorRecebidoServico = $('#inputValorRecebidoServico').val();

    var textoLI = document.getElementById(idTextoPeca).innerText;

    var quantidade = 1
    if(textoLI.includes("Quantidade")) {
        quantidade = parseInt(textoLI.split("Quantidade ")[1]);
    }

    var valor = textoLI.split(" (único)")[0].split("R$")[1];

    var valorSubtraido = parseFloat(valor) * quantidade;

    var valorFinal = parseFloat(objValorTotalAtual.innerText.replace(",", ".")) - valorSubtraido;

    objValorTotalAtual.innerText = valorFinal.toFixed(2).replace(".", ",");
    if(valorRecebidoServico != null) {
        $('#inputValorRecebidoServico').val(novoValorFinal.toFixed(2));
    }
    removerLI(id);
}

function incrementarValorFinal(valorAdicionado, quantidade) {
    var objValorTotalAtual = document.getElementById('lbl-valor-final-servico');
    var valorRecebidoServico = $('#inputValorRecebidoServico').val();

    var valor = parseFloat(valorAdicionado) * parseInt(quantidade)
    var valorAntigo = parseFloat(objValorTotalAtual.innerText.replace(",", "."));
    var novoValorFinal = valorAntigo + valor;
    objValorTotalAtual.innerText = novoValorFinal.toFixed(2).replace(".", ",");

    if(valorRecebidoServico != null) {
        $('#inputValorRecebidoServico').val(novoValorFinal.toFixed(2));
    }
}

function isPecaNova(quantidade, peca, garantia) {
    var toReturn = null;
    $("#list-descricoes-pecas li").each(function() {
        var descricaoPeca = $(this).text();
        
        if(descricaoPeca.indexOf(peca) !== -1) {
            var id = $(this).attr('id');
            var textoId = 'textoPeca' + id.split('peca')[1];


            var itemPecaEstoque = $('#inputDescPeca').val() + " - " + $('#inputValorPeca').val() + " - " + $('#inputGarantiaPeca').val();
            var idPecaSelecionada = document.querySelector("#data-list-pecas-nomes option[value='" + itemPecaEstoque + "']").id;

            if(idPecaSelecionada == null) {
                idPecaSelecionada = "";
            }

            removerLIDecrementarValorFinal(id, textoId);

            var valor = descricaoPeca.split(" (único)")[0].split("R$")[1];
            quantidade = parseInt(quantidade) + parseInt(descricaoPeca.split("Quantidade ")[1]);

            peca += " Garantia de " + garantia + ". Quantidade " + quantidade

            var _li = `<li class='list-group-item' id='${id}'><div class='d-flex justify-content-between'><div class='col-md-8 flex-grow-1'><h6 class='pecas-servicos-values'><span id='${textoId}' id-peca-servico='${idPecaSelecionada}'>${peca}</span></h6></div><div><h6 onclick="removerLIDecrementarValorFinal('${id}', '${textoId}')"><span class='badge bg-primary rounded-pill'><i class='bi bi-x-circle'></i></span></h6></div></div></li>`;

            $("#list-descricoes-pecas").append(_li);

            incrementarValorFinal(valor, quantidade);
            toReturn = false;
            return false;
        }
    });

    return toReturn == null;
}

function buscarQuantidadeEstoquePeca() {
    $("#lblQtdEstoquePecasUsadasNoServico").siblings().remove();
    var idPecas = [];
    var pecasServico = document.querySelectorAll('[id-peca-servico]');

    pecasServico.forEach(function(peca){
        var idPeca = Number(peca.getAttribute("id-peca-servico"));

        if(!isNaN(idPeca)) {
            idPecas.push(idPeca);
        }
    });

    if(idPecas.length !== 0) {
        const objRequest = {idPecas: idPecas};
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/peca/obterQuantidadeEstoquePecas",
            data: JSON.stringify(objRequest),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            error: function (e) {
                if(e.responseText != "") {

                }
            },
            success: function (data,status,xhr) {
                if(data != null) {
                    var corQuantidade = "list-group-item-light";
                    data.forEach(function(dataPeca){
                        var quantidadeEstoque = parseInt(dataPeca.quantidadePecaEstoque);
                        if(quantidadeEstoque < 7) {
                            corQuantidade = "list-group-item-danger";
                        }

                        var valor = dataPeca.nomePecaEstoque + ' - Quantidade restante: ' + quantidadeEstoque;
                        var newLine = ('<a class="list-group-item list-group-item-action ' + corQuantidade + '" value="' + valor + '">' + valor + '</a>');

                        $('#divQtdEstoquePecasUsadasNoServico').append(newLine);
                    });
                }

            }

        });
    }
}