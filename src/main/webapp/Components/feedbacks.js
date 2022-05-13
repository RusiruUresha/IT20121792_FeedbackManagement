$(document).ready(function() 
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 

//save
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateFeedbackForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidFeedbackIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "FeedbacksAPI", 
 type : type, 
 data : $("#formFeedback").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onFeedbackSaveComplete(response.responseText, status); 
 } 
 }); 
});



// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
$("#hidFeedbackIDSave").val($(this).data("feedbackid")); 
 $("#fbType").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#fbDesc").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#fbRate").val($(this).closest("tr").find('td:eq(2)').text()); 
});
 
 //delete
 $(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "FeedbacksAPI", 
 type : "DELETE", 
 data : "feedbackID=" + $(this).data("feedbackid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onFeedbackDeleteComplete(response.responseText, status); 
 } 
 }); 
});
 
 
// CLIENT-MODEL================================================================
function validateFeedbackForm() 
{ 
	// TYPE
	if ($("#fbType").val().trim() == "") 
	 { 
	 return "Insert Feedback Type."; 
	 } 
	// DESCRIPTION
	if ($("#fbDesc").val().trim() == "") 
	 { 
	 return "Insert Feedback Description."; 
	 }
	// RATE-------------------------------
	if ($("#fbRate").val().trim() == "") 
	 { 
	 return "Insert Feedback Rate."; 
	 } 
	// is numerical value
	var tmpRate = $("#fbRate").val().trim(); 
	if (!$.isNumeric(tmpRate)) 
	 { 
	 return "Insert a numerical value for Feedback Rate."; 
	 } 
	// convert to decimal price
	 $("#fbRate").val(parseFloat(tmpRate).toFixed(2)); 
	  
return true; 
}

//onsave
function onFeedbackSaveComplete(response, status) 
{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully saved."); 
	 $("#alertSuccess").show(); 
	 $("#divItemsGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
	 $("#alertError").text(resultSet.data); 
	 $("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
	 $("#alertError").text("Error while saving."); 
	 $("#alertError").show(); 
	 } else
	 { 
	 $("#alertError").text("Unknown error while saving.."); 
	 $("#alertError").show(); 
      }

   $("#hidFeedbackIDSave").val(""); 
   $("#formFeedback")[0].reset(); 
}


//ondelete
function onFeedbackDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divFeedbacksGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}
