import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Bar } from "../NavBar/Bar";
import { DeletePoll } from "./DeletPoll";
export const UserVotedPoll = () => {

      const[userPoll,setUserPoll] = useState([]);
    const navigate = useNavigate();
    const userPollIds = [];
    const[isRender,setIsRender] = useState(false);
    // We will maintain userSelectedOption in json, so user know which option as they selected at previous or now....
    const [userSelectedOption,setUserSelectOption] = useState({});
    useEffect(()=>{
        setTimeout(()=>setIsRender(true),100    );
        const token = localStorage.getItem("token");
        if(token === undefined || token === null){
         navigate("/signup");
         return;
        }
        const fetchUserPoll = async ()=>{
        try{
            const response = await fetch(`http://localhost:1001/api/getUserPoll?email=${localStorage.getItem("email")}`,{
                method:"GET",
                headers:{
                    'Content-Type':'application/json'
                },
            });
            if(response.ok){
                const data = await response.json();
                setUserPoll(data);   
                data.map((item,key)=>{
                    userPollIds.push(item.id);
                }); 
                // Fetch EDA....
                const EDAResponse = await fetch(`http://localhost:1002/api/isPollIdExists`,{
                    method:"POST",
                    headers:{
                        "Content-Type":"application/json",
                    },
                    body:JSON.stringify({
                        email:localStorage.getItem("email"),
                        pollId:userPollIds,
                    })
                });
                if(EDAResponse.ok){
                    const votedPolls = await EDAResponse.json();
                    Object.keys(votedPolls).map((pollId,key) =>{
                        const parent = document.getElementById(pollId);
                         const arr = parent.children;
                         const option = parseInt(votedPolls[pollId])-1;
                         if(option < -1){
                            document.getElementById(pollId+"#parent").remove();
                            return;
                         }
                        arr[option].style.backgroundColor = "black";
                        arr[option].style.color = "white";
                        userSelectedOption[pollId] = arr[option];
                        setUserSelectOption((prev) => ({...prev,[pollId] : arr[option]}));
                    }); 
                    
                }
            }   
        } 
        catch(e){
          console.log(e);
        }
    };
    fetchUserPoll();
    },[]);
    return(
        <>
          <Bar></Bar>
            {isRender && <div className="container min-h-screen flex flex-col justify-center items-center min-w-screen bg-gradient-to-br from-[#7c42da] via-[#d11bb4] to-[#e5e500]">
           <div className="grid xl:grid-cols-2 gap-10 py-20 my-4">
            {       
               userPoll.map((items,key) => (
                   <div key={key} id={items.id+"#parent"} className="poll hover:py-3 hover:scale-y-102 transition-all">
            <div  
            
            className="question-box  bg-black w-75 md:w-150 lg:w-180 text-white border px-6 py-10 wrap-break-word text-center text-3xl rounded-t-xl cursor-pointer  border-black ">
                {items?.question}
            </div>
            {/* options... */}
             <div 
             key={key}
             id={items.id}
             className="options  border-white w-75 md:w-150 lg:w-180 border rounded-br-xl py-6 m-0 bg-gray-100">
               {
                items?.options?.split(", ").map((item,option_key) => {
                    return (
                      <div 
                      key={option_key} 
                      id={option_key}
                      className="text-black text-2xl hover:opacity-10 shadow-xl outline-gray-300 bg-gray-200 border transition border-gray-200 mx-10 my-2 rounded-2xl px-12 py-6 ">{item}</div>
                    ); 
                })
               }
             </div>
             <div className="flex justify-between max-w-400">
                <div className="dateAndTime  rounded-b-2xl  outline-white outline-2 text-white text-xl max-w-100 bg-[#3333ff]">
                 <span className="border italic text-2xl font-bold outline-2 outline-white px-2  bg-white text-blue-900 rounded-br-xl">
                       CREATED AT
                    </span>
                <div className="dataBox text-center text-2xl">
                   
                    {items?.time.substring(0,10)}
                </div>
             </div> 
             <div className="delete-poll px-2 rounded-b-2xl py-2 text-center hover:bg-red-400  outline-white text-white text-xl bg-red-600 cursor-pointer outline-2 max-w-400">
                <span 
                onClick={() => {
                    DeletePoll(items.id);
                }}
                className="text-3xl px-4  italic">Delete</span>
             </div>
             </div>
            
        </div>  
               ))   
            }
           </div>
       </div>}
       </>
    );
}