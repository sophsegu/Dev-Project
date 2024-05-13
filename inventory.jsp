<!DOCTYPE html>
<html>
<script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="writeReport2.css?version=2">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900&display=swap" rel="stylesheet">
<!-- Bootstrap CSS -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css'>
<!-- Font Awesome CSS -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css'>
  
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
<div class="student-profile py-4">
  <div class="container">
    <div class="row">
    <form method="post" action="Admin">
      <div class="col-lg-8">
        <div class="card shadow-sm">
        <div class="card shadow-sm">
        <img class="logo" src="img/globalys_logo.png" alt="logo">
        </div>
        <form method="post" action="Admin">
        <table class="table table-bordered">
        <tr>
        <th width="20%">Cereal Bag</th>
        <td width="2%">:</td>
        <td><input type="number" name="cereal_bag" value=<%=request.getAttribute("cereal_bag") %>>
        <th width="20%">Cereal Box</th>
        <td width="2%">:</td>
        <td><input type="number" name="cereal_box" value=<%=request.getAttribute("cereal_box") %>></td>
        </tr>
        <tr>
        <th width="20%">Similac Aimentum</th>
        <td width="2%">:</td>
        <td><input type="number" name="similac_aimentum" value=<%=request.getAttribute("similac_aimentum") %>></td>
        <th width="20%">Similac Advanced</th>
        <td width="2%">:</td>
        <td><input type="number" name="similac_advanced" value=<%=request.getAttribute("similac_advanced") %>></td>
        </tr>
        <tr>
        <th width="20%">Baby Pacifier</th>
        <td width="2%">:</td>
        <td><input type="number" name="baby_pacifier" value=<%=request.getAttribute("baby_pacifier") %>></td>
        <th width="20%">Enfamily 12 Months+</th>
        <td width="2%">:</td>
        <td><input type="number" name="enfamily_12months" value=<%=request.getAttribute("enfamily_12months") %>></td>
        </tr>
        <tr>
        <th width="20%">Enfagrow Toddler</th>
        <td width="2%">:</td>
        <td><input type="number" name="enfagrow_toddler" value=<%=request.getAttribute("enfagrow_toddler") %>></td>
        <th width="20%">Nestle Baby Forumla</th>
        <td width="2%">:</td>
        <td><input type="number" name="nestle_baby_formula" value=<%=request.getAttribute("nestle_baby_formula") %>></td>
        </tr>
        <tr>
        <th width="20%">Good Start Forumla</th>
        <td width="2%">:</td>
        <td><input type="number" name="good_start_formula" value=<%=request.getAttribute("good_start_formula") %>></td>
        <th width="20%">Tide Pods</th>
        <td width="2%">:</td>
        <td><input type="number" name="tide_pods" value=<%=request.getAttribute("tide_pods") %>></td>
        </tr>
        <tr>
        <th width="20%">Dishwashing Liquid</th>
        <td width="2%">:</td>
        <td><input type="number" name="dishwashing" value=<%=request.getAttribute("dishwashing")%>></td>
        <th width="20%">Mini Shampoo</th>
        <td width="2%">:</td>
        <td><input type="number" name="mini_shampoo" value=<%=request.getAttribute("mini_shampoo") %>></td>
        </tr>
        <tr>
        <th width="20%">Shampoo</th>
        <td width="2%">:</td>
        <td><input type="number" name="shampoo" value=<%=request.getAttribute("shampoo")%>></td>
        <th width="20%">Mini Conditioner</th>
        <td width="2%">:</td>
        <td><input type="number" name="mini_conditioner" value=<%=request.getAttribute("mini_conditioner") %>></td>
        </tr>
        <tr>
        <th width="20%">Conditioner</th>
        <td width="2%">:</td>
        <td><input type="number" name="conditioner" value=<%=request.getAttribute("conditioner") %>></td>
        <th width="20%">Diapers Size Newborn</th>
        <td width="2%">:</td>
        <td><input type="number" name="diapers_newborn" value=<%=request.getAttribute("diapers_newborn") %>></td>
        </tr>
        <tr>
        <th width="20%">Diapers Size 1</th>
        <td width="2%">:</td>
        <td><input type="number" name="diapers_1" value=<%=request.getAttribute("diapers_1") %>></td>
        <th width="20%">Diapers Size 2</th>
        <td width="2%">:</td>
        <td><input type="number" name="diapers_2" value=<%=request.getAttribute("diapers_2") %>></td>
        </tr>
        <tr>
        <th width="20%">Diapers Size 3</th>
        <td width="2%">:</td>
        <td><input type="number" name="diapers_3" value=<%=request.getAttribute("diapers_3") %>></td>
        <th width="20%">Diapers Size 4</th>
        <td width="2%">:</td>
        <td><input type="number" name="diapers_4" value=<%=request.getAttribute("diapers_4") %>></td>
        </tr>
        <tr>
        <th width="20%">Diapers Size 5</th>
        <td width="2%">:</td>
        <td><input type="number" name="diapers_5" value=<%=request.getAttribute("diapers_5") %>></td>
        <th width="20%">Diapers Size 6</th>
        <td width="2%">:</td>
        <td><input type="number" name="diapers_6" value=<%=request.getAttribute("diapers_6")%>></td>
        </tr>
        <tr>
        <th width="20%">Pull Ups</th>
        <td width="2%">:</td>
        <td><input type="number" name="pull_ups" value=<%=request.getAttribute("pull_ups") %>></td>
        <th width="20%">Sanitary Pads</th>
        <td width="2%">:</td>
        <td><input type="number" name="sanitary_pad" value=<%=request.getAttribute("sanitary_pad") %>></td>
        </tr>
        <tr>
        <th width="20%">Defense Underwear (S/M)</th>
        <td width="2%">:</td>
        <td><input type="number" name="defense_underwearsm" value=<%=request.getAttribute("defense_underwearsm") %>></td>
        <th width="20%">Defense Underwear (L)</th>
        <td width="2%">:</td>
        <td><input type="number" name="defense_underwearl" value=<%=request.getAttribute("defense_underwearl") %>></td>
        </tr>
        <tr>
        <th width="20%">Baby Wipes</th>
        <td width="2%">:</td>
        <td><input type="number" name="baby_wipes" value=<%=request.getAttribute("baby_wipes") %>></td>
        <th width="20%">Bags</th>
        <td width="2%">:</td>
        <td><input type="number" name="bags" value=<%=request.getAttribute("bags") %>></td>
        </tr>
        <tr>
        <th width="20%">Lotion</th>
        <td width="2%">:</td>
        <td><input type="number" name="lotion" value=<%=request.getAttribute("lotion") %>></td>
        <th width="20%">Baby Wash</th>
        <td width="2%">:</td>
        <td><input type="number" name="baby_wash" value=<%=request.getAttribute("baby_wash") %>></td>
        </tr>
        <tr>
        <th width="20%">9oz Bottles</th>
        <td width="2%">:</td>
        <td><input type="number" name="bottle9oz" value=<%=request.getAttribute("bottle9oz") %>></td>
        <th width="20%">5oz Bottles</th>
        <td width="2%">:</td>
        <td><input type="number" name="bottle3oz" value=<%=request.getAttribute("bottle3oz") %>></td>
        </tr>
        <tr>
        <th width="20%">Fruit Snack</th>
        <td width="2%">:</td>
        <td><input type="number" name="fruit_snack" value=<%=request.getAttribute("fruit_snack") %>></td>
        <th width="20%">Body Soap</th>
        <td width="2%">:</td>
        <td><input type="number" name="soap" value=<%=request.getAttribute("soap") %>></td>
        </tr>
        <tr>
        <th width="20%">Enfamil Nipple</th>
        <td width="2%">:</td>
        <td><input type="number" name="enfamil_nipple" value=<%=request.getAttribute("enfamil_nipple") %>></td>
        <th width="20%">Enfamil Vitamin</th>
        <td width="2%">:</td>
        <td><input type="number" name="enfamil_vitamin" value=<%=request.getAttribute("enfamil_vitamin") %>></td>
        </table>
        <input type="hidden" name="method" value="submitInventory">
        <button type="submit" class="button" style="width: 130px">Save Inventory</button>
        </form>
        <div>
        <h3>   </h3>
        </div>
              
              </div>
              </div>
              </div>
          </div>
      </div>
    </div>

</body>
</html>