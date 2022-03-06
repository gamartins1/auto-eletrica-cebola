$(document).ready(function() {

    //Carrega os clientes ao renderizar a página
    $("#content-lista-clientes").load("newConsultaCliente");

    $(window).keydown(function(event){
        if(event.keyCode == 13) {
            event.preventDefault();
            return false;
        }
    });

    $("#cepEnderecoCliente").mask("00000-000");
    $("#telefoneCliente").mask("(00)00000-0000");
    $(".placaVeiculo").mask("AAA-0A00");

    function limpa_formulário_cep() {
        // Limpa valores do formulário de cep.
        $("#ruaEnderecoCliente").val("");
        $("#bairroEnderecoCliente").val("");
        $("#cidadeEnderecoCliente").val("");
    }

    //Quando o campo cep perde o foco.
    $("#cepEnderecoCliente").blur(function() {

        //Nova variável "cep" somente com dígitos.
        var cep = $(this).val().replace(/\D/g, '');

        //Verifica se campo cep possui valor informado.
        if (cep != "") {

            //Expressão regular para validar o CEP.
            var validacep = /^[0-9]{8}$/;

            //Valida o formato do CEP.
            if(validacep.test(cep)) {

                //Preenche os campos com "..." enquanto consulta webservice.
                $("#ruaEnderecoCliente").val("carregando...");
                $("#bairroEnderecoCliente").val("carregando...");
                $("#cidadeEnderecoCliente").val("carregando...");

                //Consulta o webservice viacep.com.br/
                $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                    if (!("erro" in dados)) {
                        //Atualiza os campos com os valores da consulta.
                        $("#ruaEnderecoCliente").val(dados.logradouro);
                        $("#bairroEnderecoCliente").val(dados.bairro);
                        $("#cidadeEnderecoCliente").val(dados.localidade);
                        $("#numeroEnderecoCliente").focus();
                    } //end if.
                    else {
                        //CEP pesquisado não foi encontrado.
                        limpa_formulário_cep();
                        alert("CEP não encontrado.");
                    }
                });
            } //end if.
            else {
                //cep é inválido.
                limpa_formulário_cep();
                alert("Formato de CEP inválido.");
            }
        } //end if.
        else {
            //cep sem valor, limpa formulário.
            limpa_formulário_cep();
        }
    });

    $("#btn-search-cliente").on("click", function () {
        var telefoneCliente = $('#telefoneCliente').val();
        var nomeCliente = $('#nomeCliente').val();
        $("#content-lista-clientes").load("cliente/reSearchClientUsingParams", {telefoneCliente:telefoneCliente, nomeCliente:nomeCliente});
    });

    $("#adicionarNovoTelefone").click(function () {
        var idNewLI = "telefone" + (parseInt($("#list-telefones-cliente").children().length) + 1);

        var telefone = $('#telefoneCliente').val();

        if(telefone === "") {
            return;
        }

        var _li = "<li class='list-group-item' id='" + idNewLI + "'>" +
            "<div class='d-flex justify-content-between'>" +
            "<div class='col-md-8 flex-grow-1'>" +
            "<h6 class='telefones-cliente-values'>" + telefone + "</h6>" +
            "</div>" +
            "<div>" +
            "<h6 onclick=removerLITelefoneCliente('" + idNewLI +"')><span class='badge bg-primary rounded-pill'><i class='bi bi-x-circle'></i></span></h6>" +
            "</div>" +
            "</div>" +
            "</li>";
        $("#list-telefones-cliente").append(_li);

        $('#telefoneCliente').val("");
    });

    $("#form-editar-cliente").submit(function (event) {
        //stop submit the form event. Do this manually using ajax post function
        event.preventDefault();

        var idCliente = document.forms['form-editar-cliente'].name;

        var telefonesCliente = [];
        var telefones = document.getElementsByClassName("telefones-cliente-values");
        for (var i = 0; i < telefones.length; i++) {
            telefonesCliente[i] = telefones[i].innerText;
        }

        var nomeCliente = $("#inputNomeCliente").val();

        var ruaEnderecoCliente = $("#ruaEnderecoCliente").val();
        var numeroEnderecoCliente = $("#numeroEnderecoCliente").val();
        var bairroEnderecoCliente = $("#bairroEnderecoCliente").val();
        var cidadeEnderecoCliente = $("#cidadeEnderecoCliente").val();
        var cepEnderecoCliente = $("#cepEnderecoCliente").val();

        const objRequest = {idCliente : idCliente, nomeCliente : nomeCliente, telefonesCliente : telefonesCliente, ruaEnderecoCliente : ruaEnderecoCliente, numeroEnderecoCliente : numeroEnderecoCliente, bairroEnderecoCliente : bairroEnderecoCliente, cidadeEnderecoCliente : cidadeEnderecoCliente, cepEnderecoCliente : cepEnderecoCliente};

        $("#btnSalvarEdicaoCliente").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "cliente/" + idCliente +"/editarUmCliente",
            data: JSON.stringify(objRequest),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            error: function (e) {

                if(e.responseText === "Cliente editado com sucesso") {
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

                $("#btnSalvarEdicaoCliente").prop("disabled", false);

            }
        });
    });
});

function removerLITelefoneCliente(id) {
    $("#" + id).remove();
}