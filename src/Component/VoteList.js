export const VoteList = async(voteList) => {
    console.log(voteList);
    
    try{
       const response = await fetch("http://localhost:1002/api/savePoll",{
        method:"POST",
        headers:{
            "Content-Type":"application/json",
        },
        body:JSON.stringify(voteList)
    });
    if(response.ok){
        return response;
    }
    else{
        console.log(response);
    }
    }
    catch(e){
        console.log(e.message);  
    }
}