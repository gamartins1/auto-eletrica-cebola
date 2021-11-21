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
            "<h6>" + descricao + "</h6>" +
            "</div>" +
            "<div>" +
            "<h6 onclick=removerDescricao('" + idNewLI +"')><span class='badge bg-primary rounded-pill'><i class='bi bi-x-circle'></i></span></h6>" +
            "</div>" +
            "</div>" +
            "</li>";
        $("#list-descricoes-servicos").append(_li);

        $('#inputDescServico').val("");
    });


});

function removerDescricao(id) {
    $("#" + id).remove();
}