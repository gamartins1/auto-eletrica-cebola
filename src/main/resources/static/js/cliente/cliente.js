$(document).ready(function() {

    //Carrega os clientes ao renderizar a página
    $("#content-lista-clientes").load("/newConsultaCliente");

    $(window).keydown(function(event){
        if(event.keyCode == 13) {
            event.preventDefault();
            return false;
        }
    });

    $("#cepEnderecoCliente").mask("00000-000");
    $("#telefoneCliente").mask("(00)00000-0000");
    $("#placaVeiculo").mask("AAA-0A00");

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
        $("#content-lista-clientes").load("/cliente/reSearchClientUsingParams", {telefoneCliente:telefoneCliente, nomeCliente:nomeCliente});
    })
});