<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="largeFormat.css?version=48">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <title>Agent Portal</title>
</head>
<body>
<div class="top-row">
<form method="post" action="Client">
<input type="hidden" name="method" value="home">
<button class="btn"><i class="fa fa-home"></i></button>
</form>
</div>
  <div class="admin-page">
    <div class="form">
      <div class="admin">
      <img src="img/globalys_logo.png" style="width:200px;">
      <div class="container">
        <form method="post" action="Client">
          <input type="hidden" name="method" value="search">
            <select name="searchField">
              <option value="room_num">Room Number</option>
              <option value="nom">First Name</option>
              <option value="surname">Last Name</option>
              <option value="uci">UCI Number</option>
            </select>
            <input type="text" placeholder="Search..." name="search">
            <button class="button" type="submit">Search</button>
        </form>
        </div>
      </div>
      <br>
      <div class="Format-Button">
	<table>
		<tr>
			<th style="width:5%">Status</th>
			<th style="width:10%">UCI</th>
			<th style="width:5%">Room Number</th>
			<th style="width:20%">First Name</th>
			<th style="width:20%">Surname</th>
			<th style="width:10%">Date of Birth</th>
			<th style="width:10%">Gender</th>
			<th style="width:10%">Country of Origin</th>
			<th style="width:10%">Open Passport</th>
		</tr>
		<tbody>
			<tr>
				<%= request.getAttribute("html") %>
			</tr>
			<!-- Add more rows as needed -->
		</tbody>
	</table>
	</div>
	<br>
	<div class="pagination">
	<%= request.getAttribute("pagination") %>
	
	</div>
	</div>
    </div>
</body>
</html>