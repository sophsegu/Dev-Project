<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="smallQuestionnaire.css?version=7">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <title>Add Room</title>
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
<div class="float-right">
<form method="post" action="Admin">
<input type="hidden" name="method" value="assigned">
<button class="btn"><i class="fa fa-address-card"></i></button>
</form>
</div>
</div>
  <div class="agent-page">
    <div class="form">
      <div class="agent">
      <img src="img/globalys_logo.png" style="width:200px;">
      <form method="post" action="Admin">
  <input type="hidden" name="method" value="createRoom">
  
  <h2>Create Room</h2>
  Room Type:<br>
  <select name = "room_type" id="room_type">  
  	<option value="ACCQ">ACCQ</option>
  	<option value="BUNK">BUNK</option>
  	<option value="DOUBLE BUNK">DOUBLE BUNK</option>
  	<option value="DX2D">DX2D</option>
  	<option value="DX2Q">DX2Q</option>
  	<option value="DXSQ">DXSQ</option>
  	<option value="EXSQ">EXSQ</option>
  	<option value="SK">SK</option>
  	<option value="SQ">SQ</option>
  	<option value="TRADD">TRADD</option>
  	<option value="TRADK">TRADK</option>
  	<option value="TRADQ">TRADQ</option>
  	</select>
  <br><br>
  Room Number:<br>
  <input type="text" name="room_num" id="room_num" placeholder = "Room Number">  
  <br><br>
  Number of Beds:<br>
  <input type="text" name="number_of_beds" id="number_of_beds" placeholder = "Number of Beds">  
  <br><br>
  Comfortable Occupancy:<br>
  <input type="text" name="max_occupancy" id="max_occupancy" placeholder = "Comfortable Occupancy">  
  <br><br>
  <button>Create Room</button>
</form>
      </div>
      <br>
	<br>
    </div>
  </div>
<script>
document.getElementById('room_type').addEventListener('change', function(){
	var selectedOption = this.value;
	var number_of_beds = document.getElementById('number_of_beds');
	var max_occupancy = document.getElementById('max_occupancy');
	
	number_of_beds.value = '';
	max_occupancy.value = '';
	
	switch(selectedOption){
	case 'ACCQ':
		number_of_beds.value = '1';
		max_occupancy.value = '2';
		break;
	case 'BUNK':
		number_of_beds.value = '2';
		max_occupancy.value = '2';
		break;
	case 'DOUBLEBUNK':
		number_of_beds.value = '4'
		max_occupancy.value = '4';
		break;
	case 'DX2D':
		number_of_beds.value = '2';
		max_occupancy.value = '4';
		break;
	case 'DX2Q':
		number_of_beds.value = '2';
		max_occupancy.value='4';
		break;
	case 'DXSQ':
		number_of_beds.value = '2';
		max_occupancy.value = '3';
		break;
	case 'EXSQ':
		number_of_beds.value='1';
		max_occupancy.value='2';
		break;
	case 'SK':
		number_of_beds.value = '2';
		max_occupancy.value = '3';
		break;
	case 'SQ':
		number_of_beds.value = '2';
		max_occupancy.value = '3';
		break;
	case 'TRADD':
		number_of_beds.value = '1';
		max_occupancy.value = '2';
		break;
	case 'TRADK':
		number_of_beds.value = '1';
		max_occupancy.value = '2';
		break;
	case 'TRADQ':
		number_of_beds.value = '1';
		max_occupancy.value = '2';
		break;
	default:
		break;
	}
});
</script>
</body>
</html>