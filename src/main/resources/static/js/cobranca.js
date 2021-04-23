/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// apenas para colocar barra / no delete/id

//substitui o texto

$('#confirmacaoExclusaoModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);

    var codigoTitulo = button.data('codigo');
    var descricaoTitulo = button.data('descricao');

    var modal = $(this);
    var form = modal.find('form');
    var action = form.data('url-base');
    if (!action.endsWith('/')) {
        action += '/';
    }
    form.attr('action', action + codigoTitulo);

    modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + descricaoTitulo + '</strong>?');
});

$(function () {
    $('[rel="tooltip"]').tooltip();
    $('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});

    $('.js-atualizar-status').on('click', function (event) {
        // evita o evento default para nao logo para o controller log q é clicado
        // ou seja encaminha a requisicao - o js fará isso via PUT
        event.preventDefault();

        var botaoReceber = $(event.currentTarget);

        // pega essa linha da url pra enviar pra o metodo
        // th:href="@{receber/{codigo}(codigo=${titulo.codigo})}">
        var urlReceber = botaoReceber.attr('href');


        // ajax vai fazer um nova requisicao via post na url 
        //  th:href="@{receber/{codigo}(codigo=${titulo.codigo})}">
        var response = $.ajax({
            url: urlReceber,
            type: 'PUT'
        });

        //console.log(urlReceber);
        // th:href="@{receber/{codigo}(codigo=${titulo.codigo})}">
        response.done(function (e) {
            //console.log(e);
            var codigoTitulo = botaoReceber.data('codigo');
            //console.log(codigoTitulo);            
            botaoReceber.hide();  // faz sumir o botaco receber           
            $('[data-role=' + codigoTitulo + ']').html('<span class="label label-success">' + e + '</span>');                                     
        });

        response.fail(function (e) {
            console.log(e);
            alert('Erro recebendo cobrança');
        });

    });
});

