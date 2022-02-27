$(document).ready(function() {
    $("#form-cadastrar-usuario").submit(function (event) {
        //stop submit the form event. Do this manually using ajax post function
        event.preventDefault();

        const loginUsuario = $('#loginUsuario').val();
        const senhaUsuario = $('#senhaUsuario').val();
        const nomeUsuario = $('#nomeUsuario').val();

        if(loginUsuario === "" || senhaUsuario === "" || nomeUsuario === "") {
            $('#toastErro').toast('show');
            return;
        }

        const objRequest = {loginUsuario : loginUsuario, senhaUsuario : senhaUsuario, nomeUsuario : nomeUsuario};

        $("#btnCadastrarNovoUsuario").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "usuario/cadastrarNovoUsuario",
            data: JSON.stringify(objRequest),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            error: function (e) {

                if(e.responseText === "Usuário salvo com sucesso") {
                    $('#toastSucesso').toast('show');

                    setTimeout(function() {
                        window.location.href = "menu";
                    }, 3500);
                }
                else if(e.responseText === "Já há um usuário com esse login") {
                    $('#toastErroUserJaCadastrado').toast('show');
                    console.log("ERROR : ", e.responseText);
                }
                else {
                    $('#toastErro').toast('show');
                    console.log("ERROR : ", e.responseText);
                }

                $("#btnCadastrarNovoUsuario").prop("disabled", false);

            }
        });

    });
});