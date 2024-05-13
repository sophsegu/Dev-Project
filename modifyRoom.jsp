<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="largeFormat3.css?version=61">
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
          <input type="hidden" name="method" value="search_room">
            <select name="searchField">
              <option value="room_num">Room Number</option>
              <option value="max_occupancy">Comfortable Occupancy</option>
              <option value="room_type">Room Type</option>
            </select>
            <input type="text" placeholder="Search..." name="search">
            <button type="submit">Search</button>
        </form>
        </div>
        <form method="post" action="Admin">
        <input type="hidden" name="method" value="addRoom" >
            <button type="submit">Add Room</button>
            </form>
      </div>
      <br>
	<table>
		<tr>
			<th width="15%">Room Number</th>
			<th width="15%">Comfortable Occupancy</th>
			<th width="15%">Number of Beds</th>
			<th width="15%">Beds Occupied</th>
			<th width="15%">Room type</th>
			<th width="15%">Occupied</th>
			<th width="15%">Open Room</th>
		</tr>
		<tbody>
			<tr>
				<%= request.getAttribute("html") %>
			</tr>
			<!-- Add more rows as needed -->
		</tbody>
		
	</table>
	<div class="pagination">
	<%= request.getAttribute("pagination") %>
	</div>
	<br>
    </div>
  </div>

</body>
</html>