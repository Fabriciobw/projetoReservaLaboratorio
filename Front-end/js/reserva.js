$(document).ready(function(){
  

    var aValue = localStorage.getItem('login');
    const objStored = JSON.parse(aValue);
    const token = objStored.token;
    const api = "http://localhost:8080/backend"
    function parseJwt (token) {
        var base64Url = token.split('.')[1];
        var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));
    
        return JSON.parse(jsonPayload);
       };


    const userSettings = parseJwt(token);

    //Monta a tela de acordo com o perfil
 if(userSettings.auth[0].authority === 'ROLE_GESTOR'){

  //Converte os parametros em obj
  var search = location.search.substring(1);
  var jsonObj =  JSON.parse('{"' + search.replace(/&/g, '","').replace(/=/g,'":"') + '"}', function(key, value) { return key===""?value:decodeURIComponent(value) })

    $('#status-reserva').css("display",'block')
    $('#data-limite').css("display",'none')
    $('#laboratorio-reserva').css("display",'none')
    $('#data-reserva').css("display",'none')
    $('#descricao-reserva').css("display",'none')
    $('#descricao-laboratorio-gestor').html("<label>Laboratorio : <br>"+jsonObj.laboratorio+"</label>")
    $('#descricao-reserva-gestor').html("<label>Descricao da reserva: <br>"+jsonObj.descricao+"</label>")
    $('#descricao-data-gestor').html("<label>Data da reserva: <br>"+jsonObj.dataReserva+" </label>")
    $('#descricao-dataLimite-gestor').html("<label>Data Limite: <br>"+jsonObj.dataLimite+"</label>")
   

    $("#status option").filter(function() {
      return $(this).text() ==jsonObj.status;
    }).prop("selected", true);


 }else{
  
    $('#descricao-laboratorio-gestor').css("display",'none')
    $('#descricao-reserva-gestor').css("display",'none')
    $('#descricao-data-gestor').css("display",'none')
    $('#descricao-dataLimite-gestor').css("display",'none')
    $('#status-reserva').css("display",'none')

    $.ajax({
      
        method: "GET",
        url: api +"/laboratorio/listarLaboratorios",
        headers: {"Authorization": "Bearer "+token},
        //contentType: 'application/json',
        //data: JSON.stringify(obj),
        success: function (data) {
          
            $.each(data, function(i, item) {
                $('#laboratorio').html(
                  "<option value="+item.id+">"+item.nome+"</option>"
                ); //.appendTo('#records_table');
              
            });
        
        
        },
        statusCode: {
            422: function(xhr) {
              console.log(xhr.responseText);
            }
          }    
        })


 }
  
 
 const changeDateFormatTo = date => {
        const dataEHora =  date.split("T")
        const [yy, mm, dd] = dataEHora[0].split(/-/g);
        const hora = dataEHora[1]
        return `${dd}/${mm}/${yy} ${hora}`;
 };
 const enviarReserva = () =>{

  let obj = {}
  let url

  if(userSettings.auth[0].authority === 'ROLE_GESTOR'){
    url =  api +"/reserva/mudarStatus/"+jsonObj.id+"/"+$("#status option:selected").text()+""
    $.ajax({
      
      method: "POST",
      url: url,
      headers: {"Authorization": "Bearer "+token},
      //contentType: 'application/json',
      
      success: function (data) {
        alert('Dados enviados com sucesso!')
       
      },
      statusCode: {
          422: function(xhr) {
            console.log(xhr.responseText);
          }
        }    
      })
  }else{

    url =  api +"/reserva/solicitarReserva"

    obj = {
      //  id: null,
        descricao: $("#descricao").val(),
        laboratorio: {
            id:$("#laboratorio").val()
        },
        usuario: {
            id: userSettings.userId
        },
       // status: $("#status").val(),
        dataReserva:  changeDateFormatTo($("#data").val()),
        dataLimite:  changeDateFormatTo($("#dataLimite").val())

   }
   $.ajax({
      
    method: "POST",
    url: api +"/reserva/solicitarReserva",
    headers: {"Authorization": "Bearer "+token},
    contentType: 'application/json',
    data: JSON.stringify(obj),
    success: function (data) {
      alert('Dados enviados com sucesso!')
     
    },
    statusCode: {
        422: function(xhr) {
          console.log(xhr.responseText);
        }
      }    
    })
}

  }
   
  // console.log(obj)

    


   $('#enviar').on('click',enviarReserva)
    
})