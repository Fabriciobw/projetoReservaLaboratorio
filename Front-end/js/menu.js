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
      case 'ROLE_ADMIN':
     $("#menu").append(' <li class="nav-item">' +
      '<a class="nav-link active menu-link" aria-current="page" href="cadastroUsuario.html">Cadastrar Usuário</a></li>');
      $("#menu").append(' <li class="nav-item">' +
      '<a class="nav-link  menu-link" aria-current="page" href="#">Cozinha</a></li>');
      $("#menu").append(' <li class="nav-item">' +
      '<a class="nav-link menu-link" aria-current="page" href="#">Brunch</a></li>');
      $("#menu").append(' <li class="nav-item">' +
      '<a class="nav-link menu-link" aria-current="page" href="#">Bar</a></li>');
      $("#menu").append(' <li class="nav-item">' +
      '<a class="nav-link menu-link" aria-current="page" href="#">Pizzas</a></li>');
      $("#menu").append(' <li class="nav-item">' +
      '<a class="nav-link menu-link" aria-current="page" href="#">Burgues</a></li>');
      $("#menu").append(' <li class="nav-item">' +
      '<a class="nav-link menu-link" aria-current="page" href="#">Sobremesa</a></li>');
      $("#menu").append(' <li class="nav-item">' +
      '<a class="nav-link menu-link" aria-current="page" href="#">Limpeza</a></li>');
      $("#menu").append(' <li class="nav-item">' +
      '<a class="nav-link menu-link" aria-current="page" href="#">Descatáveis</a></li>');
      $("#menu").append(' <li class="nav-item">' +
      '<a class="nav-link menu-link" id="button-sair" aria-current="page" href="login.html">Sair</a></li>');
      break;
      case 'ROLE_COZINHA':
        $("#menu").append(' <li class="nav-item">' +
        '<a class="nav-link menu-link" aria-current="page" href="#">Cozinha</a></li>');
        $("#menu").append(' <li class="nav-item">' +
        '<a class="nav-link menu-link" id="button-sair" aria-current="page" href="login.html">Sair</a></li>');
        break;
      case 'ROLE_BRUNCH':
        $("#menu").append(' <li class="nav-item">' +
        '<a class="nav-link menu-link" aria-current="page" href="#">Brunch</a></li>');
        $("#menu").append(' <li class="nav-item">' +
        '<a class="nav-link menu-link" id="button-sair" aria-current="page" href="login.html">Sair</a></li>');
        break;
      case 'ROLE_BAR':
        $("#menu").append(' <li class="nav-item">' +
        '<a class="nav-link menu-link" aria-current="page" href="#">Bar</a></li>');
        $("#menu").append(' <li class="nav-item">' +
        '<a class="nav-link menu-link" id="button-sair" aria-current="page" href="login.html">Sair</a></li>');
        break;
      case 'ROLE_PIZZAS':
        $("#menu").append(' <li class="nav-item">' +
        '<a class="nav-link menu-link" aria-current="page" href="#">Pizzas</a></li>');
        $("#menu").append(' <li class="nav-item">' +
        '<a class="nav-link menu-link" id="button-sair" aria-current="page" href="login.html">Sair</a></li>');
        break;
      case 'ROLE_BURGUES':
        $("#menu").append(' <li class="nav-item">' +
        '<a class="nav-link menu-link" aria-current="page" href="#">Burgues</a></li>');
        $("#menu").append(' <li class="nav-item">' +
        '<a class="nav-link menu-link" id="button-sair" aria-current="page" href="login.html">Sair</a></li>');
        break;
      case 'ROLE_SOBREMESA':
        $("#menu").append(' <li class="nav-item">' +
        '<a class="nav-link menu-link" aria-current="page" href="#">Sobremesa</a></li>');
        $("#menu").append(' <li class="nav-item">' +
        '<a class="nav-link menu-link" id="button-sair" aria-current="page" href="login.html">Sair</a></li>');
        break;
      case 'ROLE_LIMPEZA':
        $("#menu").append(' <li class="nav-item">' +
        '<a class="nav-link menu-link" aria-current="page" href="#">Limpeza</a></li>');
        $("#menu").append(' <li class="nav-item">' +
        '<a class="nav-link menu-link" id="button-sair" aria-current="page" href="login.html">Sair</a></li>');
        break;
      case 'ROLE_DESCATAVEIS':
        $("#menu").append(' <li class="nav-item">' +
        '<a class="nav-link menu-link" aria-current="page" href="#">Descatáveis</a></li>');
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