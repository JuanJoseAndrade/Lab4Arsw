
function newTable(order) {
	
	if (order.products.length>0){
		var nombre="Order"+order.tableNumber;
		var nuevaTabla='<div id='+nombre+'>';
		nuevaTabla+='<h2>Order '+order.table_id+'</h2>';
		nuevaTabla+='<table class="table table-striped table-sm"> <thead> <tr> <th> Product </th> <th> Quantity </th> </tr> </thead> <tbody>';

		
		for (i in order.orderAmountsMap) {
			nuevaTabla += '<tr>';
			nuevaTabla += '<td>'+order.orderAmountsMap[i].product+'<td>';
			nuevaTabla += '<td>'+order.orderAmountsMap[i].quantity+'<td>';
			
			nuevaTabla += '</tr>';
		}
		nuevaTabla += '</tbody></table></div>';
		document.getElementById("cuerpoDeTablas").innerHTML+=nuevaTabla;
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
  var resultElement = document.getElementById('cuerpoDeTablas');
  resultElement.innerHTML = '';

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
	for(i in orders){
		newTable(orders[i]);
	}
  
}
function errorMessage(){
	alert("There is a problem with our servers. We apologize for the inconvince, please try again later");
}



