function handleSubmit () {

    //const name = document
    sessionStorage.setItem("hide","false");
    //location.reload(true);
    //sessionStorage.setItem("firstload","false");
    //window.location.replace(loginmaster.html);
    location.reload(true);
    return;
}


function hideSignIn()  {
    //var firstload = sessionStorage.getItem("firstload");
    //if (!firstload == "true"){
        var hide = sessionStorage.getItem("hide");
        if(hide == "false"){
            document.getElementById("sign-in").style.visibility="visible";    
        }else{
            document.getElementById("sign-in").style.visibility="hidden";
        }
        sessionStorage.setItem("hide","true");
    //}else{
    //    document.getElementById("sign-in").style.visibility="hidden";
    //}
    //sessionStorage.setItem("firstload","false");
    //location.reload();
    //window.location.replace(loginmaster.html);
}

async function UserAction() {
/*    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            alert(this.responseText);
        }
    };
    xhttp.open("POST", "Your Rest URL Here", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("Your JSON Data Here");*/
    var username = document.getElementById("username").value;
    console.log("came here: ");
    console.log(username);
    if (!username == ""){
        const response = await fetch('https://database-microservice.azurewebsites.net/credentials/'+username);
        const myJson = await response.json();
        //var myresponse = myJson.stringify(); 
        console.log(myJson.some(item => item.name === username));
        //return true; 
    }else{
        //return false;
    }
}
  