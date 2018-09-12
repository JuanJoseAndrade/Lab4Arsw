
function newTable(order) {
	
	if (order.orderAmountsMap.length>0){
		var nombre="Order"+order.tableNumber;
		var nuevaTabla='<div id='+nombre+'>';
		nuevaTabla+='<h2>Order '+nombre+'</h2>';
		nuevaTabla+='<table class="table table-striped table-sm"> <thead> <tr> <th> Product </th> <th> Quantity </th> </tr> </thead> <tbody>';

		
		for (i in order.orderAmountsMap) {
			nuevaTabla += '<tr>';
			nuevaTabla += '<td>'+order.orderAmountsMap[i]+'<td>';
			nuevaTabla += '<td>'+order.orderAmountsMap[i].data+'<td>';
			
			nuevaTabla += '</tr>';
		}
		nuevaTabla += '</tbody></table></div>';
		document.getElementById("cuerpoDeTablas").innerHTML+=nuevaTabla;
		console.log(nuevaTabla);
	}
	
	
}

function removeOrderById(id) {
	axios.delete('/orders/'+id)
		.then(function(){                        
			document.getElementById("Order"+id).remove();
		})
		.catch(function(error){
			console.log(error);
			errorMessage();
		});
}
function getOrders(){
    
	console.log("1");
	axios.get('/orders')
    .then(function (response) {
	console.log("2");
     exitoAgregarTablas(response);
    })
    .catch(function (error) {
      console.log(error);
	  errorMessage();
    });
}

function exitoAgregarTablas(response) {
	alert("There is a problem with our servers. We apologize for the inconvince, please try again later");
	orders = response.data;
	console.log(orders);
	$("cuerpoDeTablas").innerHTML = '';
	for(i in orders){
		newTable(orders[i]);
	}
  
}
function errorMessage(){
	alert("There is a problem with our servers. We apologize for the inconvince, please try again later");
}



