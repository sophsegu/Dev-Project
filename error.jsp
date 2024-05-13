<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="smallQuestionnaire.css?version=4">
<title> Login </title>
</head>
<body>
  <body>
    <div class="login-page">
      <div class="form">
        <div class="login">
          <div class="login-header">
          <img src="img/globalys_logo.png" style="width:200px;">
            <h3>LOGIN</h3>
            <p>Please enter your credentials to login.</p>
          </div>
        </div>
        <div class="error">
        <p>Wrong username or password. Please try again!</p>
        </div>
        <form class="login-form" method="post" action="Login">
        <input type="hidden" name="method" value="login">
          <input type="text" placeholder="username" name="uname" />
          <input type="password" placeholder="password" name="password"/>
          <button>login</button>
        </form>
      </div>
    </div>
</body>
</body>
</html>