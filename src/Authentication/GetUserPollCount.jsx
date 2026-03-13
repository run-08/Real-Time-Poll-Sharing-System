const GetUserPollCount = async() => {
   const email = localStorage.getItem('email');
   const response = await fetch(`http://localhost:1001/api/getUserPollCount?email=${email}`,{
    method:"GET",
    headers:{
        "Content-Type":"application/json",
    }
   });
   if(response.ok){
     const data = await response.text();
     localStorage.setItem("userPollCount",parseInt(data));
   }
   
}
export default GetUserPollCount;