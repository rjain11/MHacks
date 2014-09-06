
// Javascript file for Mhacks Music App

function playVideo(){
	
	var url=$("#search-basic").val();
	alert(url);
	$("#youPlay").attr('src', url);	
	$("#player").show();

	
}