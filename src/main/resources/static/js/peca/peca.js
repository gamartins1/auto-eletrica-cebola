$(document).ready(function() {

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

});