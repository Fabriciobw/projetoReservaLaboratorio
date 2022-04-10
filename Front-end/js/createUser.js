$(document).ready(function(){
  
  $(".close").on('click', function(){
    $('#success_tic').modal('hide');
  })

  $("#cadastrar").on('click', function(event){
   
    event.preventDefault();

    
    var aValue = localStorage.getItem('login');
    const objStored = JSON.parse(aValue);
    const token = objStored.token;
  
    const username = $("#username").val()
    const password = $("#password").val()
    const email = $("#email").val()
    const perfil = $("#perfil").val()
    const api = "http://localhost:8080/users"

    const obj = { username: username,
                  password: password,
                  email: email,
                  appUserRoles: [
                    perfil
                  ]};

    $.ajax({
    
      method: "POST",
      url: api +"/signup",
      headers: {"Authorization": "Bearer "+token},
      contentType: 'application/json',
      data: JSON.stringify(obj),
      success: function (data) {
        $('#success_tic').modal('show');
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