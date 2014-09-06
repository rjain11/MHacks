
// Javascript file for Mhacks Music App

function playVideo(){
	
	var url=$("#search-basic").val();
	$("#youPlay").attr('src', url);	
	$("#player").show();

	
}