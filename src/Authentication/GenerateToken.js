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
        const token = await response.text();
        console.log(token);
        alert("Logged successfully!");
        localStorage.setItem("token",token);
    }
    catch(error){
        console.log("Error: ",error);
        return false;
    }
    return true;
    
}
