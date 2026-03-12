import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Bar } from "../NavBar/Bar";
import { votePoll } from "./VotePoll";
export const UserPoll = () => {
    const[userPoll,setUserPoll] = useState([]);
    const navigate = useNavigate();
    // We will maintain userSelectedOption of json, so user know which option as they selected at previous or now....
    let userSelectedOption = {};
    const changeOptionColor = (option_key,pollId,e) => {
        if(userSelectedOption[pollId] !== undefined){
           const lastClickedRef = userSelectedOption[pollId]; 
           lastClickedRef.target.style.backgroundColor = "white";
           lastClickedRef.target.style.color = "black";
        } 
        userSelectedOption[pollId] = e;
        e.target.style.backgroundColor = "black";
        e.target.style.color = "white";
        
    } 
    useEffect(()=>{
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
      <div className="container min-h-screen flex flex-col justify-center items-center min-w-screen bg-gradient-to-br from-[#7c42da] via-[#d11bb4] to-[#e5e500]">
           <div className="grid xl:grid-cols-2 gap-10 py-20 my-4">
            {       
               userPoll.map((items,key) => (
                   <div key={key}className="poll hover:py-3 hover:scale-y-115 transition-all">
            {/* <question styleName={}></question> */}
            <div  key={key+"_1"}
            className="question-box  bg-black w-75 md:w-150 lg:w-180 text-white border px-6 py-10 wrap-break-word text-center text-3xl rounded-t-xl cursor-pointer  border-black ">
                {items?.question}
            </div>
            {/* options... */}
             <div 
             key={key}
             className="options  border-white w-75 md:w-150 lg:w-180 border rounded-br-xl py-6 m-0 bg-gray-100">
               {
                items?.options?.split(", ").map((item,option_key) => {
                    return (
                      <div 
                      key={option_key} 
                      id={option_key}
                      onClick={(e) => { 
                        const poll = userPoll[key];
                        votePoll(poll.id,parseInt(option_key)+1);
                        changeOptionColor(option_key,poll.id,e);
                      }}  
                      className="text-black text-2xl hover:bg-gray-300 shadow-xl outline-gray-300 bg-gray-200 border transition border-gray-200 mx-10 my-2 rounded-2xl px-12 py-6 cursor-pointer">{item}</div>
                    ); 
                })
               }
             </div>
             <div className="dateAndTime  rounded-b-2xl  outline-white outline-2 text-white text-xl max-w-100 bg-[#3333ff]">
                 <span className="border italic text-2xl font-bold outline-2 outline-white px-2  bg-white text-blue-900 rounded-br-xl">
                       CREATED AT
                    </span>
                <div className="dataBox text-center text-2xl">
                   
                    {items?.time.substring(0,10)}
                </div>
             </div>
        </div>  
               ))   
            }
           </div>
       </div>
       </>
    );
}