var script = document.createElement('script');
script.src = 'https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js'; 
document.getElementsByTagName('head')[0].appendChild(script);

function submitForm(){
	if(document.getElementById("name").value.length > 0
		&& document.getElementById("email").value.length > 0  && validateEmail(document.getElementById("email")) && document.getElementById("description").value.length > 0
			&&  document.getElementById("number").value.length > 0 && validateNumber(document.getElementById("number"))){
		
		//if (document.getElementById("file").files[0].name.lastIndexOf('.docx') === -1 && document.getElementById("file").files[0].name.lastIndexOf('.xlsx') === -1) {
        //  	alert("Please note: only excel and word file formats are allowed.");
     	//	this.value = '';
     	//	return;
		//}

			const form = {
	       			username :'prathiksha',
	   				password:'abcd'
	   		};
	   		const credential = "prathiksha:abcd";
	   		const encode = btoa(credential);
	console.log("encode: "+encode);
			fetch('http://localhost:8080/auth/authenticate', {
		        method : "GET",
		        headers: {
		        	"Authorization": "Basic "+encode
				}
			})
		    .then((response) => {
		       if (!response.ok) {
			        return response.json().then((errorText) => {
			            console.log("Error Text: " + errorText.message);
			            throw new Error(errorText.message);
			        });
			    } else {
			        return response.json();
			    }
		    })
		    .then(data => {
		       console.log("Data test: "+data); console.log("Data test text: "+data.jwtToken);
		       callWebService(data.jwtToken);
		    })
		    .catch(function(error) {
		     	 console.log("error text: " + error.message);
		         document.getElementById('responses').innerHTML = error.message;
		    });		  
  	  	}else{
	  		 document.getElementById('responses').innerHTML = "Please provide all the required information.";
	  	}
}
function callWebService(token){
	const formData = {
  				name: document.getElementById("name").value,
  				email:document.getElementById("email").value,
  				number: document.getElementById("number").value,
  				description:document.getElementById("description").value
			};
			
			fetch('http://localhost:8080/notify/postEmailwithAttachment', {
	        method : "POST",
	        headers: {
				"Authorization":"Bearer " + token,
		        "Accept": "application/json",
		        "Content-Type": "application/json"
			},
			body: JSON.stringify(formData)
			})
	    .then((response) => {
	        if (!response.ok) {
	            document.getElementById('responses').innerHTML = response.error;
	            throw new Error(response.error)
	        }
	        return response.json();
	    })
	    .then(data => {
	    console.log("Data response: "+data); 
	       document.getElementById('responses').innerHTML = data.jwtToken;
	       //document.getElementById("file").files[0].value = "";
		  	document.getElementById("name").value = "";
		  	document.getElementById("email").value = "";
		  	document.getElementById("number").value = "";
		  	document.getElementById("description").value = "";
	    })
	    .catch(function(error) {
	        document.getElementById('responses').innerHTML = error.message;
	    });	

}
function validateNumber(input) {
            const inputVal = input.value.trim();

            if (!/^\d{10}$/.test(inputVal)) {
                document.getElementById("response").textContent = "Please enter a 10-digit number.";
                input.setCustomValidity("Invalid input");
                return false;
            } else {
                document.getElementById("response").textContent = "";
                input.setCustomValidity(""); 
                return true;
            }
}
function validateEmail(input) {
            const regexVal = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
            const inputVal = input.value.trim();
            
             console.log("email test: "+input.value.match(inputVal))
            if (!regexVal.test(inputVal)) {
                if(inputVal ==''){
                	 document.getElementById('response').innerHTML = "";
                }else{
                	document.getElementById('response').innerHTML = "Please provide valid email.";
                	input.setCustomValidity("Invalid email");
                }
                return false;
            } else {
                document.getElementById("response").textContent = "";
                input.setCustomValidity(""); 
                return true;
            }
}