$(document).ready(function(){
  

    const pupulateSelect = (users) =>{

        $.each(users, function(i, item) {
            $('#multiple').append(
              "<option value="+item.id+">"+item.username+"</option>"
            ); //.appendTo('#records_table');
          
        });
    }


    var aValue = localStorage.getItem('login');
    let objStored = JSON.parse(aValue);
    let token = objStored.token;
    let api = "http://localhost:8080"

    $.ajax({
      
        method: "GET",
        url: api +"/users/getTodosUsuarios",
        headers: {"Authorization": "Bearer "+token},
        
        success: function (data) {
         // $('#success_tic').modal('show');
         
         pupulateSelect(data) 
        },
        statusCode: {
            422: function(xhr) {
              console.log(xhr.responseText);
            }
          }    
        })




    
  
    $("#enviar-laboratorio").on('click', function(event){
     
      event.preventDefault();
    
      const nome = $("#nome-laboratorio").val()

      const usuarios = $("#multiple").val()

      arrayId = []

      $.each(usuarios, function(i, item) {

        usuario = {
            id: item
        }

        arrayId.push(usuario)
      });

      console.log(arrayId)
      const obj = {
           nome: nome,
           prioridadeUsuarios: arrayId
        }
  
      $.ajax({
      
        method: "POST",
        url: api +"/laboratorio/cadastrar",
        headers: {"Authorization": "Bearer "+token},
        contentType: 'application/json',
        data: JSON.stringify(obj),
        success: function (data) {
         // $('#success_tic').modal('show');
         alert('Laboratorio criado com sucesso!')
        },
        statusCode: {
            422: function(xhr) {
              console.log(xhr.responseText);
            }
          }    
        })
      })
  
  
  //   } else{
  //     window.open("login.html", "_self")
  //   }
   })