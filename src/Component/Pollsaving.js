export const PollSaving = async(question,options,pollId,navigate) => {
    const modifiedOptions = Object.values(options).join(", ");
     const data = {
        pollId:pollId,
        question:question,
        options:modifiedOptions,
        emailId : "aru701567@gmail.com",
     }
     console.log(data);
     try{
       const response = await fetch(`http://localhost:1001/api/savePoll`,{
        method:"POST",
        headers:{
            "Content-Type":"application/json",
        },
        body:JSON.stringify({
        id:pollId,
        question:question,
        options:modifiedOptions,
        emailId : "aru701567@gmail.com",
     })
     });
     if(response.ok){
        const body = await response.json();
        console.log(body); 
        navigate(`poll/${pollId}`,{
                    state:{
                        question:question,
                        options:options,
                    }
        });
     }
     }
     catch(e){
        console.log(e);
     }
}
