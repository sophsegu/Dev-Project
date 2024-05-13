<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="largeFormat2.css?version=57">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <title>Admin Portal</title>
</head>
<body>
<div class="top-row">
<form method="post" action="Admin">
<input type="hidden" name="method" value="home">
<button class="btn"><i class="fa fa-home"></i></button>
</form>
<div class="float-right">
<form method="post" action="Admin">
<input type="hidden" name="method" value="reports">
<button class="btn"><i class="fa fa-commenting"></i></button>
</form>
</div>
<div class="float-right">
<form method="post" action="Admin">
<input type="hidden" name="method" value="stats">
<button class="btn"><i class="fa fa-pie-chart"></i></button>
</form>
</div>
<div class="float-right">
<form method="post" action="Admin">
<input type="hidden" name="method" value="inventory">
<button class="btn"><i class="fa fa-tag"></i></button>
</form>
</div>
</div>
  <div class="admin-page">
    <div class="form">
      <div class="admin">
      <img src="img/globalys_logo.png" style="width:200px;">
      <div class="container">
      <form method="post" action="Admin">
          <input type="hidden" name="method" value="searchAgent">
            <select name="searchField">
              <option value="nom">Agent Name</option>
              <option value="username">Username</option>
            </select>
            <input type="text" placeholder="Search..." name="search">
            <button type="submit">Search</button>
        </form>
        </div>
        <form method="post" action="Admin">
        <input type="hidden" name="method" value="addAgent" >
            <button type="submit">Add Agents</button>
            </form>
      </div>
      <br>
	<table>
		<tr>
			<th width="20%">Account Role</th>
			<th width="20%">Username</th>
			<th width="20%">Agent Name</th>
			<th width="20%">Last Name</th>
			<th width="20%">Open Profile</th>
		</tr>
		<tbody>
			<tr>
				<%= request.getAttribute("html") %>
			</tr>
			<!-- Add more rows as needed -->
		</tbody>
		
	</table>
	<br>
    </div>
  </div>

</body>
</html>