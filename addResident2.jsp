<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="openResident3.css?version=4">
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
</div>
  <div class="student-profile py-4">
  <div class="container">
    <div class="row">
      <div class="col-lg-4">
        <div class="col-lg-8">
        
        <div class="card shadow-sm">
          <div class="card-header bg-transparent border-0">
        <img class="logo" src="img/globalys_logo.png" alt="logo">
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>Intake</h3>
          </div>
          <div class="card-body pt-0">
          <form method="post" action = "Agent">
            <table class="table table-bordered">
              <tr>
                <th width="20%">Currently Working</th>
                <td width="2%">:</td>
                <td><select name="employed">
                <option value="Employed">Employed</option>
                <option value="Unemployed">Unemployed</option>
                <option value="N/A">N/A</option>
                </select></td>
                <th width="20%">Where are they employed?</th>
                <td width="2%">:</td>
                <td><input type="text" name="employer"></td>
              </tr>
              <tr>
                <th width="20%">Date of Employment</th>
                <td width="2%">:</td>
                <td><input type="date" name="start_employment"></td>
                <th width="20%">Vehicle (YEAR/MAKE/MODEL)</th>
                <td width="2%">:</td>
                <td><input type="text" name="vehicle"></td>
              </tr>
              
              <tr>
                <th width="20%">Insurance</th>
                <td width="2%">:</td>
                <td><select name="insurance">
                <option value="Yes">Yes</option>
                <option value="No">No</option>
                <option value="N/A">N/A</option>
                </select></td>
                <th width="20%">Registration</th>
                <td width="2%">:</td>
                <td><select name="registration">
                <option value="Yes">Yes</option>
                <option value="No">No</option>
                <option value="N/A">N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Highest Level of Education</th>
                <td width="2%">:</td>
                <td><select name="education" multiple>
                <option value="None">None</option>
                <option value="Elementary">Elementary</option>
                <option value="High School">High School</option>
                <option value="College">College</option>
                <option value="University">University</option>
                <option value="Masters">Masters</option>
                <option value="PhD">PhD</option>
                </select></td>
                <th width="20%">Certifications</th>
                <td width="2%">:</td>
                <td><input type="text" name="certifications"></td>
              </tr>
              <tr>
                <th width="20%">Work Experience</th>
                <td width="2%">:</td>
                <td><input type="text" name="work_experience"></td>
                <th width="20%">Professional Goals</th>
                <td width="2%">:</td>
                <td><select name="initial_assessment" multiple>
                <option value="In their field">In their field</option>
                <option value="Looking for same salary range">Looking for same salary range</option>
                <option value="Working on re-certification">Working on re-certification</option>
                <option value="Training">Training</option>
                <option value="Language Classes">Language Classes</option>
                <option value="School">School</option>
                <option value="N/A">N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Destination Preference</th>
                <td width="2%">:</td>
                <td><select name="destination">
                <option value="NWT">NWT</option>
                <option value="BC">BC</option>
                <option value="Victoria">Victoria</option>
                <option value="Vancouver">Vancouver</option>
                <option value="Alberta">Alberta</option>
                <option value="Calgary">Calgary</option>
                <option value="Edmonton">Edmonton</option>
                <option value="Saskatchewan">Saskatchewan</option>
                <option value="Manitoba">Manitoba</option>
                <option value="Ontario">Ontario</option>
                <option value="Brampton">Brampton</option>
                <option value="Cornwall">Cornwall</option>
                <option value="Hamilton">Hamilton</option>
                <option value="Ottawa">Ottawa</option>
                <option value="Toronto">Toronto</option>
                <option value="Québec">Québec</option>
                <option value="Québec City">Québec City</option>
                <option value="Lévis">Lévis</option>
                <option value="Montreal">Montreal</option>
                <option value="New Brunswick">New Brunswick</option>
                <option value="Newfoundland">Newfoundland</option>
                </select></td>
                <th width="20%">Medical Condition</th>
                <td width="2%">:</td>
                <td><input type="text" name="medical"></td>
              </tr>
              </table>
              <div class="card-header bg-transparent border-0">
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>Immigration Status Checklist</h3>
          </div>
              <table class="table table-bordered">
              <tr>
                <th width="20%">Ontario Works</th>
                <td width="2%">:</td>
                <td><select name="ontario_works">
                <option value="Applied">Applied</option>
                <option value="Recipient">Recipient</option>
                <option value="Ineligible">Ineligible</option>
                <option value="Dependent">Dependent</option>
                <option value="N/A">N/A</option>
                </select>
                </td>
                <th width="20%">Received Ontario Works</th>
                <td width="2%">:</td>
                <td><input type="date" name="date_ow"></td>
              </tr>
              <tr>
                <th width="20%">IME</th>
                <td width="2%">:</td>
                <td><select name="ime" > 
                <option value="Assessed (Received)">Assessed (Received)</option>
                <option value="Not Started">Not Started</option>
                <option value="In Progress (Applied)">In Progress (Applied)</option>
                <option value="N/A">N/A</option>
                </select></td>
                <th width="20%">Brown Paper (RPCD)</th>
                <td width="2%">:</td>
                <td><select name="rpcd"> 
                <option value="Yes">Yes</option>
                <option value="No">No</option>
                <option value="N/A">N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">eApp</th>
                <td width="2%">:</td>
                <td><select name="eapp" > 
                <option value="Yes">Yes</option>
                <option value="No">No</option>
                <option value="N/A">N/A</option>
                </select></td>
                <th width="20%">Basis of Claim</th>
                <td width="2%">:</td>
                <td><select name="boc"> 
                <option value="Submitted ">Submitted</option>
                <option value="Waiting for Lawyer">Waiting for Lawyer</option>
                <option value="Not Submitted">Not Submitted</option>
                <option value="Completed">Completed</option>
                <option value="No">No</option>
                <option value="In Progress">In Progress</option>
                <option value="N/A">N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Healthcare Coverage</th>
                <td width="2%">:</td>
                <td><select name="healthcare" > 
                <option value="IFHP">IFHP</option>
                <option value="Provincial Healthcare Coverage">Provincial Healthcare Coverage</option>
                <option value="No Healtchare Coverage">No Healthcare Coverage</option>
                <option value="N/A">N/A</option>
                </select></td>
                <th width="20%">Work Permit</th>
                <td width="2%">:</td>
                <td><select name="work_permit">
                <option value="Yes">Yes</option>
                <option value="No">No</option>
                <option value="N/A">N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Date of Work Permit</th>
                <td width="2%">:</td>
                <td><input type="date" name="date_wp"></td>
                <th width="20%">Legal Aid</th>
                <td width="2%">:</td>
                <td><select name="legal"> 
                <option value="Yes">Yes</option>
                <option value="Yes, waiting for lawyer">Yes, waiting for lawyer</option>
                <option value="No">No</option>
                <option value="No, not eligible">No, not eligible</option>
                <option value="Personal Lawyer">Personal Lawyer</option>
                <option value="Reffered">Reffered</option>
                <option value="N/A">N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Bank Account</th>
                <td width="2%">:</td>
                <td><select name="bank" > 
                <option value="Yes">Yes</option>
                <option value="No">No</option>
                <option value="Reffered">Reffered</option>
                <option value="N/A">N/A</option>
                </select></td>
                <th width="20%">IRB Hearing</th>
                <td width="2%">:</td>
                <td><select name="irb_decision"> 
                <option value="Protected Person - Convention Refugee">Protected Person - Convention Refugee</option>
                <option value="Deemed Not Protected Person">Deemed Not Protected Person</option>
                <option value="Permanent Resident">Permanent Resident</option>
                <option value="Abandoned Claim">Abandoned Claim</option>
                <option value="Withdrawn Claim">Withdrawn Claim</option>
                <option value="N/A">N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Date RPD Decision Received</th>
                <td width="2%">:</td>
                <td><input type="date" name="date_irb"></td>
                </tr>
            </table>
            <input type="hidden" name="method" value="SubmitNewResident">
            <input type="hidden" name="id" value=<%=request.getAttribute("identifier") %>>
              <button type="submit" class="button" style="float: right;">Submit</button>
            </form>
          </div>
        </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>