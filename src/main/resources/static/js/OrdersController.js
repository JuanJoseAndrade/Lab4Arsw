
function newTable(order) {
	
	
	if ( Object.keys(order.orderAmountsMap).length>0){
		
		var nombre="Order"+order.tableNumber;
		var nuevaTabla='<div id='+nombre+'>';
		nuevaTabla+='<h2>'+nombre+'</h2>';
		nuevaTabla+='<table class="table table-striped table-sm"> <thead> <tr> <th> Product </th> <th> Quantity </th> </tr> </thead> <tbody>';

		
		for (i in order.orderAmountsMap) {
			nuevaTabla += '<tr>';
			nuevaTabla += '<td>'+i+'<td>';
			nuevaTabla += '<td>'+order.orderAmountsMap[i]+'<td>';
			
			nuevaTabla += '</tr>';
		}
		nuevaTabla += '</tbody></table></div>';
		document.getElementById("cuerpoDeTablas").innerHTML+=nuevaTabla;
		
	}
	
	
}

function removeOrderById(id) {
	$("Order"+id.toString()).remove();
}
function getOrders(){
    
	
	axios.get('/orders')
    .then(function (response) {
     exitoAgregarTablas(response);
    })
    .catch(function (error) {
      console.log(error);
	  errorMessage();
    });
}

function exitoAgregarTablas(response) {
	orders = response.data;
	$("cuerpoDeTablas").innerHTML = '';
	for(i in orders){
		newTable(orders[i]);
	}
	alert("Los datos se descargaron de manera satisfactoria");
  
}
function errorMessage(){
	alert("There is a problem with our servers. We apologize for the inconvince, please try again later");
}



