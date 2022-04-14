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
       $("#menu").append(' <li class="nav-item">' +
      '<a class="nav-link active menu-link" aria-current="page" href="cadastroUsuario.html">Cadastrar Usuário</a></li>');
      $("#menu").append(' <li class="nav-item">' +
      '<a class="nav-link  menu-link" aria-current="page" href="cadastroLaboratorio.html">Novo Laboratório</a></li>');
      $("#menu").append(' <li class="nav-item">' +
      '<a class="nav-link menu-link" aria-current="page" href="aprovarReservas.html">Reservas</a></li>');
      $("#menu").append(' <li class="nav-item">' +
      '<a class="nav-link menu-link" id="button-sair" aria-current="page" href="login.html">Sair</a></li>');
      break;
      case 'ROLE_SOLICITANTE':
        $("#menu").append(' <li class="nav-item">' +
        '<a class="nav-link menu-link" aria-current="page" href="reserva.html">Cadastrar Reserva</a></li>');
        $("#menu").append(' <li class="nav-item">' +
        '<a class="nav-link menu-link" aria-current="page" href="minhasReservas.html">Minhas Reservas</a></li>');
        $("#menu").append(' <li class="nav-item">' +
        '<a class="nav-link menu-link" id="button-sair" aria-current="page" href="login.html">Sair</a></li>');
     
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