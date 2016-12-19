/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var autenticacaoExtra = {};

autenticacaoExtra.renderizarBindsEventos = function(){
    
};

autenticacaoExtra.verificarDataNascimento = function(){
    let conteudoForm = $('#form-autenticacao').serialize();
    $.post('/login/autenticar', conteudoForm)
            .then(function(resposta){
                if(resposta){
                    console.log('feitooo');
                }
            });
};

