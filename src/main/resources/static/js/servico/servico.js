$(document).ready(function() {
    $("#pickerCarro").change(function (){
        var idCarro = $('#data-list-veiculos option[value="' + $('#pickerCarro').val() + '"]').attr("id");

    });

    $("#pickerCliente").change(function (){
        var idCliente = $('#data-list-clientes option[value="' + $('#pickerCliente').val() + '"]').attr("id");

        $("#optsVeiculos").load("/cliente/getVeiculosCliente", {idCliente:idCliente});
    });

    $("#adicionarNovaDescricaoServico").click(function () {
        var idNewLI = "descServico" + (parseInt($("#list-descricoes-servicos").children().length) + 1);

        // alert(idNewLI);

        var descricao = $('#inputDescServico').val();

        var _li = "<li class='list-group-item' id='" + idNewLI + "'>" +
            "<div class='d-flex justify-content-between'>" +
            "<div class='col-md-8 flex-grow-1'>" +
            "<h6 class='descricoes-servicos-values'>" + descricao + "</h6>" +
            "</div>" +
            "<div>" +
            "<h6 onclick=removerDescricao('" + idNewLI +"')><span class='badge bg-primary rounded-pill'><i class='bi bi-x-circle'></i></span></h6>" +
            "</div>" +
            "</div>" +
            "</li>";
        $("#list-descricoes-servicos").append(_li);

        $('#inputDescServico').val("");
    });

    $("#form-cadastrar-servico").submit(function (event) {

        //stop submit the form event. Do this manually using ajax post function
        event.preventDefault();

        var idCliente = $('#data-list-clientes option[value="' + $('#pickerCliente').val() + '"]').attr("id");
        var idVeiculo = $('input[name="rbVeiculos"]:checked').attr("id");

        var descricaoServicos = []

        var descricoes = document.getElementsByClassName("descricoes-servicos-values");
        for (var i = 0; i < descricoes.length; i++) {
            var descricaoServico = {}

            // descricaoServico["descricaoDoServico"] = descricoes[i].innerText;

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
});

function removerDescricao(id) {
    $("#" + id).remove();
}