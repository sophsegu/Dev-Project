<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="openResident2.css?version=44">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900&display=swap" rel="stylesheet">
<!-- Bootstrap CSS -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css'>
<!-- Font Awesome CSS -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css'>
  <title>Agent Portal</title>
</head>
<body>
<div class="top-row">
<form method="post" action="Agent">
<input type="hidden" name="method" value="home">
<button class="btn"><i class="fa fa-home"></i></button>
</form>
<div class="float-right">
<form method="post" action="Agent">
<input type="hidden" name="method" value="openAssigned">
<button class="btn"><i class="fa fa-user"></i></button>
</form>
</div>
<div class="float-right">
<form method="post" action="Agent">
<input type="hidden" name="method" value="export">
<button class="btn"><i class="fa fa-download"></i></button>
</form>
</div>
</div>
  <div class="student-profile py-4">
  <div class="container">
    <div class="row">
      <div class="col-lg-4">
        <div class="card shadow-sm">
        <img class="logo" src="img/globalys_logo.png" alt="logo">
          <div class="card-header bg-transparent text-center">
            <img class="profile_img" src="<%=request.getAttribute("image") %>" alt="profile pic">
            <h3><%=request.getAttribute("nom")%></h3>
            <h3><%=request.getAttribute("surname") %></h3>
          </div>
          <div class="card-body">
            <p class="mb-0"><strong class="pr-1">Status:</strong><%=request.getAttribute("status") %></p>
            <p class="mb-0"><strong class="pr-1">UCI:</strong><%=request.getAttribute("uci") %></p>
            <p class="mb-0"><strong class="pr-1">Bracelet:</strong><%=request.getAttribute("bracelet") %></p>
            <p class="mb-0"><strong class="pr-1">Room Number:</strong><%=request.getAttribute("room_num") %></p>
            <p class="mb-0"><strong class="pr-1">Name:</strong><%=request.getAttribute("nom") %></p>
            <p class="mb-0"><strong class="pr-1">Surname:</strong><%=request.getAttribute("surname") %></p>
            <p class="mb-0"><strong class="pr-1">Date of Birth:</strong><%=request.getAttribute("dob") %></p>
            <p class="mb-0"><strong class="pr-1">Age:</strong><%=request.getAttribute("age") %></p>
            <p class="mb-0"><strong class="pr-1">Gender:</strong><%=request.getAttribute("gender") %></p>
            <p class="mb-0"><strong class="pr-1">Primary Language:</strong><%=request.getAttribute("primary_language") %></p>
            <p class="mb-0"><strong class="pr-1">Other Languages:</strong><%=request.getAttribute("other_language") %></p>
            <p class="mb-0"><strong class="pr-1">Citizenship:</strong><%=request.getAttribute("citizenship") %></p>
            <p class="mb-0"><strong class="pr-1">Phone Number:</strong><%=request.getAttribute("phone") %></p>
            <p class="mb-0"><strong class="pr-1">Email:</strong><%=request.getAttribute("email") %></p>
            <p class="mb-0"><strong class="pr-1">Family Composition:</strong><%=request.getAttribute("family_composition") %></p>
            <p class="mb-0"><form method="post" action="Agent"><input type="hidden" name = "method" value = "openFamily"> <input type="hidden" name="family_id" value = <%=request.getAttribute("family_id") %>><button>Open Family</button></form></p>
            </div>
        </div>
      </div>
      <div class="col-lg-8">
        <div class="card shadow-sm">
          <div class="card-header bg-transparent border-0">
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>General Information</h3>
          </div>
          <form method="post" action="Agent">
          <div class="card-body pt-0">
            <table class="table table-bordered">
              <tr>
                <th width="20%">Occurances</th>
                <td width="2%">:</td>
                <td><select name="occurances" multiple>
                <option value="Verbal Warning TC" ${occurances.contains('Verbal Warning TC') ? 'selected' : '' }>Verbal Warning TC</option>
                <option value="Written Warning TC" ${occurances.contains('Written Warning TC') ? 'selected' : '' }>Written Warning TC</option>
                <option value="Verbal Warning IRCC" ${occurance.contains('Verbal Warning IRCC') ? 'selected' : '' }>Verbal Warning IRCC</option>
                <option value="Written Warning IRCC" ${occurance.contains('Written Warning IRCC') ? 'selected' : '' }>Written Warning IRCC</option>
                <option value="Transfer IRCC" ${occurances.contains('Transfer IRCC') ? 'selected' : '' }>Transfer IRCC</option>
                <option value="Eviction IRCC" ${occurances.contains('Eviction IRCC') ? 'selected' : '' }>Evection IRCC</option>
                <option value="Police Called" ${occurances.contains('Police Called') ? 'selected' : '' }>Police Called</option>
                <option value="Police Charges" ${occurances.contains('Police Charges') ? 'selected' : '' }>Police Charges</option>
                <option value="CAS Investigation #1" ${occurances.contains('CAS Investigation #1') ? 'selected' : '' }>CAS Investigation #1</option>
                <option value="CAS Investigation #2" ${occurances.contains('CAS Investigation #2') ? 'selected' : '' }>CAS Investigation #2</option>
                <option value="Emergency by Taxi #1" ${occurances.contains('Emergency by Taxi #1') ? 'selected' : ''}>Emergency by Taxi #1</option>
                <option value="Emergency by Taxi #2" ${occurances.contains('Emergency by Taxi #2') ? 'selected' : '' }>Emergency by Taxi #2</option>
                <option value="N/A" ${occurances.contains('N/A') ? 'selected' : '' }>N/A</option>
                </select></td>
                <th width="20%">IRCC Refferal</th>
                <td width="2%">:</td>
                <td><div class="ircc_refferal"><select name="ircc_refferal" multiple>
                <option value="P.R." ${ircc_refferal.contains('P.R.') ? 'selected' : '' }>P.R.</option>
                <option value="Travel Document" ${ircc_refferal.contains('Travel Document') ? 'selected' : '' }>Travel Document</option>
                <option value="Meeting with IRCC Agent #1" ${ircc_refferal.contains('Meeting with IRCC Agent #1') ? 'selected' : '' }>Meeting with IRCC Agent #1</option>
                <option value="Meeting with IRCC Agent #2" ${ircc_refferal.contains('Meeting with IRCC Agent #2') ? 'selected' : '' }>Meeting with IRCC Agent #2</option>
                <option value="Meeting with IRCC Agent #3" ${ircc_refferal.contains('Meeting with IRCC Agent #3') ? 'selected' : '' }>Meeting with IRCC Agent #3</option>
                <option value="Meeting with IRCC Agent #4" ${ircc_refferal.contains('Meeting with IRCC Agent #4') ? 'selected' : '' }>Meeting with IRCC Agent #4</option>
                <option value="Meeting with IRCC Agent #5" ${ircc_refferal.contains('Meeting with IRCC Agent #5') ? 'selected' : '' }>Meeting with IRCC Agent #5</option>
                <option value="Meeting with IRCC Agent #6" ${ircc_refferal.contains('Meeting with IRCC Agent #6') ? 'selected' : '' }>Meeting with IRCC Agent #6</option>
                <option value="Meeting with IRCC Agent #7" ${ircc_refferal.contains('Meeting with IRCC Agent #7') ? 'selected' : '' }>Meeting with IRCC Agent #7</option>
                <option value="Meeting with IRCC Agent #8" ${ircc_refferal.contains('Meeting with IRCC Agent #8') ? 'selected' : '' }>Meeting with IRCC Agent #8</option>
                <option value="Meeting with IRCC Agent #9" ${ircc_refferal.contains('Meeting with IRCC Agent #9') ? 'selected' : '' }>Meeting with IRCC Agent #9</option>
                <option value="Meeting with IRCC Agent #10" ${ircc_refferal.contains('Meeting with IRCC Agent #10') ? 'selected' : '' }>Meeting with IRCC Agent #10</option>
                <option value="N/A" ${ircc_refferal.contains('N/A') ? 'selected' : '' }>N/A</option>
                </select></div></td>
              </tr>
              <tr>
                <th width="20%">Date of Last IRCC Refferal</th>
                <td width="2%">:</td>
                <td><input type="date" name="date_ircc" value=<%=request.getAttribute("date_ircc")%>></td>
                <th width="20%">Special Medical Condition</th>
                <td width="2%">:</td>
                <td><textarea name="medical_condition" rows="1" style="resize: none;"><%=request.getAttribute("medical_condition")%></textarea></td>
              </tr>
              <tr>
                <th width="20%">Mental Health Support</th>
                <td width="2%">:</td>
                <td><select name="mental_health" multiple>
                <option value="Inspire" ${mental_health.contains('Inspire') ? 'selected' : '' }>Inspire</option>
                <option value="VSmart" ${mental_health.contains('VSmart') ? 'selected' : '' }>VSmart</option>
                <option value="Crisis Intervention" ${mental_health.contains('Crisis Intervention') ? 'selected' : '' }>Crisis Intervention</option>
                <option value="AMH" ${mental_health.contains('AMH') ? 'selected' : '' }>AMH</option>
                <option value="Help Within" ${mental_health.contains('Help Within') ? 'selected' : '' }>Help Within</option>
                <option value="NND NP Refferal" ${mental_health.contains('NND NP Refferal') ? 'selected' : '' }>NND NP Refferal</option>
                <option value="N/A" ${mental_health.contains('N/A') ? 'selected' : '' }>N/A</option>
                </select></td>
                <th width="20%">Clothing</th>
                <td width="2%">:</td>
                <td><select name="clothing" multiple>
                <option value="La Friperie" ${clothing.contains('La Friperie') ? 'selected' : '' }>La Friperie</option>
                <option value="Lighthouse" ${clothing.contains('Lighthouse') ? 'selected' : '' }>Lighthouse</option>
                <option value="N/A" ${clothing.contains('N/A') ? 'selected' : '' }>N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Childcare</th>
                <td width="2%">:</td>
                <td><select name="childcare">
                <option value="No" ${childcare == 'No' ? 'selected' : '' }>No</option>
                <option value="Yes" ${childcare == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="N/A" ${childcare == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
                <th width="20%">CRA</th>
                <td width="2%">:</td>
                <td><select name="cra">
                <option value="No" ${cra == 'No' ? 'selected' : '' }>No</option>
                <option value="Yes" ${cra == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="N/A" ${cra == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Volunteering</th>
                <td width="2%">:</td>
                <td><select name="volunteering">
                <option value="No" ${volunteering == 'No' ? 'selected' : '' }>No</option>
                <option value="Yes" ${volunteering == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="N/A" ${volunteering == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
                <th width="20%">Furniture</th>
                <td width="2%">:</td>
                <td><select name="furniture" multiple>
                <option value="La Friperie" ${furniture.contains('La Friperie') ? 'selected' : '' }>La Friperie</option>
                <option value="Lighthouse" ${furniture.contains('Lighthouse') ? 'selected' : '' }>Lighthouse</option>
                <option value="N/A" ${furniture.contains('N/A') ? 'selected' : '' }>N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Speciality Foods</th>
                <td width="2%">:</td>
                <td><select name="speciality_food">
                <option value="No" ${speciality_food == 'No' ? 'selected' : '' }>No</option>
                <option value="Yes" ${speciality_food == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="N/A" ${speciality_food == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
                <th width="20%">Food Banks</th>
                <td width="2%">:</td>
                <td><select name="food_bank">
                <option value="No" ${food_bank == 'No' ? 'selected' : '' }>No</option>
                <option value="Yes" ${food_bank == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="N/A" ${food_bank == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
              </tr>
              
              <tr>
                <th width="20%">Vehicle (Year/Make/Model)</th>
                <td width="2%">:</td>
                <td><textarea name="vehicle" rows="1" style="resize: none;"><%=request.getAttribute("vehicle") %></textarea></td>
                <th width="20%">Insurance</th>
                <td width="2%">:</td>
                <td><select name="insurance">
                <option value="No" ${insurance == 'No' ? 'selected' : '' }>No</option>
                <option value="Yes" ${insurance == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="N/A" ${insurance == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Parking Pass</th>
                <td width="2%">:</td>
                <td><input type="text" name="parking_pass" value=<%=request.getAttribute("parking_pass") %>></td>
                <th width="20%">Registration</th>
                <td width="2%">:</td>
                <td><select name="registration">
                <option value="No" ${registration == 'No' ? 'selected' : '' }>No</option>
                <option value="Yes" ${registration == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="N/A" ${registration == 'N/A' ? 'selected' : '' }>No</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Vaccination/EOHU</th>
                <td width="2%">:</td>
                <td><select name="vaccinations">
                <option value="No" ${vaccinations == 'No' ? 'selected' : '' }>No</option>
                <option value="Yes" ${vaccinations == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="N/A" ${vaccinations == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
                <th width="20%">Refferal Tri County</th>
                <td width="2%">:</td>
                <td><select name="tricounty_refferal">
                <option value="No" ${tricounty_refferal == 'No' ? 'selected' : '' }>No</option>
                <option value="Yes" ${tricounty_refferal == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="N/A" ${tricounty_refferal == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">School Registration</th>
                <td width="2%">:</td>
                <td><div class="school"><select name="school" multiple>
                <option value="DEV Bridgewood" ${school.contains('DEV Bridgewood') ? 'selected' : '' }>DEV Bridgewood</option>
                <option value="Bridgewood Public Elementary" ${school.contains('Bridgewood Public Elementary') ? 'selected' : '' }>Bridgewood Public Elementary</option>
                <option value="St. Peters Catholic Elementary" ${school.contains('St. Peters Catholic Elementary') ? 'selected' : '' }>St. Peters Catholic Elementary</option>
                <option value="Rose-des-Vents Public Elementary" ${school.contains('Rose-des-Vents Public Elementary') ? 'selected' : '' }>Rose-des-Vents Public Elementary</option>
                <option value="Marie-Tanguay Catholic Elementary" ${school.contains('Marie-Tanguay Catholic Elementary') ? 'selected' : '' }>Marie-Tanguay Catholic Elementary</option>
                <option value="Holy Trinity Catholic Secondary" ${school.contains('Holy Trinity Catholic Secondary') ? 'selected' : '' }>Holy Trinity Catholic Secondary</option>
                <option value="St. Lawrence Public Secondary" ${school.contains('St. Lawrence Public Secondary') ? 'selected' : '' }>St. Lawrence Public Secondary</option>
                <option value="Heritage Public Secondary" ${school.contains("Heritage Public Secondary") ? 'selected' : '' }>L'Heritage Public Secondary</option>
                <option value="La Citadelle Catholic Secondary" ${school.contains('La Citadelle Catholic Secondary') ? 'selected' : '' }>La Citadelle Catholic Secondary</option>
                <option value="Exceptionality" ${school.contains('Exceptionality') ? 'selected' : '' }>Exceptionality</option>
                <option value="N/A" ${school.contains('N/A') ? 'selected' : '' }>N/A</option>
                </select></div></td>
                <th width="20%">Service Ontario</th>
                <td width="2%">:</td>
                <td><select name="service_ontario" multiple> 
                <option value="Purple Photo Card ID" ${service_ontario.contains('Purple Photo Card ID') ? 'selected' : ''}>Purple Photo Card ID</option>
                <option value="Drivers License" ${service_ontario.contains("Drivers License") ? 'selected' : ''}>Driver's License</option>
                <option value="OHIP" ${service_ontario.contains('OHIP') ? 'selected' : ''}>OHIP</option>
                <option value="Birth Certificate" ${service_ontario.contains('Birth Certificate') ? 'selected' : ''}>Birth Certificate</option>
                <option value="N/A" ${service_ontario.contains('N/A') ? 'selected' : ''}>N/A</option>
                </select></td>
              </tr><tr>
                <th width="20%">Victim Services</th>
                <td width="2%">:</td>
                <td><select name="victim_services" > 
                <option value="No" ${victim_services == 'No' ? 'selected' : ''}>No</option>
                <option value="Yes" ${victim_services == 'Yes' ? 'selected' : ''}>Yes</option>
                <option value="N/A" ${victim_services == 'N/A' ? 'selected' : ''}>N/A</option>
                </select></td>
                <th width="20%">House of Faith List</th>
                <td width="2%">:</td>
                <td><select name="house_faith" > 
                <option value="No" ${house_faith == 'No' ? 'selected' : ''}>No</option>
                <option value="Yes" ${house_faith == 'Yes' ? 'selected' : ''}>Yes</option>
                <option value="N/A" ${house_faith == 'N/A' ? 'selected' : ''}>N/A</option>
                </select></td>
              </tr>
              <tr>
              <th width="20%">2LGBTQ+</th>
                <td width="2%">:</td>
                <td><select name="lgbt" > 
                <option value="No" ${victim_services == 'No' ? 'selected' : ''}>No</option>
                <option value="Yes" ${victim_services == 'Yes' ? 'selected' : ''}>Yes</option>
                <option value="N/A" ${victim_services == 'N/A' ? 'selected' : ''}>N/A</option>
                </select></td>
                <th width ="20%">Family Identifier</th>
                <td width="2%">:</td>
                <td><input type="number" value=<%=request.getAttribute("family_id") %> name="family_id"></td>
                </tr>
            </table>
          </div>
          <input type="hidden" name="method" value="saveChanges5">
            <input type="hidden" name="id" value=<%=request.getAttribute("id") %>>
            <button type="submit" class="button" style="float: right;">Save Resident</button>
            </form>
        </div>
          <div style="height: 26px"></div>
        <div class="card shadow-sm">
          <div class="card-header bg-transparent border-0">
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>Pagination</h3>
          </div>
          <div class="pagination">
              <p>
              <form method="post" action = "Agent">
              <input type="hidden" name="method" value="SectionA">
              <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
              <button type="submit" class="buttons">General</button>
              </form>
              <form method="post" action = "Agent">
              <input type="hidden" name="method" value="SectionB">
              <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
              <button type="submit" class="buttons">Claim Process</button>
              </form>
              <form method="post" action = "Agent">
              <input type="hidden" name="method" value="SectionC">
              <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
              <button type="submit" class="buttons">Integration/Workshop</button>
              </form>
              <form method="post" action = "Agent">
              <input type="hidden" name="method" value="SectionD">
              <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
              <button type="submit" class="buttons">Employment/Housing</button>
              </form>
              <div class="change_button">
              <button class="button">DEV</button>
              </div>
              <form method="post" action = "Agent">
              <input type="hidden" name="method" value="SectionF">
              <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
              <button type="submit" class="buttons">Reports</button>
              </form>
              </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>