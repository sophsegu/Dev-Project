<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="largeFormat5.css?version=2">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
  <div class="admin-page">
    <div class="form">
      <div class="admin">
      <img src="img/globalys_logo.png" style="width:200px;">
      <div class="container">
        
        </div>
      </div>
      <br>
      <div class="Format-Button">
	<table>
		<tr>
			<th style="width:5%">Status</th>
			<th style="width:5%">Agent</th>
			<th style="width:10%">UCI</th>
			<th style="width:5%">Room Number</th>
			<th style="width:15%">First Name</th>
			<th style="width:15%">Surname</th>
			<th style="width:10%">Date of Birth</th>
			<th style="width:10%">Gender</th>
			<th style="width:10%">Country of Origin</th>
			<th style="width:10%">Open Profile</th>
		</tr>
		<tbody id="tableContent">
			<tr>
				<%= request.getAttribute("html") %>
			</tr>
			<!-- Add more rows as needed -->
		</tbody>
	</table>
	</div>
	<br>
	<div id="pagination">
	
	</div>
	</div>
    </div>
</body>
<script>
var Pagination = {

	    code: '',

	    // --------------------
	    // Utility
	    // --------------------

	    // converting initialize data
	    Extend: function(data) {
	        data = data || {};
	        Pagination.size = data.size || 300;
	        Pagination.page = data.page || 1;
	        Pagination.step = data.step || 3;
	        Pagination.query = data.query || '';
	    },

	    // add pages by number (from [s] to [f])
	    Add: function(s, f) {
	        for (var i = s; i < f; i++) {
	            Pagination.code += '<a>' + i + '</a>';
	        }
	    },

	    // add last page with separator
	    Last: function() {
	        Pagination.code += '<i>...</i><a>' + Pagination.size + '</a>';
	    },

	    // add first page with separator
	    First: function() {
	        Pagination.code += '<a>1</a><i>...</i>';
	    },



	    // --------------------
	    // Handlers
	    // --------------------

	    // change page
	    Click: function() {
    Pagination.page = +this.innerHTML;
    Pagination.Start();

    fetch('/Immigration_Tracker/Agent?page='+Pagination.page, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ 
            page: Pagination.page,
            query: Pagination.query
        })
    })
    .then(response => response.text())
    .then(val => {
    	console.log(val);
        document.getElementById('tableContent').innerHTML = val;
    })
    .catch(error => {
        console.error('Error:', error);
    });
},


	    // previous page
	    Prev: function() {
	        Pagination.page--;
	        if (Pagination.page < 1) {
	            Pagination.page = 1;
	        }
	        Pagination.Start();
	        fetch('/ImmigrationTracker/Agent?page='+Pagination.page, {
	            method: 'POST',
	            headers: {
	                'Content-Type': 'application/json'
	            },
	            body: JSON.stringify({ 
	                page: Pagination.page,
	                query: Pagination.query
	            })
	        })
	        .then(response => response.text())
	        .then(val => {
	        	console.log(val);
	            document.getElementById('tableContent').innerHTML = val;
	        })
	        .catch(error => {
	            console.error('Error:', error);
	        });
	    },

	    // next page
	    Next: function() {
	        Pagination.page++;
	        if (Pagination.page > Pagination.size) {
	            Pagination.page = Pagination.size;
	        }
	        Pagination.Start();
	        fetch('/ImmigrationTracker/Agent?page='+Pagination.page, {
	            method: 'POST',
	            headers: {
	                'Content-Type': 'application/json'
	            },
	            body: JSON.stringify({ 
	                page: Pagination.page,
	                query: Pagination.query
	            })
	        })
	        .then(response => response.text())
	        .then(val => {
	        	console.log(val);
	            document.getElementById('tableContent').innerHTML = val;
	        })
	        .catch(error => {
	            console.error('Error:', error);
	        });
	    },



	    // --------------------
	    // Script
	    // --------------------

	    // binding pages
	    Bind: function() {
	        var a = Pagination.e.getElementsByTagName('a');
	        for (var i = 0; i < a.length; i++) {
	            if (+a[i].innerHTML === Pagination.page) a[i].className = 'current';
	            a[i].addEventListener('click', Pagination.Click, false);
	        }
	    },

	    // write pagination
	    Finish: function() {
	        Pagination.e.innerHTML = Pagination.code;
	        Pagination.code = '';
	        Pagination.Bind();
	    },

	    // find pagination type
	    Start: function() {
	        if (Pagination.size < Pagination.step * 2 + 6) {
	            Pagination.Add(1, Pagination.size + 1);
	        }
	        else if (Pagination.page < Pagination.step * 2 + 1) {
	            Pagination.Add(1, Pagination.step * 2 + 4);
	            Pagination.Last();
	        }
	        else if (Pagination.page > Pagination.size - Pagination.step * 2) {
	            Pagination.First();
	            Pagination.Add(Pagination.size - Pagination.step * 2 - 2, Pagination.size + 1);
	        }
	        else {
	            Pagination.First();
	            Pagination.Add(Pagination.page - Pagination.step, Pagination.page + Pagination.step + 1);
	            Pagination.Last();
	        }
	        Pagination.Finish();
	    },



	    // --------------------
	    // Initialization
	    // --------------------

	    // binding buttons
	    Buttons: function(e) {
	        var nav = e.getElementsByTagName('a');
	        nav[0].addEventListener('click', Pagination.Prev, false);
	        nav[1].addEventListener('click', Pagination.Next, false);
	    },

	    // create skeleton
	    Create: function(e) {

	        var html = [
	            '<a>&#9668;</a>', // previous button
	            '<span></span>',  // pagination container
	            '<a>&#9658;</a>'  // next button
	        ];

	        e.innerHTML = html.join('');
	        Pagination.e = e.getElementsByTagName('span')[0];
	        Pagination.Buttons(e);
	    },

	    // init
	    Init: function(e, data) {
	        Pagination.Extend(data);
	        Pagination.Create(e);
	        Pagination.Start();
	    }
	};



	/* * * * * * * * * * * * * * * * *
	* Initialization
	* * * * * * * * * * * * * * * * */

	var init = function() {
	    Pagination.Init(document.getElementById('pagination'), {
	        size: <%=request.getAttribute("count")%>, // pages size
	        page: <%=request.getAttribute("page")%>,  // selected page
	        step: 3,   // pages before and after current
	        query: '<%=request.getAttribute("query")%>'
	    });
	};

	document.addEventListener('DOMContentLoaded', init, false);

</script>
</html>