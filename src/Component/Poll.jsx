import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { Bar } from "../NavBar/Bar";
import { votePoll } from "./VotePoll";
export const Poll = () => {
    const [isUserSelected,setIsUserSelected] = useState(false);
    const[userSelectedOption,setUserSelectedOption] = useState(null);
    const location = useLocation();
    const pollData = location?.state;
    console.log(pollData);
    
    const {question} = pollData;
    const {options} = pollData;
    const {pollId} = pollData;
    const navigate = useNavigate();
    const token = localStorage.getItem("token");
    const[lastClickedRef,setLastClickedRef] = useState(null);
    
    const changedClikced = (e) => {
      if(lastClickedRef === null){
         setLastClickedRef(e);
      }
      else{
        lastClickedRef.target.style.backgroundColor = "white";
        lastClickedRef.target.style.color = "black";
        setLastClickedRef(e);
      }
    }
    // we need ref for clicking the options... 
    useEffect(()=>{
           const token = localStorage.getItem("token");
            if(token === undefined || token === null){
            navigate("/signup");
            return;
    }
    
    if(userSelectedOption !== null){
      votePoll(pollId,userSelectedOption);
    }
    },[userSelectedOption]);
   
  return (
    <>
    <Bar></Bar>
     <div 
     className="container  min-h-screen flex flex-col justify-center items-center m-0 p-0 min-w-screen bg-gradient-to-br from-[#7c42da] via-[#d11bb4] to-[#e5e500]">
        <div className="poll">
            <div 
            className="question-box bg-black w-75 md:w-150 lg:w-180 text-white border px-6 py-10 wrap-break-word text-center text-3xl rounded-t-xl   border-black ">
                {question}
            </div>
            {/* options... */}
             <div className="options border-white w-75 md:w-150 lg:w-180 border rounded-b-xl py-6 m-0 bg-gray-100">
               {
                options.map((item,key) => {
                    return (
                      <div 
                      key={key} 
                      id={key}
                      onClick={(e) => { 
                        setIsUserSelected(true);
                        setUserSelectedOption(parseInt(e.target.id)+1);
                        e.target.style.backgroundColor = "black";
                        e.target.style.color = "white";
                        changedClikced(e);
                      }}  
                      className="text-black hover:bg-gray-300 bg-gray-200  text-2xl  shadow-xl outline-gray-300 border transition border-gray-200 mx-10 my-2 rounded-2xl px-12 py-6 cursor-pointer">{item}</div>
                    ); 
                })
               }
             </div>
        </div>
     </div>
    </>
  )
}
