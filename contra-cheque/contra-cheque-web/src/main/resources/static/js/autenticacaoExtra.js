/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var autenticacaoExtra = {};

$(autenticacaoExtra.renderizarBindsEventos = function () {
    $('#btn-validar-nascimento').on('click', autenticacaoExtra.verificarDataNascimento);
});

autenticacaoExtra.verificarDataNascimento = function () {
    let conteudoForm = $('#form-autenticacao').serialize();
    $.post('/login/autenticar', window.btoa(conteudoForm))
            .then(function (resposta) {
                if (resposta) {
                    let campos = conteudoForm.replace(conteudoForm.substring(conteudoForm.indexOf('&data='), conteudoForm.length), '').split('&');
                    let objUser = {"username": campos[0].replace('%40', '@').replace('username=', ''),
                        "password": campos[1].replace('password=', '')};
                    $.post('/login', objUser).then(function () {
                        window.location.replace("/home");
                    });
                } else {
                    window.location.replace("/login?error");
                }
            });
};

