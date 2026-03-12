export const votePoll = async(pollId,userSelectedOption) => {
      try{
            const email = localStorage.getItem("email");
            const pollVoteRequestDTO = {
              pollId:pollId,
              email:email,
              option:userSelectedOption,
            }
            const response = await fetch("http://localhost:1002/api/votePoll",{
              method:"PUT",
              headers:{
                "Content-Type":"application/json",
              },
              body:JSON.stringify(pollVoteRequestDTO),
            });
            if(response.ok){
              alert("Your voted saved!");
            }
            }
            catch(e){
              console.log(e?.message);
              
            }
}