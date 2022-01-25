$(document).ready(function() {
    $("#placaVeiculo").mask("AAA-0A00");
    $("#telefoneCliente").mask("(00)00000-0000");
    $("#content-lista-servicos").load("/newConsultaServicosPadrao");

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

        $("#optsVeiculos").load("/cliente/getVeiculosCliente", {idCliente:idCliente});
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

        if(descricao === "" || mecanico === "" || garantia === "" || valor === "") {
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

        if(descricao === "" || quantidade === "" || garantia === "" || valor === "") {
            alert('Preencha todos os campos da peça utilizada para adicionar ao serviço.');
            return;
        }

        var textoLI = descricao + " - R$" + valor + " (único). Garantia de " + garantia + ". Quantidade " + quantidade

        var _li = `<li class='list-group-item' id='${idNewLI}'><div class='d-flex justify-content-between'><div class='col-md-8 flex-grow-1'><h6 class='pecas-servicos-values' id='${idTextoLI}'>${textoLI}</h6></div><div><h6 onclick="removerLIDecrementarValorFinal('${idNewLI}', '${idTextoLI}')"><span class='badge bg-primary rounded-pill'><i class='bi bi-x-circle'></i></span></h6></div></div></li>`;
        $("#list-descricoes-pecas").append(_li);

        incrementarValorFinal(valor, quantidade);

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
        var notaServico = $("input[type='radio']:checked").val();

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

        const objRequest = {idServico : idServico, descricaoServicos : descricaoServicos, maosDeObraServico : maosDeObraServico, pecasServico : pecasServico, valorFinalServico : valorFinalServico, notaServico : notaServico};

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

    $("#btn-search-servicos").on("click", function () {
        var telefoneCliente = $('#telefoneCliente').val();
        var nomeCliente = $('#nomeCliente').val();
        var placaVeiculo = $('#placaVeiculo').val();
        var dataAte = $('#dataAte').val();
        var dataDe = $('#dataDe').val();

        $("#content-lista-servicos").load("/servico/reSearchServicesUsingParams", {telefoneCliente:telefoneCliente, nomeCliente:nomeCliente, placaVeiculo:placaVeiculo, dataAte:dataAte, dataDe:dataDe});
    });
});

function removerLI(id) {
    $("#" + id).remove();
}

function removerLIDecrementarValorFinal(id, idTextoPeca) {
    var objValorTotalAtual = document.getElementById('lbl-valor-final-servico');

    var textoLI = document.getElementById(idTextoPeca).innerText;

    var quantidade = 1
    if(textoLI.includes("Quantidade")) {
        quantidade = parseInt(textoLI.split("Quantidade ")[1]);
    }

    var valor = textoLI.split(" (único)")[0].split("R$")[1];

    var valorSubtraido = parseFloat(valor) * quantidade;

    var valorFinal = parseFloat(objValorTotalAtual.innerText.replace(",", ".")) - valorSubtraido;

    objValorTotalAtual.innerText = valorFinal.toFixed(2).replace(".", ",");
    removerLI(id);
}

function incrementarValorFinal(valorAdicionado, quantidade) {
    var objValorTotalAtual = document.getElementById('lbl-valor-final-servico');

    var valor = parseFloat(valorAdicionado) * parseInt(quantidade)
    var valorAntigo = parseFloat(objValorTotalAtual.innerText.replace(",", "."));
    var novoValorFinal = valorAntigo + valor;
    objValorTotalAtual.innerText = novoValorFinal.toFixed(2).replace(".", ",");

}