// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#dataTable').DataTable( {
     language: {
   		 url: 'http://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json'
 	 	 },
		"dom" : '<"d-flex justify-content-between"fl>t<"d-flex justify-content-between"ip>',
						"searching" : true,
						"lengthMenu" : [ [ 5, 10, 25, 50, -1 ],
								[ 5, 10, 25, 50, "Mostrar Todo" ] ],
						columnDefs : [ {
							targets : [ 0, 1, 2 ],
							className : 'mdl-data-table__cell--non-numeric'
						} ]

} );

	$('#dataTable2').DataTable({
     language: {
  		 url: 'http://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json'
 	  },
		"dom" : '<"d-flex justify-content-between"fl>t<"d-flex justify-content-between"ip>',
						"searching" : true,
						"lengthMenu" : [ [ 5, 10, 25, 50, -1 ],
								[ 5, 10, 25, 50, "Mostrar Todo" ] ],
						columnDefs : [ {
							targets : [ 0, 1, 2 ],
							className : 'mdl-data-table__cell--non-numeric'
						} ]

} );
	
});
