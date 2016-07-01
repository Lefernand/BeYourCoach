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