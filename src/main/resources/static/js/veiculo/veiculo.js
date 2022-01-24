$(document).ready(function() {

    $("#content-lista-veiculos").load("/newConsultaVeiculosPadrao");
    $("#form-cadastrar-veiculo").submit(function (event) {
        //stop submit the form event. Do this manually using ajax post function
        event.preventDefault();

        const idCliente = $('#data-list-clientes option[value="' + $('#pickerCliente').val() + '"]').attr("id");
        const modeloVeiculo = $('#modeloVeiculo').val();
        const placaVeiculo = $('#placaVeiculo').val();
        const observacoesVeiculo = $('#observacoesVeiculo').val();


        if(modeloVeiculo === "" || placaVeiculo === "") {
            $('#toastErro').toast('show');
            return;
        }
        const objRequest = {idCliente: idCliente, modeloVeiculo:modeloVeiculo, placaVeiculo:placaVeiculo, observacoesVeiculo:observacoesVeiculo};

        $("#btnCadastrarNovoVeiculo").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "veiculo/cadastrarNovoVeiculo",
            data: JSON.stringify(objRequest),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            error: function (e) {

                if(e.responseText === "Veículo salvo com sucesso") {
                    $('#toastSucesso').toast('show');

                    setTimeout(function() {
                        window.location.href = "menu";
                    }, 3500);
                }
                else {
                    $('#toastErro').toast('show');
                    console.log("ERROR : ", e.responseText);
                }

                $("#btnCadastrarNovoVeiculo").prop("disabled", false);

            }
        });

    });

    $("#btn-search-veiculo").on("click", function () {
        var nomeCliente = $('#nomeCliente').val();
        var placaVeiculo = $('#placaVeiculo').val();
        var modeloVeiculo = $('#modeloVeiculo').val();

        $("#content-lista-veiculos").load("veiculo/reSearchCarsUsingParams", {nomeCliente:nomeCliente, placaVeiculo:placaVeiculo, modeloVeiculo:modeloVeiculo});

    });
});