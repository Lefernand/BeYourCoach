$(function(){
	
	$(document).scroll(function(){
		position = $(document).scrollTop();
		if(position > 0){
			$(".masthead .inner").addClass('shrink');
		}else{
			$(".masthead .inner").removeClass('shrink');
		}
	});
	
});

$(document).mouseup(function (e)
		{
		    var container = $(".helloGuy");

		    if (!container.is(e.target) // if the target of the click isn't the container...
		        && container.has(e.target).length === 0) // ... nor a descendant of the container
		    {
		    	container.fadeOut( "slow", function() {
		    		container.hide();
		    	  });
		        
		    }
		});