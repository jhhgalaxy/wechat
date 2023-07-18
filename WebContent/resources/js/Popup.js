jQuery(document).ready(function($){
	
	//open Home_sort
	$('.Home_sort_data').on('click', function(event){
		event.preventDefault();
		$('.cd-Home_business').removeClass('is-visible');
		$('.cd-Home_money').removeClass('is-visible');
		if($('.cd-Home_sort').attr("class").indexOf("is-visible")>-1){
			$('.cd-Home_sort').removeClass('is-visible');
		}else{
			$('.cd-Home_sort').addClass('is-visible');
		}
	});
	
	//close Home_sort
	$('.cd-Home_sort').on('click', function(event){
		if(  $(event.target).is('.cd-Home_sort') ) {
			event.preventDefault();
			$(this).removeClass('is-visible');
		}
	});
	
	//open Home_business
	$('.Home_business_data').on('click', function(event){
		event.preventDefault();
		$('.cd-Home_sort').removeClass('is-visible');
		$('.cd-Home_money').removeClass('is-visible');
		if($('.cd-Home_business').attr("class").indexOf("is-visible")>-1){
			$('.cd-Home_business').removeClass('is-visible');
		}else{
			$('.cd-Home_business').addClass('is-visible');
		}
	});
	
	//close Home_business
	$('.cd-Home_business').on('click', function(event){
		if(  $(event.target).is('.cd-Home_business')  ) {
			event.preventDefault();
			$(this).removeClass('is-visible');
		}
	});
	
	//open Home_money
	$('.Home_money_data').on('click', function(event){
		event.preventDefault();
		$('.cd-Home_sort').removeClass('is-visible');
		$('.cd-Home_business').removeClass('is-visible');
		if($('.cd-Home_money').attr("class").indexOf("is-visible")>-1){
			$('.cd-Home_money').removeClass('is-visible');
		}else{
			$('.cd-Home_money').addClass('is-visible');
		}
	});
	
	//close Home_money
	$('.cd-Home_money').on('click', function(event){
		if(  $(event.target).is('.cd-Home_money')) {
			event.preventDefault();
			$(this).removeClass('is-visible');
		}
	});

	
	
	
	
			
				//open Gift_collection
	$('.Gift_collection_data').on('click', function(event){
		event.preventDefault();
		$('.cd-Gift_collection').addClass('is-visible');
	});
	
	//close Gift_collection
	$('.cd-Gift_collection').on('click', function(event){
		if($(event.target).is('.cd-Gift_collection-quxiao') ) {
			event.preventDefault();
			$(this).removeClass('is-visible');
		}
	});
	
	
	
	
});