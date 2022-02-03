$(document).ready(function() {
    $("#content-lista-pecas").load("/newConsultaPecasPadrao");

    $("#form-cadastrar-pecas").submit(function (event) {
        //stop submit the form event. Do this manually using ajax post function
        event.preventDefault();

        const descricaoPeca = $('#descricaoPeca').val();
        const quantidadePeca = $('#quantidadePeca').val();
        const tempoGarantia = $('#tempoGarantia').val();
        const precoCompra = $('#precoCompra').val();
        const precoVenda = $('#precoVenda').val();

        if(descricaoPeca === "" || quantidadePeca === "" || tempoGarantia === "" || precoCompra === "" || precoVenda === "") {
            $('#toastErro').toast('show');
            return;
        }

        const objRequest = {descricaoPeca : descricaoPeca, quantidadePeca : quantidadePeca, tempoGarantia : tempoGarantia, precoCompra : precoCompra, precoVenda : precoVenda};

        $("#btnCadastrarNovaPeca").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "peca/cadastrarNovaPeca",
            data: JSON.stringify(objRequest),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            error: function (e) {

                if(e.responseText === "Peça salva com sucesso") {
                    $('#toastSucesso').toast('show');

                    setTimeout(function() {
                        window.location.href = "menu";
                    }, 3500);
                }
                else {
                    $('#toastErro').toast('show');
                    console.log("ERROR : ", e.responseText);
                }

                $("#btnCadastrarNovaPeca").prop("disabled", false);

            }
        });

    });

    $("#btn-search-peca").on("click", function () {
        var nomePeca = $('#nomePeca').val();

        $("#content-lista-pecas").load("peca/reSearchPecaUsingParams", {nomePeca : nomePeca});

    });
});