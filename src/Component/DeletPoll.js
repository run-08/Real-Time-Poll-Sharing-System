export const DeletePoll = async (pollId)  =>{
    const response = await fetch(`http://localhost:1001/api/deletePoll?pollID=${pollId}`,{
        method:"DELETE",
        headers:{
            "headers":"application/json",
        }
    });
    if(response.ok){
        console.log("Poll deleted successfully!");
    }
}