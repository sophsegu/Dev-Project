<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="openResident2.css?version=35">
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
</div>>
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
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>Integration</h3>
          </div>
          <form method="post" action="Agent">
          <div class="card-body pt-0">
            <table class="table table-bordered">
              <tr>
                <th width="20%">Bank Account</th>
                <td width="2%">:</td>
                <td><select name="bank">
                <option value="No" ${bank == 'No' ? 'selected' : '' }>No</option>
                <option value="Yes" ${bank == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="Reffered" ${bank == 'Reffered' ? 'selected' : '' }>Reffered</option>
                <option value="N/A" ${bank == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
                <th width="20%">Legal Aid</th>
                <td width="2%">:</td>
                <td><select name="legal">
                <option value="No" ${legal == 'No' ? 'selected' : '' }>No</option>
                <option value="Yes" ${legal == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="Yes, waiting for lawyer" ${legal == 'Yes, waiting for lawyer' ? 'selected' : '' }>Yes, waiting for lawyer</option>
                <option value="No, not eligible" ${legal == 'No, not eligible' ? 'selected' : '' }>No, not eligible</option>
                <option value="Personal Lawyer" ${legal == 'Personal Lawyer' ? 'selected' : '' }>Personal Lawyer</option>
                <option value="Reffered" ${legal == 'Reffered' ? 'selected' : '' }>Reffered</option>
                <option value="N/A" ${legal == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Ontario Works</th>
                <td width="2%">:</td>
                <td><select name="ontario_works">
                <option value="N/A" ${ontario_works == 'N/A' ? 'selected' : '' }>N/A</option>
                <option value="Applied" ${ontario_works == 'Applied' ? 'selected' : '' }>Applied</option>
                <option value="Recipient" ${ontario_works == 'Recipient' ? 'selected' : '' }>Recipient</option>
                <option value="Ineligible" ${ontario_works == 'Ineligible' ? 'selected' : '' }>Ineligible</option>
                <option value="Dependent" ${ontario_works == 'Dependent' ? 'selected' : '' }>Dependent</option>
                </select></td>
                <th width="20%">Date of enrollement Ontario Works</th>
                <td width="2%">:</td>
                <td><input type="date" name="length_ow" value=<%=request.getParameter("length_ow") %>></td>
              </tr>
              <tr>
                <th width="20%">Service Canada</th>
                <td width="2%">:</td>
                <td><select name="service_canada" multiple>
                <option value="E.I." ${service_canada.contains('E.I.') ? 'selected' : '' }>E.I.</option>
                <option value="SIN" ${service_canada.contains('SIN') ? 'selected' : '' }>SIN</option>
                <option value="RoE" ${service_canada.contains('RoE') ? 'selected' : '' }>RoE</option>
                <option value="Child Benefits" ${service_canada.contains('Child Benefits') ? 'selected' : '' }>Child Benefits</option>
                <option value="Yes" ${service_canada.contains('Yes') ? 'selected' : '' }>Yes</option>
                <option value="No" ${service_canada.contains('No') ? 'selected' : '' }>No</option>
                <option value="N/A" ${service_canada.contains('N/A') ? 'selected' : '' }>N/A</option>
                </select></td>
                <th width="20%">General Assistance</th>
                <td width="2%">:</td>
                <td><input type="text" name="gen_assistance" value=<%=request.getAttribute("gen_assistance")%>></td>
              </tr>
              <tr>
                <th width="20%">Initial Settelment</th>
                <td width="2%">:</td>
                <td><div class="initial-settelment"><select name="initial_settelment" multiple>
                <option value="Ready for employment search" ${initial_settelment.contains('Ready for employment search') ? 'selected' : '' }>Ready for employment search</option>
                <option value="Requires support before employment search" ${initial_settelment.contains('Requires support before employment search') ? 'selected' : '' }>Requires support before employment search</option>
                <option value="Unable to work due to limitations" ${initial_settelment.contains('Unable to work due to limitations') ? 'selected' : '' }>Unable to work due to limitations</option>
                <option value="Childcare" ${initial_settelment.contains('Childcare') ? 'selected' : '' }>Childcare</option>
                <option value="ODSP" ${initial_settelment.contains('ODSP') ? 'selected' : '' }>ODSP</option>
                <option value="Pregnant" ${initial_settelment.contains('Pregnant') ? 'selected' : '' }>Pregnant</option>
                <option value="Medical" ${initial_settelment.contains('Medical') ? 'selected' : '' }>Medical</option>
                <option value="Transportation" ${initial_settelment.contains('Transportation') ? 'selected' : '' }>Transportation</option>
                <option value="Language" ${initial_settelment.contains('Language') ? 'selected' : '' }>Language</option>
                <option value="Still in School" ${initial_settelment.contains('Still in School') ? 'selected' : '' }>Still in School</option>
                <option value="Recertification" ${initial_settelment.contains('Recertification') ? 'selected' : '' }>Recertification</option>
                <option value="Layoff" ${initial_settelment.contains('Layoff') ? 'selected' : '' }>Layoff</option>
                <option value="Enroll in ESL" ${initial_settelment.contains('Enroll in ESL') ? 'selected' : '' }>Enroll in ESL</option>
                <option value="Enroll in FSL" ${initial_settelment.contains('Enroll in FSl') ? 'selected' : '' }>Enroll in FSL</option>
                <option value="No language class required" ${initial_settelment.contains('No language class required') ? 'selected' : '' }>No language class required</option>
                <option value="Cannot take language classes - work in native language" ${initial_settelment.contains('Cannot take language classes - work in native language') ? 'selected' : ''}>Cannot take language classes - work in native language</option>
                <option value="N/A" ${initial_settelment.contains('N/A') ? 'selected' : '' }>N/A</option>
                </select></div></td>
                <th width="20%">Destination Preference</th>
                <td width="2%">:</td>
                <td><select name="destination_preference">
                <option value="NWT" ${destination_preference == 'NWT' ? 'selected' : '' }>NWT</option>
                <option value="BC" ${destination_preference == 'BC' ? 'selected' : '' }>BC</option>
                <option value="Victoria" ${destination_preference == 'Victoria' ? 'selected' : '' }>Victoria</option>
                <option value="Vancouver" ${destination_preference == 'Vancouver' ? 'selected' : '' }>Vancouver</option>
                <option value="Alberta" ${destination_preference == 'Alberta' ? 'selected' : '' }>Alberta</option>
                <option value="Calgary" ${destination_preference == 'Calgary' ? 'selected' : '' }>Calgary</option>
                <option value="Edmonton" ${destination_preference == 'Edmonton' ? 'selected' : '' }>Edmonton</option>
                <option value="Saskatchewan" ${destination_preference == 'Saskatchewan' ? 'selected' : '' }>Saskatchewan</option>
                <option value="Manitoba" ${destination_preference == 'Manitoba' ? 'selected' : '' }>Manitoba</option>
                <option value="Ontario" ${destination_preference == 'Ontario' ? 'selected' : '' }>Ontario</option>
                <option value="Brampton" ${destination_preference == 'Brampton' ? 'selected' : '' }>Brampton</option>
                <option value="Cornwall" ${destination_preference == 'Cornwall' ? 'selected' : '' }>Cornwall</option>
                <option value="Hamilton" ${destination_preference == 'Hamilton' ? 'selected' : '' }>Hamilton</option>
                <option value="Ottawa" ${destination_preference == 'Ottawa' ? 'selected' : '' }>Ottawa</option>
                <option value="Toronto" ${destination_preference == 'Toronto' ? 'selected' : '' }>Toronto</option>
                <option value="Québec" ${destination_preference == 'Québec' ? 'selected' : '' }>Québec</option>
                <option value="Québec City" ${destination_preference == 'Québec City' ? 'selected' : '' }>Québec City</option>
                <option value="Lévis" ${destination_preference == 'Lévis' ? 'selected' : '' }>Lévis</option> 
                <option value="Montreal" ${destination_preference == 'Montreal' ? 'selected' : '' }>Montreal</option>
                <option value="New Brunswick" ${destination_preference == 'New Brunswick' ? 'selected' : '' }>New Brunswick</option>
                <option value="Newfoundland" ${destination_preference == 'Newfoundland' ? 'selected' : '' }>Newfoundland</option>
                <option value="N/A" ${destination_preference == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Settlement Assessment</th>
                <td width="2%">:</td>
                <td><textarea name="settelment_updates" rows="1" style="resize: none"><%=request.getAttribute("settelment_updates")%></textarea></td>
                <th width="20%">Highest Level of Education</th>
                <td width="2%">:</td>
                <td><select name="education">
                <option value="None" ${education == 'None' ? 'selected' : '' }>None</option>
                <option value="Elementary" ${education == 'Elementary' ? 'selected' : '' }>Elementary</option>
                <option value="High School" ${education == 'High School' ? 'selected' : '' }>High School</option>
                <option value="College" ${education == 'College' ? 'selected' : '' }>College</option>
                <option value="University" ${education == 'University' ? 'selected' : '' }>University</option>
                <option value="Masters" ${education == 'Master' ? 'selected' : '' }>Masters</option>
                <option value="PhD" ${education == 'PhD' ? 'selected' : '' }>PhD</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Certifications</th>
                <td width="2%">:</td>
                <td><textarea name="certifications" rows="1" style="resize: none;"><%=request.getAttribute("certifications") %></textarea></td>
                <th width="20%">Adult Learning</th>
                <td width="2%">:</td>
                <td><select name="adult_learning" multiple>
                <option value="PSW Course with CDSBEO" ${adult_learning.contains('PSW Course with CDSBEO') ? 'selected' : '' }>PSW Course with CDSBEO</option>
                <option value="Healthcare Support Worker with TriCounty" ${adult_learning.contains('Healthcare Support Worker with TriCounty') ? 'selected' : '' }>Healthcare Support Worker</option>
                <option value="Daycare Support Worker with TriCounty" ${adult_learning.contains('Daycare Support Worker with TriCounty') ? 'selected' : '' }>Daycare Support Worker</option>
                <option value="Auto Mechanic" ${adult_learning.contains('Auto Mechanic') ? 'selected' : '' }>Auto Mechanic</option>
                <option value="Carpentry" ${adult_learning.contains('Carpentry') ? 'selected' : '' }>Carpentry</option>
                <option value="Welding" ${adult_learning.contains('Welding') ? 'selected' : '' }>Welding</option>
                <option value="AZ and DZ Driving Training" ${adult_learning.contains('AZ and DZ Driving Training') ? 'selected' : '' }>AZ and DZ Driving Training</option>
                <option value="N/A" ${adult_learning.contains('N/A') ? 'selected' : '' }>N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Initial Language Proficiency</th>
                <td width="2%">:</td>
                <td><select name="initial_proficiency">
                <option value="N/A" ${initial_proficiency == 'N/A' ? 'selected' : '' }>N/A</option>
                <option value="0" ${initial_proficiency == '0' ? 'selected' : '' }>0</option>
                <option value="1" ${initial_proficiency == '1' ? 'selected' : '' }>1</option>
                <option value="2" ${initial_proficiency == '2' ? 'selected' : '' }>2</option>
                <option value="3" ${initial_proficiency == '3' ? 'selected' : '' }>3</option>
                <option value="4" ${initial_proficiency == '4' ? 'selected' : '' }>4</option>
                <option value="5" ${initial_proficiency == '5' ? 'selected' : '' }>5</option>
                <option value="6" ${initial_proficiency == '6' ? 'selected' : '' }>6</option>
                <option value="6+" ${inital_proficiency == '6+' ? 'selected' : '' }>6+</option>
                </select></td>
                <th width="20%">Current Language Proficiency</th>
                <td width="2%">:</td>
                <td><select name="current_proficiency">
                <option value="N/A" ${current_proficiency == 'N/A' ? 'selected' : '' }>N/A</option>
                <option value="0" ${current_proficiency == '0' ? 'selected' : '' }>0</option>
                <option value="1" ${current_proficiency == '1' ? 'selected' : '' }>1</option>
                <option value="2" ${current_proficiency == '2' ? 'selected' : '' }>2</option>
                <option value="3" ${current_proficiency == '3' ? 'selected' : '' }>3</option>
                <option value="4" ${current_proficiency == '4' ? 'selected' : '' }>4</option>
                <option value="5" ${current_proficiency == '5' ? 'selected' : '' }>5</option>
                <option value="6" ${current_proficiency == '6' ? 'selected' : '' }>6</option>
                <option value="6+" ${current_proficiency == '6+' ? 'selected' : '' }>6+</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Language Classes</th>
                <td width="2%">:</td>
                <td><select name="language_class">
                <option value="No" ${language_class == 'No' ? 'selected' : '' }>No</option>
                <option value="T.R. Leger @ DEV" ${language_class == 'T.R. Leger @ DEV' ? 'selected' : '' }>T.R. Leger @ DEV</option>
                <option value="T.R. Leger School" ${language_class == 'T.R. Leger School' ? 'selected' : '' }>T.R. Leger School</option>
                <option value="T.R. Leger @SLSS" ${language_class == 'T.R. Leger @SLSS' ? 'selected' : ''}>T.R. Leger @SLSS</option>
                <option value="Moi j''apprends" ${language_class.contains("Moi") ? 'selected' : '' }>Moi j'apprends</option>
                <option value="N/A" ${language_class == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
              </tr>
              </table>
              <div class="card-header bg-transparent border-0">
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>Workshops</h3>
          </div>
          <table class="table table-bordered">
              <tr>
                <th width="20%">Civil/Law Workshop</th>
                <td width="2%">:</td>
                <td><input name="civil_ws" type="date" value=<%=request.getAttribute("civil_ws")%>></td>
                <th width="20%">Tenants Rights + Obligations</th>
                <td width="2%">:</td>
                <td><input name="tenant_ws" type="date" value=<%=request.getAttribute("tenant_ws")%>></td>
              </tr>
              <tr>
                <th width="20%">Resume/Employment Workshop</th>
                <td width="2%">:</td>
                <td><input name="resume_ws" type="date" value=<%=request.getAttribute("resume_ws")%>></td>
                <th width="20%">Legal Aid Workshop</th>
                <td width="2%">:</td>
                <td><input name="legal_ws" type="date" value=<%=request.getAttribute("legal_ws")%>></td>
              </tr>
              <tr>
                <th width="20%">Domestic/Consent Workshop</th>
                <td width="2%">:</td>
                <td><input name="domestic_ws" type="date" value=<%=request.getAttribute("domestic_ws")%>></td>
                <th width="20%">Traffic Act Workshop</th>
                <td width="2%">:</td>
                <td><input name="traffic_ws" type="date" value=<%=request.getAttribute("traffic_ws")%>></td>
              </tr>
              <tr>
                <th width="20%">JobZone Resume</th>
                <td width="2%">:</td>
                <td><input name="jobzone_resume" type="date" value=<%=request.getAttribute("jobzone_resume")%>></td>
                <th width="20%">JobZone Job Search</th>
                <td width="2%">:</td>
                <td><input name="jobzone_search" type="date" value=<%=request.getAttribute("jobzone_search")%>></td>
              </tr>
              <tr>
                <th width="20%">JobZone Interview</th>
                <td width="2%">:</td>
                <td><input name="jobzone_interview" type="date" value=<%=request.getAttribute("jobzone_interview")%>></td>
              </tr>
            </table>
          </div>
          <input type="hidden" name="method" value="saveChanges3">
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
              <div class="change_button">
              <button class="button">Integration/Workshop</button>
              </div>
              <form method="post" action = "Agent">
              <input type="hidden" name="method" value="SectionD">
              <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
              <button type="submit" class="buttons">Employment/Housing</button>
              </form>
              <form method="post" action = "Agent">
              <input type="hidden" name="method" value="SectionE">
              <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
              <button class="buttons">DEV</button>
              </form>
              <form method="post" action = "Agent">
              <input type="hidden" name="method" value="SectionF">
              <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
              <button class="buttons">Reports</button>
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