$(document).ready(function(){
    var aValue = localStorage.getItem('login');
    const obj = JSON.parse(aValue);
    if(obj && obj.login == true){
  
      const token = obj.token;
  
      const userSettings = parseJwt(token);
  
      pupulateMenu(userSettings.auth[0].authority)
      
  
      function parseJwt (token) {
        var base64Url = token.split('.')[1];
        var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));
    
        return JSON.parse(jsonPayload);
    };
  
  
    function pupulateMenu(userAuth){
      switch (userAuth) {
        case 'ROLE_GESTOR':
         $("#homepage").append('<p class="textogestor"> Você está logado como Gestor <br> Utilize o menu acima para navegar nas opções.</p>');
         $("#homepage").append('<img src="/images/computadores.png" alt="Imagem" >');
        
        break;
        case 'ROLE_SOLICITANTE':
          $("#homepage").append('<p class="textouser"> Você está logado como Usuário <br> Utilize o menu acima para cadastrar sua reserva.</p>');
          $("#homepage").append('<p class="textouser2"> Segue abaixo a lista de suas reservas:</p>');
         $("#homepage").append('<img src="/images/computadores.png" alt="Imagem" >');
          break;
       
        default:
          
      }
    }
    }else{
      window.open("login.html", "_self")
    }
  
    $("#button-sair").on('click', function(){
      
      localStorage.removeItem('login');
      
    })
    
  });