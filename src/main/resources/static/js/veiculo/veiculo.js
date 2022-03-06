$(document).ready(function() {

    $("#content-lista-veiculos").load("newConsultaVeiculosPadrao");
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

    $("#form-editar-veiculo").submit(function (event) {
        //stop submit the form event. Do this manually using ajax post function
        event.preventDefault();

        var idVeiculo = document.forms['form-editar-veiculo'].name;

        var modeloVeiculo = $('#modeloVeiculo').val();
        var observacoesVeiculo = $('#observacoesVeiculo').val();

        const objRequest = {idVeiculo : idVeiculo, modeloVeiculo : modeloVeiculo, observacoesVeiculo : observacoesVeiculo};

        $("#btnEditarVeiculo").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "veiculo/" + idVeiculo +"/editarUmVeiculo",
            data: JSON.stringify(objRequest),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            error: function (e) {

                if(e.responseText === "Veículo editado com sucesso") {
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

                $("#btnEditarVeiculo").prop("disabled", false);
            }
        });
    });
});