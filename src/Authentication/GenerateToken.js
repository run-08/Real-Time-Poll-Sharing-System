export const GenerateToken = async({formData,isSignUp}) => {
    try{
        const URL = `http://localhost:1000/api/${isSignUp ? "signup":"login"}`
        console.log(formData);
        const response = await fetch(URL,{
          method:"POST",
          headers:{
            "Content-Type":"application/json",
          },
          body:JSON.stringify(formData)
        });
        if(!response.ok){
          alert("Failed to Authenticate...")
          return false;
        }
        if(!isSignUp){
            const details = await response.json();
            console.log(details);
            localStorage.setItem("name",details?.name);
            const token = details?.token;
            localStorage.setItem("token",token);
        }
        else{
          const token = await response.text();
          localStorage.setItem("token",token);
        }
        alert("Logged successfully!");
        localStorage.setItem("email",formData.email);
    }
    catch(error){
        console.log("Error: ",error);
        return false;
    }
    return true;
    
}
