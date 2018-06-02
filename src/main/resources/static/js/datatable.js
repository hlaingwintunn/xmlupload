$(document).ready( function () {
	 var table = $('#studentsTable').DataTable({
			"sAjaxSource": "/students",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
//			      { "mData": "id"},
		          { "mData": "studentName" },
		          { "mData": "dob" },
				  { "mData": "totalMarks" },
		            {
	                     data: null,
	                     orderable: false,
	                     className: "dt-center",
	                     defaultContent: ' <a href="#" style="color: red; cursor: pointer" data-toggle="modal" data-target="#myModal">Delete</a>'

	           },
				  
			]
	 })
	 
	 $('#studentsTable tbody').on('click', 'tr td #del', function () {
		    var row = $(this).parents('tr')[0];
		    var mydata = (table.row(row).data());
		    var con=confirm("Are you sure you want to delet this "+ mydata["studentName"])
		  if(con){
		       // Do Something
		  }
		  else
		   {
		      // Nothing to do here
		   }
		});
	
});
