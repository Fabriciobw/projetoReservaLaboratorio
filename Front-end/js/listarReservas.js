$(document).ready(function(){
  
    // $(".close").on('click', function(){
    //   $('#success_tic').modal('hide');
    // })
 
   const loadReservas = () =>{
    var aValue = localStorage.getItem('login');
    const objStored = JSON.parse(aValue);
    const token = objStored.token;
    const api = "http://localhost:8080"
    $.ajax({
      
        method: "GET",
        url: api +"/reserva/listarReservas",
        headers: {"Authorization": "Bearer "+token},
        //contentType: 'application/json',
        //data: JSON.stringify(obj),
        success: function (data) {
          //console.log(data)
          pupulateTable(data)
        },
        statusCode: {
            422: function(xhr) {
              console.log(xhr.responseText);
            }
          }    
        })
   }
   loadReservas();

function prepareButton(id, descricao, status, dataReserva, dataLimite, laboratorio){
  button = $('<button type="button" class="btn btn-success">Editar</button>');
  button.click(function () {
    open("/reserva.html?id="+id+"&descricao="+descricao+"&status="+status+"&dataReserva="+dataReserva+"&dataLimite="+dataLimite+"&laboratorio="+laboratorio+"", '_self');
  });
  return button
}
  
 

   const pupulateTable = (response) =>{
    // Use like:
   
    $(function() {
        $.each(response, function(i, item) {

            var button = prepareButton(item.id,item.descricao, item.status, item.dataReserva,item.dataLimite, item.laboratorio.nome)
            var $tr = $('<tr>').append(
                $('<td>').text(item.descricao),
                $('<td>').text(item.status),
                $('<td>').text(item.dataReserva),
                $('<td>').text(item.laboratorio.nome),
                $('<td>').append(button)
            ); //.appendTo('#records_table');
            $('#tbody').append($tr)
        });
       })
   }

 
  
})