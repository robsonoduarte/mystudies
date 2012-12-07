
$(function() {
	$( ".draggable" ).draggable();
	$( ".droppable" ).droppable({
		drop: function( event, ui ) {


			if( $(this).attr("id") != $(ui.draggable).parent().attr("id")){

				$('#kanbanFase').val( $(this).attr("id"));
				$('#storyID').val($(ui.draggable).attr("id"));

				$.get(
					'kanban',
					$('form').serialize()
				);
			}
		}
	});
});

