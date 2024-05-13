<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="openResident2.css?version=48">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900&display=swap" rel="stylesheet">
<!-- Bootstrap CSS -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css'>
<!-- Font Awesome CSS -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css'>
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
      
      <div class="col-lg-8">
        <div class="card shadow-sm">
          <div class="card-header bg-transparent border-0">
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>Meeting</h3>
            <table class="table table-bordered" style="width:100%">
            <tr>
            <th width="20%">Agent Name</th>
            <td width="2%">:</td>
            <td><%=request.getAttribute("agent") %></td>
            <th width="20%">Meeting Date</th>
            <td width="2%">:</td>
            <td><%=request.getAttribute("meeting_date") %></td>
            </tr>
            <tr>
            <th width="20%">Meeting Type</th>
            <td width="2%">:</td>
            <td><select name="meeting_type">
            <option value="Impromptu" ${meeting_type == 'Imprompty' ? 'selected' : '' }>Impromptu</option>
            <option value="Scheduled" ${meeting_type == 'Scheduled' ? 'selected' : '' }>Scheduled</option>
            <option value="No Show" ${meeting_type == 'No Show' ? 'selected' : '' }>No Show</option>
            <option value="File Update" ${meeting_type == 'File Update' ? 'selected' : '' }>File Update</option>
            </select>
            </td>
            <th width="20%">Meeting Notes</th>
            <td width="2%">:</td>
            <td><%=request.getAttribute("meeting_notes") %></textarea></td>
            </tr>
            <tr>
            <th width="20%">Assigned Agent</th>
            <td width="2%">:</td>
            <td>
            <form method="post" action="Admin">
            <input type="hidden" name="method" value="changeAssigned">
            <input type="hidden" name="family_id" value=<%=request.getAttribute("family_id") %>>
            <select name="assigned_agent">
            <option value="Angel" ${assigned_agent == 'Angel' ? 'selected' : '' }>Angel</option>
            <option value="Ashley" ${assigned_agent == 'Ashley' ? 'selected' : '' }>Ashley</option>
            <option value="Edris" ${assigned_agent == 'Edris' ? 'selected' : '' }>Edris</option>
            <option value="Eren" ${assigned_agent == 'Eren' ? 'selected' : '' }>Eren</option>
            <option value="Ken/Peggy" ${assigned_agent == 'Ken/Peggy' ? 'selected' : '' }>Ken/Peggy</option>
            <option value="Layth" ${assigned_agent == 'Layth' ? 'selected' : '' }>Layth</option>
            <option value="Lourdes" ${assigned_agent == 'Lourdes' ? 'selected' : '' }>Lourdes</option>
            <option value="Nataly" ${assigned_agent == 'Nataly' ? 'selected' : '' }>Nataly</option>
            <option value="Roy" ${assigned_agent == 'Roy' ? 'selected' : '' }>Roy</option>
            <option value="Zakir" ${assigned_agent == 'Zakir' ? 'selected' : '' }>Zakir</option>
            </select>
            <button class="button" type="submit">Save</button>
            </form>
            </table>
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>Employment</h3>
            <table class="table table-bordered" style="width:100%">
            <tr>
            <th width="20%">Employment Status</th>
            <td width="2%">:</td>
            <td><select id="employment_status" name="employment_status" onchange="changeFunc();">
            <option value="N/A" ${employment_status == 'N/A' ? 'selected' : '' }>N/A</option>
            <option value="Yes" ${employment_status == 'Yes' ? 'selected' : '' }>Yes</option>
            <option value="No" ${employment_status == 'No' ? 'selected' : '' }>No</option>
            </select></td>
            <th width="20%">Employment Type</th>
            <td width="2%">:</td>
            <td><select name="employment_type">
            <option value="Full Time" ${employment_type == 'Full Time' ? 'selected' : '' }>Full Time</option>
            <option value="Part Time" ${employment_type == 'Part Time' ? 'selected' : '' }>Part Time</option>
            <option value="Seasonal" ${employment_type == 'Seasonal' ? 'selected' : '' }>Seasonal</option>
            <option value="Self-Employed" ${employment_type == 'Self-Employed' ? 'selected' : ''  }>Self-Employed</option>
            <option value="Volunteering" ${employment_type == 'Volunteering' ? 'selected' : '' }>Volunteering</option>
            <option value="Other (Notes)" ${employment_type == 'Other (Notes)' ? 'selected' : '' }>Other (Notes)</option>
            </select>
            </tr>
            <tr id="looking/resume">
            <th width="20%">Looking for Job</th>
            <td width="2%">:</td>
            <td><select name="looking_job">
            <option value="Yes" ${looking_job == 'Yes' ? 'selected' : '' }>Yes</option>
            <option value="No (Have One)" ${looking_job == 'No (Have One)' ? 'selected' : '' }>No (Have One)</option>
            <option value="No (Pregnant)" ${looking_job == 'No (Pregnant)' ? 'selected' : '' }>No (Pregnant)</option>
            <option value="No (Young Kids)" ${looking_job == 'No (Young Kids)' ? 'selected' : '' }>No (Young Kids)</option>
            <option value="No (Work Permit)" ${looking_job == 'No (Work Permit)' ? 'selected' : '' }>No (Work Permit)</option>
            <option value="No Other (Notes)" ${looking_job == 'No Other (Notes' ? 'selected' : '' }>No Other (Notes)</option>
            </select>
            <th width="20%">Resume</th>
            <td width="2%">:</td>
            <td><select name="resume">
            <option value="Yes" ${resume == 'Yes' ? 'selected' : '' }>Yes</option>
            <option value="No" ${resume == 'No' ? 'selected' : '' }>No</option>
            <option value="N/A" ${resume == 'N/A' ? 'selected' : '' }>N/A</option>
            </select>
            </td>
            </tr>
            <tr id="actions/obstacle">
            <th width="20%">Actions Taken</th>
            <td width="2%">:</td>
            <td><div class="actions"><select name="actions" multiple>
            <option value="Cold calls to potential employers" ${actions.contains('Cold calls to potential employers') ? 'selected' : '' }>Cold calls to potential employers</option>
            <option value="In person visits to potential workplaces" ${actions.contains('In person visits to potential workplaces') ? 'selected' : '' }>In person visits to potential workplaces</option>
            <option value="Applied for jobs online" ${actions.contains('Applied for jobs online') ? 'selected' : ''  }>Applied for jobs online</option>
            <option value="Followed up on previous applications" ${actions.contains('Followed up on previous applications') ? 'selected' : '' }>Followed up on previous applications</option>
            <option value="Went to job fairs" ${actions.contains('Went to job fairs') ? 'selected' : '' }>Went to job fairs</option>
            <option value="Consulted employment services" ${actions.contains('Consulted employment services') ? 'selected' : '' }>Consulted employment services</option> 
            </select></div></td>
            <th width="20%">Job Search Obstacles</th>
            <td width="2%">:</td>
            <td><select name="obstacles" multiple>
            <option value="No Canadian Experience" ${obstacles.contains('No Canadian Experience') ? 'selected' : '' }>No Canadian Experience</option>
            <option value="Discrimination" ${obstacles.contains('Discrimination') ? 'selected' : '' }>Discrimination</option>
            <option value="No Childcare" ${obstacles.contains('No Childcare') ? 'selected' : '' }>No Childcare</option>
            <option value="Transporation" ${obstacles.contains('Transportation') ? 'selected' : '' }>Transportation</option>
            <option value="Medical Issues" ${obstacles.contains('Medical Issues') ? 'selected' : '' }>Medical Issues</option>
            <option value="Diploma Recognition" ${obstacles.contains('Diploma Recognition') ? 'selected' : '' }>Diploma Recognition</option>
            <option value="Job Scarcity" ${obstacles.contains('Job Scarcity') ? 'selected' : '' }>Job Scarcity</option>
            <option value="Language Barrier" ${obstacles.contains('Language Barrier') ? 'selected' : '' }>Language Barrier</option>
            <option value="Other (Notes)" ${obstacles.contains('Other (Notes)') ? 'selected' : '' }>Other (Notes)</option>
            </select></td></tr>
            <tr><th width="20%">Jobs on JobBank.gc.ca</th>
            <td width="2%">:</td>
            <td><select name="jobBank">
            <option value="Yes" ${jobBank == 'Yes' ? 'selected' : ''}>Yes</option>
            <option value="No" ${jobBank == 'No' ? 'selected' : ''}>No</option>
            </select></td>
            <th width="20%">Industry of Certification</th>
            <td width="2%">:</td>
            <td><select name="certificateIndustry">
            <option value="Agriculture" ${certificateIndustry == 'Agriculture' ? 'selected' : '' }>Agriculture</option>
            <option value="Construction" ${certificateIndustry == 'Construction' ? 'selected' : '' }>Construction</option>
            <option value="Education" ${certificateIndustry == 'Education' ? 'selected' : '' }>Education</option>
            <option value="Healthcare" ${certificateIndustry == 'Healthcare' ? 'selected' : '' }>Healthcare</option>
            <option value="Hospitality/Tourism" ${certificateIndustry == 'Hospitality/Tourism' ? 'selected' : '' }>Hospitality/Tourism</option>
            <option value="Industrial" ${certificateIndustry == 'Industrial' ? 'selected' : '' }>Industrial</option>
            <option value="Logistics" ${certificateIndustry == 'Logistics' ? 'selected' : '' }>Logistics</option>
            <option value="Media/Communications" ${certificateIndustry == 'Media/Communications' ? 'selected' : '' }>Media/Communications</option>
            <option value="Retail & Service" ${certificateIndustry == 'Retail & Service' ? 'selected' : '' }>Retail/Services</option>
            <option value="Security & Defense" ${certificateIndustry == 'Security & Defense' ? 'selected' : '' }>Security & Defense</option>
            <option value="Technology" ${certificateIndustry == 'Technology' ? 'selected' : '' }>Technology</option>
            <option value="None/Unknown" ${certificateIndustry == 'None/Unknown' ? 'selected' : '' }>None/Unknown</option>
            </select></td>
            </tr>
            <tr>
            <th width="20%">Experience</th>
            <td width="2%">:</td>
            <td><select name="experience">
            <option value="Agriculture" ${experience == 'Agriculture' ? 'selected' : '' }>Agriculture</option>
            <option value="Construction" ${experience == 'Construction' ? 'selected' : '' }>Construction</option>
            <option value="Education" ${experience == 'Education' ? 'selected' : '' }>Education</option>
            <option value="Healthcare" ${experience == 'Healthcare' ? 'selected' : '' }>Healthcare</option>
            <option value="Hospitality/Tourism" ${experience == 'Hospitality/Tourism' ? 'selected' : '' }>Hospitality/Tourism</option>
            <option value="Industrial" ${experience == 'Industrial' ? 'selected' : '' }>Industrial</option>
            <option value="Logistics" ${experience == 'Logistics' ? 'selected' : '' }>Logistics</option>
            <option value="Media/Communications" ${experience == 'Media/Communications' ? 'selected' : '' }>Media/Communications</option>
            <option value="Retail & Service" ${experience == 'Retail & Service' ? 'selected' : '' }>Retail/Services</option>
            <option value="Security & Defense" ${experience == 'Security & Defense' ? 'selected' : '' }>Security & Defense</option>
            <option value="Technology" ${experience == 'Technology' ? 'selected' : '' }>Technology</option>
            <option value="None/Unknown" ${experience == 'None/Unknown' ? 'selected' : '' }>None/Unknown</option>
            </select></td>
            <th width="20%">Income</th>
            <td width="2%">:</td>
            <td><select name="income">
            <option value="Employment Income" ${income == 'Employment Income' ? 'selected' : ''}>Employment Income</option>
            <option value="Social Assistance" ${income == 'Social Assistance' ? 'selected' : '' }>Social Assistance</option>
            <option value="Child Benefits" ${income == 'Child Benefits' ? 'selected' : '' }>Child Benefits</option>
            <option value="Disability" ${income == 'Disability' ? 'selected' : '' }>Disability</option>
            <option value="Other (Notes)" ${income == 'Other (Notes)' ? 'selected' : '' }>Other (Notes)</option>
            </select></td></tr>
            <tr>
            <th width="20%">Transportation</th>
            <td width="2%">:</td>
            <td><select name="transportation">
            <option value="My Car" ${transportation == 'My Car' ? 'selected' : '' }>My Car</option>
            <option value="Carpooling (Not my Car)" ${transportation == 'Carpooling (Not my Car)' ? 'selected' : '' }>Carpooling (Not my Car)</option>
            <option value="My Motorcycle" ${transportation == 'My Motorcycle' ? 'selected' : '' }>My Motorcycle</option>
            <option value="My Bicycle" ${transportation == 'My Bicycle' ? 'selected' : '' }>My Bicycle</option>
            <option value="Public Transportation" ${transportation == 'Public Transportation' ? 'selected' : '' }>Public Transportation</option>
            <option value="Other (Notes)" ${transportation == 'Other (Notes)' ? 'selected' : '' }>Other (Notes)</option>
            </select></td></tr>
            <tr></tr>
            </table>
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>Housing</h3>
            <table class="table table-bordered" style="width:100%">
            <tr>
            <th width="20%">Lodging Found</th>
            <td width="2%">:</td>
            <td><select name="lodging" id="lodging">
            <option value="N/A" ${lodging == 'N/A' ? 'selected' : '' }>N/A</option>
            <option value="Yes" ${lodging == 'Yes' ? 'selected' : '' }>Yes</option>
            <option value="No" ${lodging == 'No' ? 'selected' : '' }>No</option>
            </select></td>
            <th width="20%">Moving Date</th>
            <td width="2%">:</td>
            <td><input type="date" name="moving_date" value=<%=request.getAttribute("moving_date") %>></td>
            </tr>
            <tr id="foundLodging" >
            <th width="20%">Way of Finding Housing</th>
            <td width="2%">:</td>
            <td><select name="wayOfFindingLodging">
            <option value="On my own" ${wayOfFindingLodging == 'On my own' ? 'selected' : '' }>On my own</option>
            <option value="Through friends/family" ${wayOfFindingLodging == 'Through friends/family' ? 'selected' : '' }>Through friends/family</option>
            <option value="Through a Service (Globalys Services)" ${wayOfFindingLodging == 'Through a Service (Globalys Services)' ? 'selected' : '' }>Through a Service (Globalys Services)</option>
            <option value="Through a Service (Name in notes)" ${wayOfFindingLodging == 'Through a Service (Name in notes)' ? 'selected' : '' }>Through a Service (Name in notes)</option>
            </select>
            </tr>
            <tr>
            <th width="20%">Housing Search</th>
            <td width="2%">:</td>
            <td><select name="housing_search">
            <option value="Yes" ${housing_search == 'Yes' ? 'selected' : '' }>Yes</option>
            <option value="No (Going Back to Country)" ${housing_search == 'No (Going Back to Country)' ? 'selected' : '' }>No (Going Back to Country)</option>
            <option value="No (Feels No Rush)" ${housing_search == 'No (Feels No Rush)' ? 'selected' : '' }>No (Feels No Rush)</option>
            <option value="No (Waiting for RPCD)" ${housing_search == 'No (Waiting for RPCD)' ? 'selected' : '' }>No (Waiting for RPCD)</option>
            <option value="No (Language Barrier)" ${housing_search == 'No (Language Barrier)' ? 'selected' : '' }>No (Language Barrier)</option>
            <option value="No Others (Notes)" ${housing_search == 'No Others (Notes)' ? 'selected' : '' }>No Others (Notes)</option>
            </select></td>
            <th width="20%">Housing Search Outside Urban Area (35+ km)</th>
            <td width="2%">:</td>
            <td><select name="housing_outside">
            <option value="Yes" ${housing_outside == 'Yes' ? 'selected' : '' }>Yes</option>
            <option value="No" ${housing_outside == 'No' ? 'selected' : '' }>No</option>
            </select></td></tr>
            <tr>
            <th width="20%">Co-location</th>
            <td width="2%">:</td>
            <td><select name="colocation">
            <option value="Yes (and Looking Together)" ${colocation == 'Yes (and Looking Together)' ? 'selected' : '' }>Yes (and Looking Together)</option>
            <option value="Yes (but Found No Coloc)" ${colocation == 'Yes (but Found No Coloc)' ? 'selected' : '' }>Yes (but Found No Coloc)</option>
            <option value="Yes (but Didn't Work)" ${colocation.contains('Yes (but Didn') ? 'selected' : '' }>Yes (but Didn't Work)</option>
            <option value="No (but Could Consider it)" ${colocation == 'No (but Could Consider it)' ? 'selected' : '' }>No (but Could Consider it)</option>
            <option value="No (Family Size)" ${colocation == 'No (Family Size)' ? 'selected' : '' }>No (Family Size)</option>
            <option value="No Other (Notes)" ${colocation == 'No Other (Notes)' ? 'selected' : '' }>No Other (Notes)</option>
            <option value="No Way" ${colocation == 'No Way' ? 'selected' : '' }>No Way</option>
            </select></td>
            <th width="20%">Housing Challenges</th>
            <td width="2%">:</td>
            <td><select name="housing_obstacles" multiple>
            <option value="Don''t Pass Credit Check" ${housing_obstacles.contains('t Pass Credit Check') ? 'selected' : ''}>Don't Pass Credit Check</option>
            <option value="Scarcity" ${housing_obstacles.contains('Scarcity') ? 'selected' : '' }>Scarcity</option>
            <option value="Medical Issues" ${housing_obstacles.contains('Medical Issues') ? 'selected' : '' }>Medical Issues</option>
            <option value="Don''t Have A Job" ${housing_obstacles.contains('t Have A Job') ? 'selected' : '' }>Don't Have A Job</option>
            <option value="On Wellfare" ${housing_obstacles.contains('One Wellfare') ? 'selected' : '' }>On Wellfare</option>
            <option value="Size of our Family" ${housing_obstacles.contains('Size of our Family') ? 'selected' : '' }>Size of our Family</option>
            <option value="Discrimination" ${housing_obstacles.contains('Discrimination') ? 'selected' : '' }>Discrimination</option>
            <option value="Can''t Afford" ${housing_obstacles.contains('t Afford') ? 'selected' : '' }>Can't Afford</option>
            <option value="Language Barrier" ${housing_obstacles.contains('Language Barrier') ? 'selected' : '' }>Language Barrier</option>
            <option value="Other (Notes)" ${housing_obstacles.contains('Other (Notes)') ? 'selected' : '' }>Other (Notes)</option>
            </select></td></tr>
            <tr>
            <th width="20%">Housing Search Support</th>
            <td width="2%">:</td>
            <td><select name="housing_support">
            <option value="Yes (Provincial)" ${housing_support == 'Yes (Provincial)' ? 'selected' : '' }>Yes (Provincial)</option>
            <option value="Yes (Municipality)" ${housing_support == 'Yes (Municipality)' ? 'selected' : '' }>Yes (Municipality)</option>
            <option value="Yes (Globalys Support)" ${housing_support == 'Yes (Globalys Support)' ? 'selected' : '' }>Yes (Globalys Support)</option>
            <option value="Yes (Community Groups)" ${housing_support == 'Yes (Community Groups)' ? 'selected' : '' }>Yes (Community Groups)</option>
            <option value="Yes (Social Media)" ${housing_support == 'Yes (Social Media)' ? 'selected' : '' }>Yes (Social Media)</option>
            <option value="No (Don''t Know Where to Start)" ${housing_support == 't Know Where to Start' ? 'selected' : '' }>No (Don't Know Where to Start)</option>
            <option value="No Other (Notes)" ${housing_support == 'No Other (Notes)' ? 'selected' : '' }>No Other (Notes)</option>
            <option value="Just No" ${housing_support == 'Just No' ? 'selected' : '' }>Just No</option>
            </select></td></tr>
            </table>
          </div>
          <div class="card-body pt-0">
          </div>
        </div>
          <div style="height: 26px"></div>
      </div>
    </div>
  </div>
</div>
</body>
</html>