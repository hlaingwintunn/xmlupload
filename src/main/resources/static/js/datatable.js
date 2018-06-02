$(document).ready( function () {
	var delId = 0;
	var row;
	
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
	                     defaultContent: ' <a href="#" id="del" style="color: red; cursor: pointer" data-toggle="modal" data-target="#myModal">Delete</a>'

	           },
				  
			]
	 })
	 
	 $('#studentsTable').on( 'click', 'tr td #del', function () {
		 row = $(this).parents('tr')[0];
	     var data = (table.row(row).data());
  
	     delId =  data["id"];
	 } );
	 
	 $('#confirmdelete').click(function() {
		 row.remove();
	 })
	
});
