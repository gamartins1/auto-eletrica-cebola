$(document).ready(function() {

    $("#content-lista-mecanicos").load("newConsultaMecanicosPadrao");

    $("#form-cadastrar-mecanico").submit(function (event) {
        //stop submit the form event. Do this manually using ajax post function
        event.preventDefault();

        const nomeMecanico = $('#nomeMecanico').val();


        if(nomeMecanico === "") {
            $('#toastErro').toast('show');
            return;
        }

        const objRequest = {nomeMecanico : nomeMecanico};

        $("#btnCadastrarNovoMecanico").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "mecanico/cadastrarNovoMecanico",
            data: JSON.stringify(objRequest),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            error: function (e) {

                if(e.responseText === "Mecânico salvo com sucesso") {
                    $('#toastSucesso').toast('show');

                    setTimeout(function() {
                        window.location.href = "menu";
                    }, 3500);
                }
                else {
                    $('#toastErro').toast('show');
                    console.log("ERROR : ", e.responseText);
                }

                $("#btnCadastrarNovoMecanico").prop("disabled", false);

            }
        });

    });

    $("#btn-search-mecanicos").on("click", function () {
        var nomeMecanico = $('#nomeMecanico').val();

        $("#content-lista-mecanicos").load("mecanico/reSearchMecanicoUsingParams", {nomeMecanico : nomeMecanico});
    });

    $("#form-editar-mecanico").submit(function (event) {
        //stop submit the form event. Do this manually using ajax post function
        event.preventDefault();

        var idMecanico = document.forms['form-editar-mecanico'].name;

        var nomeMecanico = $("#nomeMecanico").val();
        var isAtivo = $("#ativoMecanico").is(":checked");

        const objRequest = {idMecanico : idMecanico, nomeMecanico : nomeMecanico, isAtivo : isAtivo};

        $("#btnSalvarEdicaoMecanico").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "mecanico/" + idMecanico +"/editarUmMecanico",
            data: JSON.stringify(objRequest),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            error: function (e) {

                if(e.responseText === "Mecânico editado com sucesso") {
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

                $("#btnSalvarEdicaoMecanico").prop("disabled", false);

            }
        });
    });
});