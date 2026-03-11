import { DeletePoll } from "./DeletPoll";
import { VoteList } from "./VoteList";

export const PollSaving = async(question,options,pollId,navigate) => {
    const modifiedOptions = Object.values(options).join(", ");
    console.log(options);
   //  generate options in serial order...
    const voterListoptions = generateSerialOrder(options.length);
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
      //   then save into voteList...
      const voteList = {
         pollId:pollId,
         options:voterListoptions,
         voted:{} 
      }
      const voteListResponse = await VoteList(voteList);
      if(voteListResponse?.ok){
         navigate(`poll/${pollId}`,{
                    state:{
                        question:question,
                        options:options,
                    }
        });
      }
     }
     else{
      // delete the poll id in Resource server and alert...
      DeletePoll(pollId);
      alert("Failed to create your poll!")
     }    
     }
     catch(e){
        console.log(e);
     }
}
// local functiuon....
const generateSerialOrder = (max_serial) => {
   const options = [];
   let option = 1;
   while(option <= max_serial){
      options.push(option);
      option++;
   }
   return options;
} 