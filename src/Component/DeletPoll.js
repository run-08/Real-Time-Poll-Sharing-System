export const DeletePoll = async (pollId)  =>{
    const email = localStorage.getItem("email");
    const response = await fetch(`http://localhost:1001/api/deletePoll?pollID=${pollId}`,{
        method:"DELETE",
        headers:{
            "Content-Type":"application/json",
        },
    });
    if(response.ok){
        console.log("Poll Deleted at resource server....");
        console.log(email);
        //  then delete in EDA Server also... 
        const EDAResponse = await fetch(`http://localhost:1002/api/deletePollById?PollId=${pollId}&emailId=${email}`,{
            method:"DELETE",
            headers:{
                "Content-Type":"application/json",
            },
        });
        if(EDAResponse.ok){
            console.log("Poll Deleted at EDA Server....");
            window.location.reload();
            const userPollCount = parseInt(localStorage.getItem("userPollCount"));
            localStorage.setItem("userPollCount",userPollCount-1);
        }
    }
}